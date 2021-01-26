package com.xyinc.list.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PointOfInterest(val name: String, val coordinateX: Int, val coordinateY: Int)