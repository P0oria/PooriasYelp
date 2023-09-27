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

1. Visit the Yelp Fusion API website: [Yelp Fusion API](https://www.yelp.com/developers/documentation/v3/get_started).
2. Create a Yelp account or log in if you already have one.
3. Once logged in, go to the [Create App](https://www.yelp.com/developers/v3/manage_app) page.
4. Create a new app and fill in the required information. 
5. Upon app creation, you will be provided with an API key. Keep this key secure, as it will be needed to authenticate requests to the API.
6. Replace the text YOUR API KEY with your own API key on line 22 of app>java>com.pooria.pooriasyelp>RetrofitClient. 

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

   <img width="329" alt="Screenshot 2023-09-27 at 3 20 40 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/38f9ccb1-683f-4417-9c63-fccca49424e2">
   <img width="337" alt="Screenshot 2023-09-27 at 3 20 50 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/6d784a3d-564c-42f4-aa55-f8e18830f14d">
   <img width="336" alt="Screenshot 2023-09-27 at 3 21 12 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/8f9ea053-aa82-48fc-98bc-e189f362a9f4">
   <img width="334" alt="Screenshot 2023-09-27 at 3 21 25 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/ca35c476-f035-4b8b-9f83-ef804a0f2482">
   <img width="333" alt="Screenshot 2023-09-27 at 3 21 43 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/e8a3143f-a1d1-46a4-9254-9c422e59e6f8">
   <img width="333" alt="Screenshot 2023-09-27 at 3 21 56 PM" src="https://github.com/P0oria/PooriasYelp/assets/116241252/7b71855b-d70a-4e35-a8fe-d35a7af74794">




