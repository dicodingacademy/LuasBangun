package com.nbs.luasbangun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtPanjang, edtLebar;

    private Button btnHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initAction();
    }

    private void initAction() {
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPanjang =
                        edtPanjang.getText()
                        .toString().trim();

                String strLebar =
                        edtLebar.getText()
                        .toString().trim();

                validate(strPanjang, strLebar);
            }
        });
    }

    private void initView() {
        edtPanjang = findViewById(R.id.edt_panjang);
        edtLebar = findViewById(R.id.edt_lebar);
        btnHitung = findViewById(R.id.btn_hitung);
    }

    private void validate(String panjang, String lebar){
        if (TextUtils.isEmpty(panjang) ||
                TextUtils.isEmpty(lebar)){
            Toast.makeText(MainActivity.this,
                    "Field tidak boleh kosong",
                    Toast.LENGTH_SHORT).show();
        }else{
            double dPanjang =
                    Double.parseDouble(panjang);

            double dLebar =
                    Double.parseDouble(lebar);

            if (dPanjang < 0 || dLebar < 0){
                Toast.makeText(MainActivity.this,
                        "Nilai tidak boleh negatif", Toast.LENGTH_SHORT)
                        .show();
            }else{
                getLuas(dPanjang, dLebar);
            }
        }
    }

    private void getLuas(double dPanjang, double dLebar) {
        double luas = dPanjang * dLebar;
        Toast.makeText(MainActivity.this,
                "Luas "+luas, Toast.LENGTH_SHORT)
                .show();
    }
}
