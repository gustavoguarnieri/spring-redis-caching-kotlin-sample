package com.example.springrediscachingkotlinsample.service

import com.example.springrediscachingkotlinsample.constants.CacheNames.Companion.CACHE_PRODUCTS
import com.example.springrediscachingkotlinsample.constants.ErrorMessages.Companion.PRODUCT_NOT_FOUND
import com.example.springrediscachingkotlinsample.exceptions.NotFoundException
import com.example.springrediscachingkotlinsample.model.entities.ProductEntity
import com.example.springrediscachingkotlinsample.model.request.ProductRequest
import com.example.springrediscachingkotlinsample.model.response.ProductResponse
import com.example.springrediscachingkotlinsample.repositories.ProductRepository
import mu.KotlinLogging
import org.modelmapper.ModelMapper
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class ProductService(
    private val repository: ProductRepository,
    private val modelMapper: ModelMapper
) {

    private val log = KotlinLogging.logger {}

    @Caching(
        put = [CachePut(CACHE_PRODUCTS, key = "#result.id")],
        evict = [CacheEvict(value = arrayOf(CACHE_PRODUCTS), key = LIST_OF_PRODUCTS_NAME_IN_CACHE)]
    )
    fun createProduct(productRequest: ProductRequest): ProductResponse {
        val productEntity = convertToEntity(productRequest)
        val productResponse = repository.save(productEntity)
        return convertToDto(productResponse)
    }

    @Caching(
        evict = [
            CacheEvict(value = arrayOf(CACHE_PRODUCTS), key = "#id"),
            CacheEvict(value = arrayOf(CACHE_PRODUCTS), key = LIST_OF_PRODUCTS_NAME_IN_CACHE)
        ]
    )
    fun updateProduct(id: BigInteger, productRequest: ProductRequest) {
        repository.findById(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND).also {
            log.warn { "updateProduct: product not found, productId=$id" }
        }

        val productEntity = convertToEntity(productRequest)
        productEntity.id = id

        repository.save(productEntity)
    }

    @Cacheable(CACHE_PRODUCTS, key = LIST_OF_PRODUCTS_NAME_IN_CACHE)
    fun getProducts(): List<ProductResponse> {
        Thread.sleep(TIME_TO_SLEEP_IN_MILLISECONDS)

        return repository.findAll().map { convertToDto(it) }.also {
            log.info("getProducts: method was called to getting list of products")
        }
    }

    @Cacheable(CACHE_PRODUCTS, key = "#id")
    fun getProduct(id: BigInteger): ProductResponse {
        Thread.sleep(TIME_TO_SLEEP_IN_MILLISECONDS)

        val product = repository.findById(id) ?: throw NotFoundException(PRODUCT_NOT_FOUND).also {
            log.warn { "getProduct: product not found, productId=$id" }
        }
        return convertToDto(product)
    }

    private fun convertToDto(productEntity: ProductEntity): ProductResponse {
        return modelMapper.map(productEntity, ProductResponse::class.java)
    }

    private fun convertToEntity(productRequest: ProductRequest): ProductEntity {
        return modelMapper.map(productRequest, ProductEntity::class.java)
    }

    companion object {
        private const val TIME_TO_SLEEP_IN_MILLISECONDS = 5_000L
        private const val LIST_OF_PRODUCTS_NAME_IN_CACHE = "'allProducts'"
    }
}
