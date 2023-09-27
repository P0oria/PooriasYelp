package com.pooria.pooriasyelp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResponse {

    @SerializedName("total")
    int total;

    @SerializedName("businesses")
    ArrayList<Business> businesses;
}

class Business {

    @SerializedName("name")
    String name;

    @SerializedName("rating")
    float rating;

    @SerializedName("phone")
    String phone;

    @SerializedName("categories")
    ArrayList<Category> categories;

    @SerializedName("price")
    String price;

    @SerializedName("location")
    Location location;

    @SerializedName("id")
    String id;

    @SerializedName("image_url")
    String url;
}


class Category {
    @SerializedName("alias")
    String alias;

    @SerializedName("title")
    String title;
}

class Location {
    @SerializedName("address1")
    String address1;

    @SerializedName("address2")
    String address2;

    @SerializedName("address3")
    String address3;

    @SerializedName("zip_code")
    String zipCode;

    @SerializedName("display_address")
    ArrayList<String> displayAddress;
}

