package br.com.coradini.loans.loansappskel.application.configuration

import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.connection.R2dbcTransactionManager

@Configuration
class DatabaseConfig {

    @Value("\${spring.r2dbc.url}")
    private lateinit var r2dbcUrl: String

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(r2dbcUrl)
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): R2dbcTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }
}