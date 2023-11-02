package com.hactiv8.calculator3;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_tv, result_tv;
    MaterialButton btnC, btnKurungAwal, btnKurungAkhir;
    MaterialButton btnBagi, btnKali, btnKurang, btnTambah;
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

    }
}