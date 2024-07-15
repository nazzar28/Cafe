package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MakeOrderActivity extends AppCompatActivity {

    TextView greetings;
    TextView additives;
    RadioGroup drinks;
    RadioButton tea;
    RadioButton coffee;
    CheckBox sugar;
    CheckBox milk;
    CheckBox lemon;
    Spinner spTea;
    Spinner spCoffee;
    Button makeOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_make_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    init values();

    public static Intent newIntent(Context context, String username){
        Intent intent = new Intent(context, MakeOrderActivity.class);

        intent.putExtra("username", username);

        return intent;
    }

    public void
}