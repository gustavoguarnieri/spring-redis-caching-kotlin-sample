package com.example.springrediscachingkotlinsample.repositories

import com.example.springrediscachingkotlinsample.model.entities.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import java.math.BigInteger

interface ProductRepository : MongoRepository<ProductEntity, String> {
    fun findById(id: BigInteger): ProductEntity?
}
