# Simple Spring API example

Providing a basic starting point for implementing spring REST APIs.

# Usage

```
# Build package
gradle build

# Start server on port 8080
gradle bootRun
```

# Database Scripts

```
# Reset the database completely (DROP + CREATE)
db/reset_database.sh
```

# Environment configuration

You can configure the server's behavior with OS environment variables as follows:

```
export RESULTS_PER_PAGE=10
export DEFAULT_GENRE=jazz
gradle bootRun
```

## Curl & jq

curl is a tool to send http requests from the console.
jq is a tool to display json API outputs in a prettyfied format on the console.
curl can be combined with a piped jq call to display request responses on the console.

## Endpoints

```
# Retrieve greeting
curl -v 'localhost:8080/greeting?name=Sire' |jq '.'


### Customers

# Retrieve a list of customers
curl -v 'localhost:8080/v1/customers' |jq '.'

# Retrieve single customer
curl -v 'localhost:8080/v1/customers/1' |jq '.'

# Create a customer with required params
curl -v -X POST -d '{ "email" : "new@example.com", "password": "test123" }' 'localhost:8080/v1/customers' -H 'Content-Type: application/json' |jq '.'

# Create a customer with full params
curl -v -X POST -d '{ "email" : "clark@gable.com", "password": "test123", "firstName":"Clark", "lastName":"Gable" }' 'localhost:8080/v1/customers' -H 'Content-Type: application/json' |jq '.'

# Update a customer
curl -v -X PUT -d '{ "email":"test@example.com" }' 'localhost:8080/v1/customers/1' -H 'Content-Type: application/json' |jq '.'

# Delete a customer
curl -v -X DELETE 'localhost:8080/v1/customers/1'

### Records

# Retrieve a list of records
curl -v 'localhost:8080/v1/records' |jq '.'

# Retrieve a list of records for a certain customer id
curl -v 'localhost:8080/v1/records?customerId=1' |jq '.'

# Retrieve single record
curl -v 'localhost:8080/v1/records/1' |jq '.'

# Create a record with required params
curl -v -X POST -d '{ "title" : "Experience", "artist": "Jimi Hendrix", "customer_id": 1 }' 'localhost:8080/v1/records' -H 'Content-Type: application/json' |jq '.'

# Create a record with full params
curl -v -X POST -d '{ "title" : "Experience", "artist": "Jimi Hendrix", "customerId": 1, "genre":"Rock", "format":"12Inch" }' 'localhost:8080/v1/records' -H 'Content-Type: application/json' |jq '.'

# Update a record
curl -v -X PUT -d '{ "genre":"Funk" }' 'localhost:8080/v1/records/1' -H 'Content-Type: application/json' |jq '.'

# Delete a record
curl -v -X DELETE 'localhost:8080/v1/records/1'

```
