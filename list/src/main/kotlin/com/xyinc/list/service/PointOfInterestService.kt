package com.xyinc.list.service

import com.xyinc.list.repository.PointOfInterestRepository
import org.springframework.stereotype.Service

@Service
class PointOfInterestService(private val repository: PointOfInterestRepository) {

    fun findAll() = repository.findAll()
}