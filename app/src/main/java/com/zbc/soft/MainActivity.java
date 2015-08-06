package com.zbc.soft;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class MainActivity extends Activity implements Runnable{

	private WebView webView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * �ж������Ƿ���������
		 * */
		ConnectivityManager connManager  = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo ni=connManager.getActiveNetworkInfo();
		if(ni.isAvailable()){///�ж������Ƿ����
			
			Toast.makeText(this, "������������"+ni.getTypeName(), Toast.LENGTH_LONG).show();
			
		}else{
			Toast.makeText(MainActivity.this, "���������쳣������������", Toast.LENGTH_LONG).show();
			 startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));  
			//startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));  
		}
		
		  State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
		  if(State.CONNECTED==state){  
		   Log.i("֪ͨ", "GPRS����������");  
		  }  
		    
		  state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();  
		  if(State.CONNECTED==state){  
		   Log.i("֪ͨ", "WIFI����������");  
		  }  
		    
//		   if(ni.getType()==ConnectivityManager.TYPE_WIFI){  
//				// ��ת������wifi�������ý���  
//				startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));  
//			}else if(ni.getType()==ConnectivityManager.TYPE_MOBILE) {  
//			
//				// ��ת�������������ý���  
//				startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));  
//			}  
		 

		
		webView1=(WebView)this.findViewById(R.id.webView1);
		webView1.loadUrl("http://www.baidu.com");
		webView1.getSettings().setJavaScriptEnabled(true);
//		Thread td=new Thread(MainActivity.this);
//		td.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("======================");
		InputStream is;
		try {
			is = new URL("http://www.baidu.com").openStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(is));
			String str=null;
			StringBuffer sb=new StringBuffer();
			while((str=br.readLine())!=null){
				
				sb.append(str);
			}
			System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
