package com.chemwater.week5day3hwreceiver;

import android.content.ContentValues ;
import android.content.Context ;
import android.database.Cursor ;
import android.database.sqlite.SQLiteDatabase ;
import android.database.sqlite.SQLiteOpenHelper ;
import android.support.annotation.NonNull ;
import android.support.annotation.Nullable ;

import java.util.ArrayList ;
import java.util.Locale ;

import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.COLUMN_ID;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.COLUMN_NAME;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.COLUMN_ID_NUMBER;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.COLUMN_INVENTORY_COUNT;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.COLUMN_DESCRIPTION;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.DATABASE_NAME;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.DATABASE_VERSION;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.TABLE_NAME;
import static com.chemwater.week5day3hwreceiver.UserDatabaseContract.getWhereClauseById;




public class UserDatabaseHelper extends SQLiteOpenHelper {

    //Constructor for
    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
    }

    //create the tables(will run only once per install)
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UserDatabaseContract.createQuery()) ;
    }

    //If version database changes, make adjustments here
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }

    //Insert a user into the database
    public long insertUserIntoDatabase(@NonNull User user) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase() ;
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues() ;

        //insert key value pairs into the contentValues container
        contentValues.put(COLUMN_NAME, user.getProductName()) ;
        contentValues.put(COLUMN_ID_NUMBER, user.getProductIDNumber()) ;
        contentValues.put(COLUMN_INVENTORY_COUNT, user.getProductInventoryCount()) ;
        contentValues.put(COLUMN_DESCRIPTION, user.getProductDescription()) ;


        //insert the user into the table using contentValues
        return writeableDatabase.insert(TABLE_NAME, null, contentValues) ;
    }


    //Get all Users from Database and return an ArrayList
    public ArrayList<User> getAllUsersFromDatabase() {
        ArrayList<User> returnUserList = new ArrayList<>() ;
        SQLiteDatabase readableDatabase = this.getReadableDatabase() ;
        //Get results from query and hold in cursor(iterable object for database operations
        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getAllUsersQuery(), null) ;
        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)) ;
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)) ;
                String productIDNumber = cursor.getString(cursor.getColumnIndex(COLUMN_ID_NUMBER)) ;
                String inventoryCount = cursor.getString(cursor.getColumnIndex(COLUMN_INVENTORY_COUNT)) ;
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)) ;

                //add to list
                returnUserList.add(new User(name, productIDNumber, inventoryCount, description, id)) ;
            } while (cursor.moveToNext()) ;
            //return the result in a list
        }
        cursor.close() ;
        return returnUserList ;
    }




    //Get one entry from database
    public User getUserById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase() ;
        //User to return
        User returnUser = new User() ;
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getOneUserById(id), null) ;
        if(cursor.moveToFirst()) {
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)) ;
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)) ;
            String productIDNumber = cursor.getString(cursor.getColumnIndex(COLUMN_ID_NUMBER)) ;
            String inventoryCount = cursor.getString(cursor.getColumnIndex(COLUMN_INVENTORY_COUNT)) ;
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)) ;

            //set return user
            returnUser = new User(name, productIDNumber, inventoryCount, description, idFromDB) ;
        }
        cursor.close() ;
        return returnUser ;
    }



    //update an item in the database
    public long updateUserInDatabase(@NonNull User newUserInfo) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase() ;
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues() ;

        //insert key value pairs into the contentValues container
        contentValues.put(COLUMN_NAME, newUserInfo.getProductName()) ;
        contentValues.put(COLUMN_ID_NUMBER, newUserInfo.getProductIDNumber()) ;
        contentValues.put(COLUMN_INVENTORY_COUNT, newUserInfo.getProductInventoryCount()) ;
        contentValues.put(COLUMN_DESCRIPTION, newUserInfo.getProductDescription()) ;
        return writeableDatabase.update(TABLE_NAME,
                contentValues,
                getWhereClauseById(),
                new String[]{String.valueOf(newUserInfo.getUserId())}) ;
    }


    //delete entry(ies) from the database
    public long deleteFromDatabaseById(String[] id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        return sqLiteDatabase.delete(TABLE_NAME, getWhereClauseById() + id[0], null) ;
    }


}