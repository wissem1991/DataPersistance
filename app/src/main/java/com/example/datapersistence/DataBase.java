package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataBase extends AppCompatActivity {

    EditText inputId;
    EditText inputProductName;
    EditText inputQuantity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        inputId = findViewById(R.id.idInput);
        inputProductName = findViewById(R.id.nameInput);
        inputQuantity = findViewById(R.id.quantiteInput);
    }

    public void insertProduct(View view){
        ProductDBHandler productDBHandler = new ProductDBHandler(this);
        String name = inputProductName.getText().toString();
        int quantity = Integer.parseInt(inputQuantity.getText().toString());
        Product product = new Product(name, quantity);
        productDBHandler.addProduct(product);
        inputQuantity.setText("");
        inputProductName.setText("");
    }

    public void searchProductByName(View view) {
        ProductDBHandler productDBHandler = new ProductDBHandler(this);
        String name = inputProductName.getText().toString();
        Product product = productDBHandler.findProduct(name);
        if (null != product){
            inputId.setText(String.valueOf(product.getId()));
            inputQuantity.setText(String.valueOf(product.getQuantity()));
        } else {
            inputId.setText("No product found");
        }
    }
}