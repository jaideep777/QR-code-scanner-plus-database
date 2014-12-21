package iisc.jaideep.sccsapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {
	
	public static String curr_day = "25";
	public static String curr_meal = "L";

	public static Map <String, Integer> mealCounts = new HashMap<String,Integer>();
	
	// context.getExternalFilesDir(null).getAbsolutePath() + "/" +   <--- add this for sdcard path.
    public DatabaseHandler(Context context) {
    	super(context, context.getExternalFilesDir(null).getAbsolutePath() + "/" + SccsEventsContract.DATABASE_NAME, null, SccsEventsContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SccsEventsContract.Table1.CREATE_TABLE);
  
        // create empty counts map
   	    for (int i=25; i<29; ++i){
   	    	for (String s : new String[]{"B","L","C","D"}){
	  		    // set current session count to 0
   	    		String DT = Integer.toString(i)+s;
	  		    DatabaseHandler.mealCounts.put(DT, 0);
	  		    Log.d("Map", DT+": "+DatabaseHandler.mealCounts.get(DT));

	  		    // set next session count to 0
	  		    DT = DT+"n";
	    		DatabaseHandler.mealCounts.put(DT, 0);
	  		    Log.d("Map", DT+": "+DatabaseHandler.mealCounts.get(DT));
	  	    }
	    }

        // Insert events for the first time
        Log.d("OnCreate ", "Inserting entries...");
        for (int i=100001; i<100700; ++i){
        	for (int day=25; day<29; ++day){
                addEvent(db, new Event(Integer.toString(i),Integer.toString(day), "B", "0"));       
                addEvent(db, new Event(Integer.toString(i),Integer.toString(day), "L", "0"));       
                addEvent(db, new Event(Integer.toString(i),Integer.toString(day), "C", "0"));       
                addEvent(db, new Event(Integer.toString(i),Integer.toString(day), "D", "0"));       
        	}
        }
        Log.d("OnCreate: ", "Done!");
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SccsEventsContract.Table1.DELETE_TABLE);
        onCreate(db);
    }

    // print out database in log
	public void displayDatabase(){
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Event> eventList = getAllEvents();      
         
        for (Event cn : eventList) {
            String log = "Id: "+cn.getID()+" ,Day: " + cn.getDay() + " ,Meal: " + cn.getMeal() + " ,N: " + cn.getN()  + " ,next: " + cn._next;
            Log.d("Name: ", log);
        }
	}
	
   
    // Add a new event to an already open database
	// this function needs an open database because it may be called within onCreate
    public void addEvent(SQLiteDatabase db, Event e) {
        // SQLiteDatabase db = this.getWritableDatabase();
        
        // create row with values from event e
        ContentValues values = new ContentValues();
        values.put(SccsEventsContract.Table1.COL_ID, e.getID());
        values.put(SccsEventsContract.Table1.COL_1, e._day); 
        values.put(SccsEventsContract.Table1.COL_2, e._meal); 
        values.put(SccsEventsContract.Table1.COL_3, e._n); 
        values.put(SccsEventsContract.Table1.COL_4, e._next); 
             
        // Insert row
        db.insert(SccsEventsContract.Table1.TABLE_NAME, null, values);

        //db.close(); // Closing database connection <-- DONT CLOSE
    }
    
    // get the "n" and "next" fields for an (id, day, meal) triplet
    public Cursor queryById(String p_id){ 
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("Quering","...");
        String whereClause = SccsEventsContract.Table1.COL_ID + "=? AND " + SccsEventsContract.Table1.COL_1 + "= ? AND " + SccsEventsContract.Table1.COL_2 + "= ?";
    	String whereArgs[] = {p_id, curr_day, curr_meal}; 
        Cursor cursor = db.query(SccsEventsContract.Table1.TABLE_NAME, 
        						 null, // new String[] {SccsEventsContract.Table1.COL_3, SccsEventsContract.Table1.COL_4}, 
        						 whereClause, whereArgs, 
        						 null, null, null, null);

        if (cursor.moveToFirst()) {
        	Log.d(new String(curr_day+" "+curr_meal+": "), new String(cursor.getString(4)+" "+cursor.getString(5)) );
        // get row with COL_ID id
        }
        else {
        	Log.d("Fatal Error", new String(curr_day+" "+curr_meal+": " + " not found!"));
        }
        db.close();
        return cursor;
    }
    
    // Updating single event
    int updateEvent(String p_id, String lnext){
        // open database
    	SQLiteDatabase db = this.getWritableDatabase();

        // format whereClause
    	String whereClause = SccsEventsContract.Table1.COL_ID + "=? AND " + SccsEventsContract.Table1.COL_1 + "= ? AND " + SccsEventsContract.Table1.COL_2 + "= ?";
    	String whereArgs[] = {p_id, curr_day, curr_meal}; 

    	// Query and get "n" and "next" values 
        Log.d("Quering","...");
        Cursor cursor = db.query(SccsEventsContract.Table1.TABLE_NAME, 
        						 null, // new String[] {SccsEventsContract.Table1.COL_3, SccsEventsContract.Table1.COL_4}, 
        						 whereClause, whereArgs, 
        						 null, null, null, null);

        // if Query is successful, update values
        int k=-1;
        if (cursor.moveToFirst()) {
        	Log.d(new String(curr_day+" "+curr_meal+": "), new String(cursor.getString(4)+" "+cursor.getString(5)) );
        	Log.d("Info", "incrementing value");
        	ContentValues cv = new ContentValues();
            cv.put(SccsEventsContract.Table1.COL_3, Integer.toString(Integer.parseInt(cursor.getString(4))+1) );
            cv.put(SccsEventsContract.Table1.COL_4, lnext );

            db.update(SccsEventsContract.Table1.TABLE_NAME, 
            		  cv, 
            		  whereClause, whereArgs);

            // increment map counts
            String DT = new String(curr_day+curr_meal);
            mealCounts.put(DT, mealCounts.get(DT)+1);
            
            if (ScanActivity.attendingNext == "Y"){
	            DT = new String(curr_day+curr_meal+"n");
	    		mealCounts.put(DT, mealCounts.get(DT)+1);
	            Log.d("Info", "updated Row:");
            }
            
        	queryById(p_id);
            k=0;
        }
        else {
        	
        	Log.d("Fatal Error", new String("QR ID: "+p_id+", "+curr_day+" "+curr_meal+": " + " not found!"));
        	k=1;
        }
        db.close();
        return k;
    }
    
    
    // Getting All events
    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SccsEventsContract.Table1.TABLE_NAME;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event e = new Event();
                e.setID(cursor.getString(1));
                e._day =cursor.getString(2);
                e._meal = cursor.getString(3);
                e._n = cursor.getString(4);
                e._next = cursor.getString(5);
                // Adding event to list
                eventList.add(e);
            } while (cursor.moveToNext());
        }
 
        db.close();
        // return event list
        return eventList;
    }
     

}

