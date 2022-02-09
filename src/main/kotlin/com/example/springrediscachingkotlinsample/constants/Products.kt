package com.example.springrediscachingkotlinsample.constants

import com.example.springrediscachingkotlinsample.model.entities.ProductEntity
import java.math.BigDecimal
import java.math.BigInteger

class Products {
    companion object {
        val productCatalog: List<ProductEntity> = listOf(
            ProductEntity(
                BigInteger.valueOf(1L),
                "Notebook Dell",
                "Notebook Ultrafino Dell Inspiron i5402-M10S 14 Full HD 11ª Geração Intel Core i5",
                BigDecimal(5000)
            ),
            ProductEntity(
                BigInteger.valueOf(2L),
                "Smartphone Samsung Galaxy A12",
                "Smartphone Samsung Galaxy A12 64GB 4G Wi-Fi Tela 6.5'' Dual Chip 4GB RAM Câmera Quádrupla + Selfie 8MP",
                BigDecimal(1100)
            ),
            ProductEntity(
                BigInteger.valueOf(3L),
                "Chandon Réserve Brut",
                "Chandon Réserve Brut 750 ml",
                BigDecimal(70)
            )
        )
    }
}
