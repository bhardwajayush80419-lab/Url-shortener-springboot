# 🚀 Production-Ready URL Shortener API

A robust, highly optimized URL Shortener service built with **Spring Boot** and **PostgreSQL**. This API converts long, cumbersome URLs into crisp, 6-character short codes and tracks click analytics in real-time.

## 🌟 Key Features
- **Base62 Encoding Logic:** Generates highly unique 6-character short links to avoid collisions.
- **Analytics & Tracking:** Tracks real-time click counts for every generated short URL.
- **Persistent Storage:** Safely stores mappings and analytics in a PostgreSQL database using Spring Data JPA.
- **Data Integrity:** Implements `@Transactional` protocols to ensure safe database operations.
- **Interactive Documentation:** Fully documented API endpoints using Swagger UI (OpenAPI).

## 🛠️ Tech Stack
- **Language:** Java (JDK 17+)
- **Framework:** Spring Boot 3 (Web, Data JPA)
- **Database:** PostgreSQL
- **Utilities:** Lombok (Boilerplate reduction)
- **API Documentation:** Springdoc OpenAPI (Swagger UI)

## ⚙️ Architecture (3-Tier MVC)
1. **Controller Layer:** Handles incoming REST/HTTP requests and routes them. Includes custom Regex path variables to prevent routing conflicts.
2. **Service Layer:** Houses the core business logic (Base62 generation, duplicate checks, transaction management).
3. **Repository/Entity Layer:** Manages ORM configurations and executes optimized `@Modifying` update queries directly at the database level.

## 🚀 Getting Started (Local Setup)

### Prerequisites
- Java 17 or higher installed
- PostgreSQL installed and running locally
- Maven installed

### Installation Steps
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YOUR_USERNAME/Url-shortener-springboot.git](https://github.com/YOUR_USERNAME/Url-shortener-springboot.git)
