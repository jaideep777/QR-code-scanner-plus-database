package iisc.jaideep.sccsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class SetEventActivity extends MainActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// display
		setContentView(R.layout.activity_setevent);
	}

	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.day_radio1:
	            if (checked)
	                DatabaseHandler.curr_day = "25";
	            break;
	        case R.id.day_radio2:
	            if (checked)
	                DatabaseHandler.curr_day = "26";
	            break;
	        case R.id.day_radio3:
	            if (checked)
	                DatabaseHandler.curr_day = "27";
	            break;
	        case R.id.day_radio4:
	            if (checked)
	                DatabaseHandler.curr_day = "28";
	            break;
	        case R.id.meal_radioB:
	            if (checked)
	            	DatabaseHandler.curr_meal = "B";
	            break;
	        case R.id.meal_radioL:
	            if (checked)
	            	DatabaseHandler.curr_meal = "L";
	            break;
	        case R.id.meal_radioC:
	            if (checked)
	            	DatabaseHandler.curr_meal = "C";
	            break;
	        case R.id.meal_radioD:
	            if (checked)
	            	DatabaseHandler.curr_meal = "D";
	            break;
	    }
	    Log.d("RadioClick", "day: "+DatabaseHandler.curr_day+", meal: "+DatabaseHandler.curr_meal);
	    
	}	
	
//	public void onStartScanClicked(View view) {
//    	Intent intent = new Intent(this, ScanActivity.class);
//    	startActivity(intent);
//	}
}
