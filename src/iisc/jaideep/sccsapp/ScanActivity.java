package iisc.jaideep.sccsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class ScanActivity extends Activity{
	
	public static String attendingNext = "";
	public static String result_full = "";
	public static String result = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
    	Intent qrDroid = new Intent("la.droid.qr.scan");
    	qrDroid.putExtra("la.droid.qr.complete", true);
    	startActivityForResult(qrDroid,0);

	}

    @Override
    protected void onStop(){
	      super.onStop();
	
	      // We need an Editor object to make preference changes.
	      // All objects are from android.context.Context
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
	      Log.d("ScanActivity Save", "25B:"+Integer.toString(DatabaseHandler.mealCounts.get("25B")));
    }

	protected void updateAndRestartScan(){

		// update database
    	Log.d("Scanned:", result);
    	DatabaseHandler db = new DatabaseHandler(this);
    	int k = db.updateEvent(result, ScanActivity.attendingNext);
    	db.close();
	
    	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
    	
    	if (k !=0){
	    	dlgAlert.setMessage("Invalid QRCode: "+result);
	    	dlgAlert.setTitle("Error!");
	    	dlgAlert.setCancelable(true);
	    	dlgAlert.setPositiveButton("Ok",
			    new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	// restart the scan activity
			        	Intent intent = new Intent(ScanActivity.this, ScanActivity.class);
			        	startActivity(intent);
			        }
			    });
	    	dlgAlert.create().show();
    	}
    	else{
        	// restart the scan activity
        	Intent intent = new Intent(ScanActivity.this, ScanActivity.class);
        	startActivity(intent);
    	}
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	
    	Log.d("ScanResult", Integer.toString(resultCode));
    	if (resultCode == RESULT_CANCELED){	// e.g. back button was pressed in QrDroid
	    	Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
    	}
    	else{	// assume scan successful. update db and restart scan
	    	
    		result_full = data.getExtras().getString("la.droid.qr.result");
    		// result is expected to be a URL <-- extract QR code 
    		result = result_full.substring(Math.max(result_full.length()-6,0), result_full.length());
    		
	        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                switch (which){
	                	case DialogInterface.BUTTON_POSITIVE:
	                		//Yes button clicked
	                		ScanActivity.attendingNext = "Y";
	                		updateAndRestartScan();
	        	    	break;

	                	case DialogInterface.BUTTON_NEGATIVE:
	                		//No button clicked
	                		ScanActivity.attendingNext = "N";
	                		updateAndRestartScan();
	                    break;
	                }
	            }
	        };

	        AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this);
	        builder.setMessage("Coming for next Meal?")
	        		.setPositiveButton("Yes", dialogClickListener)
	        		.setNegativeButton("No", dialogClickListener)
	        		.show();
    	}   
    	
    	
    }    
	

}
