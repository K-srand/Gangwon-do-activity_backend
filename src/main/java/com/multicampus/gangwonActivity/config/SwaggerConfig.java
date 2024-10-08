package com.multicampus.gangwonActivity.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@OpenAPIDefinition(
        info = @Info(title = "강원 액티비티 API 명세서",
            description = " 서비스 API 명세서",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
        public OpenAPI openAPI(){
            SecurityScheme securityScheme = new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER).name("Authorization");
            SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

            return new OpenAPI()
                    .info(new io.swagger.v3.oas.models.info.Info()
                            .title("강원 액티비티 API")
                            .description("강원 액티비티럭키비키")
                            .version("v1")
                    )
                    .components(new Components()
                            .addSecuritySchemes("bearerAuth", securityScheme))
                    .security(Arrays.asList(securityRequirement));

    }
}
