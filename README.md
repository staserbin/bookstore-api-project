# bookstore-api-project
API test automation framework for the Bookstore service using  **Java 21 (Temurin)**, **JUnit 5**, **RestAssured**, **Allure Reports**, and **AssertJ**.  
Tests cover **Books** and **Authors** endpoints with positive and negative scenarios.

---

### Project Structure

```text
src
├── main
│   └── java
│       └── com.bookstore.api
│           ├── client        # Base REST client & request configuration
│           ├── model         # POJO models (Book, Author)
│           ├── service       # API service layer (BooksService, AuthorService)
│           └── config        # API & test configuration
└── test
    └── java
        └── com.bookstore.api.tests
            ├── base          # Base test setup
            ├── books         # Books API tests
            └── authors       # Authors API tests
```

### Prerequisites

Before running the tests locally, make sure you have:

- **Java 21 (Temurin)** installed
- **Git** installed
- **Gradle Wrapper** - no need to install Gradle globally, the project includes `gradlew`
- **Allure CLI** installed locally (optional but recommended to view reports):

   **MacOS (via Homebrew):**
   ```bash
   brew install allure
   ```
   **Windows (via Scoop or Chocolatey):**
   ```bash
   scoop install allure
   ```
   or
   ```bash
   choco install allure
   ```
   **Verify installation:**
   ```bash
   allure --version
   ```

### Quick Start

1. Clone the repo:
```bash
git clone https://github.com/staserbin/bookstore-api-project.git cd bookstore-api-project
```
2. Make Gradle wrapper executable (Linux/macOS):
```bash
chmod +x gradlew
```
3. Run all tests:
```bash
./gradlew clean test
```
4. Generate Allure report:
```bash
./gradlew allureReport
./gradlew allureServe
```

### Running Tests via GitHub Actions (CI/CD)

1. The workflow is manually triggered via GitHub -> Actions -> Bookstore - API Tests -> Run workflow
2. After completion, the Allure report is published to GitHub Pages
3. The workflow prints the URL to the Allure report in the Actions build summary for easy access

### Known Limitations / Not Implemented Cases

- Book can be created without pageCount

- PUT /api/v1/Books/{id} with non-existing ID always creates a book

- DELETE /api/v1/Books/{id} with 9999999999 exceeds 32-bit integer

- POST /api/v1/Authors without idBook succeeds

- PUT /api/v1/Authors/{id} with non-existing ID always creates an author

- DELETE /api/v1/Authors/{id} with 9999999999 exceeds 32-bit integer
