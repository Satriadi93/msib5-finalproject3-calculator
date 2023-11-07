package com.hactiv8.calculator3;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_tv, result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);

        deklarasiIdBtn(R.id.btnC);
        deklarasiIdBtn(R.id.btnKurungAwalAkhir);
        deklarasiIdBtn(R.id.btnBagi);
        deklarasiIdBtn(R.id.btnKali);
        deklarasiIdBtn(R.id.btnKurang);
        deklarasiIdBtn(R.id.btnTambah);
        deklarasiIdBtn(R.id.btnKoma);
        deklarasiIdBtn(R.id.btnHapus);
        deklarasiIdBtn(R.id.btnSamaDengan);
        deklarasiIdBtn(R.id.btnPersen);
        deklarasiIdBtn(R.id.btn0);
        deklarasiIdBtn(R.id.btn1);
        deklarasiIdBtn(R.id.btn2);
        deklarasiIdBtn(R.id.btn3);
        deklarasiIdBtn(R.id.btn4);
        deklarasiIdBtn(R.id.btn5);
        deklarasiIdBtn(R.id.btn6);
        deklarasiIdBtn(R.id.btn7);
        deklarasiIdBtn(R.id.btn8);
        deklarasiIdBtn(R.id.btn9);
    }


    void deklarasiIdBtn(int id){
        MaterialButton btnTES = findViewById(id);
        btnTES.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solution_tv.getText().toString();

        switch (buttonText) {
            case "C":
                solution_tv.setText("");
                result_tv.setText("0");
                return;
            case "=":
                solution_tv.setText(result_tv.getText());
                return;
            case "del":
                if (!"".equals(dataCalculate)) {
                    dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
                }
                break;

            case "( )":
                int openParenthesisCount = countOccurrences(dataCalculate, "(");
                int closeParenthesisCount = countOccurrences(dataCalculate, ")");

                if (openParenthesisCount == closeParenthesisCount) {
                    dataCalculate = dataCalculate + "(";
                } else if (openParenthesisCount > closeParenthesisCount) {
                    dataCalculate = dataCalculate + ")";
                } else {
                    dataCalculate = dataCalculate + "(";
                }

                break;

            default:
                List<String> operator = Arrays.asList("+", "-", "*", "/", "%", ".");
                List<String> operatorv2 = Arrays.asList("+", "-", "*", "/", ".");
                if (operator.contains(buttonText) && dataCalculate.isEmpty()) {
                    return;
                } else if (operator.contains(buttonText) && operatorv2.contains(dataCalculate.substring(dataCalculate.length() - 1))) {
                    dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
                }
                dataCalculate = dataCalculate + buttonText;

                break;
        }

        solution_tv.setText(dataCalculate);
        String finalResult = getResult(dataCalculate);
            if (!"Err".equals(finalResult)){
                if (!"".equals(dataCalculate)){
                    result_tv.setText(finalResult);
                }else {
                    result_tv.setText("0");
                }
            }
    }

    private int countOccurrences(String text, String target) {
        int count = 0;
        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = text.indexOf(target, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += target.length();
            }
        }

        return count;
    }


    String getResult(String data) {
        char temp;
        for (int i = 0; i < data.length(); i++) {
            temp = data.charAt(i);
            if (temp == '%') {
                if (i + 1 >= data.length() || !Character.isDigit(data.charAt(i + 1))) {
                    data = data.substring(0, i) + "/100*1" + data.substring(i + 1);
                } else {
                    data = data.substring(0, i) + "/100*" + data.substring(i + 1);
                }
            }
        }
        data = data.replaceAll("\\b0+(?!\\b)", "");
        try {

            if(data.isEmpty()){
                return "";
            }

            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            double result = Double.parseDouble(finalResult);
            DecimalFormat decimalFormat = new DecimalFormat("#.########"); // Format untuk menghilangkan koma nol
            finalResult = decimalFormat.format(result);

            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }

}