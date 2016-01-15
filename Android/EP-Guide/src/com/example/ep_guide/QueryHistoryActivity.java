package com.example.ep_guide;

import com.ep_guide.myadapter.DBAdapter;
import com.ep_guide.thread.Data_History;
import com.ep_guide.thread.HttpRegist;
import com.ep_guide.thread.QueryParkHistory;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QueryHistoryActivity extends Activity {
	String car_ID;
	TextView textview1,textview2,textview3,textview4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_history);
		Button query = (Button) findViewById(R.id.query);
		textview1 = (TextView) findViewById(R.id.textView2);
		textview2 = (TextView) findViewById(R.id.textView3);
		textview3 = (TextView) findViewById(R.id.textView4);
		textview4 = (TextView) findViewById(R.id.textView5);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				car_ID = ((EditText) findViewById(R.id.car_ID)).getText().toString();
				if(TextUtils.isEmpty(car_ID)){
					new AlertDialog.Builder(QueryHistoryActivity.this)
					.setTitle("�벹����������").setMessage("��ѯ��Ϣ����Ϊ�գ�������")
							.setPositiveButton("ȷ��", null).show();
				}
				else {
					new QueryParkHistory(car_ID).start();
					for (int i = 0; i < 200000000; i++) {
						;
					}
					if(Data_History.data_history == 2006){
						textview1.setText("δ�ҵ��ó�����¼��");
					}
					if(Data_History.data_history == 8006){
						String garageID = "����ţ�"+Data_History.garage_ID[0].toString();
						String pspaceID = "��λ�ţ�"+Data_History.pSpace_ID[0].toString();
						String getIntime = "���ʱ�䣺"+Data_History.getIn_time[0];
						String parktime = "ͣ��ʱ����"+Data_History.park_time[0];
						textview1.setText(garageID);
						textview2.setText(pspaceID);
						textview3.setText(getIntime);
						textview4.setText(parktime);
					}
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_query_history, menu);
		return true;
	}
	
}
