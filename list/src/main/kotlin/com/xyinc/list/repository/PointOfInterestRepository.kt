package com.xyinc.list.repository

import com.xyinc.list.domain.PointOfInterest
import org.springframework.data.mongodb.repository.MongoRepository

interface PointOfInterestRepository: MongoRepository<PointOfInterest, String>