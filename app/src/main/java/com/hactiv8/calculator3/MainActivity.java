package com.hactiv8.calculator3;

//import android.content.Context;

import android.os.Build;
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

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_tv, result_tv;
    MaterialButton btnC, btnKurungAwal, btnKurungAkhir;
    MaterialButton btnBagi, btnKali, btnKurang, btnTambah, btnPersen;
    MaterialButton btnKoma, btnHapus, btnSamaDengan;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);

        deklarasiIdBtn(btnC, R.id.btnC);
        deklarasiIdBtn(btnKurungAwal, R.id.btnKurungAwal);
        deklarasiIdBtn(btnKurungAkhir, R.id.btnKurungAkhir);
        deklarasiIdBtn(btnBagi, R.id.btnBagi);
        deklarasiIdBtn(btnKali, R.id.btnKali);
        deklarasiIdBtn(btnKurang, R.id.btnKurang);
        deklarasiIdBtn(btnTambah, R.id.btnTambah);
        deklarasiIdBtn(btnPersen, R.id.btnPersen);
        deklarasiIdBtn(btnKoma, R.id.btnKoma);
        deklarasiIdBtn(btnHapus, R.id.btnHapus);
        deklarasiIdBtn(btnSamaDengan, R.id.btnSamaDengan);
        deklarasiIdBtn(btn0, R.id.btn0);
        deklarasiIdBtn(btn1, R.id.btn1);
        deklarasiIdBtn(btn2, R.id.btn2);
        deklarasiIdBtn(btn3, R.id.btn3);
        deklarasiIdBtn(btn4, R.id.btn4);
        deklarasiIdBtn(btn5, R.id.btn5);
        deklarasiIdBtn(btn6, R.id.btn6);
        deklarasiIdBtn(btn7, R.id.btn7);
        deklarasiIdBtn(btn8, R.id.btn8);
        deklarasiIdBtn(btn9, R.id.btn9);
    }


    void deklarasiIdBtn(MaterialButton btnTES, int id){
        btnTES = findViewById(id);
        btnTES.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solution_tv.getText().toString();

        if ("C".equals(buttonText)){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        } else if ("=".equals(buttonText)) {
            solution_tv.setText(result_tv.getText());
            return;
        } else if ("del".equals(buttonText)) {
            if (!"".equals(dataCalculate)) {
                dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
            }

        } else {
            List<String> operator = Arrays.asList("+", "-", "*", "/", "%", ".");
            List<String> operatorv2 = Arrays.asList("+", "-", "*", "/", ".");
            if (operator.contains(buttonText) && dataCalculate.isEmpty()) {
                return;
            } else if (operator.contains(buttonText) && operatorv2.contains(dataCalculate.substring(dataCalculate.length() - 1))) {
                dataCalculate = dataCalculate.substring(0, dataCalculate.length() - 1);
            } else if (")".equals(buttonText)) {
                int jumlahKurungBuka = 0;
                int jumlahKurungTutup = 0;
                for (char c : dataCalculate.toCharArray()) {
                    if (c == '(') {
                        jumlahKurungBuka++;
                    } else if (c == ')') {
                        jumlahKurungTutup++;
                    }
                }
                if (jumlahKurungTutup >= jumlahKurungBuka) {
                    return;
                }
            }
            dataCalculate = dataCalculate+buttonText;
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
        String getResult(String data){
            char temp;
            for (int i = 0; i < data.length(); i++) {
                temp = data.charAt(i);
                if (temp == '%') {
                    data = data.substring(0, i) + "/100" + data.substring(i+1);
                }
            }
            try {
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable;
                scriptable = context.initStandardObjects();
                String finalResult = context.evaluateString(scriptable,data,"Javascript", 1,null).toString();
                return finalResult;
            }catch (Exception e){
                return "Err";
            }
        }
}