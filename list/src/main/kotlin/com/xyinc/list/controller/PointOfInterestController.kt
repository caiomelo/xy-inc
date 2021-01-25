package com.xyinc.list.controller

import com.xyinc.list.service.PointOfInterestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poi")
class PointOfInterestController(private val service: PointOfInterestService) {

    @GetMapping
    fun findAll() = service.findAll()
}