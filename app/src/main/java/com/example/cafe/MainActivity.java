package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initValues();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                if(username.isEmpty() || userPassword.isEmpty()){
                    Toast.makeText(
                            MainActivity.this,
                            R.string.please_fill_all_fields,
                            Toast.LENGTH_SHORT
                    ).show();
                }else{
                    launchNextScreen(username);
                }
            }
        });
    }

    private void initValues(){
        name = findViewById(R.id.editTextName);
        password = findViewById(R.id.editTextPassword);
        signIn = findViewById(R.id.signInButton);
    }

    private void launchNextScreen(String username){
        Intent intent = MakeOrderActivity.newIntent(this, username);

        startActivity(intent);
    }
}