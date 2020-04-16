package com.pro.gui; //package name

//different packages to be imported

import java.io.IOException;
import android.gesture.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.pro.gui.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AndroguiActivity extends Activity implements TextToSpeech.OnInitListener{
	private TextToSpeech mTts;
	private static final int MY_DATA_CHECK_CODE = 1234;
	GestureLibrary gestureLibrary = null;
	GestureOverlayView gestureOverlayView;
	TextView gestureResult;
	int tempcontact,languagechange=0;
	String askcall,askmsg;
	int flagforcall=-1;
    public int flag=10,sound_alarm,master_sound=1;  	   
    float x,y,x1,y1;
    int[][] arr= new int[600][600];
    int forlang=0,forontouch=0;
    int[] xcord=new int[600];
    int[] ycord=new int[600];
    int [][]dp=new int[100][14];
    int[][] db = new int[100][20];
    int[][] crop= new int[600][600];
    int[][] temp=new int[600][600]; 
    int[] pt23arr=new int[13];
    int level=0,tempno,firstflag=0,min,hr,date,month,time,year,i;
    int cnt,cnt1,total=0,total1=0,report=0,getwd,getht,dev=0,minval=999;
    int rowno,pattern_flag=0,call_msg=0,circle_flag=0,second_var=0;
    public int numberc=0,phoneno,callormsg;
    String timeis,patt13=null,mybattery,aaa;
    double newabc,abc,pabc,newxyz,pxyz,xyz;
	String myposition,myaddress,provider;
    public String number,patt,data0,data1,data2,data3,data4,data5;
    public String data6,data7,data8,data9,temp0,temp1,temp2,temp3;
    public String temp4,pos0,pos1,temp5,temp6,temp7,temp8,temp9,pos2,pos3,pos4;
    GesturePoint gp;
    TextView tv; 
    Cursor c,t,p,l; 
 	private MediaPlayer   mPlayer = null;
	
public void onCreate(Bundle savedInstanceState)
    		{
	                super.onCreate(savedInstanceState);
                    
	                //get width and height of the screen
    				Display display=getWindowManager().getDefaultDisplay(); 
    				getwd=display.getWidth();
    				getht=display.getHeight();
    				
    				//Initialize the 13 point array with all values one.
    				for(int i=0;i<getwd;i++)
    					for(int j=0;j<getht;j++)
    						arr[i][j]=1;
    				
    				//open the main.xml layout at the beginning
    				setContentView(R.layout.main); 
                    getalldata();
    				sound_alarm=0;
                    sound();
    				//call sound function for welcome sound 
		}
 public String fetch(long i)
    		{
    				Contact info = new Contact(getApplicationContext());
    				info.open();
    				Cursor c = info.fetchEmployee(i);
    				String data10=c.getString(3);
    				info.close();
    				return data10;
    		}
public String fetchm(long i)
    		{
    				Contact info = new Contact(getApplicationContext());
    				info.open();
    				Cursor c = info.fetchEmployee(i);
    				String data10=c.getString(2);
    				info.close();
    				return data10;
    		}
public String fetchTem(long i)
    		{
    				Template tem =new Template(getApplicationContext());
    				tem.open();
    				Cursor c = tem.fetchTemp(i);
    				String data10=c.getString(2);
    				tem.close();
    				return data10;
    		}
public void pattern() 
    		{
    			     setContentView(R.layout.gestures);
    				 gestureResult = (TextView)findViewById(R.id.gestureresult);
    				gestureOverlayView = (GestureOverlayView)findViewById(R.id.gestures);
    				gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
					gestureLibrary.load();
    				gestureOverlayView.addOnGesturePerformedListener(gesturePerformedListener);
          	      
    		}
OnGesturePerformedListener gesturePerformedListener
    		= new OnGesturePerformedListener(){
    
    			public void onGesturePerformed(GestureOverlayView view, Gesture gesture)
    			{
    				
    				ArrayList<Prediction> prediction = gestureLibrary.recognize(gesture);
    				if(prediction.size() > 0){
    					gestureResult.setText(prediction.get(0).name);
    					patt=gestureResult.getText().toString();
    					gestureOverlayView.setDrawingCacheEnabled(true);	
        		    	gestureOverlayView.setDrawingCacheEnabled(false);
                    	if(call_msg==1)
    				    findmsg();
    					else
    					recognize();   
    						
    			}
     }};
public void recognize()
     		{
     			if(pattern_flag==1)
     			{ 
     				call_msg=99;
     			    forlang=1;
     			
     			    if(patt.contains("Zero")) callyamsg(0);
     			    if(patt.contains("One"))  callyamsg(1);
     			    if(patt.contains("Two"))  callyamsg(2);
     			    if(patt.contains("Three"))callyamsg(3);
     			    if(patt.contains("Four")) callyamsg(4);
     			    if(patt.contains("Five")) callyamsg(5);
     			    if(patt.contains("Six"))  callyamsg(6);
     			    if(patt.contains("Seven"))callyamsg(7);
     			    if(patt.contains("Eight"))callyamsg(8);	
     			    if(patt.contains("Nine")) callyamsg(9);
  			}
     	
  		else
  		{  
  		   		
  		   if(patt.contains("C")){
  			   
  			  // sound for "pattern is C"
  		        sound_alarm=5;
  		        sound();
  		   	    call_msg=0;
  		   	    pattern_flag=1;
  		   	 }
  		   
  		   if(patt.contains("M")){
  			// sound for "pattern is M"
  			  sound_alarm=4;
  		      sound();
  		      call_msg=1;
  		      pattern_flag=1;
  		      }
  		   
  		   if(patt.contains("P")){
  			 // sound for "pattern is P"
  			  position(); 
  			  int x_sound=master_sound;
		      master_sound=1;
		      sound_alarm=6;
			  sound();
		      master_sound=x_sound;
	     
  			}
	   
	
  	   if(patt.contains("B")){
  		   //  sound for "pattern is B"
  		      battery();
  		      int x_sound=master_sound;
  		      master_sound=1;
  		      sound_alarm=7;
  			  sound();
  		      master_sound=x_sound;
  	   }
     
  	   if(patt.contains("L")){
    //	 sound for "Pattern is L"
  	       location();
  	     int x_sound=master_sound;
		      master_sound=1;
		      sound_alarm=8;
			  sound();
		      master_sound=x_sound;
  	       
  	}
  	   if(patt.contains("T")){
  		// sound for "pattern is T"
  		   time time1= new time();
  		   timeis=time1.timefun();	  
  		 int x_sound=master_sound;
	      master_sound=1;
	      sound_alarm=9;
		  sound();
	      master_sound=x_sound;
	    
  			  
  			  }
  	   
  	 if(patt.contains("A")){
  		// sound for aborting/exiting from the application.
  		 if(master_sound!=1)
  		 {
  		   sound_alarm=99;
  		   sound();
  		   Thread thread1 = new Thread(new RunnableThread(),"thread1");
		   thread1.start();
		   try{
			      setContentView(R.layout.gestures);
			      pattern();
			      pattern_flag=0;
			      Thread.currentThread();			
			      Thread.sleep(10000);
			      finish();
		        }
		 catch (InterruptedException e) {
		       
		 }
  		 }
  		 else
  		 {
  	        mTts.speak("Thank you for using andronetra. See u soon. Have a nice day.",TextToSpeech.QUEUE_FLUSH,null);
  		    finish();
  		 }
  		 System.exit(0);
  			  
  			  }
  		 }
    }
public void callyamsg(int callormsg)
{
	if (pattern_flag==1 && firstflag!=1)
		{  
		
		if(master_sound==1)
		{
			mTts.speak("do you want to call",TextToSpeech.QUEUE_FLUSH,null);
			Thread thread1 = new Thread(new RunnableThread(),"thread1");
			 thread1.start();
			 try{
				 Thread.currentThread();			
				 Thread.sleep(2000);
				 mTts.speak(fetchm(callormsg),TextToSpeech.QUEUE_FLUSH,null);
			 }
			 catch (InterruptedException e) {
				       
			 }
		}
		else
		{
			mTts.speak(fetchm(callormsg),TextToSpeech.QUEUE_FLUSH,null);
			Thread thread1 = new Thread(new RunnableThread(),"thread1");
			 thread1.start();
			 try{
				 Thread.currentThread();			
				 Thread.sleep(2000);
				 sound_alarm=71;
					sound();		 
			   }
			 catch (InterruptedException e) {
				       
			 }
		
		}
		
		
		 
		
		// master_sound=x_sound;
		
			//************************************	
				Thread thread2 = new Thread(new RunnableThread(),"thread1");
				 thread2.start();
				 try{
					 Thread.currentThread();			
					 Thread.sleep(1000);
					 askcall="Press up volume button to make call else down volume button to make a new pattern";	
					// mTts.speak(askcall,TextToSpeech.QUEUE_FLUSH,null);
				     sound_alarm=72;
				     sound();
				 }
				 catch (InterruptedException e) {
					       
				 }
				
			  //************************************	
				
			flagforcall=callormsg;
			numberc=0;
		}
		
	    if(firstflag==1)
		{   
			//sound_alarm=51;
			//sound();
			forlang=1;
			//************************************
			if(master_sound==1)
			{	
			askmsg="do you want to send message to ";
			mTts.speak(askmsg,TextToSpeech.QUEUE_FLUSH,null);
			askmsg=fetchm(callormsg);
			Thread thread1 = new Thread(new RunnableThread(),"thread1");
	    	 thread1.start();
	    	 try{
	    		 Thread.currentThread();			
	    		 Thread.sleep(2000);
	    		 mTts.speak(askmsg,TextToSpeech.QUEUE_FLUSH,null);
	 			}
	    	 catch (InterruptedException e) {
	 		 }
			}
			else
			{
				askmsg=fetchm(callormsg);
				Thread thread1 = new Thread(new RunnableThread(),"thread1");
		    	 thread1.start();
		    	 try{
		    		 Thread.currentThread();			
		    		 Thread.sleep(1000);
		    		 mTts.speak(askmsg,TextToSpeech.QUEUE_FLUSH,null);
		 			}
		    	 catch (InterruptedException e) {
		 		 }
			sound_alarm=73;
			sound();
			}
				//************************************
			
			//************************************
			askmsg=".Press up volume button to send message else down volume button to make a new pattern";	
			Thread thread2 = new Thread(new RunnableThread(),"thread1");
	    	 thread2.start();
	    	 try{
	    		setContentView(R.layout.gestures);
	    		 pattern();
	    		 Thread.currentThread();			
	    		 Thread.sleep(1000);
	    		// mTts.speak(askmsg,TextToSpeech.QUEUE_FLUSH,null);
	 		     sound_alarm=74;
	 		     sound();	
	    	 }
	    	 catch (InterruptedException e) {
	 		       
	    	 }
			flagforcall=callormsg;
			numberc=1;
		}
}
class RunnableThread implements Runnable{
     	    	 Thread runner;
     	    		public RunnableThread() {
     	    		}
     	    		public RunnableThread(String threadName) {
     	    			runner = new Thread(this, threadName); // (1) Create a new thread.    			
     	    			runner.start(); // (2) Start the thread.
     	    		}
     	    		public void run() {    			
     	    		//	gestureResult.setText("delay of 6 seconds");
     	    		}
     	     } 
public void call(int callormsg) 
     	     	{ 	   
     	    	 
     	    	 Thread thread1 = new Thread(new RunnableThread(),"thread1");
     	    	 thread1.start();
     	    	 try{
     	    		setContentView(R.layout.gestures);
    	    		 pattern();
    	    		 pattern_flag=0;
    	    		 Thread.currentThread();			
     	    		 Thread.sleep(4000);
     	    		 Intent callIntent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+fetch(callormsg)));
     	 	 		 startActivity(callIntent);
     	    	 }
     	    	 catch (InterruptedException e) {
     	 		       
     	    	 } 	
     	   }
