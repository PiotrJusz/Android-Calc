package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Button button_plus_minus = (Button)findViewById(R.id.button_plus_minus);



    public void addNumber(View view){
        Log.i("Zdarzenie:","Wciśnięto przycisk!");
        Log.i("Button", String.valueOf(view.getId()));

        EditText editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);
        String screenResult;
        Float floatScreenResult;

        screenResult = String.valueOf(editTextNumberDecimal.getText());
        floatScreenResult = Float.valueOf(screenResult);

        if (view.getId()==R.id.button_plus_minus){
            Log.i("Informacja:","Wciśnięto przycisk plus/minus");
            floatScreenResult = (-1) * floatScreenResult;
        }
        else if(view.getId()==R.id.button_C){
            Log.i("Wciśnięto przycisk:","przucisk C.");
            floatScreenResult = (float) 0;
        }
        else if(view.getId()==R.id.button_0){
            Log.i("Wciśnięto przycisk:","przucisk 0.");

            floatScreenResult = (float) 0;
        }
        else if(view.getId()==R.id.button_1){
            Log.i("Wciśnięto przycisk:","przucisk 1.");
            floatScreenResult = (float) 1;
        }


        screenResult = String.valueOf(floatScreenResult);
        editTextNumberDecimal.setText(screenResult);
    }


}