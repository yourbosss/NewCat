package com.example.newcat2.ui.shop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.newcat2.R;

public class ShopDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        // Get the shop name from the intent
        String shopName = getIntent().getStringExtra("shop_name");

        // Display the shop name in a TextView
        TextView shopNameTextView = findViewById(R.id.shop_name_text_view);
        shopNameTextView.setText(shopName);
    }
}