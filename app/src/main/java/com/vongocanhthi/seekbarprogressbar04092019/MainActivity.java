package com.vongocanhthi.seekbarprogressbar04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CheckBox cbxCar1, cbxCar2, cbxCar3;
    SeekBar sbCar1, sbCar2, sbCar3;
    ImageButton ibtnStart;
    TextView txtDiem;
    int diem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addInit();
        disableSeekBar();
        setCheckBoxSingleChoie();
        setRun();

    }

    private void disableSeekBar() {
        sbCar1.setEnabled(false);
        sbCar2.setEnabled(false);
        sbCar3.setEnabled(false);
    }

    // kiem tra checkbox
    private void enableCheckBox(){
        cbxCar1.setEnabled(true);
        cbxCar2.setEnabled(true);
        cbxCar3.setEnabled(true);
    }

    private void disableCheckBox(){
        cbxCar1.setEnabled(false);
        cbxCar2.setEnabled(false);
        cbxCar3.setEnabled(false);
    }

    private void setCheckBoxSingleChoie() {
        cbxCar1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbxCar2.setChecked(false);
                    cbxCar3.setChecked(false);
                }
            }
        });
        cbxCar2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbxCar1.setChecked(false);
                    cbxCar3.setChecked(false);
                }
            }
        });
        cbxCar3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbxCar1.setChecked(false);
                    cbxCar2.setChecked(false);
                }
            }
        });
    }

    private void setRun() {
        final CountDownTimer countDownTimer = new CountDownTimer(100000, 800) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                Random random = new Random();
                int one = random.nextInt(5);
                int two = random.nextInt(5);
                int three = random.nextInt(5);

                sbCar1.setProgress(sbCar1.getProgress() + one);
                sbCar2.setProgress(sbCar2.getProgress() + two);
                sbCar3.setProgress(sbCar3.getProgress() + three);

                if(sbCar1.getProgress() >= sbCar1.getMax()){
                    this.cancel();
                    ibtnStart.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "One win", Toast.LENGTH_SHORT).show();

                    if(cbxCar1.isChecked()){
                        diem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đã thắng", Toast.LENGTH_SHORT).show();
                    }else{
                        diem -= 10;
                        Toast.makeText(MainActivity.this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
                    }
                    enableCheckBox();
                }else if(sbCar2.getProgress() >= sbCar2.getMax()){
                    this.cancel();
                    ibtnStart.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Two win", Toast.LENGTH_SHORT).show();

                    if(cbxCar2.isChecked()){
                        diem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đã thắng", Toast.LENGTH_SHORT).show();
                    }else{
                        diem -= 10;
                        Toast.makeText(MainActivity.this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
                    }
                    enableCheckBox();
                }else if(sbCar3.getProgress() >= sbCar3.getMax()){
                    this.cancel();
                    ibtnStart.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Three win", Toast.LENGTH_SHORT).show();

                    if(cbxCar3.isChecked()){
                        diem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đã thắng", Toast.LENGTH_SHORT).show();
                    }else{
                        diem -= 10;
                        Toast.makeText(MainActivity.this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
                    }
                    enableCheckBox();
                }
                txtDiem.setText(diem + "");

            }

            @Override
            public void onFinish() {

            }
        };

        ibtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbxCar1.isChecked() || cbxCar2.isChecked() || cbxCar3.isChecked()){
                    sbCar1.setProgress(5);
                    sbCar2.setProgress(5);
                    sbCar3.setProgress(5);

                    ibtnStart.setVisibility(View.GONE);
                    disableCheckBox();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            countDownTimer.start();
                        }
                    }, 2000);
                }else{
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void addInit() {
        txtDiem = findViewById(R.id.txtDiem);
        cbxCar1 = findViewById(R.id.cbxCar1);
        cbxCar2 = findViewById(R.id.cbxCar2);
        cbxCar3 = findViewById(R.id.cbxCar3);
        sbCar1 = findViewById(R.id.sbCar1);
        sbCar2 = findViewById(R.id.sbCar2);
        sbCar3 = findViewById(R.id.sbCar3);
        ibtnStart = findViewById(R.id.ibtnStart);
    }
}
