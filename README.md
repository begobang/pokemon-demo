# Pokemon Demo

## Description

This Android application was implemented using Jetpack Compose, and it displays a list of Pokemons with a picture and name. The app allows users to filter the list by name and refresh the data using a refresh icon. The application also has a detail view of each Pokemon with specifications such as weight, height, and type. Users can also see the moves and games by clicking on the respective button, which will display a modal with the requested data. The application is implemented with Clean Architecture, with the Data, Domain, and Presentation layers separated. Each functionality is being tested using Fakes. The app saves all data locally, so the user can recover the data when they don't have internet or when there is an error.

https://user-images.githubusercontent.com/106311072/234844281-3cf4027d-a6ba-4c77-9b8b-c68fb9957c1f.mp4

## Prerequisites

Android Studio Arctic Fox or higher.
An Android device or emulator running Android 8.0 (API level 26) or higher.

## Installation

Clone or download this repository.
Open Android Studio and select Open an existing Android Studio project.
Navigate to the downloaded repository and select the build.gradle file in the root directory.
Wait for the project to sync with Gradle.
Run the application on an emulator or connected device.

## Usage

Upon launching the application, the user will be presented with a list of Pokemons. Each item in the list shows the Pokemon's name and an image. The user can filter the list by name using the search option at the top of the screen. The user can also refresh the list by clicking on the refresh icon next to the search option.

When the user clicks on an item, a detail view of the Pokemon is displayed. The detail view shows additional information about the selected Pokemon, including its type, height, and weight. The user can also see the moves and games by clicking on the respective button, which will display a modal with the requested data.

The user can navigate back to the list view by clicking on the back button on the top left corner of the screen.

## Architecture

This application was implemented using Clean Architecture, with the Data, Domain, and Presentation layers separated. The Data layer is responsible for retrieving the data from the remote data source (API) and storing it locally (cache). The Domain layer defines the business logic of the application and provides a use case for retrieving the data. The Presentation layer is responsible for displaying the data to the user and handling user interactions.

## Testing

Each functionality in the application is tested using Fakes. The tests are located in the test directory and can be run using the gradlew test command in the terminal.

## Credits

This application was implemented using Jetpack Compose, an Android UI toolkit that simplifies the development of native Android applications. The list of Pokemons was obtained from the PokéAPI, a RESTful API that provides access to Pokémon data.
