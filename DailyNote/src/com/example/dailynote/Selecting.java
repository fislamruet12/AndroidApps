 package com.example.dailynote;

import java.sql.Date;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Selecting extends Activity {
 
	 private Context context=this;
	TextView Income,cost,lone,advance;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecting);
		 
		
		Income=(TextView) findViewById(R.id.Income);
		Income.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent;
				intent=new Intent(Selecting.this,Income.class);
				String st="farid";
				intent.putExtra("PERSON_NAME",""+1);
				startActivity(intent);
				
			} 
		});
		cost=(TextView) findViewById(R.id.Cost);
		cost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent;
				intent=new Intent(Selecting.this,Income.class);
				intent.putExtra("PERSON_NAME",""+2);
				startActivity(intent);
				
			} 
		});
		lone=(TextView) findViewById(R.id.Lone);
		lone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent;
				intent=new Intent(Selecting.this,LoneAndSavings.class);
				intent.putExtra("PERSON_NAME",""+3);
				startActivity(intent);
				
			} 
		});
		advance=(TextView) findViewById(R.id.Advance);
		advance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent;
				intent=new Intent(Selecting.this,LoneAndSavings.class);
				intent.putExtra("PERSON_NAME",""+4);
				startActivity(intent);
				
			} 
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selecting, menu);
	     
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings)
        {
        	LayoutInflater li1 = LayoutInflater.from(context);
			View promptsView1 = li1.inflate(R.layout.about, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setView(promptsView1);
		
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
				

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
       return false;
		}

        return super.onOptionsItemSelected(item);
    }
	
}
