package it.elearnpath.siav.libreria.config;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@SwaggerDefinition(
        info = @Info(
                title = "biblioteca-service",
                version = "0.0.1-SNAPSHOT",
                description = "Gestisce i dati dei libri"
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"}
)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("it.elearnpath.siav.libreria.controller"))
//                .paths(regex("/editors.*"))
                .paths(PathSelectors.any())
                .build();
//                .apiInfo(apiInfo());
    }

//    private ApiInfo apiInfo() {
//
//        return new ApiInfoBuilder()
//                .title("BIBLIOTECA WEB SERVICE")
//                .description("Esercizio Academy")
//                .contact(new Contact("Academy Siav", "http://prova/academy", "academy@fifi.it"))
//                .build();
//    }

}