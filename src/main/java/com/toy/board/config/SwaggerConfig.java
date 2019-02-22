package com.toy.board.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                /* https://github.com/springfox/springfox/issues/631#issuecomment-312729617 */
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
                .build();
    }
}
