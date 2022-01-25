Microservices with java:
- Currency Exchange Service: API for currency exchange:
  http://localhost:8000/currency-exchange/from/EUR/to/VND
- Currency Conversion: Calls currency exchange service and calculates conversion result
  http://localhost:8100/currency-conversion/from/USD/to/VND/quantity/5
  http://localhost:8100/currency-conversion-feign/from/USD/to/VND/quantity/5
- Limit Service: Limit sample microservices, retrieves another git repos to get data
  http://localhost:8080/limits
- Git Local Config: Stores configs for Limit Service
- Naming Service: Eureka: http://localhost:8761/
- API Gateway: http://localhost:8765/
  Other services (currency) now can receive request through api gateway like:
  http://localhost:8765/currency-exchange/currency-exchange/from/EUR/to/VND
  -> customize to shorten uri:
  http://localhost:8765/currency-exchange/from/EUR/to/VND


h2 db: http://localhost:8000/h2-console/
