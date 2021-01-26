package com.xyinc.base.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PointOfInterest(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val coordinateX: Int,
    val coordinateY: Int
)