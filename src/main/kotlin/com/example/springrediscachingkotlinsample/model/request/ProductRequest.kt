package com.example.springrediscachingkotlinsample.model.request

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class ProductRequest(
    @NotBlank var name: String,
    @NotBlank var description: String,
    @Min(1L) var price: BigDecimal
)
