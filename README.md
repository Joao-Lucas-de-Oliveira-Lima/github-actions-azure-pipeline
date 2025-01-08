# CI/CD Pipeline with GitHub Actions, SonarQube, and Azure WebApp Deployment  
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white) ![SonarQube](https://img.shields.io/badge/SonarQube-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white) ![Azure](https://img.shields.io/badge/Azure-0078D4?style=for-the-badge&logo=microsoft-azure&logoColor=white)  

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Joao-Lucas-de-Oliveira-Lima_github-actions-azure-pipeline&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Joao-Lucas-de-Oliveira-Lima_github-actions-azure-pipeline) ![CI](https://github.com/Joao-Lucas-de-Oliveira-Lima/github-actions-azure-pipeline/actions/workflows/main.yaml/badge.svg)  

## Table of Contents  
- [About](#about)  
- [Pipeline Overview](#pipeline-overview)  
- [Setup](#setup)  
- [Usage](#usage)  
- [Documentation](#documentation)  

---

## About <a name="about"></a>
### API
This project is a simple REST API for managing a simplified version of `USER` entities, implementing CRUD operations. These CRUD operations provide enough material for integration and unit testing.

---

## Pipeline Overview <a name="pipeline-overview"></a>  
The CI/CD pipeline is built using GitHub Actions. It compiles the application, runs unit and integration tests, and generates a `.jar` file. Test execution utilizes JaCoCo for coverage analysis, while the SonarQube plugin analyzes the compiled files and sends the results to SonarQube Cloud. Finally, the generated `.jar` is deployed to an Azure Web App Service.

### Pipeline Steps:
1. **Checkout Branch and Set Up JDK:**
   ```yaml
   - uses: actions/checkout@v4
     with:
       fetch-depth: 0
   - name: Set up JDK 21
     uses: actions/setup-java@v4
     with:
       java-version: '21'
       distribution: 'temurin'
   ```
2. **Build Project, Generate JaCoCo Report, and Analyze with SonarQube:**
   ```yaml
   - name: Build and analyze
     env:
       GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
       SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
     run: mvn install -B verify jacoco:report org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Joao-Lucas-de-Oliveira-Lima_github-actions-azure-pipeline
   ```
3. **Deploy to Azure:**
   ```yaml
   - name: Deploy to Azure Web apps
     uses: azure/webapps-deploy@v3
     env:
       AZURE_WEBAPP_PACKAGE_PATH: './target'
     with:
       app-name: ${{ secrets.AZURE_WEBAPP_NAME }}
       publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
       package: '${{ env.AZURE_WEBAPP_PACKAGE_PATH }}/${{ env.JAVA_BUILD }}'
   ```

---

## Setup <a name="setup"></a>  

### Prerequisites  
- **Java JDK 21**  
---

## Usage <a name="usage"></a> 

### Run locally

1. **Compile the Application:**  
   ```bash  
   ./mvnw clean compile  
   ```  

2. **Run the Application:**  
   ```bash  
   ./mvnw spring-boot:run  
   ```  
   Access it at: [http://localhost:8080](http://localhost:8080)  

3. **Run Tests:**  
   - **Unit Tests:**  
     ```bash  
     ./mvnw test  
     ```  
   - **Integration Tests:**  
     ```bash  
     ./mvnw verify  
     ```  

---

## Documentation <a name = "documentation"></a>

### Swagger UI

- To access the Swagger documentation, visit: [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/
swagger-ui/index.html)
- For the API documentation in JSON format, use: [http://localhost:8080/api/v3/api-docs](http://localhost:8080/api/v3/api-docs)

---
