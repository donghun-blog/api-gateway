package me.donghun.apigateway.config;

import me.donghun.apigateway.filters.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }
}
