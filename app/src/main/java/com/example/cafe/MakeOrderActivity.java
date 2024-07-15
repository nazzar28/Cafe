package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "username";

    TextView textViewGreetings;
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

    private String username;
    private String drink;

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

        initViews();
        setUpUsername();

        drinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == tea.getId()){
                    onUserChoseTea();
                }else if(id == coffee.getId()){
                    onUserChoseCoffee();
                }
            }
        });

        tea.setChecked(true);

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserMadeOrder();
            }
        });
    }

    private void onUserMadeOrder(){
        ArrayList<String> additives = new ArrayList<>();
        if(sugar.isChecked()){
            additives.add(sugar.getText().toString());
        }
        if(milk.isChecked()){
            additives.add(milk.getText().toString());
        }
        if(tea.isChecked() && lemon.isChecked()){
            additives.add(sugar.getText().toString());
        }

        String drinkType = "";
        if(tea.isChecked()){
            drinkType = spTea.getSelectedItem().toString();
        }else if(coffee.isChecked()){
            drinkType = spCoffee.getSelectedItem().toString();
        }

        Intent intent = OrderDetailActivity.newIntent(
                this,
                username,
                drink,
                drinkType,
                additives.toString());
        startActivity(intent);
    }

    private void onUserChoseTea(){
        drink = getString(R.string.tea);
        String additivesLabel = getString(R.string.what_to_add, drink);
        additives.setText(additivesLabel);
        lemon.setVisibility(View.VISIBLE);

        spTea.setVisibility(View.VISIBLE);
        spCoffee.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee(){
        drink = getString(R.string.coffee);
        String additivesLabel = getString(R.string.what_to_add, drink);
        additives.setText(additivesLabel);
        lemon.setVisibility(View.INVISIBLE);

        spTea.setVisibility(View.INVISIBLE);
        spCoffee.setVisibility(View.VISIBLE);
    }

    public static Intent newIntent(Context context, String username){
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, username);
        return intent;
    }

    private void initViews(){
        textViewGreetings = findViewById(R.id.textViewGreetings);
        additives = findViewById(R.id.textViewAdditives);
        drinks = findViewById(R.id.radioGroupDrinks);
        tea = findViewById(R.id.radioButtonTea);
        coffee = findViewById(R.id.radioButtonCoffee);
        sugar = findViewById(R.id.sugar);
        milk = findViewById(R.id.milk);
        lemon = findViewById(R.id.lemon);
        spTea = findViewById(R.id.spinnerTea);
        spCoffee = findViewById(R.id.spinnerCoffee);
        makeOrder = findViewById(R.id.makeOrder);
    }

    private void setUpUsername(){
        username = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings,username);
        textViewGreetings.setText(greetings);
    }
}