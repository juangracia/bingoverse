
# **Bingoverse**

## **Description**
Bingoverse is a microservices-based application that generates customizable bingo cards based on your chosen topics. It is designed to be scalable, user-friendly, and showcases modern technologies like Spring Boot, relational, and NoSQL databases.

---

## **Features**
- Generate personalized bingo cards based on specific topics.
- Backend built with microservices:
  - **bingo-config**: Handles configuration and PostgreSQL database connection.
  - **bingo-generator**: Generates bingo cards and stores data in MongoDB.
  - **bingo-api-gateway**: Centralized entry point for all microservices.
- Integration with PostgreSQL and MongoDB.
- Modular architecture for easy maintenance and scalability.

---

## **Prerequisites**
1. **Java 17** or higher.
2. **Maven** for dependency management.
3. **Docker** (optional) to quickly deploy the environment.
4. Databases:
   - **PostgreSQL** (for topics and configuration).
   - **MongoDB** (for storing bingo cards).

---

## **How to Run**

### **Clone the repository**
```bash
git clone https://github.com/juan-gracia/bingoverse.git
cd bingoverse
```

### **Run microservices individually**
1. Navigate to the microservice directory, e.g.:
   ```bash
   cd backend/bingo-config
   ```
2. Build and run using Maven:
   ```bash
   mvn spring-boot:run
   ```

### **Optional: Use Docker Compose to run the entire system**
1. Ensure Docker is installed and running.
2. From the project root, run:
   ```bash
   docker-compose up --build
   ```

---

## **API Endpoints**

### **Example endpoint**
- **Get topics by category:**
  - URL: `/topics/{category}`
  - Method: `GET`
  - Example response:
    ```json
    ["Queen", "The Cure", "U2"]
    ```

---

## **Project Structure**
```
bingoverse/
├── backend/                # Microservices
│   ├── bingo-config/       # Configuration and PostgreSQL database
│   ├── bingo-generator/    # Bingo card generator and MongoDB
│   └── bingo-api-gateway/  # API Gateway
├── frontend/               # Web or mobile application (pending)
├── docker-compose.yml      # Service orchestration with Docker
└── README.md               # Project documentation
```

---

## **Technologies Used**
- **Backend**: Spring Boot (Java 17), Spring Data JPA, Spring Web.
- **Databases**: PostgreSQL, MongoDB.
- **Orchestration**: Docker and Docker Compose.
- **Testing**: JUnit, Mockito.

---

## **Contributing**
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a branch for your feature:
   ```bash
   git checkout -b feature/new-feature
   ```
3. Submit a pull request with your changes.

---

## **License**
This project is licensed under the [MIT License](LICENSE).
