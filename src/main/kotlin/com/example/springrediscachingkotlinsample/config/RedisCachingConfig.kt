package com.example.springrediscachingkotlinsample.config

import com.example.springrediscachingkotlinsample.constants.CacheNames.Companion.CACHE_PRODUCTS
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.connection.RedisConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
class RedisCachingConfig(
    @Value("\${spring.redis.host}") private val host: String,
    @Value("\${spring.redis.port}") private val port: Int
) {

    @Bean
    fun connectionFactory(): RedisConnectionFactory {
        val redisConfiguration: RedisConfiguration

        redisConfiguration = RedisStandaloneConfiguration(host, port)

        return LettuceConnectionFactory(redisConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(connectionFactory())

        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer)

        return redisTemplate
    }

    /**
     * More flexibility to customize each cache
     */
    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
        return RedisCacheManagerBuilderCustomizer { builder ->
            builder
                .withCacheConfiguration(
                    CACHE_PRODUCTS,
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(TEN_MINUTES))
                        .disableCachingNullValues()
                )
                .disableCreateOnMissingCache()
        }
    }

    companion object {
        private const val TEN_MINUTES = 10L
    }
}
