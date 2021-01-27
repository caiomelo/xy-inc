package com.xyinc.search.repository

import com.xyinc.search.domain.PointOfInterest
import org.springframework.data.geo.Circle
import org.springframework.data.mongodb.repository.MongoRepository

interface PointOfInterestRepository : MongoRepository<PointOfInterest, String> {

    fun findByPositionWithin(region: Circle): List<PointOfInterest>
}