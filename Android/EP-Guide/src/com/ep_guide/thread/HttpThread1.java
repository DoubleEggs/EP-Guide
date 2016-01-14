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

import com.example.ep_guide.LoginSucActivity;
import com.example.ep_guide.MainActivity;
import com.example.ep_guide.RegistActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;


public class HttpThread1 extends Thread {
	String url;
	String username;
	String password;
	String result;
	Tool tool = new Tool();
	final String urlPath="http://192.168.191.1:8080/EP-Guide/login";
	
	public HttpThread1(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	

	private void doPost() throws JSONException
	{
		try {
			String mdpass = tool.md5(password);
			JSONObject ClientKey = new JSONObject();
			ClientKey.put("action", "1001");
			ClientKey.put("username", username);
			ClientKey.put("password", mdpass);
			
//			ClientKey.put("action", "1002");
//			ClientKey.put("username", "dan1");
//			ClientKey.put("password", "gg");
//			ClientKey.put("car_ID", "��B666668");
			
		
			//JSONObject params = new JSONObject();
			//params.put("data", ClientKey);
			//��JSON����ת����String����ʹ��������������д
			//String content = String.valueOf(params);
			String content="data="+ClientKey.toString();
//			content="{\"result\":1000}";     
//			content="data={\"action\":\"1001\",\"username\":\"dandan\",\"password\":\"gg\"}";
			System.out.println(content);
			
			URL HttpUrl = new URL(urlPath);
			try {
				HttpURLConnection conn = (HttpURLConnection) HttpUrl.openConnection();

				conn.setRequestMethod("POST");
				conn.setConnectTimeout(5000);
				conn.setDoOutput(true);//�����������
//				conn.setRequestProperty("User-Agent", "Fiddler");
//				conn.setRequestProperty("Content-Type", "application/json");
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
				Data.data=json.getInt("result");
				Data.token=json.getString("token");
				System.out.println("data="+Data.data);
				reader.close();
				//System.out.println(success);
				
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
