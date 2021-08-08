package com.mcs.liberation;

import android.content.*;

public class Storage
{
	SharedPreferences sp;
	SharedPreferences.Editor spe;
	
	public Storage(Context ctx){
		sp = ctx.getSharedPreferences("STORE",7);
		spe = sp.edit();
	}
	
	public void setOpt(int opt){
		spe.putInt("OPT",opt);
		spe.commit();
	}
	
	public int getOpt(){
		return sp.getInt("OPT",0);
	}
	
	
}
