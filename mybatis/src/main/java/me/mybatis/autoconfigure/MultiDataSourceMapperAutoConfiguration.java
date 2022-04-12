package me.mybatis.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import me.mybatis.javaconfig.MapperBindingToMultiDataSourceAndRegistration;

@Configuration
@Import(MapperBindingToMultiDataSourceAndRegistration.class)
public class MultiDataSourceMapperAutoConfiguration {

}
