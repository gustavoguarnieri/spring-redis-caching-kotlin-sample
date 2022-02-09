package com.example.springrediscachingkotlinsample.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig( @Value("\${swagger.enabled}") private val enableSwagger: Boolean ) {

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .enable(enableSwagger)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.springrediscachingkotlinsample.controller"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Spring Redis Caching Kotlin Sample Rest Api Documentation")
            .description("All necessary information is in this documentation")
            .version("1.0.0")
            .license("MIT License")
            .licenseUrl("https://choosealicense.com/licenses/mit/")
            .contact(
                Contact(
                    "Gustavo",
                    "https://www.linkedin.com/in/gustavo-guarnieri/",
                    "gustavo.guarnieri@gmail.com"
                )
            )
            .build()
    }
}
