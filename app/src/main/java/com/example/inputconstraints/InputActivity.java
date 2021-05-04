package com.example.inputconstraints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inputconstraints.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {
    ActivityInputBinding c;
    private String checkConstraints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(c.getRoot());
    }

    ///Send data back to the Main Activity..
    public void SendData(View view) {
        ///Create input...
        String input = c.data.getText().toString().trim();

        if (input.isEmpty()) {
            c.data.setError("Please enter something");
            return;
        } else if (!input.matches(checkConstraints())) {
            c.data.setError("Invalid Constraints");
            return;
        } else {
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            intent.putExtra(Constants.CONSTRAINT_KEY, input);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    private String checkConstraints() {

        Bundle bundle = getIntent().getExtras();
        for (String string : bundle.keySet()) {
            bundle.getString(string);
        }
        StringBuilder reg = new StringBuilder();

        reg.append("^([");
        if (Boolean.parseBoolean(bundle.getString(Constants.UPPERCASE_KEY, "false"))) {
            reg.append("A-Z");
        }

        if (Boolean.parseBoolean(bundle.getString(Constants.LOWERCASE_KEY, "false"))) {
            reg.append("a-z");

        }

        if (Boolean.parseBoolean(bundle.getString(Constants.DIGITS_KEY, "false"))) {
            reg.append("0-9");

        }

        if (Boolean.parseBoolean(bundle.getString(Constants.EXPRESSION_KEY, "false"))) {
            reg.append("+-/*%");

        }

        if (Boolean.parseBoolean(bundle.getString(Constants.SYMBOL_KEY, "false"))) {
            reg.append("@#\\\\^{}\\]\"\"^()?`~!;:''.,|\\[");
        }
            reg.append("])+");
            //Return final string
            return reg.toString();
        }
}


