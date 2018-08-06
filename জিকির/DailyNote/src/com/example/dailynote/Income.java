 package com.example.dailynote;

import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.R.anim;
import android.R.color;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("SimpleDateFormat")
public class Income extends Activity {
	static int checking=0;
  int month=0;
  int ch=0;
	ListView incomeListView;
	EditText editincome;
	Button addincome;
	ArrayList<IncomelistviewClass> incomeArray;

	int counter=0;
	BaseAdapter adapter;
	private DBAdapter dbAdapter;
	ArrayList<GetidEarnAndCost> arrayList=new ArrayList<GetidEarnAndCost>();
	private Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);
		
		String string=getIntent().getStringExtra("PERSON_NAME");
		int n =Integer.parseInt(string.toString());
		dbAdapter=new DBAdapter(this);
		//arrayList=new ArrayList<EarnAndCost>();
		checking=n;
		
		Initialize();
		String s1,s2;
		if(n==1){
			setTitle("Income");
			
			try {
		          dbAdapter.open();
		          
		          arrayList=dbAdapter.getAllEarn();
		        //  Toast.makeText(getBaseContext(), "  "+arrayList.size(), Toast.LENGTH_LONG).show();
					
		          calculateFromDb();
		          dbAdapter.close();
				
				}
			 catch (Exception e) {
				// TODO: handle exception
			}
	}
			
		else if(n==2) {
			setTitle("Daily Cost ");
			try {
				dbAdapter.open();
				arrayList=dbAdapter.getAllCost();
				calculateFromDb();
				dbAdapter.close();
				}
			 catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		//setTitle("Daily Cost");
	}
	public static Comparator<IncomelistviewClass> comparator =new Comparator<IncomelistviewClass>() {

		@Override
		public int compare(IncomelistviewClass date1, IncomelistviewClass date2) {
			return date2.getDate().compareTo(date1.getDate());
		}

	};
