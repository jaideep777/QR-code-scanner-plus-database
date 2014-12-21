package iisc.jaideep.sccsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowCountsActivity extends MainActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		// display
		setContentView(R.layout.activity_showcounts);

        TextView t;
        int count = 0;
        
        //  25
        t = (TextView) findViewById(R.id.tbl_25B);
        Log.d("Map Size:", Integer.toString(DatabaseHandler.mealCounts.size()));
        count = DatabaseHandler.mealCounts.get("25B");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_25L);
        count = DatabaseHandler.mealCounts.get("25L");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_25C);
        count = DatabaseHandler.mealCounts.get("25C");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_25D);
        count = DatabaseHandler.mealCounts.get("25D");
        t.setText(Integer.toString(count));

        //  26
        t = (TextView) findViewById(R.id.tbl_26B);
        count = DatabaseHandler.mealCounts.get("26B");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_26L);
        count = DatabaseHandler.mealCounts.get("26L");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_26C);
        count = DatabaseHandler.mealCounts.get("26C");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_26D);
        count = DatabaseHandler.mealCounts.get("26D");
        t.setText(Integer.toString(count));

        //  27
        t = (TextView) findViewById(R.id.tbl_27B);
        count = DatabaseHandler.mealCounts.get("27B");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_27L);
        count = DatabaseHandler.mealCounts.get("27L");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_27C);
        count = DatabaseHandler.mealCounts.get("27C");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_27D);
        count = DatabaseHandler.mealCounts.get("27D");
        t.setText(Integer.toString(count));

        //  28
        t = (TextView) findViewById(R.id.tbl_28B);
        count = DatabaseHandler.mealCounts.get("28B");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_28L);
        count = DatabaseHandler.mealCounts.get("28L");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_28C);
        count = DatabaseHandler.mealCounts.get("28C");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_28D);
        count = DatabaseHandler.mealCounts.get("28D");
        t.setText(Integer.toString(count));


        
        
        //  25 n
        t = (TextView) findViewById(R.id.tbl_25Bn);
        count = DatabaseHandler.mealCounts.get("25Bn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_25Ln);
        count = DatabaseHandler.mealCounts.get("25Ln");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_25Cn);
        count = DatabaseHandler.mealCounts.get("25Cn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_25Dn);
        count = DatabaseHandler.mealCounts.get("25Dn");
        t.setText(Integer.toString(count));

        //  26
        t = (TextView) findViewById(R.id.tbl_26Bn);
        count = DatabaseHandler.mealCounts.get("26Bn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_26Ln);
        count = DatabaseHandler.mealCounts.get("26Ln");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_26Cn);
        count = DatabaseHandler.mealCounts.get("26Cn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_26Dn);
        count = DatabaseHandler.mealCounts.get("26Dn");
        t.setText(Integer.toString(count));

        //  27
        t = (TextView) findViewById(R.id.tbl_27Bn);
        count = DatabaseHandler.mealCounts.get("27Bn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_27Ln);
        count = DatabaseHandler.mealCounts.get("27Ln");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_27Cn);
        count = DatabaseHandler.mealCounts.get("27Cn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_27Dn);
        count = DatabaseHandler.mealCounts.get("27Dn");
        t.setText(Integer.toString(count));

        //  28
        t = (TextView) findViewById(R.id.tbl_28Bn);
        count = DatabaseHandler.mealCounts.get("28Bn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_28Ln);
        count = DatabaseHandler.mealCounts.get("28Ln");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_28Cn);
        count = DatabaseHandler.mealCounts.get("28Cn");
        t.setText(Integer.toString(count));

        t = (TextView) findViewById(R.id.tbl_28Dn);
        count = DatabaseHandler.mealCounts.get("28Dn");
        t.setText(Integer.toString(count));
        
        
	}
	
}
