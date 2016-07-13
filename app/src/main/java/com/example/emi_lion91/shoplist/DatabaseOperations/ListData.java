package com.example.emi_lion91.shoplist.DatabaseOperations;

import android.provider.BaseColumns;

/**
 * Created by emi_lion91 on 6/19/2016.
 */
public class ListData {

    public ListData(){

    }

    public static abstract class TableInfo implements BaseColumns{
        public static final String ITEM = "item";
        public static final String DATABASE_NAME = "ItemDB";
        public static final String TABLE_NAME = "ShopList";
    }
}
