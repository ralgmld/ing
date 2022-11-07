package com.example.YourVoice;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.RECORD_AUDIO;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.YourVoice.SpeechToText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallActivity extends AppCompatActivity {


    public TextView timer;
    public TextView phoneNum;
    public TextView stt;
    public TextView accuracy;
    public String phone;
    public long myBaseTime;
    public SpeechToText speechToText;
    public double setAccuracy;
    private boolean popUpOpen = false;
    SharedPreferences sharedPreferences;

    public String TAG = "CallActivity";

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            timer.setText(getTimeOut());
            if(Double.parseDouble((String) accuracy.getText()) >= setAccuracy && !popUpOpen ){
                Log.d(TAG,"startPopUp : "+accuracy.getText() +" // setAccuracy : " + setAccuracy);
                mOnPopup();
            }

            //sendEmptyMessage 는 비어있는 메세지를 Handler 에게 전송하는겁니다.
            myTimer.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_record);
        popUpOpen = false;
        SetLayout();
        SetPhoneNum();
        sharedPreferences = getSharedPreferences("sFile",0);
        setAccuracy = Double.parseDouble(sharedPreferences.getString("percent","80"));
        myBaseTime = SystemClock.elapsedRealtime();
        myTimer.sendEmptyMessage(0);
        int requestCode = 5; // unique code for the permission request
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO, INTERNET}, requestCode);
        speechToText = new SpeechToText(this,stt,phone,accuracy);
        speechToText.execute();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(speechToText.getStatus() == AsyncTask.Status.RUNNING)
            speechToText.cancel(true);
    }

    public void SetLayout(){
        findViewById(R.id.endCallBtn).setOnClickListener(onClickListener);
        timer = (TextView)findViewById(R.id.timer);
        //phoneNum = (TextView)findViewById(R.id.phoneNum);
        stt = (TextView)findViewById(R.id.speechToText);
        //accuracy = (TextView)findViewById(R.id.accuracyText);
    }

    //버튼 클릭 이벤트를 담은 메소드.
    Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.endCallBtn:
                    CloseActivity();
                    break;
            }
        }
    };

    public void mOnPopup(){
        popUpOpen = true;
        View v = getWindow().getDecorView();
        Toast.makeText(this, "mOnPopUp", Toast.LENGTH_LONG).show();
        //데이터 담아서 팝업(액티비티) 호출
        //Intent intent = new Intent(this, PopUpAccuracyActivity.class);
        //intent.putExtra("accuracy", accuracy.getText());
        //startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String result = data.getStringExtra("result");
            if (result == "No")
                Toast.makeText(this, "Not Report", Toast.LENGTH_SHORT).show();
            else {
                //int count = connectFirebase.GetReportCount(phone);
                //connectFirebase.SendReport(phone,count);
                CloseActivity();
            }
        }
    }

    public void SetPhoneNum(){
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        if(phone.length()<11)
            phoneNum.setText(phone.substring(0,2)+'-'+phone.substring(2,6)+'-'+phone.substring(6,10));
        else
            phoneNum.setText(phone.substring(0,3)+'-'+phone.substring(3,7)+'-'+phone.substring(7,11));
    }

    public void CloseActivity(){
        if(speechToText.getStatus() == AsyncTask.Status.RUNNING)
            speechToText.cancel(true);
        finish();
    }
    //현재시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간(??)^^;
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d", outTime/1000 / 60, (outTime/1000)%60);
        return easy_outTime;
    }



}