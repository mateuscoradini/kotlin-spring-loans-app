package br.com.coradini.loans.loansappskel.application.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .servers(
                mutableListOf(
                    Server().apply {
                        url = "http://localhost:8080/"
                        description = "Local environment URL"
                    },
                ),
            )
            .info(
                Info()
                    .title("Loan Simulation API")
                    .version("1.0.0")
                    .description("API for simulating loan calculations.")
                    .contact(Contact().name("Your Name").email("your.email@example.com"))

            )
    }
}