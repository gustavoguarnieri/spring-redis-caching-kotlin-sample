package com.example.springrediscachingkotlinsample.controller

import com.example.springrediscachingkotlinsample.service.CacheService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/caches")
class CacheController(
    private val cacheService: CacheService
) {

    private val log = KotlinLogging.logger {}

    @PostMapping("/{cacheName}/evict")
    private fun evictCache(@PathVariable cacheName: String) {
        log.info("evictCache: evicting cache, cacheName=${cacheName}")
        return cacheService.evictCache(cacheName).also {
            log.info("evictCache: evicted cache, cacheName=${cacheName}")
        }
    }

    @GetMapping
    private fun getCaches(): Collection<String> {
        log.info("getCaches: getting list of cache names")
        return cacheService.getCacheNames().also {
            log.info("getCaches: finished getting list of cache names")
        }
    }
}
