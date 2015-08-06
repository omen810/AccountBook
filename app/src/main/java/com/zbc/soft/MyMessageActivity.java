package com.zbc.soft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MyMessageActivity extends Activity {
	private TextView showMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_message);
		showMessage=(TextView)this.findViewById(R.id.showMessage);
		Thread td=new Thread(new Runnable(){

			@Override
			public void run() {
				StringBuffer sb=new StringBuffer();
				HttpClient cliet=new DefaultHttpClient();
				HttpPost post=new HttpPost("http://120.27.46.196:8082/bianMeile/user/getAllUser.action");
		        //����Ĭ�ϵĿͻ��˶���
			
		        //��list��װҪ��������˷��͵Ĳ���
		        List<BasicNameValuePair> pairs=new ArrayList<BasicNameValuePair>();
		        pairs.add(new BasicNameValuePair("mobile", "13053549598"));
		        try {
		            //��UrlEncodedFormEntity����װList����
		        	UrlEncodedFormEntity urlEntity=new UrlEncodedFormEntity(pairs);
		            //����ʹ�õ�Entity
		            post.setEntity(urlEntity);
		            try {
		                //�ͻ��˿�ʼ��ָ������ַ��������
		                HttpResponse response=cliet.execute(post);
		                
		                if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
		                	//��������Entity
		                	 HttpEntity entity=response.getEntity();
				                InputStream is=entity.getContent();
				                //�����Ƕ�ȡ���ݵĹ���
				                BufferedReader br=new BufferedReader(new InputStreamReader(is));
				                String line=null;
				               
				                while((line=br.readLine())!=null){
				                    sb.append(line);
				                }
				                System.out.println(sb.toString());
		                	
		                }
		               
		            } catch (ClientProtocolException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		        }
				
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				msg.what=1;
				Bundle bd=new Bundle();
				bd.putString("status", sb.toString());
				msg.setData(bd);
				handler.sendMessage(msg);
			}
			
			
		});
		td.start();
	}


	
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			String status=msg.getData().getString("status");
			int what=msg.what;
			if(what==1){
				showMessage.setText(status);
				///�������
			}
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_message, menu);
		return true;
	}
}
