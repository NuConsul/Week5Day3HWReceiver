package com.chemwater.week5day3hwreceiver;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String productName ;
    private String productIDNumber ;
    private String productInventoryCount ;
    private String productDescription ;
    private int userId ;

    protected User(Parcel in) {
        productName = in.readString();
        productIDNumber = in.readString();
        productInventoryCount = in.readString();
        productDescription = in.readString();

        userId = in.readInt() ;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User(String productName, String productIDNumber, String productInventoryCount, String productDescription) {
        this.productName = productName;
        this.productIDNumber = productIDNumber;
        this.productInventoryCount = productInventoryCount;
        this.productDescription = productDescription;
    }

    public User(String productName, String productIDNumber, String productInventoryCount, String productDescription, int userId) {
        this.productName = productName;
        this.productIDNumber = productIDNumber;
        this.productInventoryCount = productInventoryCount;
        this.productDescription = productDescription;
        this.userId = userId ;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeString(productIDNumber);
        parcel.writeString(productInventoryCount);
        parcel.writeString(productDescription);
        parcel.writeInt(userId) ;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIDNumber() {
        return productIDNumber;
    }

    public void setProductIDNumber(String productIDNumber) {
        this.productIDNumber = productIDNumber;
    }

    public String getProductInventoryCount() {
        return productInventoryCount;
    }

    public void setProductInventoryCount(String productInventoryCount) {
        this.productInventoryCount = productInventoryCount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public int getUserId() {
        return userId ;
    }

    public void setUserId(int userId) {
        this.userId = userId ;
    }
}