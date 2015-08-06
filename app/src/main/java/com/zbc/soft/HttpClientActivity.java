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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HttpClientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_client);
		Button bt=(Button)this.findViewById(R.id.clientBt);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Thread td=new Thread(new Runnable(){

					@Override
					public void run() {
						/**
						 * 1.����httpclient
						 * 2.����httpget
						 * 3.ͨ��httpclient��execute��������
						 * 4.ͨ��httpResponse��.getEntity()��ȡ��Ӧ���ݶ���
						 * 5.��ȡ��Ӧ����
						 * */
						HttpClient cliet=new DefaultHttpClient();
//						HttpGet get=new HttpGet("http://120.27.46.196:8082/bianMeile/user/getAllUser.action?mobile=13053549598");
//				       
//				        try {
//				            HttpResponse response=cliet.execute(get);
//				            HttpEntity entity=response.getEntity();
//				            InputStream is=entity.getContent();
//				            BufferedReader br=new BufferedReader(new InputStreamReader(is));
//				            String line=null;
//				            StringBuffer sb=new StringBuffer();
//				            while((line=br.readLine())!=null){
//				                sb.append(line);
//				            }
//				            System.out.println(sb.toString());
//				        } catch (ClientProtocolException e) {
//				            e.printStackTrace();
//				        } catch (IOException e) {
//				            e.printStackTrace();
//				        }
						
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
							                StringBuffer sb=new StringBuffer();
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
						
					}
					
					
				});
				td.start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.http_client, menu);
		return true;
	}

}
