package ioc.javaconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 需要在component-scan的扫描范围内，会生成一个相应的AppConfig实例
 */
@Configuration
public class AppConfig {

    /**
     * 方法名就是生成的Bean的名字
     */
    @Bean(name = "ccBean")
    public CC cc() {
        return new CC();
    }

    /**
     * 方法名就是生成的Bean的名字
     */
    @Bean(name = "cc", initMethod = "init", destroyMethod = "close")
    public C c(@Value("${cKey}") String cKey) {
        C c = new C();
        c.setcKey(cKey);
        c.setCcBean(cc());

        return c;
    }
}
