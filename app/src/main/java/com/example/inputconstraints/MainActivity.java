package com.example.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.inputconstraints.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    private static final int  REQUEST_KEY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    public void SendConstraintsToInputActivity(View view) {
        ///Create Bundle...
        Bundle bundle = new Bundle();
        //When we check Uppercase..
        if (b.checkBox.isChecked()) {
            bundle.putString(Constants.UPPERCASE_KEY, "true");
        //When we check lowercase..
        } else if (b.checkBox2.isChecked()) {
            bundle.putString(Constants.LOWERCASE_KEY, "true");
            //When we check Digits..
        } else if (b.checkBox3.isChecked()) {
            bundle.putString(Constants.DIGITS_KEY, "true");
            //When we check Mathematical Operations..
        } else if (b.checkBox4.isChecked()) {
            bundle.putString(Constants.EXPRESSION_KEY, "true");
            //When we check Other symbol..
        } else if (b.checkBox5.isChecked()) {
            bundle.putString(Constants.SYMBOL_KEY, "true");
            //When nothing is check..
        } else {
            Toast.makeText(this, "Please check the box", Toast.LENGTH_SHORT).show();
            return;
        }
      ////Craete intent
        Intent intent = new Intent(MainActivity.this,InputActivity.class);
        intent.putExtras(bundle);

        startActivityForResult(intent,REQUEST_KEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode==REQUEST_KEY && resultCode==RESULT_OK ){
             b.FinalOutput.setText("Input is:-"+data.getStringExtra(Constants.CONSTRAINT_KEY));
             b.FinalOutput.setVisibility(View.VISIBLE);
         }
    }
}