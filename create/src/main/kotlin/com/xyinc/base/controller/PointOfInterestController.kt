package com.xyinc.base.controller

import com.xyinc.base.domain.CreatePOIRequest
import com.xyinc.base.domain.PointOfInterest
import com.xyinc.base.service.PointOfInterestService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/poi")
class PointOfInterestController(private val service: PointOfInterestService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody poi: CreatePOIRequest): PointOfInterest =
        service.create(PointOfInterest(name = poi.name, coordinateX = poi.coordinateX, coordinateY = poi.coordinateY))
}