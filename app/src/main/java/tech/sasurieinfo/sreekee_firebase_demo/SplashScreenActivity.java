package tech.sasurieinfo.sreekee_firebase_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                }
            }
        });
        t1.start();
    }
}
