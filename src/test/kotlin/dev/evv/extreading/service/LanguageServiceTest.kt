package dev.evv.extreading.service

import dev.evv.extreading.PostgresContextInitializer
import dev.evv.extreading.dto.LanguageDto
import dev.evv.extreading.repository.LanguageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [PostgresContextInitializer::class])
class LanguageServiceTest {

    @Autowired
    private lateinit var client: TestRestTemplate

    @Autowired
    private lateinit var languageRepository: LanguageRepository

    @Test
    fun `home endpoint should return string`() {
        val entity = client.getForEntity<String>("/home")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("welcome to home")

    }

    @Test
    fun `getById endpoint to initialized language should return it`() {
        val id = UUID.fromString("8d1208fc-f401-496c-9cb8-483fef121234")
        val entity = client.getForEntity<LanguageDto>("/v1/lang/$id")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.id).isEqualTo(id)
        assertThat(entity.body?.shortName).contains("en")
        assertThat(entity.body?.fullName).containsIgnoringCase("english")
    }

    @Test
    fun `post endpoint to language should add data to db`() {
        val requestObject = LanguageDto(shortName = "ggg", fullName = "Ggglang")
        val responseEntity = client.postForEntity("/v1/lang", requestObject, LanguageDto::class.java)
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        val id = responseEntity.body?.id
        assertThat(id).isNotNull()
        assertThat(responseEntity.body?.shortName).isEqualTo(requestObject.shortName)
        assertThat(responseEntity.body?.fullName).isEqualTo(requestObject.fullName)

        val entityFromDB = languageRepository.findById(id!!).get()
        assertThat(entityFromDB).isNotNull()
        assertThat(entityFromDB.id).isEqualTo(id)
        assertThat(entityFromDB.shortName).isEqualTo(requestObject.shortName)
        assertThat(entityFromDB.fullName).isEqualTo(requestObject.fullName)
    }

}