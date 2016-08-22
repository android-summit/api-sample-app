package com.androidsummit.androidsummitsampleapp.firebase.database;

import com.google.gson.annotations.SerializedName;

/**
 * A simple customer with an id, first name, and last name used to demonstrate Firebase Database fucntionality.
 */
public class FirebaseCustomer {

        @SerializedName("id")
        public String id;

        @SerializedName("firstName")
        public String firstName;

        @SerializedName("lastName")
        public String lastName;

        public FirebaseCustomer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public FirebaseCustomer(String id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
}
