package com.mcs.liberation;

import android.app.*;
import android.content.*;
import android.media.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity 
{
	GridView gv;
	Handler hndl=new Handler();
	String optnam[]={"HISTORY","NATIONAL ANTHEN","ABOUT MUJIB","7 MARCH SPEECH","7 BIR SRESHTHO","QUIZTEST"};
	View viw;
	AlertDialog al;
	MediaPlayer mp;
	int optimg[]={R.drawable.history,R.drawable.music,R.drawable.mujib,R.drawable.march,R.drawable.bir,R.drawable.quiz};
	Storage store;
	File f;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		getActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		store = new Storage(this);
		setContentView(R.layout.splash);
		
		
		fileHndl();
		
		
		hndl.postDelayed(new Runnable(){

			@Override
			public void run(){
				homePage();
			}
		},3500);
		
        
    }
	
	
	public void fileHndl(){
		

		f=new File("sdcard/Android/");
		if(!f.exists()){
			f.mkdirs();
		}
		
		f=new File("sdcard/Android/question.txt");
		if(f.exists()==false){
			InputStream is = this.getResources().openRawResource(R.raw.question);
			try
			{
				OutputStream os = new FileOutputStream("sdcard/Android/question.txt");
				byte b[]= new byte[1024];
				int r;
				while((r=is.read(b))!=-1){
					os.write(b,0,r);
				}
				
				
				Toast.makeText(getBaseContext(),"Copied...",Toast.LENGTH_SHORT).show();
			}
			catch (Exception e)
			{
				Toast.makeText(getBaseContext(),""+e,Toast.LENGTH_LONG).show();
			}
			
			
			is = this.getResources().openRawResource(R.raw.ans);
			try
			{
				OutputStream os = new FileOutputStream("sdcard/Android/ans");
				byte b[]= new byte[1024];
				int r;
				while((r=is.read(b))!=-1){
					os.write(b,0,r);
				}


				Toast.makeText(getBaseContext(),"Copied...",Toast.LENGTH_SHORT).show();
			}
			catch (Exception e)
			{
				Toast.makeText(getBaseContext(),""+e,Toast.LENGTH_LONG).show();
			}
			
			
		}
		
	}
	
	
	private void homePage(){
		
		setContentView(R.layout.main);
		
		gv=(GridView)findViewById(R.id.mainGridView);
		
		
		BaseAdapter ba = new BaseAdapter(){

			@Override
			public int getCount(){
				return optnam.length;
			}

			@Override
			public Object getItem(int p1){
				return p1;
			}

			@Override
			public long getItemId(int p1){
				return p1;
			}

			@Override
			public View getView(final int p1, View p2, ViewGroup p3)
			{
				viw=getLayoutInflater().inflate(R.layout.gridbutton,null);
				
				/*
				TextView tv=(TextView)viw.findViewById(R.id.opttext);
				tv.setText(""+optnam[p1]);
				ImageView img = (ImageView)viw.findViewById(R.id.optimg);
				if(p1<2)
				img.setBackgroundResource(optimg[p1]);
				*/
				LinearLayout ll = (LinearLayout)viw.findViewById(R.id.optimg);
				ll.setBackgroundResource(optimg[p1]);
				
				
				viw.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v)
					{
						Toast.makeText(getBaseContext(),"Opt : "+optnam[p1],Toast.LENGTH_SHORT).show();
						if(p1==0 || p1==2 || p1==4){
							history(p1);
						}else if(p1==1){
							playMusic();
						}else if(p1==5){
							Intent next=new Intent(MainActivity.this,Quiz.class);
							startActivity(next);
						}
						
						
					}
				});
				
				return viw;
			}

			
		};
		
		
		gv.setAdapter(ba);
		
		
		
		
		
	}
	
	
	public void history(int opt){
		
		store.setOpt(opt);
		Intent next = new Intent(this,History.class);
		startActivity(next);
	}
	
	public void playMusic(){
		
		viw = getLayoutInflater().inflate(R.layout.music,null);
		al = new AlertDialog.Builder(this).create();
		al.setView(viw);
	    al.setCancelable(false);
		al.show();
		
		mp = MediaPlayer.create(this,R.raw.sonarbangla);
		mp.start();
	}
	
	
	public void STOPMUSIC(View v){
		mp.stop();
		al.dismiss();
	}
	
}
