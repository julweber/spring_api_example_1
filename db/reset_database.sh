#!/bin/bash
DATABASE_NAME="spring_api_example_1"
SQL_DROP="DROP DATABASE $DATABASE_NAME;"
SQL_CREATE="CREATE DATABASE $DATABASE_NAME;"
echo "Dropping database $DATABASE_NAME"
psql -U vagrant -d postgres -c "$SQL_DROP"

echo "Creating database $DATABASE_NAME"
psql -U vagrant -d postgres -c "$SQL_CREATE"
