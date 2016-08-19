package com.androidsummit.androidsummitsampleapp.apimenu;

/**
 * An object representing an item in the API Menu.
 */
public class ApiMenuItem {

    private String mTitle;
    private int mImageResource;

    public ApiMenuItem(Builder builder) {
        mTitle = builder.bTitle;
        mImageResource = builder.bImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public static class Builder {

        private String bTitle;
        private int bImage;

        public Builder title(String title) {
            bTitle = title;
            return this;
        }

        public Builder imageResource(int imageResource) {
            bImage = imageResource;
            return this;
        }

        public ApiMenuItem build() {
            return new ApiMenuItem(this);
        }
    }

}
