package com.nbs.luasbangun;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText edtWidth, edtHeight;

    private Button btnCalculate;

    private TextView tvResult;

    private CalculationViewmodel calculationViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionbar();

        initView();

        initViewModel();

        initAction();

    }

    private void initViewModel() {
        calculationViewmodel = ViewModelProviders.of(this).get(CalculationViewmodel.class);
        printResult(calculationViewmodel.calculationResult);
    }

    private void initAction() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearResult();

                if (validate()){
                    printResult(calculate());
                }
            }
        });
    }

    private double calculate() {
        double width = Double.parseDouble(edtWidth.getText().toString().trim());
        double height = Double.parseDouble(edtHeight.getText().toString().trim());
        return Formula.calculateRectangleArea(width, height);
    }

    private void clearResult() {
        tvResult.setText("");
    }

    private void initView() {
        edtHeight = findViewById(R.id.edt_height);
        edtWidth = findViewById(R.id.edt_width);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
    }

    private void initActionbar() {
        getSupportActionBar().setTitle(R.string.activity_title_calculate_area);
    }

    private void printResult(double calculationResult) {
        updateViewmodelValue(calculationResult);

        String result = new DecimalFormat("##.##").format(calculationResult);
        String strResult = String.format(getString(R.string.result), result);
        tvResult.setText(strResult);
    }

    private void updateViewmodelValue(double result){
        calculationViewmodel.calculationResult = result;
    }



    private boolean validate(){
        String strWidth = edtWidth.getText().toString().trim();
        String strHeight = edtHeight.getText().toString().trim();

        boolean isValid = true;

        if (TextUtils.isEmpty(strWidth) || TextUtils.isEmpty(strHeight)){
            showToast(getString(R.string.error_message_fields_empty));
            isValid = false;
        }else{
            double dWidth = Double.parseDouble(strWidth);
            double dHeigth = Double.parseDouble(strHeight);

            if (dWidth < 0 || dHeigth < 0){
                showToast(getString(R.string.error_message_invalid_value));
                isValid = false;
            }
        }

        return isValid;
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
