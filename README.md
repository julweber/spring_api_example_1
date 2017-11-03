# Simple Spring API example

Providing a basic starting point for implementing spring REST APIs.

# Usage

```
# Build package
gradle build

# Start server on port 8080
gradle runBoot
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

# Retrieve a list of customers
curl -v 'localhost:8080/customers' |jq '.'

# Retrieve single customer
curl -v 'localhost:8080/customers/1' |jq '.'

# Create a customer
curl -v -X POST -d '{ "email" : "new@example.com"}' 'localhost:8080/customers' |jq '.'

# Update a customer
curl -v -X PUT -d '{ "email":"test@example.com" }' 'localhost:8080/customers/1' |jq '.'

# Delete a customer
curl -v -X DELETE 'localhost:8080/customers/1'
```