public void findmsg()
{
	if(firstflag==0)
	{
		firstflag=1;
	if(patt.contains("Zero")) tempno=9;
	if(patt.contains("One"))  tempno=1;
	if(patt.contains("Two"))  tempno=2;
	if(patt.contains("Three"))tempno=3;
	if(patt.contains("Four")) tempno=4;
	if(patt.contains("Five")) tempno=5;
	if(patt.contains("Six"))  tempno=6;
	if(patt.contains("Seven"))tempno=7;
	if(patt.contains("Eight"))tempno=8;
	if(patt.contains("Nine")) tempno=0;
	//************************************
	
	if(master_sound==1)
	mTts.speak("The template you have selected is ",TextToSpeech.QUEUE_FLUSH,null); 
	else
	{
		 sound_alarm=75;
		 sound();
	}
	//************************************
	
	Thread thread1 = new Thread(new RunnableThread(),"thread1");
	 thread1.start();
	 try{
		setContentView(R.layout.gestures);
		 pattern();
		 pattern_flag=0;
		 Thread.currentThread();			
		 Thread.sleep(4000);
		 mTts.speak(fetchTem(tempno),TextToSpeech.QUEUE_FLUSH,null);
			}
	 catch (InterruptedException e) {
		       
	 }
	//************************************
	 Thread thread2 = new Thread(new RunnableThread(),"thread1");
	 thread2.start();
	 try{
		setContentView(R.layout.gestures);
		 pattern();
		 pattern_flag=0;
		 Thread.currentThread();			
		 Thread.sleep(4000);
	if(master_sound==1)
		 mTts.speak("Now make pattern of the speed dial number you want to send the template to ",TextToSpeech.QUEUE_FLUSH,null);
	 else{
		 sound_alarm=76;
		 sound();
		 }}
	 catch (InterruptedException e) {
		       
	 }
	 //************************************
	
	}
	else
	{
		call_msg=0;
		
		if(patt.contains("Zero")) callyamsg(0);
		if(patt.contains("One"))  callyamsg(1);
		if(patt.contains("Two"))  callyamsg(2);
		if(patt.contains("Three"))callyamsg(3);
		if(patt.contains("Four")) callyamsg(4);
		if(patt.contains("Five")) callyamsg(5);
		if(patt.contains("Six"))  callyamsg(6);
		if(patt.contains("Seven"))callyamsg(7);
		if(patt.contains("Eight"))callyamsg(8);
		if(patt.contains("Nine")) callyamsg(9);
	}
}
public void sendmsg(int phoneno)
{
   if(master_sound==1)
   {
	mTts.speak("Sending message to " + fetchm(phoneno),TextToSpeech.QUEUE_FLUSH,null);
   }
   else
   {
	   askmsg=fetchm(phoneno);
		Thread thread1 = new Thread(new RunnableThread(),"thread1");
   	 thread1.start();
   	 try{
   		 Thread.currentThread();			
   		 Thread.sleep(2000);
   		 mTts.speak(askmsg,TextToSpeech.QUEUE_FLUSH,null);
			}
   	 catch (InterruptedException e) {
		 }
	   sound_alarm=77;
		 sound();
   }
	SmsManager sm = SmsManager.getDefault();
    sm.sendTextMessage(fetch(phoneno), null, fetchTem(tempno)+"\n"+"- Sent via ANDRONETRA B-)", null, null);
    firstflag=0;
    numberc=0;
   }
private void location() {
	double LATITUDE;
    double LONGITUDE;
    final int maxResult=1;
    String addressList[] = new String[maxResult];

	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	Criteria criteria = new Criteria();
	provider = lm.getBestProvider(criteria, false);
	Location location = lm.getLastKnownLocation(provider);
	if(location!=null)
	{
		LATITUDE=location.getLatitude();
		LONGITUDE=location.getLongitude();

		Geocoder  geocoder = new Geocoder(this, Locale.ENGLISH);

		try 
		{
			List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, maxResult);
			if(addresses != null)
			{
					for (int j=0; j<maxResult; j++)
					{
						Address returnedAddress = addresses.get(j);
						StringBuilder strReturnedAddress = new StringBuilder();
						for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++)
						{
							strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
						}
						addressList[j] = strReturnedAddress.toString();
					}
	
					myaddress=addressList[0];
				
			}
			else
			{
				myaddress="Unknown road";
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		    myaddress="unknown road";
		}
	}
	else
	{
		myaddress="Provider not available";
	}
}
private void position() {
	String provide;
    Location location;
    float[] result= new float[600];
	
    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	Criteria criteria = new Criteria();
	provide = lm.getBestProvider(criteria, false);
	location = lm.getLastKnownLocation(provide);
    if(location!=null){
	abc=location.getLatitude();
    xyz = location.getLongitude();
    ///do your thing
    Position pos1=new Position(getApplicationContext());
    pos1.open();
    float small=99999;
    String smal="null";
    for(int i=1;i<6;i++)
    {	
    Cursor p = pos1.fetchPos(i);
	String name1=p.getString(1);
	String newabc1=p.getString(2);
	String newxyz1=p.getString(3);
    newabc=Double.valueOf(newabc1).doubleValue();
	newxyz=Double.valueOf(newxyz1).doubleValue();
	location.distanceBetween(abc, xyz, newabc, newxyz,result);
	if(result[0]<small)
	 {
		small=result[0];
	    smal=name1;
	 }
    }
    
	pos1.close();
    
	if(small>1000)
	{
		small=small/1000;	
		myposition= "Distance from ";
		Thread thread1 = new Thread(new RunnableThread(),"thread1");
			 thread1.start();
			 try{
				setContentView(R.layout.gestures);
				 pattern();
				 pattern_flag=0;
				 Thread.currentThread();			
				 Thread.sleep(1000);
				 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
				}
			 catch (InterruptedException e) {
			       
			 }
		myposition=smal;
		Thread thread2 = new Thread(new RunnableThread(),"thread1");
		 thread2.start();
		 try{
			setContentView(R.layout.gestures);
			 pattern();
			 pattern_flag=0;
			 Thread.currentThread();			
			 Thread.sleep(1000);
			 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
			}
		 catch (InterruptedException e) {
		       
		 }
		myposition=" is";
		Thread thread3 = new Thread(new RunnableThread(),"thread1");
		 thread3.start();
		 try{
			setContentView(R.layout.gestures);
			 pattern();
			 pattern_flag=0;
			 Thread.currentThread();			
			 Thread.sleep(1000);
			 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
			}
		 catch (InterruptedException e) {
		       
		 }
		myposition=Float.toString(small);
		Thread thread4 = new Thread(new RunnableThread(),"thread1");
		 thread4.start();
		 try{
			setContentView(R.layout.gestures);
			 pattern();
			 pattern_flag=0;
			 Thread.currentThread();			
			 Thread.sleep(1000);
			 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
			}
		 catch (InterruptedException e) {
		       
		 }
		myposition=" Kilometers";
		Thread thread5 = new Thread(new RunnableThread(),"thread1");
		 thread5.start();
		 try{
			setContentView(R.layout.gestures);
			 pattern();
			 pattern_flag=0;
			 Thread.currentThread();			
			 Thread.sleep(1000);
			 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
			}
		 catch (InterruptedException e) {
		       
		 }
	}
	else
		myposition= "Distance from ";
	Thread thread1 = new Thread(new RunnableThread(),"thread1");
		 thread1.start();
		 try{
			setContentView(R.layout.gestures);
			 pattern();
			 pattern_flag=0;
			 Thread.currentThread();			
			 Thread.sleep(1000);
			 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
			}
		 catch (InterruptedException e) {
		       
		 }
	myposition=smal;
	Thread thread2 = new Thread(new RunnableThread(),"thread1");
	 thread2.start();
	 try{
		setContentView(R.layout.gestures);
		 pattern();
		 pattern_flag=0;
		 Thread.currentThread();			
		 Thread.sleep(1000);
		 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
		}
	 catch (InterruptedException e) {
	       
	 }
	myposition=" is";
	Thread thread3 = new Thread(new RunnableThread(),"thread1");
	 thread3.start();
	 try{
		setContentView(R.layout.gestures);
		 pattern();
		 pattern_flag=0;
		 Thread.currentThread();			
		 Thread.sleep(1000);
		 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
		}
	 catch (InterruptedException e) {
	       
	 }
	myposition=Float.toString(small);
	Thread thread4 = new Thread(new RunnableThread(),"thread1");
	 thread4.start();
	 try{
		setContentView(R.layout.gestures);
		 pattern();
		 pattern_flag=0;
		 Thread.currentThread();			
		 Thread.sleep(1000);
		 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
		}
	 catch (InterruptedException e) {
	       
	 }
	myposition=" meters";
	Thread thread5 = new Thread(new RunnableThread(),"thread1");
	 thread5.start();
	 try{
		setContentView(R.layout.gestures);
		 pattern();
		 pattern_flag=0;
		 Thread.currentThread();			
		 Thread.sleep(1000);
		 mTts.speak(myposition,TextToSpeech.QUEUE_FLUSH,null);
		}
	 catch (InterruptedException e) {
	       
	 }}
    else{
    	myposition="Provider not available";
    }
}
private void battery() {
	BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(this);
            int rawlevel = intent.getIntExtra("level", -1);
            int scale = intent.getIntExtra("scale", -1);
            level = -1;
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }
            if(level>15)
          	    mybattery="The battery level is"+Integer.toString(level)+" percent";
          	else
          		mybattery="ALERT!!! battery level is critical.The battery level is"+Integer.toString(level)+" percent";	
}
    };

    IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }
