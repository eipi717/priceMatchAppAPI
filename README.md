# PriceMatchAPI

## Overview

PriceMatchAPI is a RESTful API designed for comparing product prices across different stores. It provides endpoints for managing products, historical price data, user authentication, and administrative functions.

## Features

- **User Authentication: Secure user login and registration.**

- **Product Management: CRUD operations for products and stores.**

- **Price Tracking: Historical price data storage and retrieval.**

- **Admin Controls: User management and system health monitoring.**

## API Endpoints

### Product Management

- `GET /api/v1/products/getAllProducts` - Retrieve all products

- `POST /api/v1/products/createProduct` - Add a new product

- `DELETE /api/v1/products/deleteProduct/{productId}` - Remove a product

### Price Tracking

- `GET /api/v1/prices/getAllPrices` - Retrieve all price records

- `GET /api/v1/prices/getByProductId` - Get price history by product ID

- `POST /api/v1/prices/createPrice` - Create a price entry

### Historical Price Data

- `GET /api/v1/historicalPrice/getAllHistoricalPrices` - Retrieve all historical prices

- `GET /api/v1/historicalPrice/getByProductId` - Retrieve historical prices by product ID

- `POST /api/v1/historicalPrice/createHistoricalPrice` - Add historical price data

### Store Management

- `GET /api/v1/stores/getAllStores` - Retrieve all stores

- `POST /api/v1/stores/createStore` - Add a new store

- `DELETE /api/v1/stores/deleteStore/{storeId}` - Remove a store

### Admin Controls

- `GET /api/v1/admin/findAllActivities` - Retrieve all activities

- `GET /api/v1/admin/findActivitiesByUserId` - Get activities by user ID

- `DELETE /api/v1/admin/deleteActivitiesBetweenDates` - Delete activities within a date range

### User Management

- `GET /api/v1/users/getAllUsers` - Retrieve all users

- `GET /api/v1/users/findUserByUserName` - Find user by username

- `POST /api/v1/users/changePassword` - Change user password

- `POST /api/v1/users/accountCreate` - Create a new account

### Health Check

- `GET /` - Application health status

## Installation

### Prerequisites

- Java 17+

- MySQL Database

### Sample `db.properties`
```
# DB config
spring.datasource.url=jdbc:mysql://localhost:3306/PriceMatchApp
spring.datasource.username=root
spring.datasource.password=123456
```
### Steps

1. Clone the repository: <br>
`git clone https://github.com/eipi717/priceMatchAppAPI.git
cd PriceMatchAPI`

2. Configure database settings in `src/main/resources/db.properties`. 
3. Build and run the application

## License

This project is licensed under the MIT License.

## Contact

For inquiries, reach out at `nicholasriven717@gmail.com`.

