package com.xyinc.search.service

import com.xyinc.search.repository.PointOfInterestRepository
import org.springframework.data.geo.Circle
import org.springframework.stereotype.Service

@Service
class PointOfInterestService(private val repository: PointOfInterestRepository) {

    fun retrieveNearWithMaxDistance(x: Int, y: Int, maxDistance: Int) =
        repository.findByPositionWithin(Circle(x.toDouble(), y.toDouble(), maxDistance.toDouble()))
}