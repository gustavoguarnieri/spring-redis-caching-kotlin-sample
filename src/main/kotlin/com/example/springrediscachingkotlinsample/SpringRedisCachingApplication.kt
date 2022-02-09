package com.example.springrediscachingkotlinsample

import com.example.springrediscachingkotlinsample.constants.Products
import com.example.springrediscachingkotlinsample.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
class SpringRedisCachingKotlinSampleApplication(
    private val repository: ProductRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        repository.deleteAll()
        Products.productCatalog.forEach(repository::save)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringRedisCachingKotlinSampleApplication>(*args)
}
