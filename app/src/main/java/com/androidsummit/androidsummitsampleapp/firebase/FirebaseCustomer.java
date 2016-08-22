package com.androidsummit.androidsummitsampleapp.firebase;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mike Ferraco (tld509) on 8/22/16.
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
