package com.example.springrediscachingkotlinsample.model.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProductResponse(
    var id: BigInteger? = null,
    var name: String? = null,
    var description: String? = null,
    var price: BigDecimal? = null
) : Serializable
