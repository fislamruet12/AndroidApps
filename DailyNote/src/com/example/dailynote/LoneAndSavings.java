package com.example.dailynote;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.zip.Inflater;

import com.example.dailynote.R.layout;



import android.os.Bundle;
import android.os.Environment;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

public class LoneAndSavings extends Activity {
	static int loanchecking=0;
     EditText editName,edittaka;
     Button saveButton;
     ListView loanandsavingListView;
     ArrayList<LoneAndSavingconstructorClass> loanandsavinglistdata;
     BaseAdapter loanandsaveadapter;
    private DBAdapter dbAdapter;
    final Context context=this;
	
    ArrayList<DbInsertingClass> arrayList=new ArrayList<DbInsertingClass>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lone_and_savings);
		dbAdapter=new DBAdapter(this);
		String string=getIntent().getStringExtra("PERSON_NAME");
		int n =Integer.parseInt(string.toString());
		 loanchecking=n;
		 InitializeAll();
			
		if(n==3){
			setTitle("Take Loan");
			try {
				
				dbAdapter.open();
				arrayList=dbAdapter.getAllLoan();
				CalculateFromDb();
				dbAdapter.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			setTitle("Give Loan");
try {
				
				dbAdapter.open();
				arrayList=dbAdapter.getAllAdvance();
				CalculateFromDb();
			//	Toast.makeText(getBaseContext(),""+arrayList.size(),Toast.LENGTH_LONG).show();
				dbAdapter.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			
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
	editName=(EditText) findViewById(R.id.editlonesavingsName);
	edittaka=(EditText) findViewById(R.id.editLonesavingsTaka);
	saveButton=(Button) findViewById(R.id.Save);
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
			sname=editName.getText().toString();
			staka=edittaka.getText().toString();
			
			InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(editName.getWindowToken(),0);
			inputMethodManager.hideSoftInputFromWindow(edittaka.getWindowToken(),0);
			
			
			Calendar cal;
			cal=Calendar.getInstance();
	       editName.setText("");
	       edittaka.setText("");
	       if(loanchecking==3){
				dbAdapter.open();				
				long mil=cal.getTimeInMillis();
				String s=Long.toString(mil);
				long lval=dbAdapter.LoanInsert(new LoanAndSave(sname,staka,s));
				arrayList=dbAdapter.getAllLoan();
				CalculateFromDb();
				
				}else {
					dbAdapter.open();				
					long mil=cal.getTimeInMillis();
					String s=Long.toString(mil);
					long lval=dbAdapter.AdvanceIsert(new LoanAndSave(sname, staka,s));
					arrayList=dbAdapter.getAllAdvance();
					CalculateFromDb();
					
					//Toast.makeText(getBaseContext(), lval+"  ", Toast.LENGTH_LONG).show();
					
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
					       	if(loanchecking==3){
					       	dbAdapter.open();
					       	dbAdapter.DeleteFromDb("LOAN",Long.parseLong(s1));
							loanandsavinglistdata.remove(pos);	  
							loanandsaveadapter.notifyDataSetChanged();
						    dbAdapter.close();
					       	}else {
					       		dbAdapter.open();
						       	dbAdapter.DeleteFromDb("ADVANCE",Long.parseLong(s1));
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

							final EditText userInput15 = (EditText) promptsView15
									.findViewById(R.id.Addextra);
							alertDialogBuilder
									.setCancelable(false).setMessage(""+loanandsavinglistdata.get(pos).getTaka().toString()+" +")
									.setPositiveButton("OK",
											new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog,
														int id) {
													
													String s3 =userInput15.getText().toString();
													
													String s1=loanandsavinglistdata.get(pos).getId();
											       	String s2=loanandsavinglistdata.get(pos).getTaka();
													if(loanchecking==3)
													{
														dbAdapter.open();
														dbAdapter.updateDbl("LOAN",Long.toString(Long.parseLong(s2)+Long.parseLong(s3)),Long.parseLong(s1));
														
														arrayList=dbAdapter.getAllLoan();
														CalculateFromDb();
														
														dbAdapter.close();
														
													}else {
														dbAdapter.open();
														dbAdapter.updateDbA("ADVANCE",Long.toString(Long.parseLong(s2)+Long.parseLong(s3)),Long.parseLong(s1));
														
														arrayList=dbAdapter.getAllAdvance();
														CalculateFromDb();
														
														dbAdapter.close();
													}
													
													Toast.makeText(getBaseContext(), "Successfully Edited",Toast.LENGTH_SHORT).show();
												}
											});
							AlertDialog alertDialog = alertDialogBuilder.create();

							// show it
							alertDialog.show();
							
							
						break;
						case R.id.Substract:

							LayoutInflater li1 = LayoutInflater.from(context);
							View promptsView1 = li1.inflate(R.layout.editpromt, null);

							AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(
									context);
							alertDialogBuilder1.setView(promptsView1);

							final EditText userInput1 = (EditText) promptsView1
									.findViewById(R.id.Addextra);
							alertDialogBuilder1
									.setCancelable(false).setMessage(""+loanandsavinglistdata.get(pos).getTaka().toString()+" -")
									.setPositiveButton("OK",
											new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog,
														int id) {
												
													String s3 =userInput1.getText().toString();
													String s1=loanandsavinglistdata.get(pos).getId();
											       	String s2=loanandsavinglistdata.get(pos).getTaka();
													if(loanchecking==3)
													{
														dbAdapter.open();
														dbAdapter.updateDbl("LOAN",Long.toString(Long.parseLong(s2)-Long.parseLong(s3)),Long.parseLong(s1));
														
														arrayList=dbAdapter.getAllLoan();
														CalculateFromDb();
														
														dbAdapter.close();
														
													}else {
														dbAdapter.open();
														dbAdapter.updateDbA("ADVANCE",Long.toString(Long.parseLong(s2)-Long.parseLong(s3)),Long.parseLong(s1));
														
														arrayList=dbAdapter.getAllAdvance();
														CalculateFromDb();
														
														dbAdapter.close();
													}
													
													Toast.makeText(getBaseContext(), "Successfully Edited",Toast.LENGTH_SHORT).show();
												}
											});
							AlertDialog alertDialog1 = alertDialogBuilder1.create();

							// show it
							alertDialog1.show();
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
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.loansettings)
        {
        	File mainroot=new File(Environment.getExternalStorageDirectory()+File.separator+"Daily Note");
			if(!mainroot.exists())
			{
				mainroot.mkdir();
			}
			
			   AlertDialog.Builder setPositiveButton = new AlertDialog.Builder(context).setTitle("Save Data")
			    .setMessage("Are You sure?")
			    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						
					  
						
							if(loanchecking==3)
							{
								File secondroot=new File(Environment.getExternalStorageDirectory()+File.separator+"Daily Note"+File.separator+"Take loan");
								if(!secondroot.exists())
								{
									secondroot.mkdir();
								}
								
								
						  filestore(secondroot,"Take_");
								
							}
							else {
								File secondroot=new File(Environment.getExternalStorageDirectory()+File.separator+"Daily Note"+File.separator+"Give loan");
							
								if(!secondroot.exists())
								{
									secondroot.mkdir();
								}
								
								
						  filestore(secondroot,"give_");
							}							
						
					}
				});
			  AlertDialog alertDialog=setPositiveButton.create();
			  alertDialog.show();
        	
        	
        	
       return false;
		}

        return super.onOptionsItemSelected(item);
    }
	public void filestore(File dir,String string) {
		
		Calendar date1;
	      
		try {
			
			  for(int i=0;i<loanandsavinglistdata.size();i++){	
				   
				   date1=loanandsavinglistdata.get(i).getDate();
				   String s1=(String) DateFormat.format("MM-yyyy",date1.getTime());
				  // Toast.makeText(getBaseContext(), s1,Toast.LENGTH_LONG).show();
				   String filenameString=string+s1+".txt";
				   
				File gpxFile=new  File(dir,filenameString);
			    
			  {
				  PrintWriter writer=new PrintWriter(gpxFile);
				  writer.print("");
				  writer.close();
			  }
			  }
			
		   for(int i=0;i<loanandsavinglistdata.size();i++){	
			   
			   date1=loanandsavinglistdata.get(i).getDate();
			   String s1=(String) DateFormat.format("MM-yyyy",date1.getTime());
			  // Toast.makeText(getBaseContext(), s1,Toast.LENGTH_LONG).show();
			   String filenameString=string+s1+".txt";
			   
			File gpxFile=new  File(dir,filenameString);
		    
		    {
			FileWriter fileWriter=new FileWriter(gpxFile,true);
			fileWriter.append(loanandsavinglistdata.get(i).getName()+"   "+loanandsavinglistdata.get(i).getTaka()+"  "+SimpleDateFormat.getInstance().format(date1.getTime())+"\n");
			fileWriter.flush();
			fileWriter.close();
		    
			  }
		   }
		 Toast.makeText(getBaseContext(), "file created ",Toast.LENGTH_LONG).show();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
