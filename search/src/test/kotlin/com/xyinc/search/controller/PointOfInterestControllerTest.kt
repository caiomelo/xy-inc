package com.xyinc.search.controller

import com.xyinc.search.domain.PointOfInterest
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
            Document().append(NAME_FIELD, "cafe").append(POSITION_FIELD, listOf(27, 12)),
            Document().append(NAME_FIELD, "gas station").append(POSITION_FIELD, listOf(31, 18)),
            Document().append(NAME_FIELD, "jewelry").append(POSITION_FIELD, listOf(15, 12)),
            Document().append(NAME_FIELD, "flower shop").append(POSITION_FIELD, listOf(19, 21)),
            Document().append(NAME_FIELD, "pub").append(POSITION_FIELD, listOf(12, 8)),
            Document().append(NAME_FIELD, "supermarket").append(POSITION_FIELD, listOf(23, 6)),
            Document().append(NAME_FIELD, "restaurant").append(POSITION_FIELD, listOf(28, 2)),
        )
        mongoTemplate.getCollection(COLLECTION_NAME).insertMany(documents)
    }

    @Test
    fun `It should retrieve only POIs within given distance from the given position`() {
        val response = testTemplate.getForEntity<Array<PointOfInterest>>("/poi?x=20&y=10&maxDistance=10")

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotNull

        assertThat(response.body?.map { it.name }?.toList()).containsExactly("cafe", "jewelry", "pub", "supermarket")
    }

    @Test
    fun `It should respond with bad request if value of coordinate x is missing`() {
        val response = testTemplate.getForEntity<String>("/poi?y=10&maxDistance=10")
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `It should respond with bad request if value of coordinate y is missing`() {
        val response = testTemplate.getForEntity<String>("/poi?x=20&maxDistance=10")
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `It should respond with bad request if value of max distance is missing`() {
        val response = testTemplate.getForEntity<String>("/poi?x=20&y=10")
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @AfterEach
    fun tearDown() = mongoTemplate.getCollection(COLLECTION_NAME).drop()

    companion object {
        const val COLLECTION_NAME = "pointOfInterest"
        const val NAME_FIELD = "name"
        const val POSITION_FIELD = "position"
    }
}