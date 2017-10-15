package com.example.acer.iot;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Loginpage extends AppCompatActivity {

    public Button btn1, btn2;
    EditText etusername, etpassword;
    BluetoothAdapter b_adapter;
    int BLUETOOTH_REQUEST = 1;
    int counter = 5;

    public void init() {
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button = new Intent(Loginpage.this, signuppage.class);
                startActivity(button);
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init();
        etusername = (EditText) findViewById(R.id.etusername);
        etpassword = (EditText) findViewById(R.id.etpassword);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                validate(etusername.getText().toString(), etpassword.getText().toString());
                b_adapter = BluetoothAdapter.getDefaultAdapter();
                if (b_adapter == null) {
                    Toast.makeText(getBaseContext(), "No bluetooth adapter found", Toast.LENGTH_LONG).show();

                } else {
                    if (!b_adapter.isEnabled()) {
                        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i, BLUETOOTH_REQUEST);
                    }
                }
            }
        });
    }

    private void validate(String userName, String userpassword) {
        if ((userName.equals("iot")) && (userpassword.equals("1234"))) {
            Intent intent = new Intent(Loginpage.this, MainActivity_map.class);
            startActivity(intent);
        } else {
            counter--;
            if (counter == 0) {
                btn1.setEnabled(false);
            }
        }

    }

    public void onActivityResult(int request_code, int result_code, Intent data) {
        if (request_code == BLUETOOTH_REQUEST) {
            if (result_code == RESULT_OK) {
                Toast.makeText(getBaseContext(), "BLUETOOTH SUCCEFULLY TURNED ON", Toast.LENGTH_LONG).show();

            }
            if (result_code == RESULT_CANCELED) {
                Toast.makeText(getBaseContext(), "BLUETOOTH TURN ON FAILED", Toast.LENGTH_LONG).show();
            }
        }
    }


}

