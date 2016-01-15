package com.ep_guide.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRegist extends Thread {
	String url;
	String username;
	String password;
	String car_ID;
	String user_ID;
	String result;
	Tool tool = new Tool();
	final String urlPath="http://192.168.191.1:8080/EP-Guide/register";
	
	public HttpRegist(String username,String password,String car_ID,String user_ID)
	{
		this.username = username;
		this.password = password;
		this.car_ID = car_ID;
		this.user_ID = user_ID;
	}
	
	private void doPost() throws JSONException
	{
		try {
			String mdpass = tool.md5(password);
			JSONObject ClientKey = new JSONObject();
			ClientKey.put("action", "1002");
			ClientKey.put("username", username);
			ClientKey.put("password", mdpass);
			ClientKey.put("car_ID", car_ID);
			ClientKey.put("user_ID",user_ID);
			
			//��JSON����ת����String����ʹ��������������д
			String content="data="+ClientKey.toString();
			System.out.println(content);
			URL HttpUrl = new URL(urlPath);
			try {
				HttpURLConnection conn = (HttpURLConnection) HttpUrl.openConnection();
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(5000);
				OutputStream os = conn.getOutputStream();
				os.write(content.getBytes());

				os.close();
				//��������
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String strData;
				
				while((strData=reader.readLine())!=null)
				{
					sb.append(strData);
				}
				result = sb.toString();
				System.out.println("Str:"+result);
				JSONObject json = new JSONObject(result);
				Data_Regist.data_regist=json.getInt("result");
				
				reader.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run() {

			try {
				doPost();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	
	}

}
