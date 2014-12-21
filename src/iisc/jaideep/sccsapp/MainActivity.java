package iisc.jaideep.sccsapp;

import java.io.File;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	static boolean firstEntry = true;
	
	/**
	 * Call this method to delete any cache created by app
	 * @param context context for your application
	 */
	public static void clearApplicationData(Context context) {
	    File cache = context.getCacheDir();
	    File appDir = new File(cache.getParent());
	    if (appDir.exists()) {
	        String[] children = appDir.list();
	        for (String s : children) {
	            File f = new File(appDir, s);
	            if(deleteDir(f))
	                Log.d("", String.format("* DELETED -> (%s) *", f.getAbsolutePath()));
	        }
	    }
	    File dbDir = new File(context.getExternalFilesDir(null).getAbsolutePath());
	    if (dbDir.exists()){
	        String[] children = dbDir.list();
	        for (String s : children) {
	            File f = new File(dbDir, s);
	            if(deleteDir(f))
	                Log.d("", String.format("* DELETED -> (%s) *", f.getAbsolutePath()));
	        }
	    }
	}
	private static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    return dir.delete();
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "Entered");

        // retrieve counts from saved shared preferences
        SharedPreferences mapCounts = getSharedPreferences("CountsFile", 0);
        for (String s : new String[]{"B","L","C","D"}){
      	  for (int i=25; i<29; ++i){
      		  String DT = Integer.toString(i)+s;
      		  DatabaseHandler.mealCounts.put(DT, mapCounts.getInt(DT, 0));
      		  DT = DT+"n";
      		  DatabaseHandler.mealCounts.put(DT, mapCounts.getInt(DT, 0));
      	  }
        }
        
        TextView t;
        
        t = (TextView) findViewById(R.id.main_textView_date);
        t.setTextColor(Color.BLUE);
        t.setText(DatabaseHandler.curr_day);

        t = (TextView) findViewById(R.id.main_textView_time);
        t.setTextColor(Color.BLUE);
        String timeStr = "";
        if 		 (DatabaseHandler.curr_meal == "B") timeStr = "Breakfast";
        else if (DatabaseHandler.curr_meal == "L") timeStr = "Lunch";
        else if (DatabaseHandler.curr_meal == "C") timeStr = "Coffee";
        else if (DatabaseHandler.curr_meal == "D") timeStr = "Dinner";
        t.setText(timeStr);
        
        // set listeners for the buttons
        Button button = (Button) findViewById(R.id.button_main_setevent);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setEvent();
            }
        });

    }

    @Override
    protected void onStop(){
       super.onStop();

       Log.d("MainActivity", "stop");	
       //
//      // We need an Editor object to make preference changes.
//      // All objects are from android.context.Context
//      SharedPreferences mapCounts = getSharedPreferences("CountsFile", 0);
//      SharedPreferences.Editor editor = mapCounts.edit();
//      
//      for (String s : new String[]{"B","L","C","D"}){
//    	  for (int i=25; i<29; ++i){
//    		  String DT = Integer.toString(i)+s;
//    		  editor.putInt(DT, DatabaseHandler.mealCounts.get(DT));
//    		  DT = DT+"n";
//    		  editor.putInt(DT, DatabaseHandler.mealCounts.get(DT));
//    		  Log.d("Main: Save", DT+":"+Integer.toString(DatabaseHandler.mealCounts.get(DT)));
//    	  }
//      }
//      // Commit the edits!
//      editor.commit();
    }
    
	public void onStartScanClicked(View view) {
    	Intent intent = new Intent(this, ScanActivity.class);
    	startActivity(intent);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){

        case R.id.action_set_event:
        	setEvent();
            return true;
        case R.id.action_delete_db:
        	
        	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
	    	dlgAlert.setMessage("Delete and Rebuild Database?");
	    	dlgAlert.setTitle("Confirm");
	    	dlgAlert.setCancelable(true);
	    	dlgAlert.setPositiveButton("Delete",
			    new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	MainActivity.clearApplicationData(MainActivity.this);
			        	DatabaseHandler db = new DatabaseHandler(MainActivity.this);
			        	SQLiteDatabase dbase = db.getWritableDatabase();	// force creation of new database
			        	dbase.close();
			        	
			        	// reset counts to zero.
			  	        SharedPreferences mapCounts = getSharedPreferences("CountsFile", 0);
			  	        SharedPreferences.Editor editor = mapCounts.edit();

				  	    for (String s : new String[]{"B","L","C","D"}){
					  	  for (int i=25; i<29; ++i){
					    		String DT = Integer.toString(i)+s;
					    		editor.putInt(DT, DatabaseHandler.mealCounts.get(DT));
					    		DT = DT+"n";
					    		editor.putInt(DT, DatabaseHandler.mealCounts.get(DT));
					    		//Log.d("ScanActivity Save", DT+":"+Integer.toString(DatabaseHandler.mealCounts.get(DT)));
					    	}
					    }
					    // Commit the edits!
					    editor.commit();
					    Log.d("ResetDB Save", "25B:"+Integer.toString(DatabaseHandler.mealCounts.get("25B")));
			        }
			    });
	    	dlgAlert.setNegativeButton("Cancel",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				        }
				    });
	    	dlgAlert.create().show();

        	return true;
        case R.id.action_logcat_db:
        	DatabaseHandler db1 = new DatabaseHandler(this);
        	db1.displayDatabase();
        	db1.close();
        	return true;
        case R.id.action_home:
        	Intent intent = new Intent(this, MainActivity.class);
        	startActivity(intent);
            return true;
	    case R.id.action_show_counts:
	    	Intent intent1 = new Intent(this, ShowCountsActivity.class);
	    	startActivity(intent1);
	        return true;
	    }
        return super.onOptionsItemSelected(item);
        
    }
    
    protected void setEvent(){
    	Intent intent = new Intent(this, SetEventActivity.class);
    	startActivity(intent);
    }    

    
    
}
