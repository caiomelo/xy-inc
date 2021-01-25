package com.xyinc.base.repository

import com.xyinc.base.domain.PointOfInterest
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

interface PointOfInterestRepository: MongoRepository<PointOfInterest, String>