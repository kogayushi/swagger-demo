package com.example.swagger.config;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.example.swagger.interfaces.rest.dto.CustomErrorAttributes;

@Configuration
public class RestApiConfiguration {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
      CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
      filter.setIncludeClientInfo(true);
      filter.setIncludeQueryString(true);
      filter.setIncludeHeaders(true);
      filter.setIncludePayload(true);
      filter.setMaxPayloadLength(4096);
      return filter;
    }

    @Bean
    public ErrorAttributes customErrorAttributes() {
        return new CustomErrorAttributes();
    }

}
