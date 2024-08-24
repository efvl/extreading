package dev.evv.extreading

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer

class PostgresContextInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        @JvmStatic
        val postgresContainer = PostgreSQLContainer("postgres:16")
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        postgresContainer.start()
        TestPropertyValues.of(
            "spring.datasource.url=" + postgresContainer.jdbcUrl,
            "spring.datasource.username=" + postgresContainer.username,
            "spring.datasource.password=" + postgresContainer.password,
        ).applyTo(applicationContext.getEnvironment())
    }

}