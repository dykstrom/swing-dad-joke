# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Kotlin-based Swing desktop application that retrieves and displays dad jokes from icanhazdadjoke.com. The application uses modern UI libraries and Kotlin coroutines for asynchronous REST API calls.

## Build System

Maven-based project with Kotlin:
- **Build**: `mvn compile`
- **Run tests**: `mvn test`
- **Package**: `mvn package`
- **Run single test**: `mvn test -Dtest=JokeClientIT#shouldGetRandomJoke`
- Target JVM version: 11
- Kotlin version: 1.6.21

## Architecture

### MVC Pattern
The application follows an MVC architecture:
- **App.kt**: Entry point that initializes FlatDarkLaf look-and-feel and wires up the MVC components
- **MainView.kt**: View layer containing all Swing UI components (button, text field, text area, busy indicator)
- **MainController.kt**: Controller that handles user actions and coordinates between view and REST client

### REST Layer
Asynchronous REST communication using Retrofit and Kotlin coroutines:
- **JokeService.kt**: Retrofit interface defining API endpoints with suspend functions
- **JokeClient.kt**: Wraps JokeService, provides two operations:
  - `getRandomJoke()`: Fetches a random joke
  - `getRandomJokeBySearchTerm(searchTerm)`: Searches for jokes matching a term, then randomly selects one from the results
- API base URL: https://icanhazdadjoke.com/
- Required headers: Accept: application/json, User-Agent set to project GitHub URL

### Data Models
- **Joke.kt**: Single joke with joke text field
- **JokeList.kt**: Search results containing array of jokes and total count
- Both use Jackson annotations for JSON deserialization

### Concurrency
- REST calls run in Kotlin coroutines using `MainScope().launch`
- Controller uses `kotlinx-coroutines-swing` to ensure UI updates on Swing EDT
- JBusyComponent provides visual feedback during async operations

## Key Dependencies

- **FlatLaf**: Modern look-and-feel (FlatDarkLaf theme)
- **MigLayout**: Layout manager for component positioning
- **JBusyComponent**: Busy indicator overlay during REST calls
- **Retrofit + Jackson**: REST client with JSON conversion
- **Kotlin Coroutines**: Asynchronous programming (core + swing)

## Testing

Integration tests use `runBlocking` to test suspend functions:
- JokeClientIT contains integration tests that make real API calls
- Use `kotlin-test-junit` for test assertions
