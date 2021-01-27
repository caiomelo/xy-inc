package com.xyinc.search.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PointOfInterest(val name: String, val position: List<Int>)