private void Initialize() {
	
	  incomeListView=(ListView) findViewById(R.id.incomelistview);
	  editincome=(EditText) findViewById(R.id.editincome);
	  addincome=(Button) findViewById(R.id.Addincome);
	  
	  incomeArray=new ArrayList<IncomelistviewClass>();
	 	final int[] a=new int[10000];
	 	
	  adapter=new BaseAdapter() {
		 LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		@Override
		public View getView(int pos, View view, ViewGroup viewGroup) {
	try {
		  
		if(view==null){
	    	view=inflater.inflate(R.layout.income_listview,null);
		}
		
		TextView date=(TextView) view.findViewById(R.id.IncomeDate);
		TextView taka=(TextView) view.findViewById(R.id.Incometaka);
		
		
		
		
		//Toast.makeText(getBaseContext(),""+incomeArray.get(pos).getTaka().toString(),Toast.LENGTH_SHORT).show();

	    taka.setText(incomeArray.get(pos).getTaka().toString()+"\n");
		Calendar date1=incomeArray.get(pos).getDate();
		date.setText(DateFormat.format("dd/MM/yyyy\nHH:mm::ss a",date1.getTime()));

int check=incomeArray.get(pos).getCheck();
if(check==1)
{
	
	taka.setBackgroundColor(Color.DKGRAY);
	date.setBackgroundColor(Color.DKGRAY);
	taka.setTextColor(Color.RED);
	date.setTextColor(Color.RED);
	//incomeListView.getChildAt(pos).setEnabled(false);
    a[pos]=pos;
}else {
	taka.setBackgroundColor(Color.BLACK);
	date.setBackgroundColor(Color.BLACK);
	taka.setTextColor(Color.GREEN);
	date.setTextColor(Color.GREEN);

     }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return view;
		}
		
		@Override
		public long getItemId(int pos) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Object getItem(int pos) {
			// TODO Auto-generated method stub
			return incomeArray.get(pos);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return incomeArray.size();
		}
	};
	
	
	incomeListView.setAdapter(adapter);
	
	try {
		addincome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
			try
			{
				
				InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(editincome.getWindowToken(),0);
				
				
				
				String taka=editincome.getText().toString();
					double d=Double.parseDouble(taka.toString());
					
				if(d>0 ){
					
				
					Calendar cal=Calendar.getInstance();
					
					editincome.setText("");
					  
					 
					  // Toast.makeText(getBaseContext(), "taka1 "+taka,Toast.LENGTH_LONG).show();
						
					if(checking==1){
					dbAdapter.open();				
					long mil=cal.getTimeInMillis();
					String s=Long.toString(mil);
					//Toast.makeText(getBaseContext(), "taka2 "+taka,Toast.LENGTH_LONG).show();
					
					long lval=dbAdapter.EarnInsert(new EarnAndCost(taka,s));
					
				     arrayList=dbAdapter.getAllEarn();
				     calculateFromDb();
					
					}else {
						dbAdapter.open();				
						long mil=cal.getTimeInMillis();
						String s=Long.toString(mil);
						long lval=dbAdapter.CostInsert(new EarnAndCost(taka,s));
					//	Toast.makeText(getBaseContext(), lval+"  "+checking, Toast.LENGTH_LONG).show();
					arrayList=dbAdapter.getAllCost();
					calculateFromDb();
					}
						dbAdapter.close();
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			}
		});
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	
	incomeListView.setOnItemLongClickListener(new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				final int pos, long arg3) {
			int ch1=incomeArray.get(pos).getCheck();
			  if (ch1==0) {
				
			
			//AlertDialog.Builder alBuilder=new AlertDialog.Builder(context);
			   AlertDialog.Builder setPositiveButton = new AlertDialog.Builder(context).setTitle("Confirm Delete")
			    .setMessage(""+incomeArray.get(pos).getTaka().toString())
			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						 Calendar calendar=incomeArray.get(pos).getDate();
						long val=calendar.getTimeInMillis();
					  
						try {
							
							if(incomeArray.size()==1)
							{
								incomeArray.remove(pos);
						        adapter.notifyDataSetChanged();
							}
							if(checking==1)
							{
								dbAdapter.open();
								dbAdapter.DeletebydateE("EARN",val);
							   arrayList=dbAdapter.getAllEarn();
							   calculateFromDb();
							   dbAdapter.close();
							
							}else {
								dbAdapter.open();
								dbAdapter.DeletebydateC("COST",val);
							   arrayList=dbAdapter.getAllCost();
							   calculateFromDb();
							   dbAdapter.close();
								
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					
						
					}
				});
			  AlertDialog alertDialog=setPositiveButton.create();
			  alertDialog.show();
			  }
			return false;
		
		}
	});
	
	
	
}
public void calculateFromDb()
{
	String s0,s1,s2;
ArrayList<IncomelistviewClass> tempincomearray;
tempincomearray=new ArrayList<IncomelistviewClass>();
	
	try {
		//incomeArray.clear();
		incomeArray.clear();
		for(int ll=0;ll<arrayList.size();ll++)
		{
			 s0=arrayList.get(ll).getId().toString();
			 s1=arrayList.get(ll).getDate().toString();
			 s2=arrayList.get(ll).getTaka();
			
			 	long mils=Long.parseLong(s1);
			 	Calendar ca1=Calendar.getInstance();
			 	ca1.setTimeInMillis(mils);
			 	IncomelistviewClass incomelist1=new IncomelistviewClass(s0,s2,ca1,0);
				tempincomearray.add(incomelist1);
			
			 
		}
		arrayList.clear();
		Collections.sort(tempincomearray,comparator);
		
		Calendar c1,c2;
		int dv1=0,dv2=0;
		double sum=0;
		
		if(tempincomearray.size()==1)
		{

			 c1=tempincomearray.get(0).getDate();
			String va2=tempincomearray.get(0).getTaka().toString();
			String va1=tempincomearray.get(0).getId().toString();
			IncomelistviewClass incomelistviewClass=new IncomelistviewClass(va1,va2,c1,0);
			incomeArray.add(incomelistviewClass);
			
		}
		for (int i = tempincomearray.size()-1; i >=1; i--) {
			
		
		   c1=tempincomearray.get(i-1).getDate();
		   c2=tempincomearray.get(i).getDate();
	       String d1,d2;
		d1=(String) DateFormat.format("MM",c1.getTime());
		d2=(String) DateFormat.format("MM",c2.getTime());
		
		dv1=Integer.parseInt(d1);
		dv2=Integer.parseInt(d2);
		
		

		if(dv1==dv2)
		{
		
			
			String va2=tempincomearray.get(i).getTaka().toString();
			String va1=tempincomearray.get(0).getId().toString();
			sum+=Double.parseDouble(va2);
			IncomelistviewClass incomelistviewClass=new IncomelistviewClass(va1,va2,c2,0);
			incomeArray.add(incomelistviewClass); 
			
			if(i==1)
			{
		
				va2=tempincomearray.get(i-1).getTaka().toString();
				incomelistviewClass=new IncomelistviewClass(va1,va2,c1,0);
				incomeArray.add(incomelistviewClass);
				sum+=Double.parseDouble(va2);
				
				incomelistviewClass=new IncomelistviewClass("10000",String.valueOf(sum),c1,1);
				incomeArray.add(incomelistviewClass);
		
			}
		//	Toast.makeText(getBaseContext(), i+" "+sum,Toast.LENGTH_LONG).show();

		}else {
			
			if(i==1){
			String va1=tempincomearray.get(0).getId().toString();
			String va2=tempincomearray.get(i).getTaka().toString();
			
			IncomelistviewClass incomelistviewClass=new IncomelistviewClass(va1,va2,c2,0);
			incomeArray.add(incomelistviewClass);
			
			sum+=Double.parseDouble(va2);
			
		     incomelistviewClass=new IncomelistviewClass("10000",String.valueOf(sum),c2,1);
			incomeArray.add(incomelistviewClass);
			
			

		      va2=tempincomearray.get(i-1).getTaka().toString();
		      va1=tempincomearray.get(0).getId().toString();
			 incomelistviewClass=new IncomelistviewClass(va1,va2,c1,0);
			incomeArray.add(incomelistviewClass);
			
			sum=Double.parseDouble(va2);
			
		     incomelistviewClass=new IncomelistviewClass("1000",String.valueOf(sum),c1,1);
			incomeArray.add(incomelistviewClass);
			sum=0;
			
			}else {
				
				String va2=tempincomearray.get(i).getTaka().toString();
				String va1=tempincomearray.get(0).getId().toString();
				IncomelistviewClass incomelistviewClass=new IncomelistviewClass(va1,va2,c2,0);
				incomeArray.add(incomelistviewClass);
				
				sum+=Double.parseDouble(va2);
				
			     incomelistviewClass=new IncomelistviewClass("1000",String.valueOf(sum),c2,1);
				incomeArray.add(incomelistviewClass);
				sum=0;
				
			}
			
		}
		
	
		}
		Collections.reverse(incomeArray);
		adapter.notifyDataSetChanged();
		}
	 catch (Exception e) {
		// TODO: handle exception
	}
	
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.income, menu);
		return true;
	}

}
