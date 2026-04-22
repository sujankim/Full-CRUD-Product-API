🛒 Full CRUD Product API (Secure JWT Auth System)

A production-ready RESTful API built with Spring Boot 4 + Spring Security 7 that demonstrates full CRUD operations with secure JWT-based authentication and role-based authorization.

🚀 Features
🔐 Authentication & Security
✅ JWT-based authentication (login/register)
✅ Stateless session management
✅ Role-based authorization (USER, ADMIN)
✅ Secure password storage using BCrypt
✅ Custom 401 (Unauthorized) and 403 (Forbidden) handlers
✅ Protected routes using Spring Security filters
📦 Product Management
✅ Full CRUD operations (Create, Read, Update, Delete)
✅ Pagination & sorting support
✅ Search by keyword + price range
✅ Category-based filtering
✅ Aggregate queries (Total stock value, Average price)
🧱 Backend Architecture
✅ Clean layered architecture (Controller → Service → Repository)
✅ DTO-based request/response structure
✅ Global exception handling
✅ Validation using Jakarta Validation
🧪 Development Tools
✅ H2 in-memory database
✅ Hibernate (JPA)
✅ Lombok for boilerplate reduction
🏗️ Tech Stack
Java 21
Spring Boot 4.0.5
Spring Security 7 (JWT)
Spring Data JPA (Hibernate)
H2 Database
Lombok
Maven
📁 Project Structure
com.sujan.fullcrudproductapi

├── config            # Security, JWT filter, config
├── controller        # REST endpoints
├── service           # Business logic
├── repository        # JPA repositories
├── model             # Entities + enums
├── dto               # Request/response DTOs
├── exception         # Custom exceptions & handlers
🔐 Authentication API
Method	Endpoint	Description	Access
POST	/api/auth/register	Register new user	Public
POST	/api/auth/login	Login & get JWT token	Public
GET	/api/auth/me	Get current user info	Auth
🔑 Example — Register
POST /api/auth/register
Content-Type: application/json

{
  "username": "user1",
  "password": "password123"
}
🔑 Example — Login
POST /api/auth/login

{
  "username": "user1",
  "password": "password123"
}
✅ Response
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
🔐 Using JWT in requests
Authorization: Bearer YOUR_TOKEN_HERE
📦 Product API
Method	Endpoint	Description	Access
GET	/api/products	Get all products	Public
GET	/api/products/{id}	Get product by ID	Public
GET	/api/products/search	Search products	Public
GET	/api/products/category/{category}/stats	Category stats	Public
POST	/api/products	Create product	ADMIN only
PUT	/api/products/{id}	Update product	ADMIN only
DELETE	/api/products/{id}	Delete product	ADMIN only
🔍 Example Requests
➕ Create Product (ADMIN only)
POST /api/products
Authorization: Bearer TOKEN

{
  "name": "iPhone 15",
  "price": 1200,
  "stock": 10,
  "category": "ELECTRONICS",
  "description": "Apple flagship phone"
}
📄 Get Products
GET /api/products?page=0&size=10&sortBy=price
🔎 Search
GET /api/products/search?keyword=phone&min=500&max=1500
⚙️ Configuration (H2 Database)

Access H2 Console:

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:productdb
Username: sa
Password: (empty)
🔐 Security Flow
Client → Login → JWT Token
       → Send Token in Header
       → JWT Filter → Validate
       → SecurityContext → Access Granted
⚠️ Error Response Format
{
  "status": 401,
  "message": "Authentication required",
  "timestamp": "2026-04-21T12:00:00"
}
🧪 Running the Project
# Clone repository
git clone https://github.com/sujankim/Full-CRUD-Product-API.git

# Navigate
cd Full-CRUD-Product-API

# Run
mvn spring-boot:run
📈 Future Improvements
📄 Swagger / OpenAPI documentation
🐳 Docker support
🧪 Unit & integration tests
⚡ Redis caching
🔄 Refresh tokens (JWT)
👨‍💻 Author

Sujan Lamichhane

⭐ If you like this project

Give it a ⭐ on GitHub and share it!