package com.backfcdev.customersapirest.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Gibbsson J. A. Farias Castillo",
                        email = "gibbfarc17@gmail.com",
                        url = "https://github.com/gibbssonfarias30"
                ),
                description = "OpenApi documentation for Rest API Customers",
                title = "OpenApi specification - backfcdev",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9090"
                )
        }
)
public class OpenApiConfig {
}
