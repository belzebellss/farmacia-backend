package com.generation.farmacia.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
    OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("projeto farmácia.")
                .description("projeto farmácia - generation brasil, turma 66.")
                .version("v0.0.1")
                .license(new License()
                    .name("izabela bonzanini")
                    .url("https://github.com/izabelabonzanini/"))
                .contact(new Contact()
                    .name("izabela bonzanini")
                    .url("https://github.com/izabelabonzanini/")
                    .email("izabelabonzanini@gmail.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Github")
                .url("https://github.com/izabelabonzanini/"));
    }


	@Bean
	public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("objeto persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("objeto excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("erro na requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("acesso não autorizado!"));
				apiResponses.addApiResponse("403", createApiResponse("acesso proibido!"));
				apiResponses.addApiResponse("404", createApiResponse("objeto não encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("erro na aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
}