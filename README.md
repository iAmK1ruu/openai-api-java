# Java OpenAI Simple API Caller

## Overview

This Java application sends an HTTP POST request to an external API, passing a user-provided prompt, and retrieves the response. The application uses the `HttpURLConnection` class to make an API call and includes error handling for scenarios like invalid API keys, incorrect URLs, and rate-limiting responses.

## Features

- Accepts user input (a prompt) from the console.
- Sends the input as a request to a specified API endpoint.
- Handles API authentication using a Bearer Token.
- Processes the response and extracts the relevant content.
- Provides error handling for common issues such as:
  - Invalid API keys
  - Request limits
  - Incorrect URLs

## Prerequisites

To use this application, you will need:

- Java Development Kit (JDK) 8 or higher.
- An active internet connection to make API calls.
- An API key from the provider of the API.
- The URL of the API endpoint.
- (Optional) Model specification for the API if required by the API service.

## How to Use

1. **Clone or download** this repository to your local machine:
    ```bash
    git clone https://github.com/iAmK1ruu/openai-api-java.git
    ```
2. **Open the project** in your favorite Java IDE (e.g., Eclipse, IntelliJ IDEA) or a text editor.

3. **Edit the API call configuration**:
   In the `callAPI()` method, replace the following placeholders with your actual details:
   - **`URL PROVIDER`**: The URL endpoint of the API.
   - **`YOUR API HERE`**: Your API key for authentication.
   - **`THE SPECIFIC MODEL HERE`**: The model you're interacting with (if applicable).

4. **Compile and run** the program. When prompted, enter your text prompt, and the application will send it to the API and display the response.

### Example

```text
Input:
Prompt: Hello, world!

Output:
The response from the API will be displayed.
