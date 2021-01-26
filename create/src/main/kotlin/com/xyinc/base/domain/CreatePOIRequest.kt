package com.xyinc.base.domain

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreatePOIRequest(
    @NotEmpty
    val name: String,
    @NotNull
    val latitude: Float,
    @NotNull
    val longitude: Float
)