public void sound(){
	int resID=0;
	if(master_sound==1)
	    {
    	if(languagechange==1)
    		mPlayer.stop();
            Intent checkIntent = new Intent();
	    	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	    	startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
    	}
    
	    if(master_sound==2)
    	{
    		languagechange=1;   
    	
    		if(sound_alarm==0){
    			   resID=getResources().getIdentifier("hindi01", "raw", getPackageName());}
    	       if(sound_alarm==1){
    	    	   mPlayer.stop();
    	    	   resID=getResources().getIdentifier("hindi02", "raw", getPackageName());}
    	        if(sound_alarm==2){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("hindi03", "raw", getPackageName());}
    	        if(sound_alarm==3){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("hindi04", "raw", getPackageName());}
    	        if(sound_alarm==14){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("hindi05", "raw", getPackageName());}
    	        if(sound_alarm==4){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("hindi06", "raw", getPackageName());}
    	        if(sound_alarm==5){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("hindi07", "raw", getPackageName());}
    	        if(sound_alarm==21){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0111", "raw", getPackageName());
    	        }
    	        if(sound_alarm==22){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0112", "raw", getPackageName());
    	        }
    	        
    	      if(sound_alarm==99){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0129", "raw", getPackageName());
    	        	
    	        }
    	      if(sound_alarm==71){
  	        	mPlayer.stop();
  	        	resID=getResources().getIdentifier("v0120", "raw", getPackageName());
  	        	}
    	     // if(sound_alarm==72){
    	     //   	mPlayer.stop();
    	     //   	resID=getResources().getIdentifier("v0036", "raw", getPackageName());
    	     //   	}
    	      if(sound_alarm==73){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0118", "raw", getPackageName());
    	        	}  
    	     // if(sound_alarm==74){
    	     //   	mPlayer.stop();
    	     //   	resID=getResources().getIdentifier("v0033", "raw", getPackageName());
    	     //   	}
    	      if(sound_alarm==75){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0116", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==76){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0117", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==77){
  	        	mPlayer.stop();
  	        	resID=getResources().getIdentifier("v0119", "raw", getPackageName());
  	        	}
    	
    	        //******************************************************
    	        		mPlayer=mPlayer.create(this,resID);
                		mPlayer.start();
    	       
	          //*******************************************************
		}
    	if(master_sound==3)
    	{
    		  languagechange=1;
    		   if(sound_alarm==0){
    			   resID=getResources().getIdentifier("punjabi01", "raw", getPackageName());}
    	       if(sound_alarm==1){
    	    	   mPlayer.stop();
    	    	   resID=getResources().getIdentifier("punjabi02", "raw", getPackageName());}
    	        if(sound_alarm==2){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("punjabi03", "raw", getPackageName());}
    	        if(sound_alarm==3){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("punjabi04", "raw", getPackageName());}
    	        if(sound_alarm==14){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("punjabi05", "raw", getPackageName());}
    	        if(sound_alarm==4){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("punjabi06", "raw", getPackageName());}
    	        if(sound_alarm==5){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("punjabi07", "raw", getPackageName());}
    	        if(sound_alarm==21){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0024", "raw", getPackageName());
    	        }
    	        if(sound_alarm==22){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0025", "raw", getPackageName());
    	        }
    	       
    	      if(sound_alarm==99){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0046", "raw", getPackageName());
    	        	
    	        }
    	      if(sound_alarm==71){
  	        	mPlayer.stop();
  	        	resID=getResources().getIdentifier("v0035", "raw", getPackageName());
  	        	}
    	      if(sound_alarm==72){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0036", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==73){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0032", "raw", getPackageName());
    	        	}  
    	      if(sound_alarm==74){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0033", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==75){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0029", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==76){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0031", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==77){
  	        	mPlayer.stop();
  	        	resID=getResources().getIdentifier("v0034", "raw", getPackageName());
  	        	}
    	      //******************************************************
    	        		mPlayer=mPlayer.create(this,resID);
                		mPlayer.start();
    	       
	          //*******************************************************
		}
    	if(master_sound==4)
    	{
    		   languagechange=1;
    		   if(sound_alarm==0){
    			   resID=getResources().getIdentifier("v0063", "raw", getPackageName());}
    	       if(sound_alarm==1){
    	    	   mPlayer.stop();
    	    	   resID=getResources().getIdentifier("v0064", "raw", getPackageName());}
    	        if(sound_alarm==2){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0066", "raw", getPackageName());}
    	        if(sound_alarm==3){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0067", "raw", getPackageName());}
    	        if(sound_alarm==14){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0073", "raw", getPackageName());}
    	        if(sound_alarm==4){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0075", "raw", getPackageName());}
    	        if(sound_alarm==5){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0074", "raw", getPackageName());}
    	        if(sound_alarm==21){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0070", "raw", getPackageName());
    	        }
    	        if(sound_alarm==22){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0071", "raw", getPackageName());
    	        }
    	       
    	      if(sound_alarm==99){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0090", "raw", getPackageName());
    	        	
    	        }
    	      if(sound_alarm==71){
  	        	mPlayer.stop();
  	        	resID=getResources().getIdentifier("v0080", "raw", getPackageName());
  	        	}
    	     // if(sound_alarm==72){
    	     //   	mPlayer.stop();
    	     //   	resID=getResources().getIdentifier("v0036", "raw", getPackageName());
    	     //   	}
    	      if(sound_alarm==73){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0078", "raw", getPackageName());
    	        	}  
    	     // if(sound_alarm==74){
    	     //   	mPlayer.stop();
    	     //   	resID=getResources().getIdentifier("v0033", "raw", getPackageName());
    	     //   	}
    	      if(sound_alarm==75){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0076", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==76){
    	        	mPlayer.stop();
    	        	resID=getResources().getIdentifier("v0077", "raw", getPackageName());
    	        	}
    	      if(sound_alarm==77){
  	        	mPlayer.stop();
  	        	resID=getResources().getIdentifier("v0079", "raw", getPackageName());
  	        	}
    	        //******************************************************
    	        		mPlayer=mPlayer.create(this,resID);
                		mPlayer.start();
    	       
	          //*******************************************************
		}
}
public void onInit(int i)
    {   String xyz=null;
    	if(sound_alarm==0)
    	 xyz = "Hello folks, welcome to andronetra. Please press up volume button for settings and down volume for pattern";
        if(sound_alarm==1)
        	xyz="press speed dial button in order to set speed dials, pre saved Template button to set templates, circle button to set your circles and language button to change language.";
        if(sound_alarm==2)
        	xyz="Click on the number against which you want to assign speed dial then select the contact to assign to that number. Once all contacts finalized press up volume button to go back to settings.";
        if(sound_alarm==3)
        	xyz="Enter the templates you want to assign and click on the done button to finalize your choice";
        if(sound_alarm==14)
        	xyz="Please make pattern for any feature you want to use.     For message or call first make C or M , then the speed dial number";
        if(sound_alarm==4)
        	xyz="The pattern made is M.  Now make template number to which you want to send message.";
        if(sound_alarm==5)
        	xyz="The pattern made is C.  Now make the speed dial number to which you want to call";
        if(sound_alarm==6)
        	xyz=myposition;
        if(sound_alarm==7)
        	xyz=mybattery;
        if(sound_alarm==8)
        	xyz=myaddress;
        if(sound_alarm==9)
        	xyz=timeis;
        if(sound_alarm==21)
        	xyz="Press get co ordinates button to get co ordinates of present location, then set coordinates to set coordinates for that circle";
        if(sound_alarm==22)
        	xyz="Press done button to finalize your language selection.";
        if(sound_alarm==50)
        	xyz=null;
        if(sound_alarm==51)
        	xyz=askmsg;
        if(sound_alarm==71)
        	xyz="do you want to call";
        if(sound_alarm==72)
        	xyz=askcall;
        if(sound_alarm==79)
        	xyz=aaa;
      	if(sound_alarm==99)
        	xyz="Thank you for using andronetra. See u soon. Have a nice day.";
      	if(xyz!=null)
         mTts.speak(xyz,TextToSpeech.QUEUE_FLUSH,null);
    }
public void onDestroy()
    {

        if (mTts != null)
        {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
@Override
public void onBackPressed() {
    // do nothing.
}
public boolean onKeyDown(int keyCode, KeyEvent event) 
    { 
	
	   if(keyCode==KeyEvent.KEYCODE_VOLUME_UP)
       	{ 
    	    
    	   getalldata();
    	   
    	   if(forlang==0)//for the first time
     	   {
    	   setContentView(R.layout.settings);
           
    	   //sound for settings audio help
    	   sound_alarm = 1;
           sound();
           
           //button1(speed dial) on click action 
     	   final Button button = (Button) findViewById(R.id.button1);
           button.setOnClickListener(new View.OnClickListener()
           {
               public void onClick(View v)
               {
            	   setContentView(R.layout.contacts);
            	   
            	   //set values for each button from contacts
            	   final Button b0 = (Button) findViewById(R.id.Button09);
         		   b0.setText(data0);
         		    final Button b1 = (Button) findViewById(R.id.Button08);
         		    b1.setText(data1);
         		    final Button b2 = (Button) findViewById(R.id.Button07);
         		    b2.setText(data2);
         		    final Button b3 = (Button) findViewById(R.id.Button06);
         		    b3.setText(data3);
         		    final Button b4 = (Button) findViewById(R.id.Button05);
         		    b4.setText(data4);
         		    final Button b5 = (Button) findViewById(R.id.Button04);
         		    b5.setText(data5);
         		    final Button b6 = (Button) findViewById(R.id.Button03);
         		    b6.setText(data6);
         		    final Button b7 = (Button) findViewById(R.id.Button02);
         		    b7.setText(data7);
         		    final Button b8 = (Button) findViewById(R.id.Button01);
         		    b8.setText(data8);
         		    final Button b9 = (Button) findViewById(R.id.button1);
         		    b9.setText(data9);
         		    
                   //sound to instruct setting speed dials
         		   sound_alarm=2;
         		   sound();
         		   buttonclicks();
                   }
               private void buttonclicks()
			  {
				
				final Button b0 = (Button) findViewById(R.id.Button09);
			    b0.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=0;
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			    final Button b1 = (Button) findViewById(R.id.Button08);
			    b1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=1;     
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			   
		            
			    final Button b2 = (Button) findViewById(R.id.Button07);
			    b2.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	    flag=2;
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			    final Button b3 = (Button) findViewById(R.id.Button06);
			    b3.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=3;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			    
			    final Button b4 = (Button) findViewById(R.id.Button05);
			    b4.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=4;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });   
 
		
		      
			
			    final Button b5 = (Button) findViewById(R.id.Button04);
			    b5.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=5;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			
			    final Button b6 = (Button) findViewById(R.id.Button03);
			    b6.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=6;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			    final Button b7 = (Button) findViewById(R.id.Button02);
			    b7.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=7;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			    
			    final Button b8 = (Button) findViewById(R.id.Button01);
			    b8.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=8;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
			        }
			    });
			    final Button b9 = (Button) findViewById(R.id.button1);
			    b9.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			            flag=9;    
			    	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			            startActivityForResult(intent, 1);                
				    
			    }
			    });	
			    }}		      
			);
                
           //button2(template settings) on click action
           final Button butto = (Button) findViewById(R.id.button2);
           butto.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   setContentView(R.layout.templates);
                   final EditText	 e0 = (EditText)findViewById(R.id.editText0);
      	    	 e0.setText(temp0);
      	    	 final EditText	 e1 = (EditText)findViewById(R.id.editText1);
      	    	e1.setText(temp1);
      	    	 final EditText	 e2 = (EditText)findViewById(R.id.editText2);
      	    	e2.setText(temp2);
      	    	 final EditText	 e3 = (EditText)findViewById(R.id.editText3);
      	    	e3.setText(temp3);
      	    	 final EditText	 e4 = (EditText)findViewById(R.id.editText4);
      	    	e4.setText(temp4);
      	    	 final EditText	 e5 = (EditText)findViewById(R.id.editText5);
      	    	e5.setText(temp5);
      	    	 final EditText	 e6 = (EditText)findViewById(R.id.editText6);
      	    	e6.setText(temp6);
      	    	 final EditText	 e7 = (EditText)findViewById(R.id.editText7);
      	    	e7.setText(temp7);
      	    	 final EditText	 e8 = (EditText)findViewById(R.id.editText8);
      	    	e8.setText(temp8);
      	    	 final EditText	 e9 = (EditText)findViewById(R.id.editText9);
      	    	e9.setText(temp9);
                   
      	    	   //sound for template settings
      	    	   sound_alarm=3;
                   sound();
                   ondoneintemplates();
            }
           });
           
           //on circles
           final Button bb = (Button) findViewById(R.id.button3);
		    bb.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	setContentView(R.layout.circles);
		    	final Button c1 = (Button) findViewById(R.id.circle1);
		    	c1.setText(pos0);
		    	final Button c2 = (Button) findViewById(R.id.circle2);
		    	c2.setText(pos1);
		    	final Button c3 = (Button) findViewById(R.id.circle3);
		    	c3.setText(pos2);
		    	final Button c4 = (Button) findViewById(R.id.circle4);
		    	c4.setText(pos3);
		    	final Button c5 = (Button) findViewById(R.id.circle5);
		    	c5.setText(pos4);
		    	c1.setOnClickListener(new View.OnClickListener() {
		    	    public void onClick(View v) {
		    	    	circle_flag=1;
		    	    	oncircles();
		    	    	  	    	
		    	                                 }
		    	    });	
		        	c2.setOnClickListener(new View.OnClickListener() {
		        	    public void onClick(View v) {
		        	    	circle_flag=2;oncircles();
		        	    	}
		        	    });
		        	c3.setOnClickListener(new View.OnClickListener() {
		        	    public void onClick(View v) {
		        	    	circle_flag=3;oncircles();
		        	         }
		        	    });
		        	c4.setOnClickListener(new View.OnClickListener() {
		        	    public void onClick(View v) {
		        	    	circle_flag=4;oncircles();                  
		        	    }
		        	    });
		        	c5.setOnClickListener(new View.OnClickListener() {
		        	    public void onClick(View v) {
		        	    	circle_flag=5;oncircles();                  
		        	    }
		        	    });
		        	
		    	
		        }
		    
		    });
		    
		    //languages
		    final Button l = (Button) findViewById(R.id.button4);
		    l.setOnClickListener(new View.OnClickListener() {
        	    public void onClick(View v) {
        	    	setContentView(R.layout.language);  
        	    	sound_alarm=22;
        	    	sound();
        	    	final Button ll = (Button) findViewById(R.id.donel);
        	    	ll.setOnClickListener(new View.OnClickListener() {
		        	    public void onClick(View v) {
		        	    	RadioButton le = (RadioButton) findViewById(R.id.english);
		        	    	RadioButton lh = (RadioButton) findViewById(R.id.hindi);
		        	    	RadioButton lm = (RadioButton) findViewById(R.id.marathi);
		        	    	RadioButton lp = (RadioButton) findViewById(R.id.punjabi);
		        	    	Lang lno=new Lang(getApplicationContext());
		        	    	lno.open();
		        	    	if(le.isChecked()){
		        	         lno.updateTemp(1);
		        	         master_sound=1;
		        	        }
		        	        if(lp.isChecked()){
		        	        	  lno.updateTemp(3);
		        	        	  master_sound=3;
		        	        }
		        	        if(lm.isChecked()){
		        	        	  lno.updateTemp(4);
		        	        	  master_sound=4;
		        	        }
		        	        if(lh.isChecked()){
		        	        	  lno.updateTemp(2);
		        	        	  master_sound=2;
		        	        }
		        	        lno.close();
		        	        setContentView(R.layout.settings);      		        	
		        	   }
		        	    });
		        	
        	    	    }
        	    });
        	
     	   }

    	   if(forlang==1){
    		   
     		   if (numberc==0)  { call(flagforcall); }
   		   
     		   if(numberc==1)  {  sendmsg(flagforcall);}
     		   
     	   }
       	}
       
       	else 
       	{  
       		if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
       	{
       		    if(forlang==0)
       		    {
       			sound_alarm=14;
       		    sound();
       			pattern();
       			
       		    }
       		    if(forlang==1){
       		    	setContentView(R.layout.gesture13);
       		    	forontouch=1;    
       		    	numberc=tempcontact;
       		    	pattern_flag=1;
			     }
       			
       	}else
           return super.onKeyDown(keyCode, event); 
        }
    		return true;
}
@Override
public boolean onTouchEvent(MotionEvent e)
{  
    if(forontouch==1){
	TextView ttv=(TextView)findViewById(R.id.tv);
	ttv.setText(Float.toString(x)+"x"+Float.toString(y));
    x1=x;
    y1=y;
    x = e.getX();
    y = e.getY();
    total=total+1;
    if(x1==x && y1==y)
    {
         if(total1==1)
        	total1=0;
        else
        {
        	
               	   preprocessing();
        }
     
    }
    else
     {
        arr[(int) x][(int)y]=0;
       
        total1=total1+1;
     
     }
           return true;    
    }
    else
    	return true;
}
public void oncircles(){
    	setContentView(R.layout.setcircle);
    	final Button setc = (Button) findViewById(R.id.setc);
    	final Button getc = (Button) findViewById(R.id.getc);
    	final EditText plat=(EditText)findViewById(R.id.plat);
    	final EditText plon=(EditText)findViewById(R.id.plon);
    	final EditText pname=(EditText)findViewById(R.id.circlename);
    	sound_alarm=21;
    	sound();
    	setc.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View v) {
    	            String platitude,plongitude;
    	            String namec;
    	            
    	            platitude=plat.getText().toString();
    	            plongitude=plon.getText().toString();
    	            namec=pname.getText().toString(); 
    	            
    	            Position pos=new Position(getApplicationContext());
    	            pos.open();
    	            pos.updatePos(circle_flag,namec,platitude,plongitude);
    	            pos.close();
    	            setContentView(R.layout.circles);
    	    }
    	    }); 
    	getc.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View v) {
    	    	String provide;
    	        Location location;
    	        
    	        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	    	Criteria criteria = new Criteria();
    	    	provide = lm.getBestProvider(criteria, false);
    	    	location = lm.getLastKnownLocation(provide);
    	        if(location!=null)
    	        {
    	    	pabc=location.getLatitude();
    	        pxyz=location.getLongitude();
    	        }
    	        else
    	        {
    	        pabc=0;
    	        pxyz=0;
    	        }	            
    	        plat.setText(Double.toString(pabc));
	            plon.setText(Double.toString(pxyz));
    	            
    	    }
    	    });
    
    }
