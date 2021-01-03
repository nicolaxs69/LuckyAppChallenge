# Lucky App Challenge

![.](https://imagestuffs.s3.amazonaws.com/lucky_icon.jpg)

This is an offers and discounts app, where you can search for the best promotions of products in the middle east.

## Functionalities

- User can navigate on offers in a list.
- Offers are separated  by sections.
- Swipe to refresh new offers
- By Clicking on an offer the user can see more detail about the selected item of interest.

## Architecture, Structure and Patterns

 The app was developed using the MVVM (Model View ViewModel) architecture with some utils.

 ## App Layers
The project has three big layer which contain all classes filter by funcionality in directories. These are: <b>Api, Data, UI and Utils.</b>

- <b>Data</b> layer handle all information and bussined logic needed in the app. This folder contain inside it three big directories like <b>model, and viewmodels</b>.

- <b>Api</b> this directory contain classes that handle the communication with the API endpoints.

- <b>UI</b> layer will host all classes that have an interaction with the user.

- <b>Utils</b> contains classes that will handle functions or actions that help to our code to perform some action.

## Libraries
- Navigation Component (Jetpack)
- Networking (Retrofit 2).
- Parsing data (Gson).
- Image Loading and Cache (Glide).
- RxJava 2  
- Groupie (Recycler)
- Shimmer (Facebook)

## Running the app
The main folder contain an apk folder which inside of it you are going to see and APK file that could be install in any android device sdk version is major than 6.0 Mashmellow.

## Authors

* **Nicolas Escobar Cruz**

## License
[MIT](https://choosealicense.com/licenses/mit/)


