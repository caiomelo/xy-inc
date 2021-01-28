package com.xyinc.base.controller

import com.xyinc.base.domain.CreatePOIRequest
import com.xyinc.base.domain.PointOfInterest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PointOfInterestControllerTest(@Autowired val testTemplate: TestRestTemplate) {

    @Test
    fun `It should be able to create a point of interest`() {
        val dto = CreatePOIRequest("home", 1, 1)

        val response = testTemplate.postForEntity<PointOfInterest>("/poi", dto)

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body).isNotNull

        response.body?.apply {
            assertThat(this.name).isEqualTo(dto.name)
            assertThat(this.position[0]).isEqualTo(dto.x)
            assertThat(this.position[1]).isEqualTo(dto.y)
        }
    }

    @Test
    fun `It should respond with bad request if name of point of interest is missing`() {
        val headers = HttpHeaders().apply { this.set("Content-Type", "application/json") }
        val requestEntity = HttpEntity("{\"x\":20,\"y\":10}", headers)

        val response = testTemplate.exchange<String>("/poi", HttpMethod.POST, requestEntity)
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}