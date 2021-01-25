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

    private val pointsOfInterest: List<PointOfInterest> = listOf(
        PointOfInterest("place1", -18.90816723990092f, -48.261324002765186f),
        PointOfInterest("place2", -23.59705086490992f, -46.68661896035228f)
    )

    @BeforeEach
    fun init() {
        val documents = listOf(
            Document().append(NAME_FIELD, pointsOfInterest[0].name).append(LATITUDE_FIELD, pointsOfInterest[0].latitude)
                .append(LONGITUDE_FIELD, pointsOfInterest[0].longitude),
            Document().append(NAME_FIELD, pointsOfInterest[1].name).append(LATITUDE_FIELD, pointsOfInterest[1].latitude)
                .append(LONGITUDE_FIELD, pointsOfInterest[1].longitude)
        )

        mongoTemplate.getCollection(COLLECTION_NAME).insertMany(documents)
    }

    @Test
    fun `It should be able to retrieve all points of interest`() {
        val response = testTemplate.getForEntity<Array<PointOfInterest>>("/poi")

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).containsAll(pointsOfInterest)
    }

    @AfterEach
    fun tearDown() = mongoTemplate.getCollection(COLLECTION_NAME).drop()

    companion object {
        const val COLLECTION_NAME = "pointOfInterest"
        const val NAME_FIELD = "name"
        const val LATITUDE_FIELD = "latitude"
        const val LONGITUDE_FIELD = "longitude"
    }
}