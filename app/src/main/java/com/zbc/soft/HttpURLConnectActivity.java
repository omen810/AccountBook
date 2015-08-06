package com.zbc.soft;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HttpURLConnectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_urlconnect);
		Button bt=(Button)this.findViewById(R.id.bt);
		final TextView textView1=(TextView)this.findViewById(R.id.textView1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Thread td = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String urlStr = "http://120.27.46.196:8082/bianMeile/user/getAllUser.action?mobile=13053549598";
//						try {
//							URL url=new URL(urlStr);
//							HttpURLConnection conn;
//							try {
//								conn = (HttpURLConnection) url.openConnection();
//								 //���ӷ�����
//			                     conn.connect();
//			                     /**������������ݵĹ���**/
//			                     //�õ�������
//			                     InputStream is=conn.getInputStream();
//			                     //������װ��
//			                     BufferedReader br=new BufferedReader(new InputStreamReader(is));
//			                     //����String�������ڴ��浥������
//			                     String line=null;
//			                     //����StringBuffer�������ڴ洢��������
//			                     StringBuffer sb=new StringBuffer();
//			                     while((line=br.readLine())!=null){
//			                         sb.append(line);
//			                     }
//			                    
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//		                    
//						} catch (MalformedURLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						
						try {
							URL url=new URL(urlStr);
							HttpURLConnection conn;
		                    try {
		                        //�򿪷�����
		                        conn=(HttpURLConnection) url.openConnection();
		                        //�������������
		                        conn.setDoOutput(true);///��������д������
		                        conn.setDoInput(true);////�������ж�ȡ����
		                        //��������ķ���ΪPost
		                        conn.setRequestMethod("POST");
		                        //Post��ʽ���ܻ������ݣ�����Ҫ�ֶ�����ʹ�û����ֵΪfalse
		                        conn.setUseCaches(false);
		                        //�������ݿ�
		                        conn.connect();
		                        /**д�����**/
		                        OutputStream os=conn.getOutputStream();
		                        //��װд�������������ݣ�������Ҫ���ݵĲ�����
		                        DataOutputStream dos=new DataOutputStream(os);
		                        //д������name��keyֵ���ܱ䣬���뷽ʽʹ��UTF-8����������
	//	                        dos.writeBytes("name="+URLEncoder.encode("���", "UTF-8"));
		                        dos.writeBytes("mobile=13053549598");
		                        //�ر����װ��
		                        dos.close();
		                        /**������������**/
		                        InputStream is=conn.getInputStream();
		                        BufferedReader br=new BufferedReader(new InputStreamReader(is));
		                        String line=null;
		                        StringBuffer sb=new StringBuffer();
		                        while((line=br.readLine())!=null){
		                            sb.append(line);
		                        }
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                } catch (MalformedURLException e) {
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
		getMenuInflater().inflate(R.menu.http_urlconnect, menu);
		return true;
	}

}
