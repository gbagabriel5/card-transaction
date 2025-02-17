package com.gba.card.cardtransaction.main

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun cardTransactionApi() : OpenAPI = OpenAPI().info(Info().title("Card Transaction API").version("v1"))
}