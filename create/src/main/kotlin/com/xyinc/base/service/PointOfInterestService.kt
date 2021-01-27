package com.xyinc.base.service

import com.xyinc.base.domain.PointOfInterest
import com.xyinc.base.repository.PointOfInterestRepository
import org.springframework.stereotype.Service

@Service
class PointOfInterestService(private val repository: PointOfInterestRepository) {

    fun create(poi: PointOfInterest) = repository.save(poi)
}