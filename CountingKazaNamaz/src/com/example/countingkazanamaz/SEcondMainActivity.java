package com.example.countingkazanamaz;






import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SEcondMainActivity extends Activity {

	GetData db1 = new GetData(null,null);
	TextView txt,txt1,txt2;
	private DBAdapter dbAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_main);
		final String string=db1.getYear();
		final String string2=db1.getWakt();
		//Toast.makeText(this,"selected "+string+" "+string2,Toast.LENGTH_LONG).show();
		dbAdapter=new DBAdapter(this);
		txt=(TextView) findViewById(R.id.NameOfWakt);
		txt2=(TextView) findViewById(R.id.remain);

		txt1=(TextView) findViewById(R.id.TotalWAktofKazaNamaz);
	    txt.setText(string2);
	   
	    dbAdapter.open();
		final int n=dbAdapter.getQuary(string,string2);
		//Toast.makeText(this, "n = "+n, Toast.LENGTH_LONG).show();
		if(n>0){
		txt1.setText(""+n);
		txt2.setText("Kaza Wakt's Remain "+(365-n));
	}
		else {
			txt1.setText("0");
			txt2.setText("Kaza Wakt's Remain "+(365));
			
			
		}
		
		dbAdapter.close();
	 
	    findViewById(R.id.Inc).setOnClickListener(new OnClickListener() {
	    		@Override
			public void onClick(View arg0) {
				
				String title = string;
				String author =string2;
				String category = "12";
				String ISBN = "12";
				double price = Double.parseDouble("12");

				dbAdapter.open();
				dbAdapter.insertBook(new Book(title, author,
						category, ISBN, price));
				 String tpp=txt1.getText().toString();
		    	 int tp=(int) Double.parseDouble(tpp);
			
				txt1.setText(""+(tp+1));
				txt2.setText("Kaza Wakt's Remain "+(365-tp-1));
				dbAdapter.close();
				Toast.makeText(getApplication(), "Alhamdulillah "+(tp+1)+" Kaza Wakt's is Completed", Toast.LENGTH_LONG).show();
				
				
			}
		});
	    
	    findViewById(R.id.Dec).setOnClickListener(new OnClickListener() {
	   
			@Override
			public void onClick(View arg0) {
				
				dbAdapter.open();
			dbAdapter.delQuary(string,string2);
			 dbAdapter.close();
			 String tString=txt1.getText().toString();
			 int value=(int) Double.parseDouble(tString);
			 if(value>0){
					txt1.setText(""+(value-1));
					txt2.setText("Kaza Wakt's Remain "+(365-(value-1)));
					
			 }
				Toast.makeText(getApplication(), "Thanks for Correction ", Toast.LENGTH_LONG).show();
						
		
			}
		});
		
		 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second_main, menu);
		return true;
	}

}