public void ondoneintemplates(){
    	final Button d0 = (Button) findViewById(R.id.button1);
	    d0.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
	    	String t0=null,t1=null,t2=null,t3=null,t4=null;
	    	String t5=null,t6=null,t7=null,t8=null,t9=null;	
	    	Template temp1=new Template(getApplicationContext());
	    	temp1.open();
	    	final EditText	 e0 = (EditText)findViewById(R.id.editText0);
	    	 t0 = e0.getText().toString();
	    	 temp1.updateTemp(0,t0);
	    	 final EditText	 e1 = (EditText)findViewById(R.id.editText1);
	    	 t1 = e1.getText().toString();
	    	 temp1.updateTemp(1,t1);
	    	 final EditText	 e2 = (EditText)findViewById(R.id.editText2);
	    	 t2 = e2.getText().toString();
	    	 temp1.updateTemp(2,t2);
	    	 final EditText	 e3 = (EditText)findViewById(R.id.editText3);
	    	 t3 = e3.getText().toString();
	    	 temp1.updateTemp(3,t3);
	    	 final EditText	 e4 = (EditText)findViewById(R.id.editText4);
	    	 t4 = e4.getText().toString();
	    	 temp1.updateTemp(4,t4);
	    	 final EditText	 e5 = (EditText)findViewById(R.id.editText5);
	    	 t5 = e5.getText().toString();
	    	 temp1.updateTemp(5,t5);
	    	 final EditText	 e6 = (EditText)findViewById(R.id.editText6);
	    	 t6 = e6.getText().toString();
	    	 temp1.updateTemp(6,t6);
	    	 final EditText	 e7 = (EditText)findViewById(R.id.editText7);
	    	 t7 = e7.getText().toString();
	    	 temp1.updateTemp(7,t7);
	    	 final EditText	 e8 = (EditText)findViewById(R.id.editText8);
	    	 t8 = e8.getText().toString();
	    	 temp1.updateTemp(8,t8);
	    	 final EditText	 e9 = (EditText)findViewById(R.id.editText9);
	    	 t9 = e9.getText().toString();
	    	 temp1.updateTemp(9,t9);
	    	 temp1.close();
	     	   setContentView(R.layout.settings);             	    
	    }
	    });
    }
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == MY_DATA_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                // success, create the TTS instance
                mTts = new TextToSpeech(this, this);
            }
            else
            {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    	if (data != null) {
            Uri uri = data.getData();

            if (uri != null)
            {
                Cursor c = null;
                try 
                {
                     c = getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER },null, null, null);
                     if (c != null && c.moveToFirst())
                     {
                       number = c.getString(0);
                       String no=c.getString(1);
                     switch(flag){
                        case 0:Button b0 = (Button) findViewById(R.id.Button09);
                               b0.setText(number);
                               String name = b0.getText().toString();
                               Contact entry = new Contact(this);
                               entry.open();
                               entry.updateEmployee(0,name,no);
                               entry.close();
                               break;
                        case 1:Button b1 = (Button) findViewById(R.id.Button08);
                               b1.setText(number);
                              String name1 = b1.getText().toString();
                               Contact entry1 = new Contact(this);
                               entry1.open();
                               entry1.updateEmployee(1,name1,no);
                               entry1.close();
                               break;
                        case 2:Button b2 = (Button) findViewById(R.id.Button07);
                        	   b2.setText(number);
                        	   String name2 = b2.getText().toString();
                        	   Contact entry2 = new Contact(this);
                               entry2.open();
                               entry2.updateEmployee(2,name2,no);
                               entry2.close();
                               break;
                        case 3:Button b3 = (Button) findViewById(R.id.Button06);
                        		b3.setText(number);
                        		String name3 = b3.getText().toString();
                        		Contact entry3 = new Contact(this);
                                entry3.open();
                                entry3.updateEmployee(3,name3,no);
                                entry3.close();
                        		break;
                        case 4:Button b4 = (Button) findViewById(R.id.Button05);
                        		b4.setText(number);
                        		String name4 = b4.getText().toString();
                        		Contact entry4 = new Contact(this);
                                entry4.open();
                                entry4.updateEmployee(4,name4,no);
                                entry4.close();
                        		break;
                        case 5:Button b5 = (Button) findViewById(R.id.Button04);
                        		b5.setText(number);
                        		String name5 = b5.getText().toString();
                        		Contact entry5 = new Contact(this);
                                entry5.open();
                                entry5.updateEmployee(5,name5,no);
                                entry5.close();
                        		break;
                        case 6:Button b6 = (Button) findViewById(R.id.Button03);
                        		b6.setText(number);
                        		String name6 = b6.getText().toString();
                        		Contact entry6 = new Contact(this);
                                entry6.open();
                                entry6.updateEmployee(6,name6,no);
                                entry6.close();
                        		break;
                        case 7:Button b7 = (Button) findViewById(R.id.Button02);
                        		b7.setText(number);
                        		String name7 = b7.getText().toString();
                        		Contact entry7 = new Contact(this);
                                entry7.open();
                                entry7.updateEmployee(7,name7,no);
                                entry7.close();break;
                        case 8:Button b8 = (Button) findViewById(R.id.Button01);
                        	   b8.setText(number);
                        	   String name8 = b8.getText().toString();
                               Contact entry8 = new Contact(this);
                               entry8.open();
                               entry8.updateEmployee(8,name8,no);
                               entry8.close();
                               break;
                        case 9:Button b9 = (Button) findViewById(R.id.button1);
                               b9.setText(number);
                               String name9 = b9.getText().toString();
                               Contact entry9 = new Contact(this);
                               entry9.open();
                               entry9.updateEmployee(9,name9,no);
                               entry9.close();
                               break;
                 		
                        }
                    }
                } 
                finally 
                {
                   if (c != null) 
                        c.close();
                }
            }
        }
		}
