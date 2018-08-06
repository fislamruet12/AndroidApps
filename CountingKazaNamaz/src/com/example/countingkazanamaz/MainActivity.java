package com.example.countingkazanamaz;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity{
 
	int keepTrack=0;
	private boolean select1,select2;
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Spinner spinner=(Spinner) findViewById(R.id.SelectYear);
		
		List<String> cataList =new ArrayList<String>();
		cataList.add("FAZAR");
		cataList.add("ZAHAR");
		cataList.add("ASAR");
		cataList.add("MAGRIB");
		cataList.add("ESHA");
		cataList.add("BETER");
		select1=true;
		select2=true;
		ArrayAdapter< String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cataList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		
		

		Spinner spinner2=(Spinner) findViewById(R.id.SelectWakt);
		
		
		List<String> cataList2 =new ArrayList<String>();
		for(int i=1;i<=35;i++)
		cataList2.add(""+i);
		
		ArrayAdapter< String> dataAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cataList2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter2);
		
		
		

		final GetData data = new GetData(null,null);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String itemString=parent.getItemAtPosition(position).toString();
				
			if(select1)
			{
				select1=false;
				data.setWakt(itemString);
			}else 
				{	
					//Toast.makeText(parent.getContext(),"selected "+itemString,Toast.LENGTH_LONG).show();
				data.setWakt(itemString);
//				if(!select2)
//				{
//					
//					Intent intent=new Intent(MainActivity.this,SEcondMainActivity.class);
//				     startActivity(intent);
//				}
				}
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				String itemString=parent.getItemAtPosition(position).toString();
				if(select2){
					select2=false;
				 data.setYear(itemString);}
					else {
						data.setYear(itemString);
//						if(!select1)
//						{
//							
//							Intent intent=new Intent(MainActivity.this,SEcondMainActivity.class);
//						     startActivity(intent);
//						}
					}
				
		
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		findViewById(R.id.go).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			     Intent intent=new Intent(MainActivity.this,SEcondMainActivity.class);
			     startActivity(intent);
			}
		});
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
			
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_Resets)
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

