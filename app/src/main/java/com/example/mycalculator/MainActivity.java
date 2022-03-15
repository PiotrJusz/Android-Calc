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
    String currentResult;
    String currentOperation="";
    boolean status = false; //true dla operacji, false dla liczby

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

    public String addNumber(int k, String result){  //dopisanie cyfry na ostatnia pozycje
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
    public String addNumber(String symbol, String result){  //wciśniecie przycisku "'" - point decimal
        String newScreenResult;
        if (result.contains(".")){
            newScreenResult = result;
        }
        else{
            newScreenResult = result+String.valueOf(symbol);
        }
        return newScreenResult;
    }
    public String addNumber(String result){ //wciśnięcie przycisku DEL
        if(result.length()==1){
            return "0";
        }
        else{
            return result.substring(0,result.length()-1);
        }
    }
    private String getResult(String first,String op, String second){
        String tempInfo =  first + " " + op + " " + second;
        Log.i("getResult(): ",tempInfo);
        //op - currentOperation
        String result="";
        switch(op){
            case "None":
                //nic sie na razie nie dzieje
                result = String.valueOf( 0 + Double.parseDouble(second));
            case "+":
                if (!(first ==(""))){
                    result =  String.valueOf( (Double.parseDouble(first) + Double.parseDouble(second)) );
                }
                break;
            case "-":
                if (!(first ==(""))){
                    result =  String.valueOf( (Double.parseDouble(first) - Double.parseDouble(second)) );
                }
                break;
            case "*":
                if (!(first ==(""))){
                    result =  String.valueOf( (Double.parseDouble(first) * Double.parseDouble(second)) );
                }
                break;
            case "/":
                if (!(first ==(""))){
                    result =  String.valueOf( (Double.parseDouble(first) / Double.parseDouble(second)) );
                }
                break;

        }
        return result;
    }

    public void clickButton(View view){
        //Log.i("Zdarzenie:","Wciśnięto przycisk!");
        //Log.i("Button", String.valueOf(view.getId()));

        EditText editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);

        String screenResult = String.valueOf(editTextNumberDecimal.getText());
        if(view.getId()==R.id.button_0){
            if (!status) {
                status = false;
                screenResult = addNumber(0, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(0,"");
            }
        }
        if(view.getId()==R.id.button_1){
            if (!status) {
                status = false;
                screenResult = addNumber(1, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(1,"");
            }
        }
        else if(view.getId()==R.id.button_2){
            if (!status) {
                status = false;
                screenResult = addNumber(2, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(2,"");
            }
        }
        else if(view.getId()==R.id.button_3){
            if (!status) {
                status = false;
                screenResult = addNumber(3, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(3,"");
            }
        }
        else if(view.getId()==R.id.button_4){
            if (!status) {
                status = false;
                screenResult = addNumber(4, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(4,"");
            }
        }
        else if(view.getId()==R.id.button_5){
            if (!status) {
                status = false;
                screenResult = addNumber(5, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(5,"");
            }
        }
        else if(view.getId()==R.id.button_6){
            if (!status) {
                status = false;
                screenResult = addNumber(6, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(6,"");
            }
        }
        else if(view.getId()==R.id.button_7){
            if (!status) {
                status = false;
                screenResult = addNumber(7, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(7,"");
            }
        }
        else if(view.getId()==R.id.button_8){
            if (!status) {
                status = false;
                screenResult = addNumber(8, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(8,"");
            }
        }
        else if(view.getId()==R.id.button_9){
            if (!status) {
                status = false;
                screenResult = addNumber(9, screenResult);
            }
            else if(status){
                status=false;
                screenResult = addNumber(9,"");
            }
        }
        else if(view.getId()==R.id.button_C){
            status=false;
            screenResult = addNumber(0,"");
            //zerowanie currentOperation i screenBufor - przygotowanie do następneh operacji
            currentOperation="None";
            screenBufor="";
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
        else if(view.getId()==R.id.button_result){
            //status=true;
            //tu powinna byc funkcja getResult
            screenResult = getResult(screenBufor,currentOperation,screenResult);
            //zerowanie currentOperation i przypisanie wyniku do zmiennej screenBufor - przygotowanie do następnej operacji
            screenBufor=screenResult;
            currentOperation="";
        }
        else if(view.getId()==R.id.button_toAdd){
            status = true;
            Log.i("currentOperation:",currentOperation);
            if(currentOperation.equals("+")){
                screenResult = getResult(screenBufor,"+",screenResult);
            }
            currentOperation = "+";

            screenBufor=screenResult;
            Log.i("screenBuffor_1: ",screenBufor );
        }
        else if(view.getId()==R.id.button_toSubstract){
            status = true;
            Log.i("currentOperation:",currentOperation);
            if(currentOperation.equals("+")){
                screenResult = getResult(screenBufor,"+",screenResult);
            }
            else if (currentOperation.equals("-")) {
                screenResult = getResult(screenBufor,"-",screenResult);
            }
            currentOperation = "-";

            screenBufor=screenResult;
            Log.i("screenBuffor_1: ",screenBufor );
        }


        if(!status) {    //wciśnięcie przycisku z cyfrą
            editTextNumberDecimal.setText(screenResult);
            if (status) {    //wcisnięcie klawisza operacji + - * /
                screenBufor = screenResult;   //zerowanie lwartości - przygotowanie do następnej operacji
            }
        }
        else{
            editTextNumberDecimal.setText(screenResult);
        }





    }


}