
# CI/CD Pipeline with GitHub Actions, SonarQube, Jacoco, and Azure WebApp Deployment

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Joao-Lucas-de-Oliveira-Lima_github-actions-azure-pipeline&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Joao-Lucas-de-Oliveira-Lima_github-actions-azure-pipeline)

# Overview

This project implements a REST API for managing `USER` entities with CRUD operations and a greeting endpoint, adhering to **Clean Architecture** principles. It includes **unit**, **integration**, and **architectural tests** (using **ArchUnit**) to ensure code quality and compliance.

## CI/CD Pipeline

A **GitHub Actions** pipeline automates:

- **Testing**: Executes unit and integration tests with Maven (`verify`) and generates coverage reports via **JaCoCo**.  
- **Code Quality**: Analyzes metrics with **SonarQube Cloud Scanner**.  
- **Deployment**: Deploys to **Azure WebApp** upon pipeline success.

---
## Project Setup

### Requirements for the Production Branch
1. The production branch (`main`) is subject to specific restrictions, such as enabling **Require status checks to pass** and **Require a pull request before merging**. These settings ensure that workflows are validated through pull requests before code is merged.

### Creating the CI/CD Pipeline
```yaml
name: Continuous Integration and Continuous Delivery

on:
  workflow_dispatch:
  push:
    branches:
      - main
      - test
  pull_request:
    types:
      - opened
      - synchronize
      - reopened

jobs:
  test_and_deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
```
This pipeline includes a `test_and_deploy` job running on an Ubuntu virtual machine. The `workflow_dispatch` event allows manual pipeline execution. The [actions/checkout@v4](https://github.com/actions/checkout) action clones the repository, and `fetch-depth: 0` ensures full commit history. [actions/setup-java@v4](https://github.com/actions/setup-java) installs JDK 21.

---

## Building the Project

### Caching Configuration
```yaml
- name: Cache SonarQube packages
  uses: actions/cache@v4
  with:
    path: ~/.sonar/cache
    key: ${{ runner.os }}-sonar
    restore-keys: ${{ runner.os }}-sonar

- name: Cache Maven packages
  uses: actions/cache@v4
  with:
    path: ~/.m2
    key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
    restore-keys: ${{ runner.os }}-m2
```

### Build and Quality Analysis
```yaml
- name: Build and Analyze
  env:
    GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
    SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
  run: mvn install -B verify jacoco:report org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Joao-Lucas-de-Oliveira-Lima_github-actions-azure-pipeline

- name: Set JAR file name
  run: |
    echo "JAVA_BUILD=$(ls target | grep '\.jar$')" >> $GITHUB_ENV
```
These steps build the project, run unit and integration tests, generate coverage reports with Jacoco, and send data to SonarQube for quality analysis. More details are available in the official [documentation](https://docs.sonarsource.com/sonarqube-server/10.7/devops-platform-integration/github-integration/adding-analysis-to-github-actions-workflow/).

---

## Deploying to Azure WebApp
```yaml
- name: Deploy to Azure Web Apps
  uses: azure/webapps-deploy@v3
  env:
    AZURE_WEBAPP_PACKAGE_PATH: './target'
  with:
    app-name: ${{ secrets.AZURE_WEBAPP_NAME }}
    publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
    package: '${{ env.AZURE_WEBAPP_PACKAGE_PATH }}/${{ env.JAVA_BUILD }}'
```
Deployment is done to an Azure WebApp service. Authentication uses **Publish Profile** with basic authentication enabled on Azure. More details are available in the [official documentation](https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions?tabs=applevel%2Cjava).

---

## Running the Application

### Prerequisites
- Java JDK 21
- Maven 3+
- Docker Desktop (optional)

### Building the Application
```bash
mvn clean install
```

### Running the Application
```bash
mvn spring-boot:run
```
The application will start at: [http://localhost:8080](http://localhost:8080)

### Running Tests
**Unit tests:**
```bash
mvn test
```

**Integration tests:**
```bash
mvn verify
```

---

## Using SonarQube with Docker
Use the `docker-compose.yaml` file to run SonarQube locally:
```bash
docker-compose up -d
```
Access SonarQube at the address specified in the `.yaml` file (default: [http://localhost:9001](http://localhost:9001)), log in, and follow the instructions provided.

---

## Available Endpoints

### Main Endpoints
- **POST /api/v1/users**: Create a new user.
- **GET /api/v1/users**: List all users.
- **GET /api/v1/users/{id}**: Retrieve details of a specific user.
- **PUT /api/v1/users/{id}**: Update user information.
- **DELETE /api/v1/users/{id}**: Delete a user.
- **GET**: Return a greeting message.

### Examples
**Create User**
Request:
```json
POST /api/v1/users
Content-Type: application/json
{
  "username": "new_user",
  "fullName": "New User"
}
```
Response:
```json
HTTP/1.1 201 Created
Content-Type: application/json
{
  "id": 1,
  "username": "new_user",
  "fullName": "New User"
}
```

**List Users**
Request:
```bash
GET /api/v1/users
```
Response:
```json
HTTP/1.1 200 OK
Content-Type: application/json
[
  {
    "id": 1,
    "username": "new_user",
    "fullName": "New User"
  }
]
```

---

## Interactive Documentation
Interactive API documentation is available at `/swagger-ui/index.html`, and the API specification can be accessed at `/v3/api-docs`.