public void preprocessing() {
	
croparr();

	algo13pt();
	entryintodb();
	   	
}
private void croparr() {
	int i,j;
	cnt=0;
	
	for(i=0;i<getwd;i++)
	for(j=0;j<getht;j++)
	if(arr[i][j]!=1)
	{
		
	for(j=0;j<getht;j++)
	temp[cnt][j]=arr[i][j];
	cnt++;
	break;
	}
	cnt1=0;
	for(j=0;j<getht;j++)
	for(i=0;i<cnt;i++)
	{
	if(temp[i][j]!=1)
	{
	for(i=0;i<cnt;i++)
	crop[i][cnt1]=temp[i][j];
	
	cnt1++;
	break;
	}

	}
	for(i=0;i<getwd;i++)
    	for(j=0;j<getht;j++)
    	if(crop[i][j]!=1)
    	{
    		report=report+1;
    	}
	}
private void algo13pt() {
	
	
		for(int i=0;i<13;i++)
		pt23arr[i]=0;
int divx,divy,remx,remy,i,j;
remx=cnt%4;
if(remx>=(4-remx))
divx=(cnt+(4-remx))/4;
else
divx=cnt/4;

remy=cnt1%2;
if(remy>=(2-remy))
divy=(cnt1+(2-remy))/2;
else
divy=cnt1/2;

for(i=0;i<divy;i++)
	for(j=0;j<divx;j++)
		if(crop[j][i]==0)
		pt23arr[0]++;

for(i=divy;i<(2*divy+remy);i++)
	for(j=0;j<divx;j++)
		if(crop[j][i]==0)
		pt23arr[1]++;


for(i=0;i<divy;i++)
	for(j=divx;j<2*divx;j++)
		if(crop[j][i]==0)
		pt23arr[2]++;


for(i=divy;i<(2*divy+remy);i++)
	for(j=divx;j<2*divx;j++)
		if(crop[j][i]==0)
		pt23arr[3]++;

for(i=0;i<divy;i++)
	for(j=2*divx;j<3*divx;j++)
		if(crop[j][i]==0)
		pt23arr[4]++;

for(i=divy;i<(2*divy+remy);i++)
	for(j=2*divx;j<3*divx;j++)
		if(crop[j][i]==0)
		pt23arr[5]++;


    for(i=0;i<divy;i++)
	for(j=3*divx;j<(4*divx+remx);j++)
		if(crop[j][i]==0)
		pt23arr[6]++;

for(i=divy;i<(2*divy+remy);i++)
	for(j=3*divx;j<(4*divx+remx);j++)
		if(crop[j][i]==0)
		pt23arr[7]++;


pt23arr[8]=pt23arr[2]+pt23arr[3];
pt23arr[9]=pt23arr[5]+pt23arr[4];
pt23arr[10]=pt23arr[0]+pt23arr[2]+pt23arr[4]+pt23arr[6];
pt23arr[11]=pt23arr[3]+pt23arr[1]+pt23arr[5]+pt23arr[7];
pt23arr[12]=pt23arr[10]+pt23arr[11];

}
void entryintodb(){
	
    db_entries();
  	if(pattern_flag==1)
	{
		
		minval=999;
		
		for(i=0;i<95;i++)
		{
			
			dev=0;
			for(int j=1;j<14;j++)
					dev=dev+Math.abs(pt23arr[j-1]-db[i][j]);
			
			if(dev<minval)
			{
				minval=dev;
				rowno=i;
			}
		
		}
		
	   if(db[rowno][0]==0){
			second_var=0;
			patt13="Zero";
            			
		}
		if(db[rowno][0]==1){
			second_var=1;
			patt13="One";
			
		}
		if(db[rowno][0]==2){
			second_var=2;
			patt13="Two";
			
		}
		if(db[rowno][0]==3){
		second_var=3;
		patt13="Three";
		
		}
		if(db[rowno][0]==4){
			second_var=4;
			patt13="Four";
		
		}
		if(db[rowno][0]==5){
			second_var=5;
			patt13="Five";
		
		}
		if(db[rowno][0]==6){
			second_var=6;
			patt13="Six";
		
		}
		if(db[rowno][0]==7){
		second_var=7;
		patt13="Seven";
		
		}
		if(db[rowno][0]==8){
			second_var=8;
			patt13="Eight";
		
		}
		if(db[rowno][0]==9){
			second_var=9;
			patt13="Nine";
		
		}
		
	}
patt=patt13;

recognize();
}
public void db_entries(){
	 	
		db[0][0]=0;	db[0][1]=1;	 db[0][2]=0; db[0][3]=2;  db[0][4]=0;  db[0][5]=0;  db[0][6]=0;  db[0][7]=0;  db[0][8]=0;  db[0][9]=2;	db[0][10]=0;  db[0][11]=3;  db[0][12]=0;  db[0][13]=3;
		db[1][0]=0;	db[1][1]=2;	 db[1][2]=0; db[1][3]=1;  db[1][4]=1;  db[1][5]=0;  db[1][6]=0;  db[1][7]=0;  db[1][8]=0;  db[1][9]=2;	db[1][10]=0;  db[1][11]=3;  db[1][12]=1;  db[1][13]=4;
		db[2][0]=0;	db[2][1]=0;	 db[2][2]=0; db[2][3]=2;  db[2][4]=0;  db[2][5]=2;  db[2][6]=0;  db[2][7]=0;  db[2][8]=1;  db[2][9]=2;	db[2][10]=2;  db[2][11]=4;  db[2][12]=1;  db[2][13]=5;
		db[3][0]=0;	db[3][1]=5;	 db[3][2]=6; db[3][3]=7;  db[3][4]=0;  db[3][5]=1;  db[3][6]=5;  db[3][7]=0;  db[3][8]=0;  db[3][9]=7;	db[3][10]=6;  db[3][11]=13; db[3][12]=11; db[3][13]=24;
		db[4][0]=0;	db[4][1]=0;	 db[4][2]=0; db[4][3]=0;  db[4][4]=0;  db[4][5]=1;  db[4][6]=2;  db[4][7]=5;  db[4][8]=0;  db[4][9]=0;	db[4][10]=3;  db[4][11]=6;  db[4][12]=2;  db[4][13]=8;
		db[5][0]=0;	db[5][1]=1;	 db[5][2]=0; db[5][3]=8;  db[5][4]=1;  db[5][5]=10; db[5][6]=2;  db[5][7]=0;  db[5][8]=1;  db[5][9]=9;	db[5][10]=12; db[5][11]=19; db[5][12]=4;  db[5][13]=23;
		db[6][0]=0;	db[6][1]=0;	 db[6][2]=0; db[6][3]=5;  db[6][4]=0;  db[6][5]=0;  db[6][6]=1;  db[6][7]=0;  db[6][8]=0;  db[6][9]=5;	db[6][10]=1;  db[6][11]=5;  db[6][12]=1;  db[6][13]=6;
		db[7][0]=0;	db[7][1]=0;	 db[7][2]=0; db[7][3]=4;  db[7][4]=1;  db[7][5]=2;  db[7][6]=3;  db[7][7]=0;  db[7][8]=0;  db[7][9]=5;	db[7][10]=5;  db[7][11]=6;  db[7][12]=4;  db[7][13]=10;
		db[8][0]=1;	db[8][1]=4;	 db[8][2]=0; db[8][3]=4;  db[8][4]=0;  db[8][5]=0;  db[8][6]=4;  db[8][7]=0;  db[8][8]=1;  db[8][9]=4;	db[8][10]=4;  db[8][11]=8;  db[8][12]=5;  db[8][13]=13;
		db[9][0]=1;	db[9][1]=0;	 db[9][2]=9; db[9][3]=0;  db[9][4]=8;  db[9][5]=9;  db[9][6]=0;  db[9][7]=9;  db[9][8]=0;  db[9][9]=8;	db[9][10]=9;  db[9][11]=18; db[9][12]=17; db[9][13]=35;
		db[10][0]=1;db[10][1]=10;db[10][2]=0;db[10][3]=8; db[10][4]=1; db[10][5]=0; db[10][6]=9; db[10][7]=0; db[10][8]=7; db[10][9]=9; db[10][10]=9; db[10][11]=18;db[10][12]=17;db[10][13]=35;
		db[11][0]=1;db[11][1]=0; db[11][2]=5;db[11][3]=1; db[11][4]=5; db[11][5]=6; db[11][6]=0; db[11][7]=4; db[11][8]=0; db[11][9]=6; db[11][10]=6; db[11][11]=11;db[11][12]=10;db[11][13]=21;
		db[12][0]=1;db[12][1]=4; db[12][2]=1;db[12][3]=12;db[12][4]=0; db[12][5]=12;db[12][6]=0; db[12][7]=7; db[12][8]=2; db[12][9]=12;db[12][10]=12;db[12][11]=35;db[12][12]=3; db[12][13]=38;
		db[13][0]=1;db[13][1]=7; db[13][2]=6;db[13][3]=1; db[13][4]=0; db[13][5]=0; db[13][6]=0; db[13][7]=0; db[13][8]=0; db[13][9]=1; db[13][10]=0; db[13][11]=8; db[13][12]=6; db[13][13]=14;
		db[14][0]=1;db[14][1]=0; db[14][2]=0;db[14][3]=0; db[14][4]=0; db[14][5]=0; db[14][6]=0; db[14][7]=8; db[14][8]=7; db[14][9]=0; db[14][10]=0; db[14][11]=8; db[14][12]=7; db[14][13]=15;
		db[15][0]=1;db[15][1]=8; db[15][2]=4;db[15][3]=0; db[15][4]=3; db[15][5]=0; db[15][6]=0; db[15][7]=0; db[15][8]=0; db[15][9]=3; db[15][10]=0; db[15][11]=8 ;db[15][12]=7; db[15][13]=15;
 	    db[16][0]=2;db[16][1]=7; db[16][2]=1;db[16][3]=3; db[16][4]=11;db[16][5]=10;db[16][6]=6; db[16][7]=7; db[16][8]=0; db[16][9]=14;db[16][10]=16;db[16][11]=27;db[16][12]=18;db[16][13]=45;
	    db[17][0]=2;db[17][1]=4; db[17][2]=8;db[17][3]=2; db[17][4]=0; db[17][5]=8; db[17][6]=5; db[17][7]=0; db[17][8]=0; db[17][9]=2; db[17][10]=13;db[17][11]=14;db[17][12]=13;db[17][13]=27;
		db[18][0]=2;db[18][1]=15;db[18][2]=3;db[18][3]=0; db[18][4]=13;db[18][5]=3; db[18][6]=11;db[18][7]=14;db[18][8]=2; db[18][9]=13;db[18][10]=14;db[18][11]=32;db[18][12]=29;db[18][13]=61;
		db[19][0]=2;db[19][1]=2; db[19][2]=5;db[19][3]=4; db[19][4]=2; db[19][5]=5; db[19][6]=2; db[19][7]=4; db[19][8]=0; db[19][9]=6; db[19][10]=7; db[19][11]=15;db[19][12]=9; db[19][13]=24;
		db[20][0]=2;db[20][1]=0; db[20][2]=9;db[20][3]=4; db[20][4]=4; db[20][5]=10;db[20][6]=2; db[20][7]=0; db[20][8]=0; db[20][9]=8; db[20][10]=12;db[20][11]=14;db[20][12]=15;db[20][13]=29;
		db[21][0]=2;db[21][1]=5; db[21][2]=4;db[21][3]=4; db[21][4]=5; db[21][5]=10;db[21][6]=2; db[21][7]=0; db[21][8]=0; db[21][9]=9; db[21][10]=12;db[21][11]=19;db[21][12]=11;db[21][13]=13;
		db[22][0]=2;db[22][1]=5; db[22][2]=9;db[22][3]=3; db[22][4]=5; db[22][5]=7; db[22][6]=2; db[22][7]=0; db[22][8]=0; db[22][9]=8; db[22][10]=9; db[22][11]=15;db[22][12]=16;db[22][13]=31;
		db[23][0]=2;db[23][1]=0; db[23][2]=3;db[23][3]=2; db[23][4]=0; db[23][5]=5; db[23][6]=2; db[23][7]=0; db[23][8]=0; db[23][9]=2; db[23][10]=7; db[23][11]=7; db[23][12]=5; db[23][13]=12;
		db[24][0]=2;db[24][1]=13;db[24][2]=1;db[24][3]=0; db[24][4]=12;db[24][5]=0; db[24][6]=8; db[24][7]=12;db[24][8]=7; db[24][9]=12;db[24][10]=8; db[24][11]=25;db[24][12]=28;db[24][13]=53;
		db[25][0]=2;db[25][1]=7; db[25][2]=2;db[25][3]=4; db[25][4]=6; db[25][5]=6; db[25][6]=5; db[25][7]=6; db[25][8]=3; db[25][9]=10;db[25][10]=11;db[25][11]=23;db[25][12]=16;db[25][13]=29;
        db[26][0]=3;db[26][1]=4; db[26][2]=4;db[26][3]=4; db[26][4]=7; db[26][5]=7; db[26][6]=7; db[26][7]=11;db[26][8]=12;db[26][9]=11;db[26][10]=14;db[26][11]=26;db[26][12]=30;db[26][13]=56;
		db[27][0]=3;db[27][1]=8; db[27][2]=2;db[27][3]=5; db[27][4]=5; db[27][5]=11;db[27][6]=4; db[27][7]=3; db[27][8]=9; db[27][9]=10;db[27][10]=15;db[27][11]=27;db[27][12]=20;db[27][13]=47;
		db[27][0]=3;db[27][1]=6; db[27][2]=0;db[27][3]=9; db[27][4]=1; db[27][5]=12;db[27][6]=3; db[27][7]=0; db[27][8]=10;db[27][9]=10;db[27][10]=15;db[27][11]=27;db[27][12]=14;db[27][13]=41;
		db[28][0]=3;db[28][1]=1; db[28][2]=8;db[28][3]=1; db[28][4]=1; db[28][5]=12;db[28][6]=1; db[28][7]=2; db[28][8]=11;db[28][9]=9; db[28][10]=13;db[28][11]=23;db[28][12]=14;db[28][13]=37;
		db[29][0]=3;db[29][1]=4; db[29][2]=3;db[29][3]=8; db[29][4]=2; db[29][5]=13;db[29][6]=2; db[29][7]=3; db[29][8]=11;db[29][9]=10;db[29][10]=15;db[29][11]=28;db[29][12]=18;db[29][13]=46;
		db[30][0]=3;db[30][1]=2; db[30][2]=1;db[30][3]=4; db[30][4]=6; db[30][5]=8; db[30][6]=4; db[30][7]=6; db[30][8]=11;db[30][9]=10;db[30][10]=12;db[30][11]=20;db[30][12]=22;db[30][13]=42;
		db[31][0]=3;db[31][1]=4; db[31][2]=7;db[31][3]=2; db[31][4]=10;db[31][5]=14;db[31][6]=4; db[31][7]=8; db[31][8]=6; db[31][9]=12;db[31][10]=18;db[31][11]=28;db[31][12]=27;db[31][13]=55;
		db[32][0]=3;db[32][1]=2; db[32][2]=0;db[32][3]=8; db[32][4]=6; db[32][5]=4; db[32][6]=8; db[32][7]=10;db[32][8]=12;db[32][9]=14;db[32][10]=12;db[32][11]=24;db[32][12]=26;db[32][13]=15;
		db[33][0]=3;db[33][1]=5; db[33][2]=0;db[33][3]=5; db[33][4]=5; db[33][5]=12;db[33][6]=1; db[33][7]=1; db[33][8]=9; db[33][9]=10;db[33][10]=13;db[33][11]=23;db[33][12]=15;db[33][13]=38;
		db[34][0]=3;db[34][1]=7; db[34][2]=0;db[34][3]=6; db[34][4]=3; db[34][5]=9; db[34][6]=3; db[34][7]=2; db[34][8]=7; db[34][9]=9; db[34][10]=12;db[34][11]=24;db[34][12]=13;db[34][13]=37;
		db[35][0]=3;db[35][1]=7; db[35][2]=1;db[35][3]=4; db[35][4]=7; db[35][5]=5; db[35][6]=4; db[35][7]=2; db[35][8]=5; db[35][9]=11;db[35][10]=9;db[35][11]=18;db[35][12]=17;db[35][13]=35;
		db[36][0]=4;db[36][1]=6; db[36][2]=4;db[36][3]=5; db[36][4]=2; db[36][5]=2; db[36][6]=6; db[36][7]=0; db[36][8]=10;db[36][9]=7; db[36][10]=8; db[36][11]=13;db[36][12]=22;db[36][13]=35;
		db[37][0]=4;db[37][1]=2; db[37][2]=5;db[37][3]=2; db[37][4]=2; db[37][5]=1; db[37][6]=5; db[37][7]=2; db[37][8]=0; db[37][9]=4; db[37][10]=6; db[37][11]=7; db[37][12]=12;db[37][13]=19;
		db[38][0]=4;db[38][1]=2; db[38][2]=2;db[38][3]=2; db[38][4]=2; db[38][5]=2; db[38][6]=2; db[38][7]=0; db[38][8]=3; db[38][9]=4; db[38][10]=4; db[38][11]=6; db[38][12]=9; db[38][13]=15;
		db[39][0]=4;db[39][1]=2; db[39][2]=3;db[39][3]=5; db[39][4]=2; db[39][5]=0; db[39][6]=4; db[39][7]=2; db[39][8]=0; db[39][9]=7; db[39][10]=4; db[39][11]=9; db[39][12]=9; db[39][13]=18;
		db[40][0]=4;db[40][1]=2; db[40][2]=3;db[40][3]=3; db[40][4]=2; db[40][5]=2; db[40][6]=3; db[40][7]=3; db[40][8]=2; db[40][9]=5; db[40][10]=5; db[40][11]=10;db[40][12]=10;db[40][13]=20;
		db[41][0]=4;db[41][1]=0; db[41][2]=6;db[41][3]=3; db[41][4]=2; db[41][5]=2; db[41][6]=2; db[41][7]=6; db[41][8]=1; db[41][9]=4; db[41][10]=5; db[41][11]=11;db[41][12]=11;db[41][13]=22;
		db[42][0]=4;db[42][1]=0; db[42][2]=7;db[42][3]=4; db[42][4]=1; db[42][5]=1; db[42][6]=3; db[42][7]=9; db[42][8]=3; db[42][9]=5; db[42][10]=4; db[42][11]=14;db[42][12]=14;db[42][13]=28;
		db[43][0]=4;db[43][1]=1; db[43][2]=5;db[43][3]=4; db[43][4]=1; db[43][5]=2; db[43][6]=3; db[43][7]=0; db[43][8]=5; db[43][9]=5; db[43][10]=5; db[43][11]=7; db[43][12]=14;db[43][13]=21;
		db[44][0]=4;db[44][1]=2; db[44][2]=4;db[44][3]=2; db[44][4]=3; db[44][5]=3; db[44][6]=3; db[44][7]=8; db[44][8]=7; db[44][9]=6; db[44][10]=15;db[44][11]=17;db[44][12]=11;db[44][13]=32;
		db[45][0]=4;db[45][1]=1; db[45][2]=6;db[45][3]=3; db[45][4]=4; db[45][5]=2; db[45][6]=3; db[45][7]=2; db[45][8]=2; db[45][9]=7; db[45][10]=5; db[45][11]=8; db[45][12]=15;db[45][13]=23;
		db[46][0]=5;db[46][1]=10;db[46][2]=0;db[46][3]=9; db[46][4]=0; db[46][5]=5; db[46][6]=8; db[46][7]=4; db[46][8]=11;db[46][9]=9; db[46][10]=13;db[46][11]=28;db[46][12]=19;db[46][13]=47;
		db[47][0]=5;db[47][1]=5; db[47][2]=3;db[47][3]=4; db[47][4]=4; db[47][5]=3; db[47][6]=8; db[47][7]=5; db[47][8]=6; db[47][9]=8; db[47][10]=11;db[47][11]=17;db[47][12]=21;db[47][13]=38;
		db[48][0]=5;db[48][1]=2; db[48][2]=0;db[48][3]=3; db[48][4]=0; db[48][5]=4; db[48][6]=1; db[48][7]=1; db[48][8]=7; db[48][9]=3; db[48][10]=5; db[48][11]=10;db[48][12]=8; db[48][13]=18;
		db[49][0]=5;db[49][1]=0; db[49][2]=0;db[49][3]=7; db[49][4]=1; db[49][5]=9; db[49][6]=1; db[49][7]=0; db[49][8]=11;db[49][9]=8; db[49][10]=10;db[49][11]=16;db[49][12]=13;db[49][13]=29;
		db[50][0]=5;db[50][1]=12;db[50][2]=4;db[50][3]=5; db[50][4]=0; db[50][5]=6; db[50][6]=0; db[50][7]=3; db[50][8]=9; db[50][9]=5; db[50][10]=6; db[50][11]=26;db[50][12]=13;db[50][13]=39;
		db[51][0]=5;db[51][1]=11;db[51][2]=0;db[51][3]=8; db[51][4]=0; db[51][5]=5; db[51][6]=2; db[51][7]=4; db[51][8]=8; db[51][9]=8; db[51][10]=7; db[51][11]=28;db[51][12]=10;db[51][13]=38;
		db[52][0]=5;db[52][1]=10;db[52][2]=2;db[52][3]=4; db[52][4]=3; db[52][5]=4; db[52][6]=2; db[52][7]=1; db[52][8]=7; db[52][9]=7; db[52][10]=6; db[52][11]=19;db[52][12]=14;db[52][13]=33;
		db[53][0]=5;db[53][1]=19;db[53][2]=0;db[53][3]=11;db[53][4]=1; db[53][5]=11;db[53][6]=4; db[53][7]=4; db[53][8]=12;db[53][9]=12;db[53][10]=15;db[53][11]=35;db[53][12]=17;db[53][13]=52;
		db[54][0]=5;db[54][1]=6; db[54][2]=5;db[54][3]=9; db[54][4]=2; db[54][5]=6; db[54][6]=2; db[54][7]=3; db[54][8]=10;db[54][9]=11;db[54][10]=8; db[54][11]=24;db[54][12]=19;db[54][13]=43;
		db[55][0]=5;db[55][1]=11;db[55][2]=5;db[55][3]=3; db[55][4]=2; db[55][5]=7; db[55][6]=1; db[55][7]=5; db[55][8]=8; db[55][9]=5; db[55][10]=8; db[55][11]=26;db[55][12]=16;db[55][13]=42;
		db[56][0]=5;db[56][1]=11;db[56][2]=5;db[56][3]=8; db[56][4]=1; db[56][5]=7; db[56][6]=1; db[56][7]=4; db[56][8]=9; db[56][9]=9; db[56][10]=8; db[56][11]=30;db[56][12]=16;db[56][13]=46;
		db[57][0]=6;db[57][1]=1; db[57][2]=8;db[57][3]=8; db[57][4]=2; db[57][5]=12;db[57][6]=2; db[57][7]=6; db[57][8]=2; db[57][9]=10;db[57][10]=14;db[57][11]=27;db[57][12]=14;db[57][13]=41;
		db[58][0]=6;db[58][1]=0; db[58][2]=4;db[58][3]=3; db[58][4]=5; db[58][5]=4; db[58][6]=1; db[58][7]=5; db[58][8]=0; db[58][9]=8;	db[58][10]=5; db[58][11]=12;db[58][12]=10;db[58][13]=22;
		db[59][0]=6;db[59][1]=1; db[59][2]=4;db[59][3]=4; db[59][4]=3; db[59][5]=7;	db[59][6]=0; db[59][7]=2; db[59][8]=3; db[59][9]=7;	db[59][10]=7; db[59][11]=14;db[59][12]=10;db[59][13]=24;
		db[60][0]=6;db[60][1]=1; db[60][2]=5;db[60][3]=4; db[60][4]=4; db[60][5]=4;	db[60][6]=2; db[60][7]=2; db[60][8]=0; db[60][9]=8;	db[60][10]=6; db[60][11]=11;db[60][12]=11;db[60][13]=22;		
		db[61][0]=6;db[61][1]=0; db[61][2]=3;db[61][3]=1; db[61][4]=2; db[61][5]=4;	db[61][6]=0; db[61][7]=6; db[61][8]=0; db[61][9]=3;	db[61][10]=4; db[61][11]=11;db[61][12]=5; db[61][13]=16;
		db[62][0]=6;db[62][1]=0; db[62][2]=8;db[62][3]=6; db[62][4]=0; db[62][5]=4;	db[62][6]=0; db[62][7]=3; db[62][8]=3; db[62][9]=6;	db[62][10]=4; db[62][11]=13;db[62][12]=11;db[62][13]=24;
		db[63][0]=6;db[63][1]=0; db[63][2]=7;db[63][3]=6; db[63][4]=0; db[63][5]=6;	db[63][6]=2; db[63][7]=0; db[63][8]=1; db[63][9]=6;	db[63][10]=8; db[63][11]=12;db[63][12]=10;db[63][13]=22;
		db[64][0]=6;db[64][1]=0; db[64][2]=8;db[64][3]=7; db[64][4]=2; db[64][5]=8;	db[64][6]=0; db[64][7]=1; db[64][8]=3; db[64][9]=9;	db[64][10]=8; db[64][11]=16;db[64][12]=13;db[64][13]=29;
		db[65][0]=6;db[65][1]=2;db[65][2]=6; db[65][3]=4; db[65][4]=5; db[65][5]=7;	db[65][6]=1; db[65][7]=0; db[65][8]=2; db[65][9]=9;	db[65][10]=8; db[65][11]=13;db[65][12]=14;db[65][13]=27;
	    db[66][0]=7;db[66][1]=4;db[66][2]=8; db[66][3]=1; db[66][4]=6; db[66][5]=8;	db[66][6]=0; db[66][7]=8; db[66][8]=0; db[66][9]=7;	db[66][10]=8; db[66][11]=21;db[66][12]=14;db[66][13]=35;
		db[67][0]=7;db[67][1]=4;db[67][2]=9; db[67][3]=1; db[67][4]=5; db[67][5]=6;	db[67][6]=0; db[67][7]=8; db[67][8]=0; db[67][9]=6;	db[67][10]=6; db[67][11]=19;db[67][12]=14;db[67][13]=33;
		db[68][0]=7;db[68][1]=0;db[68][2]=7; db[68][3]=2; db[68][4]=0; db[68][5]=2; db[68][6]=5; db[68][7]=7; db[68][8]=3; db[68][9]=2; db[68][10]=7; db[68][11]=11;db[68][12]=15;db[68][13]=26;
		db[69][0]=7;db[69][1]=4;db[69][2]=6; db[69][3]=1; db[69][4]=5; db[69][5]=4; db[69][6]=2; db[69][7]=7; db[69][8]=0;db[69][9]=6;	db[69][10]=6; db[69][11]=16;db[69][12]=13;db[69][13]=29;
		db[70][0]=7;db[70][1]=4;db[70][2]=3; db[70][3]=0; db[70][4]=6; db[70][5]=6;	db[70][6]=0; db[70][7]=6; db[70][8]=0;db[70][9]=6;	db[70][10]=6; db[70][11]=16;db[70][12]=9; db[70][13]=25;
		db[71][0]=7;db[71][1]=4;db[71][2]=5; db[71][3]=2; db[71][4]=3; db[71][5]=7; db[71][6]=0; db[71][7]=7; db[71][8]=0;db[71][9]=5;  db[71][10]=7; db[71][11]=20;db[71][12]=8; db[71][13]=28;
		db[72][0]=7;db[72][1]=2;db[72][2]=4; db[72][3]=1; db[72][4]=8; db[72][5]=8; db[72][6]=0; db[72][7]=9; db[72][8]=0;db[72][9]=9;  db[72][10]=8; db[72][11]=20;db[72][12]=22;db[72][13]=32;
		db[73][0]=7;db[73][1]=4;db[73][2]=1; db[73][3]=1; db[73][4]=7; db[73][5]=5;	db[73][6]=2; db[73][7]=11;db[73][8]=1;db[73][9]=8; db[73][10]=7;db[73][11]=21; db[73][12]=10;db[73][13]=31;
		db[74][0]=7;db[74][1]=3;db[74][2]=3; db[74][3]=3; db[74][4]=1; db[74][5]=5;	db[74][6]=6; db[74][7]=1; db[74][8]=7;db[74][9]=6; db[74][10]=7; db[74][11]=17;db[74][12]=9; db[74][13]=26;	
		db[75][0]=7;db[75][1]=3;db[75][2]=4; db[75][3]=1; db[75][4]=5; db[75][5]=6;	db[75][6]=1; db[75][7]=6; db[75][8]=0;db[75][9]=6; db[75][10]=7; db[75][11]=16;db[75][12]=10;db[75][13]=26;
		db[76][0]=8;db[76][1]=0;db[76][2]=1; db[76][3]=4; db[76][4]=1; db[76][5]=4;	db[76][6]=1; db[76][7]=0; db[76][8]=3;db[76][9]=5; db[76][10]=5; db[76][11]=8; db[76][12]=6; db[76][13]=14;
		db[77][0]=8;db[77][1]=0;db[77][2]=1; db[77][3]=2; db[77][4]=0; db[77][5]=3;	db[77][6]=0; db[77][7]=0; db[77][8]=3;db[77][9]=2; db[77][10]=3; db[77][11]=5; db[77][12]=4; db[77][13]=9;
		db[78][0]=8;db[78][1]=0;db[78][2]=1; db[78][3]=3; db[78][4]=0; db[78][5]=0;	db[78][6]=0; db[78][7]=0; db[78][8]=0;db[78][9]=3; db[78][10]=0; db[78][11]=3; db[78][12]=1; db[78][13]=4;
		db[79][0]=8;db[79][1]=0;db[79][2]=1; db[79][3]=2; db[79][4]=0; db[79][5]=3;	db[79][6]=0; db[79][7]=3; db[79][8]=0;db[79][9]=2; db[79][10]=3; db[79][11]=8; db[79][12]=1; db[79][13]=9;
		db[80][0]=8;db[80][1]=2;db[80][2]=1; db[80][3]=3; db[80][4]=0; db[80][5]=0;	db[80][6]=2; db[80][7]=0; db[80][8]=0;db[80][9]=3; db[80][10]=2; db[80][11]=5; db[80][12]=3; db[80][13]=8;
		db[81][0]=8;db[81][1]=0;db[81][2]=0; db[81][3]=3; db[81][4]=1; db[81][5]=0;	db[81][6]=1; db[81][7]=0; db[81][8]=0;db[81][9]=4; db[81][10]=1; db[81][11]=3; db[81][12]=2; db[81][13]=5;
		db[82][0]=8;db[82][1]=0;db[82][2]=1; db[82][3]=3; db[82][4]=0; db[82][5]=0;	db[82][6]=1; db[82][7]=0; db[82][8]=0;db[82][9]=3; db[82][10]=1; db[82][11]=3; db[82][12]=2; db[82][13]=5;
		db[83][0]=8;db[83][1]=1;db[83][2]=1; db[83][3]=3; db[83][4]=0; db[83][5]=0;	db[83][6]=1; db[83][7]=0; db[83][8]=0;db[83][9]=3; db[83][10]=1; db[83][11]=4; db[83][12]=2; db[83][13]=6;
		db[84][0]=8;db[84][1]=1;db[84][2]=1; db[84][3]=3; db[84][4]=0; db[84][5]=1;	db[84][6]=3; db[84][7]=0; db[84][8]=0;db[84][9]=3; db[84][10]=4; db[84][11]=5; db[84][12]=4; db[84][13]=9;
		db[85][0]=9;db[85][1]=2;db[85][2]=6; db[85][3]=8; db[85][4]=0; db[85][5]=4;	db[85][6]=6; db[85][7]=2; db[85][8]=2;db[85][9]=8; db[85][10]=10;db[85][11]=16;db[85][12]=14;db[85][13]=30;
		db[86][0]=9;db[86][1]=1;db[86][2]=3; db[86][3]=3; db[86][4]=1; db[86][5]=3;	db[86][6]=4; db[86][7]=13;db[86][8]=8;db[86][9]=4; db[86][10]=7; db[86][11]=20;db[86][12]=16;db[86][13]=36;
		db[87][0]=9;db[87][1]=1;db[87][2]=5; db[87][3]=3; db[87][4]=2; db[87][5]=4;	db[87][6]=1; db[87][7]=4; db[87][8]=1;db[87][9]=5; db[87][10]=5; db[87][11]=12;db[87][12]=9; db[87][13]=21;
		db[88][0]=9;db[88][1]=3;db[88][2]=5; db[88][3]=2; db[88][4]=5; db[88][5]=8;	db[88][6]=1; db[88][7]=4; db[88][8]=7;db[88][9]=7; db[88][10]=9; db[88][11]=17;db[88][12]=18;db[88][13]=35;
		db[89][0]=9;db[89][1]=5;db[89][2]=3; db[89][3]=2; db[89][4]=5; db[89][5]=9;	db[89][6]=2; db[89][7]=3; db[89][8]=7;db[89][9]=7; db[89][10]=11;db[89][11]=19;db[89][12]=17;db[89][13]=36;
		db[90][0]=9;db[90][1]=3;db[90][2]=2; db[90][3]=1; db[90][4]=4; db[90][5]=3;	db[90][6]=2; db[90][7]=6; db[90][8]=4;db[90][9]=5; db[90][10]=5; db[90][11]=13;db[90][12]=12;db[90][13]=25;		
		db[91][0]=9;db[91][1]=7;db[91][2]=2; db[91][3]=2; db[91][4]=7; db[91][5]=4;	db[91][6]=7; db[91][7]=9; db[91][8]=8;db[91][9]=9; db[91][10]=11;db[91][11]=22;db[91][12]=24;db[91][13]=46;
		db[92][0]=9;db[92][1]=4;db[92][2]=4; db[92][3]=1; db[92][4]=5; db[92][5]=4;	db[92][6]=3; db[92][7]=11;db[92][8]=7;db[92][9]=6; db[92][10]=7; db[92][11]=20;db[92][12]=19;db[92][13]=39;
		db[93][0]=9;db[93][1]=0;db[93][2]=1; db[93][3]=7; db[93][4]=4; db[93][5]=9;	db[93][6]=3; db[93][7]=3; db[93][8]=7;db[93][9]=11;db[93][10]=12;db[93][11]=19;db[93][12]=15;db[93][13]=34;
		db[94][0]=9;db[94][1]=5;db[94][2]=1; db[94][3]=9; db[94][4]=1; db[94][5]=1;	db[94][6]=6; db[94][7]=7; db[94][8]=5;db[94][9]=10;db[94][10]=7; db[94][11]=16;db[94][12]=13;db[94][13]=29;
      
 }
