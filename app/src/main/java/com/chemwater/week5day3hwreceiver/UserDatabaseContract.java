package com.chemwater.week5day3hwreceiver;

import android.util.Log ;
import java.util.Locale ;

public class UserDatabaseContract {
    //Database name and default version
    public static final String DATABASE_NAME = "user_db" ;
    public static final int DATABASE_VERSION = 1 ;

    //Database table name
    public static final String TABLE_NAME = "Users" ;

    //Columns in database names
    public static final String COLUMN_NAME = "name" ;
    public static final String COLUMN_ID_NUMBER = "product_id" ;
    public static final String COLUMN_INVENTORY_COUNT = "inventory_count" ;
    public static final String COLUMN_DESCRIPTION = "description" ;
    public static final String COLUMN_ID = "id";



    //Create the table query for the database
    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder() ;
        //Query to create Table
        queryBuilder.append("CREATE TABLE ") ;
        queryBuilder.append(TABLE_NAME) ;
        queryBuilder.append(" ( ") ;
        //Must have unique primary key
        queryBuilder.append(COLUMN_ID) ;
        queryBuilder.append(" ") ;
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ") ;
        //Add rest of the columns
        queryBuilder.append(COLUMN_NAME) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_ID_NUMBER) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_INVENTORY_COUNT) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_DESCRIPTION) ;
        queryBuilder.append(" TEXT, ") ;

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString()) ;

        return queryBuilder.toString() ;
    }

    public static String getAllUsersQuery() {
        return "SELECT * FROM " + TABLE_NAME ;
    }

    public static String getOneUserById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id) ;
    }



    public static String getWhereClauseById() {
        return String.format(Locale.US, "%s = ", COLUMN_ID) ;
    }

}