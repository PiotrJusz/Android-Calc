package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String screenBufor = "";
    String currentResult;
    String currentOperation = "";
    String lastOperation = "";
    boolean status = false; //true dla operacji, false dla liczby
    String memory = "0";
    int height; //for resolution
    int width;  //for resolution
    int NUMBER_Of_BUTTONS_height = 8;
    //TextView textViewMemory = (TextView) findViewById(R.id.textViewMemory);

    public MainActivity() {

    }
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getResolutions();

        //get Resolutions:
        int resolutions[] = new int[2];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        resolutions[0]=height;
        resolutions[1]=width;
        Log.i("Resolutions_height", String.valueOf(height));
        Log.i("Resolutions_width:", String.valueOf(width));

    }

    private String Multiply(String result, int k) {
        double dResult = Double.valueOf(result);
        return String.valueOf(dResult * k);
    }
    private int[] getResolutions(){
        int resolutions[] = new int[2];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels-((NUMBER_Of_BUTTONS_height+1)*9);
        int width = displayMetrics.widthPixels;
        resolutions[0]=height;
        resolutions[1]=width;
        Log.i("Resolutions_height", String.valueOf(height));
        Log.i("Resolutions_width:", String.valueOf(width));
        return resolutions;
    }
    private int setHeight(){
        return height/NUMBER_Of_BUTTONS_height;
    }

    public String addNumber(int k, String result) {  //dopisanie cyfry na ostatnia pozycje
        String newScreenResult;
        if (result.equals("0")) {
            newScreenResult = String.valueOf(k);
        } else {
            newScreenResult = result + String.valueOf(k);
        }

        return newScreenResult;
    }

    public String addNumber(String symbol, String result) {  //wciśniecie przycisku "'" - point decimal
        String newScreenResult;
        if (result.contains(".")) {
            newScreenResult = result;
        } else {
            newScreenResult = result + String.valueOf(symbol);
        }
        return newScreenResult;
    }

    public String addNumber(String result) { //wciśnięcie przycisku DEL
        if (result.length() == 1) {
            return "0";
        } else {
            return result.substring(0, result.length() - 1);
        }
    }

    private String convertPercent(String strValue, String strLastValue, String op) {
        //if(strValue.equals(""))
        //strValue="0";
        //if(strLastValue.equals(""))
        //strLastValue="0";
        if (op.equals("*") || op.equals("/")) {
            if (strValue.equals(""))
                strValue = "0";
            strValue = String.valueOf(Double.valueOf(strValue) / 100);
        } else if (op.equals("") || strLastValue.equals("")) { //zachodzi w przypadku wpisania procentu jako pierwszy argument w dzialaniu
            Log.i("convertPercent", "op is empty and strLastValue...");
            strValue = String.valueOf(Double.valueOf(strValue) / 100);
        } else {   //zachodzi dla op gdy jest+ albo -
            if (strValue.equals(""))
                strValue = "0";
            if (strLastValue.equals(""))
                strLastValue = "0";
            strValue = String.valueOf(Double.valueOf(strLastValue) * Double.valueOf(strValue) / 100);
        }

        return strValue;

    }

    private String getResult(String first, String op, String second) {
        String tempInfo = first + " " + op + " " + second;
        //op - currentOperation
        String result = "";
        switch (op) {
            case "":
                //nic sie na razie nie dzieje
                //result = String.valueOf( 0 + Double.parseDouble(second));
                //result=String.valueOf(editTextNumberDecimal.getText());
            case "+":
                if (!(first == (""))) {
                    result = String.valueOf((Double.parseDouble(first) + Double.parseDouble(second)));
                }
                break;
            case "-":
                if (!(first == (""))) {
                    result = String.valueOf((Double.parseDouble(first) - Double.parseDouble(second)));
                }
                break;
            case "*":
                if (!(first == (""))) {
                    result = String.valueOf((Double.parseDouble(first) * Double.parseDouble(second)));
                }
                break;
            case "/":
                if (!(first == (""))) {
                    result = String.valueOf((Double.parseDouble(first) / Double.parseDouble(second)));
                }
                break;
            case "%":
                if (!(first == (""))) {
                    result = String.valueOf((Double.parseDouble(first) / Double.parseDouble(second)));
                }
                break;

        }
        if (result.equals("")) {
            EditText editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);

            result = String.valueOf(editTextNumberDecimal.getText());
        }
        result = setProperlyResult(result); //ustawianie wartości bez potrzebnych zer
        return result;
    }

    private String setProperlyResult(String result) {
        //ucinanie niepotrzebnego zera
        int l = result.length();
        if (l >= 3) {
            if (result.charAt(l - 1) == '0') {
                if (result.charAt(l - 2) == '.') {
                    result = result.substring(0, l - 2);
                }
            }
        }
        return result;
    }

    private void printStatus() {
        Log.i("Status of lastoperation)", lastOperation);
        Log.i("Status of status:", String.valueOf(status));
    }
    private String getScreenValue(){
        EditText editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);
        return String.valueOf(editTextNumberDecimal.getText());
    }

    public void clickButton(View view) {

        Log.i("Status po sciśnięciu przycisku:", "______________");
        printStatus();

        EditText editTextNumberDecimal = (EditText) findViewById(R.id.editTextNumberDecimal);
        editTextNumberDecimal.setHeight(setHeight());
        //TextView textViewMemory = (TextView) findViewById(R.id.textViewMemory);

        String screenResult = String.valueOf(editTextNumberDecimal.getText());
        TextView textViewMemory = (TextView) findViewById(R.id.textViewMemory);
        textViewMemory.setHeight(setHeight());

        if (view.getId() == R.id.button_0) {
            if (!status) {
                status = false;
                screenResult = addNumber(0, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(0, "");
            }
        }
        if (view.getId() == R.id.button_1) {
            if (!status) {
                status = false;
                screenResult = addNumber(1, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(1, "");
            }
        } else if (view.getId() == R.id.button_2) {
            if (!status) {
                status = false;
                screenResult = addNumber(2, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(2, "");
            }
        } else if (view.getId() == R.id.button_3) {
            if (!status) {
                status = false;
                screenResult = addNumber(3, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(3, "");
            }
        } else if (view.getId() == R.id.button_4) {
            if (!status) {
                status = false;
                screenResult = addNumber(4, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(4, "");
            }
        } else if (view.getId() == R.id.button_5) {
            if (!status) {
                status = false;
                screenResult = addNumber(5, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(5, "");
            }
        } else if (view.getId() == R.id.button_6) {
            if (!status) {
                status = false;
                screenResult = addNumber(6, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(6, "");
            }
        } else if (view.getId() == R.id.button_7) {
            if (!status) {
                status = false;
                screenResult = addNumber(7, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(7, "");
            }
        } else if (view.getId() == R.id.button_8) {
            if (!status) {
                status = false;
                screenResult = addNumber(8, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(8, "");
            }
        } else if (view.getId() == R.id.button_9) {
            if (!status) {
                status = false;
                screenResult = addNumber(9, screenResult);
            } else if (status) {
                status = false;
                screenResult = addNumber(9, "");
            }
        } else if (view.getId() == R.id.button_C) {
            status = false;
            screenResult = addNumber(0, "");
            //zerowanie currentOperation i screenBufor - przygotowanie do następneh operacji
            currentOperation = "";
            screenBufor = "";
        } else if (view.getId() == R.id.button_decimal_point) {
            status = false;
            screenResult = addNumber(".", screenResult);
        } else if (view.getId() == R.id.button_del) {
            status = false;
            screenResult = addNumber(screenResult);
        } else if (view.getId() == R.id.button_plus_minus) {
            status = false;
            screenResult = setProperlyResult(Multiply(screenResult, -1));
        } else if (view.getId() == R.id.button_result) {
            //status=true;
            screenResult = getResult(screenBufor, currentOperation, screenResult);
            //zerowanie currentOperation i przypisanie wyniku do zmiennej screenBufor - przygotowanie do następnej operacji
            screenBufor = screenResult;
            currentOperation = "";
            status = true;    //ustawianie statusu true - umożliwienie resetowanie screenbuffor i wpisanie nowej cyfry zaar po wcisnięciu przycisku "="
        } else if (view.getId() == R.id.button_toAdd) {
            status = true;
            if (lastOperation.equals("")) {
                if (currentOperation.equals("+")) {
                    screenResult = getResult(screenBufor, "+", screenResult);
                } else if (currentOperation.equals("-")) {
                    screenResult = getResult(screenBufor, "-", screenResult);
                } else if (currentOperation.equals("*")) {
                    screenResult = getResult(screenBufor, "*", screenResult);
                } else if (currentOperation.equals("/")) {
                    screenResult = getResult(screenBufor, "/", screenResult);
                }

                screenBufor = screenResult;
            }
            currentOperation = "+";
        } else if (view.getId() == R.id.button_toSubstract) {
            status = true;
            if (lastOperation.equals("")) {
                if (currentOperation.equals("+")) {
                    screenResult = getResult(screenBufor, "+", screenResult);
                } else if (currentOperation.equals("-")) {
                    screenResult = getResult(screenBufor, "-", screenResult);
                } else if (currentOperation.equals("*")) {
                    screenResult = getResult(screenBufor, "*", screenResult);
                } else if (currentOperation.equals("/")) {
                    screenResult = getResult(screenBufor, "/", screenResult);
                }
                screenBufor = screenResult;
            }
            currentOperation = "-";


        } else if (view.getId() == R.id.button_multiply) {
            status = true;
            if (lastOperation.equals("")) {
                if (currentOperation.equals("+")) {
                    screenResult = getResult(screenBufor, "+", screenResult);
                } else if (currentOperation.equals("-")) {
                    screenResult = getResult(screenBufor, "-", screenResult);
                } else if (currentOperation.equals("*")) {
                    screenResult = getResult(screenBufor, "*", screenResult);
                } else if (currentOperation.equals("/")) {
                    screenResult = getResult(screenBufor, "/", screenResult);
                }
                screenBufor = screenResult;
            }
            currentOperation = "*";


        } else if (view.getId() == R.id.button_toDivide) {
            status = true;
            if (lastOperation.equals("")) {
                if (currentOperation.equals("+")) {
                    screenResult = getResult(screenBufor, "+", screenResult);
                } else if (currentOperation.equals("-")) {
                    screenResult = getResult(screenBufor, "-", screenResult);
                } else if (currentOperation.equals("*")) {
                    screenResult = getResult(screenBufor, "*", screenResult);
                } else if (currentOperation.equals("/")) {
                    screenResult = getResult(screenBufor, "/", screenResult);
                }
                screenBufor = screenResult;
            }
            currentOperation = "/";
        } else if (view.getId() == R.id.button_procent) {
            if (currentOperation.equals("")) {
                //editTextNumberDecimal.setText("0");
            } else {
                //konwersja liczby na procenty: funkcja convertPercent
                screenResult = convertPercent(screenResult, screenBufor, currentOperation);
                if (status == true) {
                    screenBufor = screenResult;
                }
            }
        }
        //obsuga przycisków memory
        else if (view.getId() == R.id.button_m_plus) {
            memory = String.valueOf(Double.valueOf(memory)+Double.valueOf(getScreenValue()));
        }
        else if (view.getId() == R.id.button_m_minus){
            memory = String.valueOf(Double.valueOf(memory)-Double.valueOf(getScreenValue()));
        }
        else if(view.getId() == R.id.button_mc){
            memory = "0";
        }
        else if (view.getId() == R.id.button_mr){
            if(   !(memory.equals("0") || memory.equals("0.0"))  ) {
                screenResult = setProperlyResult(memory);
            }
        }
        if (  !(memory.equals("0") || memory.equals("0.0"))  ){
            textViewMemory.setText("   MR: "+setProperlyResult(memory));
        }
        else{
            textViewMemory.setText("");
        }

        if (!status) {    //wciśnięcie przycisku z cyfrą
            lastOperation = "";
            editTextNumberDecimal.setText(screenResult);
            if (status) {    //wcisnięcie klawisza operacji + - * /
                screenBufor = screenResult;   //zerowanie lwartości - przygotowanie do następnej operacji
            }
        } else {
            editTextNumberDecimal.setText(screenResult);
            lastOperation = currentOperation;
        }

        Log.i("Ststus po wykonaniu operacji:", "______________");
        printStatus();
    }
}