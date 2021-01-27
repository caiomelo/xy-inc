package com.xyinc.search.controller

import com.xyinc.search.service.PointOfInterestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poi")
class PointOfInterestController(private val service: PointOfInterestService) {

    @GetMapping
    fun create(@RequestParam x: Int, @RequestParam y: Int, @RequestParam maxDistance: Int) =
        service.retrieveNearWithMaxDistance(x, y, maxDistance)
}