package com.xyinc.base.domain

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PointOfInterestDTO(
    @NotEmpty
    val name: String,
    @NotNull
    val latitude: Float,
    @NotNull
    val longitude: Float
) {
    fun toDocument() = PointOfInterest(name = this.name, latitude = this.latitude, longitude = this.longitude)
}
