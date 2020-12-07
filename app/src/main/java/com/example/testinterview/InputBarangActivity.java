package com.example.testinterview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InputBarangActivity extends AppCompatActivity {
    EditText edNameProduct, edQTY, edExpired, edPrice;
    Button btnSave;
    TextView tvLihatProduct;


    public static final String DATABASE_NAME = "shop";
    SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);
        edNameProduct = findViewById(R.id.edNamaBarang);
        edQTY = findViewById(R.id.edQty);
        edExpired = findViewById(R.id.edExpired);
        edPrice = findViewById(R.id.edPrice);
        btnSave.findViewById(R.id.btnSave);
        tvLihatProduct = findViewById(R.id.tvViewProduct);


        createTableProduct();

        btnSave.setOnClickListener(v -> {
            if (isValidationInput()) {
                addEmployee();
            }
        });

        tvLihatProduct.setOnClickListener(v -> startActivity(new Intent(InputBarangActivity.this,ViewProduct.class)));

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
    }


    private boolean isValidationInput() {
        if (edNameProduct.getText().toString().trim().isEmpty()) {
            edNameProduct.setError("Harap isi Nama Barang Anda");
            return false;
        }

        if (edQTY.getText().toString().trim().isEmpty()) {
            edQTY.setError("Harap isi QTY Anda");
            return false;
        }

        if (edExpired.getText().toString().trim().isEmpty()) {
            edExpired.setError("Harap isi Expired Barang Anda");
            return false;
        }

        if (edPrice.getText().toString().trim().isEmpty()) {
            edPrice.setError("Harap isi Harga Barang  Anda");
            return false;
        }

        return true;
    }

    private void createTableProduct() {
        mDatabase.execSQL(

                "CREATE TABLE IF NOT EXISTS product (\n" +
                        "id int NOT NULL CONSTRAINT product_pk PRIMARY KEY,\n" +
                        "nama_barang varchar(200) NOT NULL,\n" +
                        "qty varchar(200) NOT NULL,\n" +
                        "exp_date varchar(200) NOT NULL,\n" +
                        "harga varchar(200) NOT NULL,\n" +
                        ");"
        );
    }


    private void addEmployee() {
        String name = edNameProduct.getText().toString().trim();
        String qty = edQTY.getText().toString().trim();
        String expDate = edExpired.getText().toString().trim();
        String price = edPrice.getText().toString().trim();

        String insertSQL = "INSERT INTO product \n" +
                "(nama_barang,qty,exp_date,harga)\n" +
                "VALUES \n"+
                "(?,?,?,?);";

        mDatabase.execSQL(insertSQL, new String[]{name, qty, expDate, price});

    }

}