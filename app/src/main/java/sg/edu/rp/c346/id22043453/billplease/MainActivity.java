package sg.edu.rp.c346.id22043453.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Step 1 - Declcare variables
    EditText etAmount;
    EditText etPax;
    ToggleButton tgSvsButton;
    ToggleButton tgGstButton;
    EditText etDiscount;
    RadioGroup rgPayment;
    RadioButton rbPaynow;
    Button btnSplit;
    Button btnReset;
    TextView tvTotal;
    TextView tvEachPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.idEditTextAmount);
        etPax = findViewById(R.id.idEditTextPax);
        tgSvsButton = findViewById(R.id.idSvsToggle);
        tgGstButton = findViewById(R.id.idGstToggle);
        etDiscount = findViewById(R.id.idEditTextDiscount);
        rgPayment = findViewById(R.id.idRadioGroupPayment);
        rbPaynow = findViewById(R.id.idPaynowRadioButton);
        btnSplit = findViewById(R.id.idSplitButton);
        btnReset = findViewById(R.id.idResetButton);
        tvTotal = findViewById(R.id.idTotalText);
        tvEachPay = findViewById(R.id.idEachPaysText);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action

                String amountString = etAmount.getText().toString();
                String paxString = etPax.getText().toString();
                boolean withSvs = tgSvsButton.isChecked();
                boolean withGst = tgGstButton.isChecked();
                double toAmount = Double.parseDouble(etAmount.getText().toString());
                double enterNewAmount = 0.0;

                // Calculate with or without SVS and GST
                if (amountString.trim().length() != 0 && paxString.trim().length() != 0) {
                    if (!withSvs && !withGst) {
                        enterNewAmount = Double.parseDouble(etAmount.getText().toString());
                    }
                    else if (!withSvs && withGst) {
                        enterNewAmount = toAmount * 1.07;
                    }
                    else if (withSvs && !withGst) {
                        enterNewAmount = toAmount * 1.1;
                    }
                    else {
                        enterNewAmount = toAmount * 1.17;
                    }

                    // Calculate discount
                    String discountString = etDiscount.getText().toString();
                    double toDiscount = Double.parseDouble(etDiscount.getText().toString());

                    if (discountString.trim().length() != 0) {
                        enterNewAmount *= 1 - toDiscount / 100;
                    }

                    // To display total bill and each pays via cash or PayNow
                    tvTotal.setText("Total Bill: $ " + String.format("%.2f", enterNewAmount));
                    int toPax = Integer.parseInt(etPax.getText().toString());

                    if (toPax != 1) {
                        tvEachPay.setText("Each Pays: $" + String.format("%.2f", enterNewAmount / toPax) + " in cash");
                    }
                    else {
                        tvEachPay.setText("Each Pays: $" + enterNewAmount);
                    }

                    if (rbPaynow.isChecked()) {
                        tvEachPay.setText("Each Pays: $" + String.format("%.2f", enterNewAmount / toPax) + " via PatNow to 912345678");
                    }
                    else {
                        tvEachPay.setText("Each Pays: $" + enterNewAmount);
                    }
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action

                // To reset all elements
                etAmount.setText("");
                etPax.setText("");
                etDiscount.setText("");
                tgSvsButton.setChecked(false);
                tgGstButton.setChecked(true);
            }
        });
    }
}