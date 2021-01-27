package com.xyinc.list.controller

import com.xyinc.list.domain.PointOfInterest
import org.assertj.core.api.Assertions.assertThat
import org.bson.Document
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PointOfInterestControllerTest(
    @Autowired
    private val testTemplate: TestRestTemplate,
    @Autowired
    private val mongoTemplate: MongoTemplate
) {

    @BeforeEach
    fun init() {
        val documents = listOf(
            Document().append(NAME_FIELD, "cafe").append(POSITION_FIELD, listOf(20, 10)),
            Document().append(NAME_FIELD, "pub").append(POSITION_FIELD, listOf(10, 20))
        )
        mongoTemplate.getCollection(COLLECTION_NAME).insertMany(documents)
    }

    @Test
    fun `It should be able to retrieve all points of interest`() {
        val response = testTemplate.getForEntity<Array<PointOfInterest>>("/poi")

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.map { it.name }?.toList()).containsExactly("cafe", "pub")
    }

    @AfterEach
    fun tearDown() = mongoTemplate.getCollection(COLLECTION_NAME).drop()

    companion object {
        const val COLLECTION_NAME = "pointOfInterest"
        const val NAME_FIELD = "name"
        const val POSITION_FIELD = "position"
    }
}