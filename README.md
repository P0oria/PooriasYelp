# Pooria's Yelp

Pooria's Yelp is an Android application that allows users to explore and discover restaurants based on their preferences. This README provides an overview of the application's features, functionality, and how to get started.

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [API Key](#api-key)
- [Features](#features)
  - [Search Restaurants](#search-restaurants)
  - [Sorting Results](#sorting-results)
  - [Add Restaurants to Favorites](#add-restaurants-to-favorites)
- [Usage](#usage)

## Introduction

Pooria's Yelp is a restaurant discovery app that leverages the Yelp Fusion API to provide users with information about restaurants in their area. The app offers the following features:

- Search for restaurants based on keywords such as "sushi," "steak," or "bbq."
- Sort search results by rating and price criteria.
- Save favorite restaurants for easy access.
- Remove favorite restaurants.

The app's user interface is designed to be intuitive, with a clean layout and user-friendly features.

## Getting Started

### Prerequisites

Before you can use Pooria's Yelp, ensure that you have the following prerequisites:

- An Android device or emulator running Android OS (minimum version: Android 5.0 Lollipop).
- An internet connection to fetch restaurant data from the Yelp Fusion API.

### API Key

To access the Yelp Fusion API and retrieve restaurant data, you'll need to obtain an API key. Follow these steps to get your API key:

1. Visit the Yelp Fusion API website: [Yelp Fusion API](https://docs.developer.yelp.com/docs/fusion-intro).
2. Create a Yelp account or log in if you already have one.
3. Once logged in, go to the [Create App](https://www.yelp.com/developers/v3/manage_app) page.
4. Create a new app and fill in the required information. 
5. Upon app creation, you will be provided with an API key. Keep this key secure, as it will be needed to authenticate requests to the API.
6. Replace the text YOUR API KEY with your own API key on line 23 of app>java>com.pooria.pooriasyelp>RetrofitClient. 

## Features

### Search Restaurants

- Launch Pooria's Yelp, and you'll be presented with a search bar where you can enter keywords like "sushi," "steak," or "bbq" to find restaurants that match your search.
- To clear the entered text, simply use the provided clear button (x).
- The default location is Montreal. You can change this by replacing "Montreal" with any other city on line 137 of app>java>com.pooria.pooriasyelp>SearchFragment.

### Sorting Results

Pooria's Yelp allows you to sort search results based on two criteria: rating and price.

#### Sorting by Rating

- Restaurants are ordered from the highest rating to the lowest rating.
- The app utilizes the rating value provided by the Yelp Fusion API to determine the order.
- If rating information is unavailable, the restaurant is placed at the bottom of the list.

#### Sorting by Price

- Restaurants are ordered from the cheapest to the most expensive (e.g., $$$$$).
- The app uses price data from the Yelp Fusion API to determine the order.
- If price information is unavailable, the restaurant is placed at the top of the list.

### Add Restaurants to Favorites

- To add a restaurant to your favorites, click on a restaurant item in the search results.
- A confirmation dialog will appear, asking if you want to add the selected item to your favorites.
- If confirmed, the restaurant is added to your favorites list and stored in the database for persistence across application restarts.
- To access your favorites, swipe right on the screen to open the navigation drawer and choose the "Favorites" option.

## Usage

To use Pooria's Yelp, follow these steps:

1. Install the application on your Android device or emulator.
2. Use the search bar to find restaurants based on your preferences.
3. Explore search results, sort them by rating or price, and add your favorite restaurants to the "Favorites" list.
<img width="329" alt="Screenshot 2023-09-27 at 3 20 40 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/8bd457ec-91e8-488f-9839-f736cdd34922">
<img width="337" alt="Screenshot 2023-09-27 at 3 20 50 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/836355e3-4941-45d4-90cb-66e276cfc500">
<img width="336" alt="Screenshot 2023-09-27 at 3 21 12 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/2daed393-5f4b-4353-88f9-4ec5cce476d7">
<img width="334" alt="Screenshot 2023-09-27 at 3 21 25 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/505186d9-acd7-406e-a09f-ef25e22a087a">
<img width="333" alt="Screenshot 2023-09-27 at 3 21 43 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/30ca235d-0048-42ba-a217-ac3b8eb52c29">
<img width="333" alt="Screenshot 2023-09-27 at 3 21 56 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/ec995b70-c66c-49aa-9ee1-5909ef4e3cd8">



