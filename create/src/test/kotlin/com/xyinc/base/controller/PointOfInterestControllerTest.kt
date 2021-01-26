package com.xyinc.base.controller

import com.xyinc.base.domain.CreatePOIRequest
import com.xyinc.base.domain.PointOfInterest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
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
            assertThat(this.coordinateX).isEqualTo(dto.coordinateX)
            assertThat(this.coordinateY).isEqualTo(dto.coordinateY)
        }
    }
}