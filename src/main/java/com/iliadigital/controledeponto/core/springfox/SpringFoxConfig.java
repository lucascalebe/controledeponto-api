package com.iliadigital.controledeponto.core.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.iliadigital.controledeponto.api.exceptionhandler.Problem;
import com.iliadigital.controledeponto.api.openapi.model.LinksModelOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

    // conflito de dependencias hateoas com swagger.
    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iliadigital.controledeponto.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessage())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessage())
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(
                        ServletWebRequest.class,
                        Resource.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .apiInfo(apiInfo())
                .tags(new Tag("Batidas", "Gerencia as batidas"));
    }

    private List<ResponseMessage> globalPostPutResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Requisi????o inv??lida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),

                new ResponseMessageBuilder()
                        .code(405)
                        .message("M??todo HTTP n??o suportado para essa requisi????o")
                        .build(),

                new ResponseMessageBuilder()
                        .code(500)
                        .message("Erro interno do servidor")
                        .build(),

                new ResponseMessageBuilder()
                        .code(406)
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build(),

                new ResponseMessageBuilder()
                        .code(415)
                        .message("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
                        .build()
        );
    }

    private List<ResponseMessage> globalGetResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Requisi????o inv??lida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),

                new ResponseMessageBuilder()
                        .code(405)
                        .message("M??todo HTTP n??o suportado para essa requisi????o")
                        .build(),

                new ResponseMessageBuilder()
                        .code(500)
                        .message("Erro interno do servidor")
                        .build(),

                new ResponseMessageBuilder()
                        .code(406)
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    // informa????es na pagina HTML do swagger UI
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Controle de ponto API")
                .description("API aberta para controle de ponto")
                .version("1")
                .contact(new Contact("Lucas Calebe","https://curriculumcalebe.lucascalebe.repl.co/",
                        "lucascalebe07@hotmail.com"))
                .build();
    }

    //swagger ui gerar documenta????o HTML
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
