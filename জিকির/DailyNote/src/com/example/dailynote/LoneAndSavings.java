package com.example.dailynote;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import com.example.dailynote.R.layout;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

public class LoneAndSavings extends Activity {
	static int loanchecking=0;
     
     Button saveButton;
     ListView loanandsavingListView;
     ArrayList<LoneAndSavingconstructorClass> loanandsavinglistdata;
     BaseAdapter loanandsaveadapter;
    private DBAdapter dbAdapter;
    final Context context=this;
	long counter=0;
	long dropposition=0;
    String catagortelaoat=null;
    ArrayList<DbInsertingClass> arrayList=new ArrayList<DbInsertingClass>();
    
    private static final long delay=2000l;
    private boolean mrecentbackpress=false;
    private Handler mexitHandler=new Handler();
    private Runnable mexRunnable =new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method আব
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lone_and_savings);
		dbAdapter=new DBAdapter(this);
		//String string=getIntent().getStringExtra("PERSON_NAME");
		int n ;//=Integer.parseInt(string.toString());
		n=3;
		 loanchecking=n;
		
		 
		 InitializeAll();
			

		//	setTitle("JSK_TASBIAT");
			try {
				
				dbAdapter.open();
				arrayList=dbAdapter.getAllLoan();
				CalculateFromDb();
				dbAdapter.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(mrecentbackpress)
		{
			mexitHandler.removeCallbacks(mexRunnable);
			mexitHandler=null;
			super.onBackPressed();
			android.os.Process.killProcess(android.os.Process.myPid());
			finish();
			System.exit(0);
		}else {
			mrecentbackpress=true;
			Toast.makeText(this,"press again to exit",Toast.LENGTH_LONG).show();
			mexitHandler.postDelayed(mexRunnable, delay);
			
		}
		
	}
	
	public void CalculateFromDb()
	{
		 String s0,s1,s2,s3;
		 loanandsavinglistdata.clear();
		for(int ll=0;ll<arrayList.size();ll++)
		{
			s0=arrayList.get(ll).getId();
				s1=arrayList.get(ll).getName();
			s2=arrayList.get(ll).getTaka();
			s3=arrayList.get(ll).getDate();
			Calendar ca1=Calendar.getInstance();
			long mils=Long.parseLong(s3);
			ca1.setTimeInMillis(mils);
			//Toast.makeText(getBaseContext(), ""+s2,Toast.LENGTH_LONG).show();
			
			LoneAndSavingconstructorClass loneAndSavingconstructorClass=new LoneAndSavingconstructorClass(s0,s1, s2, ca1);
			loanandsavinglistdata.add(loneAndSavingconstructorClass);
		}
		
		arrayList.clear();
		Collections.reverse(loanandsavinglistdata);
		loanandsaveadapter.notifyDataSetChanged();
		
		dbAdapter.close();
		
	}
