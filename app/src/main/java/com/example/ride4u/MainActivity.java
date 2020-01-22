package com.example.ride4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity<mdriver, mcustomer> extends AppCompatActivity
{
    private Button driver,customer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        driver = findViewById(R.id.driver);
        customer = findViewById(R.id.customer);

        driver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,driverLogin.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        customer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,customerLogin.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
