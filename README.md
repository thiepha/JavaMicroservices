Microservices with java:
- Currency Exchange Service: API for currency exchange:
  http://localhost:8000/currency-exchange/from/EUR/to/VND
- Currency Conversion: Calls currency exchange service and calculates conversion result
  http://localhost:8100/currency-conversion/from/USD/to/VND/quantity/5
  http://localhost:8100/currency-conversion-feign/from/USD/to/VND/quantity/5
- Limit Service: Limit sample microservices, retrieves another git repos to get data
  http://localhost:8080/limits
- Git Local Config: Stores configs for Limit Service

h2 db: http://localhost:8000/h2-console/
