package com.xyinc.base.controller

import com.xyinc.base.domain.CreatePOIRequest
import com.xyinc.base.domain.PointOfInterest
import com.xyinc.base.repository.PointOfInterestRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/poi")
class PointOfInterestController(private val repository: PointOfInterestRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody poi: CreatePOIRequest): PointOfInterest =
        repository.save(PointOfInterest(name = poi.name, latitude = poi.latitude, longitude = poi.longitude))
}