package me.mybatis.javaconfig;

import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import me.dslztx.assist.client.mysql.DataSourceFactory;
import me.dslztx.assist.client.mysql.MapperFactory;
import me.dslztx.assist.util.CollectionAssist;
import me.dslztx.assist.util.ObjectAssist;

/**
 * 目标是：实现Mapper的多数据源绑定注册<br/>
 *
 * 方案尝试了两种： <br/>
 * 1）一般的@Configuration类，继承ApplicationContextAware接口，然后手动生成注册Mapper Bean到容器。存在“由于加载先后顺序导致生成的Bean不能作为Autowired候选”的问题<br/>
 * 2）继承实现BeanDefinitionRegistryPostProcessor接口，如下。然后最完美的方案是在postProcessBeanDefinitionRegistry()
 * 方法中，生成BeanDefinition，然后容器后续根据这个BeanDefinition生成Bean，但是十分麻烦；所以选择直接在postProcessBeanFactory()方法中手动生成注册Mapper
 * Bean到容器，这种方式没有相应的BeanDefinition，这点需要注意
 */
@Configuration
public class MapperBindingToMultiDataSourceAndRegistration implements BeanDefinitionRegistryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MapperBindingToMultiDataSourceAndRegistration.class);

    private void bindingAndRegistration(Map<String, DruidDataSource> dataSourceMap, Set<Class<?>> mapperClzSet,
        ConfigurableListableBeanFactory beanFactory) {

        for (String dbName : dataSourceMap.keySet()) {
            register(dbName, dataSourceMap.get(dbName), mapperClzSet, beanFactory);
        }

    }

    private void register(String name, DataSource dataSource, Set<Class<?>> mapperClzSet,
        ConfigurableListableBeanFactory beanFactory) {

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment =
            new Environment("development#" + dataSource.hashCode(), transactionFactory, dataSource);

        org.apache.ibatis.session.Configuration configuration =
            new org.apache.ibatis.session.Configuration(environment);

        for (Class mapperClz : mapperClzSet) {
            configuration.addMapper(mapperClz);
        }

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(factory);

        register0(sqlSessionTemplate, mapperClzSet, name, beanFactory);

        logger.info("register mappers for {} finished", name);
    }

    private void register0(SqlSession sqlSessionTemplate, Set<Class<?>> mapperClzSet, String dbName,
        ConfigurableListableBeanFactory beanFactory) {

        for (Class<?> mapperClz : mapperClzSet) {

            MapperProxyFactory factory = new MapperProxyFactory(mapperClz);

            Object obj = factory.newInstance(sqlSessionTemplate);

            beanFactory.registerSingleton(lowerCamelCase(mapperClz.getSimpleName()) + "#" + dbName, obj);
        }
    }

    private String lowerCamelCase(String name) {
        if (Character.isLowerCase(name.charAt(0))) {
            return name;
        } else {
            return Character.toLowerCase(name.charAt(0)) + name.substring(1);
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        // GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //
        // genericBeanDefinition.setBeanClass(MapperFactoryBean.class);
        //
        // PropertyValue v0 = new PropertyValue("mapperInterface", new TypedStringValue("mybatis.mapper.UserMapper"));
        // PropertyValue v1 = new PropertyValue("sqlSessionFactory", new RuntimeBeanReference("sqlSessionFactory"));
        // List<PropertyValue> values = new ArrayList<>();
        // values.add(v0);
        // values.add(v1);
        //
        // genericBeanDefinition.setPropertyValues(new MutablePropertyValues(values));

        // beanDefinitionRegistry.registerBeanDefinition("userMapper", genericBeanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
        throws BeansException {

        Map<String, DruidDataSource> dataSourceMap = DataSourceFactory.obtainAllDataSources();

        if (ObjectAssist.isNull(dataSourceMap) || CollectionAssist.isEmpty(dataSourceMap.keySet())) {
            logger.error("no datasource");
            return;
        }

        Set<Class<?>> mapperClzSet = MapperFactory.scanMapperInterfaceAll();
        if (CollectionAssist.isEmpty(mapperClzSet)) {
            logger.error("no mapper interface");
            return;
        }

        bindingAndRegistration(dataSourceMap, mapperClzSet, configurableListableBeanFactory);
    }
}
