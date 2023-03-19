package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtCost;
    private RadioButton rb_Okay;
    private Button btn;
    private RadioGroup radioGroup;
    private TextView result;
    private SwitchCompat round_up_tip;

    private final int AMAZING = R.id.rdoAmazing;
    private final int GOOD = R.id.rdoGood;
    private final int OKAY = R.id.rdoOkay;

    double costOfService = 0;
    double tipPercentage = 0;
    int tipAmountInInt = 0;
    double tipAmountInDouble = 0;
    boolean isChecked = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCost = findViewById(R.id.txtCostofService);
        btn = findViewById(R.id.btnCalculate);
        result = findViewById(R.id.tvTipAmount);
        radioGroup = findViewById(R.id.radio_group);
        rb_Okay = findViewById(OKAY);
        round_up_tip = (SwitchCompat) findViewById(R.id.round_up_tip);

        btn.setOnClickListener(view -> {
                    if (txtCost.getText().toString().equals("")) {
                        txtCost.setError("Enter cost of service!");
                        return;
                    }

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    if (checkedId < 0) {
                        rb_Okay.setError("Select service type!");
                        return;
                    }

                    try {
                        costOfService = Double.parseDouble(txtCost.getText().toString());
                    } catch (Exception ex) {
                        txtCost.setError("Invalid cost of Service!");
                        return;
                    }

                    switch (checkedId) {
                        case AMAZING:
                            tipPercentage = 0.20;
                            break;

                        case GOOD:
                            tipPercentage = 0.18;
                            break;

                        case OKAY:
                            tipPercentage = 0.15;
                            break;
                    }

                    round_up_tip.setOnCheckedChangeListener((compoundButton, b) -> {
                        if (b) {
                            isChecked = true;
                        } else {
                            isChecked = false;
                        }
                    });

                    if (isChecked) {
                        tipAmountInInt = (int) Math.round(costOfService * tipPercentage);
                        result.setText(Double.toString(tipAmountInInt));
                    } else {
                        tipAmountInDouble = costOfService * tipPercentage;
                        result.setText(Double.toString(tipAmountInDouble));
                    }
                });

        //SET LISTENER TO THE RADIO GROUP
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch(i) {
                case AMAZING:
                    Toast.makeText(MainActivity.this, "Amazing, clicked", Toast.LENGTH_SHORT).show();
                    break;

                case GOOD:
                    Toast.makeText(MainActivity.this, "Good, clicked", Toast.LENGTH_SHORT).show();
                    break;

                case OKAY:
                    Toast.makeText(MainActivity.this, "Okay, clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}