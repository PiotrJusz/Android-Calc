package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String screenBufor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //Button button_plus_minus = (Button)findViewById(R.id.button_plus_minus);
    private String Multiply(String result, int k){
        double dResult=Double.valueOf(result);
        return String.valueOf(dResult * k);
    }

    public String addNumber(int k, String result){
        String newScreenResult;
        if (result.equals("0")){
            newScreenResult=String.valueOf(k);
        }
        else{
            newScreenResult = result+String.valueOf(k);
        }
        //Double fResult = Double.parseDouble(result);
        //newScreenResult = String.valueOf(fResult * 10 + k);
        return newScreenResult;
    }
    public String addNumber(String symbol, String result){
        String newScreenResult;
        if (result.contains(".")){
            newScreenResult = result;
        }
        else{
            newScreenResult = result+String.valueOf(symbol);
        }
        return newScreenResult;
    }
    public String addNumber(String result){
        if(result.length()==1){
            return "0";
        }
        else{
            return result.substring(0,result.length()-1);
        }
    }

    public void clickButton(View view){
        //Log.i("Zdarzenie:","Wciśnięto przycisk!");
        //Log.i("Button", String.valueOf(view.getId()));

        EditText editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);
        boolean status; //true dla operacji, false dla liczby
        status=true;
        String screenResult = String.valueOf(editTextNumberDecimal.getText());
        if(view.getId()==R.id.button_0){
            status=false;
            screenResult = addNumber(0,screenResult);
        }
        if(view.getId()==R.id.button_1){
            status=false;
            screenResult = addNumber(1,screenResult);
        }
        else if(view.getId()==R.id.button_2){
            status=false;
            screenResult = addNumber(2,screenResult);
        }
        else if(view.getId()==R.id.button_3){
            status=false;
            screenResult = addNumber(3,screenResult);
        }
        else if(view.getId()==R.id.button_4){
            status=false;
            screenResult = addNumber(4,screenResult);
        }
        else if(view.getId()==R.id.button_5){
            status=false;
            screenResult = addNumber(5,screenResult);
        }
        else if(view.getId()==R.id.button_6){
            status=false;
            screenResult = addNumber(6,screenResult);
        }
        else if(view.getId()==R.id.button_7){
            status=false;
            screenResult = addNumber(7,screenResult);
        }
        else if(view.getId()==R.id.button_8){
            status=false;
            screenResult = addNumber(8,screenResult);
        }
        else if(view.getId()==R.id.button_9){
            status=false;
            screenResult = addNumber(9,screenResult);
        }
        else if(view.getId()==R.id.button_C){
            status=false;
            screenResult = addNumber(0,"");
        }
        else if(view.getId()==R.id.button_decimal_point){
            status=false;
            screenResult = addNumber(".",screenResult);
        }
        else if(view.getId()==R.id.button_del){
            status=false;
            screenResult=addNumber(screenResult);
        }
        else if(view.getId()==R.id.button_plus_minus){
            status=false;
            screenResult=Multiply(screenResult,-1);
        }


        if(!status){
            editTextNumberDecimal.setText(screenResult);
        }


        /*String screenResult;
        Float floatScreenResult;
        boolean status; //true jeśli ostatnim przycisliem wciśniętym jest operacja (+,-,*./ itd)
        String tempScreenResult;    //tymczasowy screen result służący do dodawanie nowych liczb

        screenResult = String.valueOf(editTextNumberDecimal.getText());
        floatScreenResult = Float.valueOf(screenResult);

        if (view.getId()==R.id.button_plus_minus){
            //Log.i("Informacja:","Wciśnięto przycisk plus/minus");
            floatScreenResult = (-1) * floatScreenResult;
        }
        else if(view.getId()==R.id.button_0){
            Log.i("Wciśnięto przycisk:","przucisk 0.");
            floatScreenResult = (float) 0;
            status=false;
        }
        else if(view.getId()==R.id.button_1){
            //Log.i("Wciśnięto przycisk:","przucisk 1.");
            floatScreenResult = (float) 1;
            status=false;
        }
        else if(view.getId()==R.id.button_2){
            floatScreenResult = (float) 2;
            status=false;
        }
        else if(view.getId()==R.id.button_3){
            floatScreenResult = (float) 3;
            status=false;
        }
        else if(view.getId()==R.id.button_4){
            floatScreenResult = (float) 4;
        }
        else if(view.getId()==R.id.button_5){
            floatScreenResult = (float) 5;
            status=false;
        }
        else if(view.getId()==R.id.button_6){
            floatScreenResult = (float) 6;
            status=false;
        }
        else if(view.getId()==R.id.button_7){
            floatScreenResult = (float) 7;
            status=false;
        }
        else if(view.getId()==R.id.button_8){
            floatScreenResult = (float) 8;
            status=false;
        }
        else if(view.getId()==R.id.button_9){
            floatScreenResult = (float) 9;
            status=false;
        }
        else if(view.getId()==R.id.button_toAdd){
            screenBufor += "+";
            status= true;
        }
        else if(view.getId()==R.id.button_toDivide){
            screenBufor += "/";
            status= true;
        }
        else if(view.getId()==R.id.button_multiply){
            screenBufor += "*";
            status= true;
        }
        else if(view.getId()==R.id.button_toSubstract){
            screenBufor += "-";
            status= true;
        }
        else if(view.getId()==R.id.button_result){
            screenBufor += "=";
            status= true;
        }
        else if(view.getId()==R.id.button_C){
            //Log.i("Wciśnięto przycisk:","przucisk C.");
            floatScreenResult = (float) 0;
            screenBufor = "";
            status= true;
        }*/


    }


}