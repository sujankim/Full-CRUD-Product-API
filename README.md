\# 🛒 Full CRUD Product API



A production-ready RESTful API built with \*\*Spring Boot\*\* that demonstrates complete CRUD operations, validation, exception handling, pagination, and custom database queries using \*\*JPA/Hibernate\*\*.



\---



\## 🚀 Features



\* ✅ Full CRUD operations (Create, Read, Update, Delete)

\* ✅ Pagination \& sorting support

\* ✅ Search by keyword + price range

\* ✅ Category-based filtering

\* ✅ Aggregate queries (Total stock value, Average price)

\* ✅ Request validation using Jakarta Validation

\* ✅ Global exception handling with structured error responses

\* ✅ H2 in-memory database (for development/testing)

\* ✅ Clean layered architecture (Controller → Service → Repository)



\---



\## 🏗️ Tech Stack



\* Java 17+

\* Spring Boot

\* Spring Data JPA (Hibernate)

\* H2 Database

\* Lombok

\* Maven



\---



\## 📁 Project Structure



```

com.sujan.fullcrudproductapi

│

├── controller        # REST API endpoints

├── service           # Business logic

├── repository        # Database access (JPA)

├── model             # Entity classes

├── dto               # Request/Response DTOs

├── mapper            # Entity ↔ DTO conversion

├── exception         # Custom exceptions \& handler

```



\---



\## 📦 API Endpoints



| Method | Endpoint                                  | Description                           |

| ------ | ----------------------------------------- | ------------------------------------- |

| GET    | `/api/products`                           | Get all products (paginated + filter) |

| GET    | `/api/products/{id}`                      | Get product by ID                     |

| GET    | `/api/products/search`                    | Search by keyword + price range       |

| GET    | `/api/products/category/{category}/stats` | Category statistics                   |

| POST   | `/api/products`                           | Create new product                    |

| PUT    | `/api/products/{id}`                      | Update product                        |

| DELETE | `/api/products/{id}`                      | Delete product                        |



\---



\## 🔍 Example Requests



\### ➕ Create Product



```http

POST /api/products

Content-Type: application/json



{

&#x20; "name": "iPhone 15",

&#x20; "price": 12000,

&#x20; "stock": 10,

&#x20; "category": "ELECTRONICS",

&#x20; "description": "Apple flagship phone"

}

```



\---



\### 📄 Get All Products



```http

GET /api/products?page=0\&size=10\&sortBy=price\&category=ELECTRONICS

```



\---



\### 🔎 Search Products



```http

GET /api/products/search?keyword=phone\&min=500\&max=1500

```



\---



\### 📊 Category Stats



```http

GET /api/products/category/ELECTRONICS/stats

```



\---



\## ⚙️ Configuration (H2 Database)



Access H2 Console:



```

http://localhost:8080/h2-console

```



Credentials:



```

JDBC URL: jdbc:h2:mem:productdb

Username: sa

Password: (empty)

```



\---



\## 📌 Validation Rules



\* \*\*name\*\* → required, max 100 chars

\* \*\*price\*\* → required, must be positive

\* \*\*stock\*\* → required, minimum 0

\* \*\*category\*\* → must be one of:



&#x20; \* ELECTRONICS

&#x20; \* CLOTHING

&#x20; \* FOOD

&#x20; \* BOOKS

\* \*\*description\*\* → optional, max 500 chars



\---



\## ⚠️ Error Response Format



```json

{

&#x20; "status": 400,

&#x20; "message": "Validation Failed",

&#x20; "errors": {

&#x20;   "name": "must not be blank"

&#x20; },

&#x20; "timestamp": "2026-04-18T12:00:00"

}

```



\---



\## 🧠 Architecture Overview



```

Client → Controller → Service → Repository → Database

```



\* \*\*Controller\*\* → Handles HTTP requests

\* \*\*Service\*\* → Business logic

\* \*\*Repository\*\* → Database queries



\---



\## 🧪 Running the Project



```bash

\# Clone repository

git clone https://github.com/sujankim/Full-CRUD-Product-API.git



\# Navigate into project

cd full-crud-product-api



\# Run application

mvn spring-boot:run

```



\---



\## 📈 Future Improvements



\* 🔄 Replace String category with Enum

\* 📄 Swagger / OpenAPI documentation

\* 🐳 Docker support

\* 🧪 Unit \& Integration tests

\* ⚡ Caching (Redis)



\---



\## 👨‍💻 Author



\*\*Sujan\*\*



\---



\## ⭐ If you like this project



Give it a ⭐ on GitHub and share it!



