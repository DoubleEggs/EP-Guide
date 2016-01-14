package com.example.ep_guide;

import com.ep_guide.myadapter.DBAdapter;
import com.ep_guide.thread.Data_Carport;
import com.ep_guide.thread.Data_Regist;
import com.ep_guide.thread.OrderCarport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarportActivity extends Activity {
	public String garage_ID,car_ID,pSpace_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carport);
		Button order = (Button) findViewById(R.id.Order);
		order.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				garage_ID  = ((EditText) findViewById(R.id.garage_ID)).getText().toString();
				car_ID  = ((EditText) findViewById(R.id.newCarID)).getText().toString();
				pSpace_ID  = ((EditText) findViewById(R.id.pSpaceID)).getText().toString();
				Intent intent = new Intent(CarportActivity.this,LoginSucActivity.class);
				if (TextUtils.isEmpty(garage_ID) || TextUtils.isEmpty(car_ID) || TextUtils.isEmpty(pSpace_ID)){
					new AlertDialog.Builder(CarportActivity.this)
					.setTitle("�벹����������").setMessage("������Ϣ����Ϊ�գ�������")
							.setPositiveButton("ȷ��", null).show();
					}
				else{
					new OrderCarport(garage_ID, car_ID, pSpace_ID).start();
					for (int i = 0; i < 200000000; i++) {
						;
					}
					if (Data_Carport.data_carport == 8005) {
						new AlertDialog.Builder(CarportActivity.this)
						.setTitle("��ϲ").setMessage("ԤԼ�ɹ�")
						.setPositiveButton("ȷ��", null).show();
						startActivity(intent);
						
					} 
					if(Data_Carport.data_carport == 8800) {
						new AlertDialog.Builder(CarportActivity.this)
						.setTitle("�ź�").setMessage("��Ǹ�����⳵λ����")
						.setPositiveButton("ȷ��", null).show();
					}
					if(Data_Carport.data_carport == 2005) {
						new AlertDialog.Builder(CarportActivity.this)
						.setTitle("����").setMessage("ԤԼʧ��")
						.setPositiveButton("ȷ��", null).show();
					}
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_carport, menu);
		return true;
	}
	

}
