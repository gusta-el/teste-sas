package com.teste.sas.testeSas.setup;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.classmate.TypeResolver;
import com.teste.sas.testeSas.dto.ErrorResponse;
import com.teste.sas.testeSas.dto.ErrorResponse.FieldValidationMessageResponse;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2).select()
            .paths(PathSelectors.any())
            .apis(RequestHandlerSelectors.basePackage("com.teste.sas.testeSas"))
            .build()
            .apiInfo(new ApiInfoBuilder().title("Teste técnico – Desenvolvedor Backend - SAS")
                    .description("\"Api de gestão de simulados\"")
                    .version("1.0.0")
                    .contact(new Contact("Gustavo Cabrera Cirilo", "https://www.linkedin.com/in/gustavo-cabrera-2894b2a3/", "gcabrerac@live.com"))
                    .build())
            .useDefaultResponseMessages(false)
            .additionalModels(typeResolver.resolve(ErrorResponse.class),
                typeResolver.resolve(FieldValidationMessageResponse.class))
            .globalResponseMessage(RequestMethod.POST, standardResponseMessages());
    }
	
    private List<ResponseMessage> standardResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder().code(400)
                .message("Bad Request")
                .responseModel(new ModelRef("ErrorResponse"))
                .build(),
            new ResponseMessageBuilder().code(422)
                .message("Unprocessable Entity")
                .responseModel(new ModelRef("ErrorResponse"))
                .build(),
            new ResponseMessageBuilder().code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("ErrorResponse"))
                .build());
    }
    
}
