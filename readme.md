# Spring Redis Caching Kotlin Sample

The project is an example using spring cache with Redis

## Requirements

Java 11+

IntelliJ IDEA / Netbeans / Eclipse

Redis

MongoDB

## Usage

```python

1) Execute command: docker-compose up -d (the file docker-compose.yml will be executed).

2) Start project
   * Some records will be automatically created. 

3) Synchronous call:
   * Create product
   curl --location --request POST 'http://localhost:9080/spring-caching/api/v1/products' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "name": "Mouse",
            "description": "Mouse sem fio",
            "price": 250
        }'
        
   * Get list of products
   curl --location --request GET 'http://localhost:9080/spring-caching/api/v1/products'
   
   * Get product
   curl --location --request GET 'http://localhost:9080/spring-caching/api/v1/products/1'
   
   * Update product
   curl --location --request PUT 'http://localhost:9080/spring-caching/api/v1/products/1' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "id": 1,
            "name": "Notebook Dell",
            "description": "Notebook Ultrafino Dell Inspiron i5402-M10S 14 Full HD 11ª Geração Intel Core i5",
            "price": 5250
        }'   
```

## Documentation

[Swagger](http://localhost:9080/spring-caching/swagger-ui/index.html)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)