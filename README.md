# Android Summit API Sample App  

This sample Android application can be used as a reference app during the Android Summit hackathon.  It includes examples of integrations with popular Android & FinTech APIs as well as Capital One's own hackathon API.

This sample app includes example integrations with the following APIs:
* Nessie - Capital One Hackathon API (http://api.reimaginebanking.com)  
* Firebase
	* Notifications
	* Auth UI
	* Realtime Database
* Card.IO - credit card visual scanning API

## Project Setup  

Project setup is easy.  See below for how to ensure all API integrations work as expected.

1. Clone this repo.  

`git clone https://github.com/android-summit/api-sample-app.git`  

2. Build and run the project in Android Studio.  

Note: Make sure you have the latest Google Play Services and Google Repository SDKs to ensure Firebase APIs work appropriately.  

## API Integrations  

### Nessie - Capital One's Hackathon API  

The Nessie API provides a virtual bank backend for use in your app.  

API Documentation - http://api.reimaginebanking.com/documentation  
Android SDK - https://github.com/nessieisreal/nessie-android-sdk  

For the Nessie API to work in the sample app:

1. Sign up for a Nessie API Key on http://api.reimaginebanking.com.  

2. Add the following entry to the project's `gradle.properties`.  

`NESSIE_API_KEY="<your_api_key_here>"`


### Firebase 

Firebase provides a host of different services for your Android app.  This sample app includes examples of using Firebase for:  

* Notifications
* Authentication w/ Google & associated Auth UI
* Realtime Databas  

Firebase - https://firebase.google.com/  
Android Getting Started Guide - https://firebase.google.com/docs/android/setup  

**To get Firebase working in the sample app:**  

1. Create a project with your Firebase account at http://firebase.google.com.  

2. Generate the `google-services.json` file and add it to this sample app.  

See the individual instructions below for each Firebase module.  The dependencies are already included in the module-level build.gradle file. A gradle sync should pull down everything you need.  

**Notifications**  

Notifications - https://firebase.google.com/docs/notifications/  

No additional setup necessary for Notifications.  Just head over to the Firebase console and try sending one to your app!

* `FirebaseMessagingActivity` - An instructional page which explains how to send a message to the app.  
* `FirebaseExampleMessagingService` - A service which intercepts and handles notifications when they are sent while the app is in the foreground.  

**Authentication**  

Firebase Auth - https://firebase.google.com/docs/auth/android/google-signin 
Auth UI - https://firebase.google.com/docs/auth/ 

Additional Setup:  

1. Enable Google Sign-In in the Firebase console for your app.

2. Add SHA1 fingerprint from your app in the Firebase Console.  See https://developers.google.com/drive/android/auth for instructions.

* `FirebaseAuthActivity` - Demonstrates sign in/sign out with the Google authentication provider.  

**Realtime Database**  

Realtime Database - https://firebase.google.com/docs/database/  

Additional Setup:  

1. Change permissions on your Firebase DB to public so you can store/retrieve items without logging in.  

To do this, go to "Database" tab on the Firebase console for your app.  Change the "Rules" to the following:  

```json
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```

* `FirebaseDatabaseActivity` - Demonstrates storing/retrieving items from a Firebase database and also subscribing to realtime updates to the data.


### Card.IO  

The Card.IO provides an API for scanning a credit card with an Android camera and pulling the details from it.  There is no special setup necessary to get the Card.IO API working in the app.  Just build the app onto your device and try it out!