public void getalldata()
{
	//fetch contacts from the contacts table in the database. 
	Contact info = new Contact(getApplicationContext());
	info.open();

	c = info.fetchEmployee(0);
	data0=c.getString(2);

	c = info.fetchEmployee(1);
	data1=c.getString(2);

	c = info.fetchEmployee(2);
	data2=c.getString(2);
	
	c = info.fetchEmployee(3);
	data3=c.getString(2);

	c = info.fetchEmployee(4);
	data4=c.getString(2);

	c = info.fetchEmployee(5);
	data5=c.getString(2);

	c = info.fetchEmployee(6);
	data6=c.getString(2);

	c = info.fetchEmployee(7);
	data7=c.getString(2);

	c = info.fetchEmployee(8);
	data8=c.getString(2);

	c = info.fetchEmployee(9);
	data9=c.getString(2);

	info.close();
	
	//fetch language type from the language table in the database
	Lang lno= new Lang(getApplicationContext());
	lno.open();
	l=lno.fetchTemp();
	master_sound=Integer.parseInt(l.getString(1));
	lno.close();
	
	//fetch templates from the template table in the database
	Template temp = new Template(getApplicationContext());
	temp.open();

	Cursor t = temp.fetchTemp(0);
	temp0=t.getString(2);

	t = temp.fetchTemp(1);
	temp1=t.getString(2);

	t = temp.fetchTemp(2);
	temp2=t.getString(2);

	t = temp.fetchTemp(3);
	temp3=t.getString(2);

	t = temp.fetchTemp(4);
	temp4=t.getString(2);

	t = temp.fetchTemp(5);
	temp5=t.getString(2);

	t = temp.fetchTemp(6);
	temp6=t.getString(2);

	t = temp.fetchTemp(7);
	temp7=t.getString(2);

	t = temp.fetchTemp(8);
	temp8=t.getString(2);

	t = temp.fetchTemp(9);
	temp9=t.getString(2);

	temp.close();
	
	//fetch positions for circles from the position table in the databse
	Position pos = new Position(getApplicationContext());
	pos.open();

	Cursor p = pos.fetchPos(1);
	pos0=p.getString(1);

     p = pos.fetchPos(2);
	pos1=p.getString(1);

	p = pos.fetchPos(3);
	pos2=p.getString(1);

	 p = pos.fetchPos(4);
	pos3=p.getString(1);

	p = pos.fetchPos(5);
	pos4=p.getString(1);

	pos.close();
}
}