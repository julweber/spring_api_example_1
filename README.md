# Simple Spring API example

Providing a basic starting point for implementing spring REST APIs.

# Startup
```
# start server on port 8080
gradle bootRun
```

# Usage

## Curl & jq

curl is a tool to send http requests from the console.
jq is a tool to display json API outputs in a prettyfied format on the console.
curl can be combined with a piped jq call to display request responses on the console.

## Endpoints

```
# Retrieve greeting
curl 'localhost:8080/greeting?name=Sire' |jq '.'
```
