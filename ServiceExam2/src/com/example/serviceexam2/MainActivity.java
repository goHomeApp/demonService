package com.example.serviceexam2;

import com.example.serviceexam2.R;
import com.example.serviceexam2.demonService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void mOnClick(View view) {
		switch(view.getId()) {
		case R.id.button1 :					//StartService버튼을 눌렀을 경우
			onStartService(view);
			break;
		case R.id.button2 :					//StopService버튼을 눌렀을 경우
			onStopService(view);
			break;
		}
	}
	
	public void onStartService(View view) {		//서비스 실행
		Intent intent = new Intent(this,demonService.class);
		startService(intent);
	}
	
	public void onStopService(View view) {		//서비스 종료
		Intent intent = new Intent(this,demonService.class);
		stopService(intent);
	}
}
