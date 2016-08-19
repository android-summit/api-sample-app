package com.androidsummit.androidsummitsampleapp.apimenu;

/**
 * An object representing an item in the API Menu.
 */
public class ApiMenuItem {

    private String mTitle;
    private int mImageResource;
    private String mNavigationClass;

    public ApiMenuItem(Builder builder) {
        mTitle = builder.bTitle;
        mImageResource = builder.bImage;
        mNavigationClass = builder.bNavigationClass;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getNavigationClass() {
        return mNavigationClass;
    }

    public static class Builder {

        private String bTitle;
        private int bImage;
        private String bNavigationClass;

        public Builder title(String title) {
            bTitle = title;
            return this;
        }

        public Builder imageResource(int imageResource) {
            bImage = imageResource;
            return this;
        }

        public Builder navigationClass(String navigationClass) {
            bNavigationClass = navigationClass;
            return this;
        }

        public ApiMenuItem build() {
            return new ApiMenuItem(this);
        }
    }

}
