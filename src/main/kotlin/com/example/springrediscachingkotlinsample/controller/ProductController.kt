package com.example.springrediscachingkotlinsample.controller

import com.example.springrediscachingkotlinsample.model.request.ProductRequest
import com.example.springrediscachingkotlinsample.model.response.ProductResponse
import com.example.springrediscachingkotlinsample.service.ProductService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    private val log = KotlinLogging.logger {}

    @PostMapping
    private fun createProduct(@RequestBody productRequest: ProductRequest): ProductResponse {
        log.info("createProduct: creating product=${productRequest.name}")
        return productService.createProduct(productRequest).also {
            log.info("createProduct: finished creating product=${productRequest.name}")
        }
    }

    @PutMapping("/{id}")
    private fun updateProduct(
        @PathVariable @Min(1) id: BigInteger,
        @RequestBody productRequest: ProductRequest
    ) {
        log.info("updateProduct: updating product=${productRequest.name}")
        return productService.updateProduct(id, productRequest).also {
            log.info("updateProduct: finished updating product=${productRequest.name}")
        }
    }

    @GetMapping
    private fun getProducts(): List<ProductResponse> {
        log.info("getProducts: getting list of products")
        return productService.getProducts().also {
            log.info("getProducts: finished getting list of products")
        }
    }

    @GetMapping("/{id}")
    private fun getProduct(@PathVariable @Min(1) id: BigInteger): ProductResponse {
        log.info("getProduct: getting productId=$id")
        return productService.getProduct(id).also {
            log.info("getProduct: finished getting productId=$id")
        }
    }
}
