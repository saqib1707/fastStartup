package com.example.saqib1707.faststartup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText user,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.button_login);
        user=(EditText)findViewById(R.id.editText_userid);
        pwd=(EditText)findViewById(R.id.editText_password);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (user.getText().toString().equals("saqib1707") && pwd.getText().toString().equals("dishani")){
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Wrong ID",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
