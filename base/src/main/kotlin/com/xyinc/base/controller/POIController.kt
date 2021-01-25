package com.xyinc.base.controller

import com.xyinc.base.domain.PointOfInterest
import com.xyinc.base.domain.PointOfInterestDTO
import com.xyinc.base.repository.PointOfInterestRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/poi")
class POIController(val repository: PointOfInterestRepository) {

    @PostMapping
    fun create(@RequestBody poi: PointOfInterestDTO): PointOfInterest = repository.save(poi.toDocument())
}