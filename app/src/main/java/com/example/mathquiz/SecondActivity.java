package com.example.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {

    public void onClickType(View view){
        Intent intent1 = new Intent(getApplicationContext(), AdditionActivity.class);
        Intent intent2 = new Intent(getApplicationContext(), SubstractionActivity.class);
        Intent intent3 = new Intent(getApplicationContext(), MultiplicationActivity.class);
        Intent intent4 = new Intent(getApplicationContext(), DivisionActivity.class);

        if(Integer.parseInt(view.getTag().toString())==1){
            startActivity(intent1);
        }
        else if(Integer.parseInt(view.getTag().toString())==2){
            startActivity(intent2);
        }
        else if(Integer.parseInt(view.getTag().toString())==3){
            startActivity(intent3);
        }
        else if(Integer.parseInt(view.getTag().toString())==4){
            startActivity(intent4);
        }
        else{
            Snackbar.make(view, "No further things", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

    }
}
