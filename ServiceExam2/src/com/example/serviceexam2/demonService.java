package com.example.serviceexam2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class demonService extends Service{

	int time=1;
	String string;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(demonService.this, "Exit", Toast.LENGTH_SHORT).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		super.onStartCommand(intent, flags, startId);
		ThreadExam thread = new ThreadExam(this,mHandler);
		thread.start();
		
		return START_NOT_STICKY;			//프로세스를 종료하면 서비스도 종료
	}
	
	class ThreadExam extends Thread {		//서비스를 백그라운드로 돌리기 위해 쓰레드 생성
		demonService mParent;
		Handler mHandler;
		
		public ThreadExam(demonService mParent,Handler mHandler) {
			this.mParent = mParent;
			this.mHandler = mHandler;
		}
		public void run() {							//5초마다 Toast메시지를 띄움
			int i;
			for(i=0;i<5;i++) {
				string = new String("in " + time);
				Message msg = new Message();
				msg.what = 0;
				msg.obj = string;
				mHandler.sendMessage(msg);
				try { 
					Thread.sleep(5000); 
					time+=5;
				} catch (Exception e) {;}
			}
		}
		
	}
	
	Handler mHandler = new Handler() {			//쓰레드 동기화 문제로 인해 쓰레드의 값을 바로 출력하기 위해서는 핸들러를 이용하여 뷰에다 출력
		public void handleMessage(Message msg) {
			if(msg.what == 0) {
				String temp = (String)msg.obj;
				Toast.makeText(demonService.this, string, Toast.LENGTH_SHORT).show();
			}
		}
	};
}