# AI-Powered Content Moderation Service 🤖

This project is a fully functional, cloud-native application designed to automatically moderate user-submitted text in real-time. It uses a microservice architecture built with Java and Spring Boot, integrates with Google's Natural Language AI for intelligent analysis, and is containerized with Docker for seamless deployment on Google Cloud Run.

**Live Demo:** *[https://frontend-service-929182564488.asia-south1.run.app]*

---

## 🏛️ Architecture

This project is built using a distributed microservice architecture to ensure a clean separation of concerns, scalability, and maintainability.

* **Frontend Service:** A lightweight, static web UI built with HTML, CSS, and JavaScript that serves as the user's entry point. It captures user text and communicates with the backend via REST API calls.
* **User-API-Service (The Clerk):** A Java/Spring Boot microservice that exposes the public-facing API. Its primary role is to handle incoming user requests, validate them, and orchestrate the moderation process by calling the `Moderator-AI-Service`.
* **Moderator-AI-Service (The Expert):** A specialized Java/Spring Boot microservice that contains the core business logic. It receives text from the `User-API-Service` and integrates directly with the Google Cloud Natural Language API to perform toxicity and sentiment analysis, returning a final "APPROVE" or "REJECT" decision.



---

## 🚀 Key Features & Technologies

This project demonstrates a wide range of modern software development practices and technologies.

#### **Key Features**
* **Real-time Moderation:** Analyzes text instantly upon submission.
* **AI-Powered Decisions:** Leverages a powerful, pre-trained AI model to detect nuances that simple keyword filters would miss.
* **Scalable & Resilient:** Built on a serverless, containerized platform that can handle variable loads.
* **Secure Configuration:** API keys and sensitive URLs are managed securely using environment variables, not hardcoded in the source.

#### **Technology Stack**
* **Backend:** Java 17, Spring Boot, Maven
* **Frontend:** HTML, CSS, JavaScript
* **DevOps:** Docker
* **Cloud Platform:** Google Cloud Run, Google Artifact Registry
* **AI Service:** Google Cloud Natural Language API

---

## 🔧 Getting Started

### **Prerequisites**
* Java 17 (JDK)
* Apache Maven
* Docker Desktop
* Google Cloud SDK (`gcloud` CLI)

### **Running Locally**
1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/RyaanVakil/ai-content-moderator.git
    ```
2.  **Configure API Key:**
    * Navigate to `moderator-ai-service/src/main/resources/`.
    * Create a file named `application.properties`.
    * Add your Google Natural Language API key to it: `google.api.key=YOUR_API_KEY`

3.  **Run the Backend Services:**
    * Start the moderator service (it will run on a random port):
        ```bash
        cd moderator-ai-service
        ./mvnw spring-boot:run
        ```
    * Start the user API service (it will run on port 8080):
        ```bash
        cd user-api-service
        ./mvnw spring-boot:run
        ```
4.  **Launch the Frontend:**
    * Open the `index.html` file located in `user-api-service/frontend` in your web browser. Ensure the `apiUrl` in the script points to `http://localhost:8080`.

### **Cloud Deployment**
The application is designed for the cloud and includes `Dockerfiles` for all three services. The deployment process involves:
1.  Building Docker images for each service.
2.  Pushing the images to a container registry (like Google Artifact Registry).
3.  Deploying the images to a serverless platform (like Google Cloud Run), ensuring that API keys and inter-service URLs are configured as environment variables.
