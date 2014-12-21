package iisc.jaideep.sccsapp;

import android.provider.BaseColumns;

public final class SccsEventsContract {
    public static final  int    DATABASE_VERSION   = 2;
    public static final  String DATABASE_NAME      = "sccsEventsDatabase.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty private constructor.
    private SccsEventsContract() {}

    // table column names and create / delete commands
    public static abstract class Table1 implements BaseColumns {
        public static final String TABLE_NAME = "talks";
        public static final String COL_ID = "id";
        public static final String COL_1 = "day";
        public static final String COL_2 = "meal";
        public static final String COL_3 = "n";
        public static final String COL_4 = "next";

        public static final String CREATE_TABLE = 
        		"CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COL_ID + TEXT_TYPE + COMMA_SEP +
                COL_1 + TEXT_TYPE + COMMA_SEP +
                COL_2 + TEXT_TYPE + COMMA_SEP +
                COL_3 + TEXT_TYPE + COMMA_SEP +
                COL_4 + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = 
        		"DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}