private void InitializeAll()
{
	

	saveButton=(Button) findViewById(R.id.add);
	loanandsavingListView=(ListView) findViewById(R.id.LonesavinglistView);
	
	loanandsavinglistdata=new ArrayList<LoneAndSavingconstructorClass>();
	
	loanandsaveadapter=new BaseAdapter() {
		
		LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
		
		@Override
		public View getView(int pos, View view, ViewGroup viewGroup) {
			if(view==null)
			{
				view=inflater.inflate(R.layout.loneandsavings,null);
			}
			  TextView name=(TextView) view.findViewById(R.id.loneName);
			  TextView lonetaka=(TextView) view.findViewById(R.id.loneTAka);
			  TextView lonedaTe =(TextView) view.findViewById(R.id.lonedate);
			  TextView lonetime =(TextView) view.findViewById(R.id.lonetime);
			  
			//  Toast.makeText(getBaseContext()," "+loanandsavinglistdata.get(pos).getName(),Toast.LENGTH_SHORT).show();
			  

			  name.setText(loanandsavinglistdata.get(pos).getName()+"");
			  lonetaka.setText(loanandsavinglistdata.get(pos).getTaka()+"");
			  Calendar cadr=loanandsavinglistdata.get(pos).getDate();
			  
			
			//    View right=findViewById(R.id.right);
			  
			  lonedaTe.setText(DateFormat.format("dd/MM/yyyy", cadr)); 
			  lonetime.setText(DateFormat.format("HH:mm a", cadr)); 
				 
			  if(pos%2==0)
			  {
				  name.setBackgroundColor(Color.DKGRAY);

				  lonetaka.setBackgroundColor(Color.DKGRAY);

				  lonedaTe.setBackgroundColor(Color.DKGRAY);
				  lonetime.setBackgroundColor(Color.DKGRAY);
				  //left.setBackgroundColor(Color.DKGRAY);
			  }
			  else {

				  name.setBackgroundColor(Color.BLACK);

				  lonetaka.setBackgroundColor(Color.BLACK);

				 lonedaTe.setBackgroundColor(Color.BLACK);
				 lonetime.setBackgroundColor(Color.BLACK);
					
				  
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
			return loanandsavinglistdata.get(pos);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return loanandsavinglistdata.size();
		}
	};
	
	loanandsavingListView.setAdapter(loanandsaveadapter);
	saveButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
		
			String sname,staka;
			sname="পরিবর্তন করুন";
			staka="0";
			
			
			
			Calendar cal;
			cal=Calendar.getInstance();
	       
	      // if(loanchecking==3)
			{
				dbAdapter.open();				
				long mil=cal.getTimeInMillis();
				String s=Long.toString(mil);
				long lval=dbAdapter.LoanInsert(new LoanAndSave(sname,staka,s));
				arrayList=dbAdapter.getAllLoan();
				CalculateFromDb();
				
				}
					dbAdapter.close();
				
			
			
		}
	});
	loanandsavingListView.setOnItemLongClickListener(new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View view,
				final int pos, long arg3) {
			

			   PopupMenu pMenu=new PopupMenu(LoneAndSavings.this, loanandsavingListView);
			   pMenu.getMenuInflater().inflate(R.menu.popupmenu,pMenu.getMenu());
			   
			   pMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
				
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					
					
					try {
						switch (item.getItemId()) {
						case R.id.Delete:
					       	String s1=loanandsavinglistdata.get(pos).getId();
					       	{
					       	dbAdapter.open();
					       	dbAdapter.DeleteFromDb("LOAN",Long.parseLong(s1));
							loanandsavinglistdata.remove(pos);	  
							loanandsaveadapter.notifyDataSetChanged();
						    dbAdapter.close();
					       	}
							break;
						case R.id.Add:

							LayoutInflater li15 = LayoutInflater.from(context);
							View promptsView15 = li15.inflate(R.layout.editpromt, null);

							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);
							alertDialogBuilder.setView(promptsView15);

							
							  final Button btnButton=(Button) promptsView15.findViewById(R.id.btninc);
							 final Spinner spinner =(Spinner) promptsView15.findViewById(R.id.catagory);
							  
								 
							 List<String> cataList =new ArrayList<String>();
								
								cataList.add("জিকির করো মামা");
								cataList.add("আল্লাহ");
							cataList.add("সুবহানাল্লাহ");
							cataList.add("আলহামদুলিল্লাহ");
							cataList.add("আল্লাহু আকবর");
							cataList.add("লা-ইলাহা ইল্লাল্লহ");
							cataList.add("দরূদ শরীফ");
							cataList.add("সূরা ইয়াসিন");
							ArrayAdapter<String > dataAdapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,cataList );
							dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						
							 spinner.setAdapter(dataAdapter);
                             
								spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

									@Override
									public void onItemSelected(AdapterView<?> parent, View view,
											int position, long id) {
										String itemString=parent.getItemAtPosition(position).toString();
										dropposition=position;
									catagortelaoat=itemString;
										
									}
									@Override
									public void onNothingSelected(AdapterView<?> arg0) {
										// TODO Auto-generated method stub
										
									}
									
								});
								
								
								
								 String s5=loanandsavinglistdata.get(pos).getTaka();
								   btnButton.setText(s5);
							  btnButton.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View arg0) {
										// TODO Auto-generated method stub

								         	counter=1;
										String s3 =Long.toString(counter);
										
										String s1=loanandsavinglistdata.get(pos).getId();
								      
											
										 String s2=loanandsavinglistdata.get(pos).getTaka();
										btnButton.setText(""+Long.toString(Long.parseLong(s2)+Long.parseLong(s3)));
											dbAdapter.open();
											if(dropposition==0)
											dbAdapter.updateDbl("LOAN",Long.toString(Long.parseLong(s2)+Long.parseLong(s3)),Long.parseLong(s1));
											else {
												dbAdapter.updateDbname("LOAN",catagortelaoat,Long.toString(Long.parseLong(s2)+Long.parseLong(s3)),Long.parseLong(s1));
											//	Toast.makeText(getBaseContext(), "  "+catagortelaoat,Toast.LENGTH_LONG).show();
												
											}
											arrayList=dbAdapter.getAllLoan();
											CalculateFromDb();												
											dbAdapter.close();
											

									}
								});
									 
							
						
							alertDialogBuilder
									.setCancelable(false)
									.setPositiveButton("OK",
											new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog,
														int id) {
													counter=0;
													
													String s3 =Long.toString(counter);
													
													String s1=loanandsavinglistdata.get(pos).getId();
											      
														
													 String s2=loanandsavinglistdata.get(pos).getTaka();
													btnButton.setText(""+Long.toString(Long.parseLong(s2)+Long.parseLong(s3)));
														dbAdapter.open();
														if(dropposition==0)
														dbAdapter.updateDbl("LOAN",Long.toString(Long.parseLong(s2)+Long.parseLong(s3)),Long.parseLong(s1));
														else {
															dbAdapter.updateDbname("LOAN",catagortelaoat,Long.toString(Long.parseLong(s2)+Long.parseLong(s3)),Long.parseLong(s1));
														//	Toast.makeText(getBaseContext(), "  "+catagortelaoat,Toast.LENGTH_LONG).show();
															
														}
														arrayList=dbAdapter.getAllLoan();
														CalculateFromDb();												
														dbAdapter.close();
														
												}
													
											});
							AlertDialog alertDialog = alertDialogBuilder.create();

							// show it
							alertDialog.show();
							
							
						break;
						default:
							break;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					return true;
				}
			});
			    
			   pMenu.show();
			return false;
			   
							
				}
			});
			
		
	
}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lone_and_savings, menu);
		return true;
	}

}
