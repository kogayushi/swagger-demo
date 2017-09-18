package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
  @Bean
  public Docket document() {
      return new Docket(DocumentationType.SWAGGER_2)
                      .select()
                      .paths(PathSelectors.regex("/api/.*"))
                      .build()
                      .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("Example Authentication App")
              .description("This spec is mainly for testing Auth App server.")
              .license("Apache 2.0")
              .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
              .termsOfServiceUrl("")
              .version("1.0.0")
              .contact(new Contact("","", "youshe.old.pleasure@gmail.com"))
              .build();
  }
}
