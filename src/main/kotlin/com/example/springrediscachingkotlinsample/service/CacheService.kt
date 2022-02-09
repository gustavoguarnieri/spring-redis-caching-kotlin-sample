package com.example.springrediscachingkotlinsample.service

import com.example.springrediscachingkotlinsample.constants.ErrorMessages
import com.example.springrediscachingkotlinsample.exceptions.NotFoundException
import mu.KotlinLogging
import org.springframework.cache.CacheManager
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val cacheManager: CacheManager
) {

    private val log = KotlinLogging.logger {}

    fun evictCache(cacheName: String) {
        cacheManager.getCache(cacheName)?.clear()
            ?: throw NotFoundException(ErrorMessages.CACHE_NOT_FOUND).also {
                log.warn { "evictCache: cache name not found, cacheName=$cacheName" }
            }
    }

    fun getCacheNames(): Collection<String> {
        return cacheManager.cacheNames
    }
}
