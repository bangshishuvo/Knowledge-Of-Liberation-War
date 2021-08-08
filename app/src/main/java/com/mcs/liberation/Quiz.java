package com.mcs.liberation;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.graphics.*;
import java.io.*;
import java.util.*;
 
public class Quiz extends Activity
{
	int i=0,qpos=-1,ra=0,wa=0;
	InputStream is;
	String str;
	List<String> list=new ArrayList<String>();
	List<Integer> ans = new ArrayList<Integer>();
	String s2="";
	TextView qv,rs,ws,qn;
	
	
	@Override
	public void onCreate(Bundle Bndl)
	{
		super.onCreate(Bndl);
		getActionBar().hide();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.quiz_board);
		
		qv=(TextView)findViewById(R.id.qv);
		qn=(TextView)findViewById(R.id.qn);
		rs=(TextView)findViewById(R.id.rs);
		ws=(TextView)findViewById(R.id.ws);
		
		qn.setText("0");
		rs.setText("0");
		ws.setText("0");
		
		
		try
		{
			String s;
			i=0;
			FileReader fr = new FileReader("sdcard/Android/question.txt");
			BufferedReader br = new BufferedReader(fr);
			while((s=br.readLine())!=null){
			    i++;
				s2+=s+"\n";
				if(i==5){
					list.add(s2);
					s2="";
					i=0;
				}
			}
			
			fr.close();
			br.close();
			
			fr = new FileReader("sdcard/Android/ans");
			br = new BufferedReader(fr);
			while((s=br.readLine())!=null){
			     ans.add(Integer.valueOf(s));
			}
			
			Toast.makeText(getBaseContext(),"Success,",Toast.LENGTH_SHORT).show();
		}
		catch (Exception e)
		{}

		
		setQues();
		
	}
	
	
	public void setQues(){
	
		//Toast.makeText(getBaseContext(),"Set Text Fun",Toast.LENGTH_LONG).show();
		qpos++;
		
		rs.setText(""+ra);
		ws.setText(""+wa);
		
		if(qpos<list.size()){
			qn.setText(""+(qpos+1));
			qv.setText(""+list.get(qpos));
			
		}else{
			setContentView(R.layout.game_over);
			TextView tv=(TextView)findViewById(R.id.score);
			tv.setText("SCORE : "+(ra*10));
		}
		
	}
	
	
	public void OPTA(View v){
		
		if(ans.get(qpos)==1){
			ra++;
			Toast.makeText(getBaseContext(),"Right Answer",Toast.LENGTH_SHORT).show();
		}else{
			wa++;
		}
		
		setQues();
	}
	
	
	public void OPTB(View v){
		if(ans.get(qpos)==2){
			ra++;
			Toast.makeText(getBaseContext(),"Right Answer",Toast.LENGTH_SHORT).show();
			
		}else{
			wa++;
			Toast.makeText(getBaseContext(),"Worng Answer",Toast.LENGTH_SHORT).show();
			
		}

		setQues();
	}
	
	public void OPTC(View v){
		
		if(ans.get(qpos)==3){
			ra++;
			Toast.makeText(getBaseContext(),"Right Answer",Toast.LENGTH_SHORT).show();
			
		}else{
			wa++;
			Toast.makeText(getBaseContext(),"Worng Answer",Toast.LENGTH_SHORT).show();
			
		}

		setQues();
	}
	
	public void OPTD(View v){
		
		if(ans.get(qpos)==4){
			ra++;
			Toast.makeText(getBaseContext(),"Right Answer",Toast.LENGTH_SHORT).show();
			
		}else{
			wa++;
			Toast.makeText(getBaseContext(),"Worng Answer",Toast.LENGTH_SHORT).show();
			
		}

		setQues();
	}
}
