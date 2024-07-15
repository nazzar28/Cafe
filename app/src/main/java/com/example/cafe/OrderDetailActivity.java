package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "username";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_DRINK_TYPE = "drinktype";
    private static final String EXTRA_ADDITIVES = "additives";

    private TextView name;
    private TextView drink;
    private TextView drinkType;
    private TextView additives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        Intent intent = getIntent();
        name.setText(intent.getStringExtra(EXTRA_USER_NAME));
        drink.setText(intent.getStringExtra(EXTRA_DRINK));
        drinkType.setText(intent.getStringExtra(EXTRA_DRINK_TYPE));
        additives.setText(intent.getStringExtra(EXTRA_ADDITIVES));
    }

    private void initViews(){
        name = findViewById(R.id.textViewName);
        drink = findViewById(R.id.textViewDrink);
        drinkType = findViewById(R.id.textViewDrinkType);
        additives = findViewById(R.id.textViewAdditives);
    }

    public static Intent newIntent(
            Context context,
            String username,
            String drink,
            String drinkType,
            String additives
            ){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME,username);
        intent.putExtra(EXTRA_DRINK,drink);
        intent.putExtra(EXTRA_DRINK_TYPE,drinkType);
        intent.putExtra(EXTRA_ADDITIVES,additives);
        return intent;

    }
}