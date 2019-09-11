package com.kelompokv.pemesananmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    Button btn_fragment;
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_fragment = findViewById(R.id.btn_fragment);
        btn_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new TampilanFragment()).commit();
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.about){
            startActivity(new Intent(this, AboutActivity.class));
        }
        return true;
    }

    public void increment(View view){
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText= findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox burgerChekBox= findViewById(R.id.burger);
        boolean hasburger=burgerChekBox.isChecked();
        Log.v("MainActivity","has burger:"+hasburger);

        CheckBox ayamgorengChekBox= findViewById(R.id.ayamgoreng);
        boolean hasayamgoreng=ayamgorengChekBox.isChecked();
        Log.v("MainActivity","has ayam goreng:"+hasayamgoreng);

        CheckBox orangejChekBox= findViewById(R.id.orange);
        boolean hasorange=orangejChekBox.isChecked();
        Log.v("MainActivity","has orange juice:"+hasorange);

        CheckBox strawberryjChekBox= findViewById(R.id.strawberry);
        boolean hasstrawberry=strawberryjChekBox.isChecked();
        Log.v("MainActivity","has strawberry juice:"+hasstrawberry);

        int price=calculateprice(hasburger,hasayamgoreng,hasorange,hasstrawberry);
        String pricemessage=createOrderSummary(price,name);

        displayMessage(pricemessage);

    }
    private int calculateprice(boolean burger,boolean ayamgoreng,boolean orange,boolean strawberry){
        int harga=0;

        if (burger){
            harga=harga+10000;
        }

        if (ayamgoreng){
            harga=harga+8000;
        }

        if (orange){
            harga=harga+5000;
        }

        if (strawberry){
            harga=harga+5000;
        }

        return quantity * harga;
    }
    private String createOrderSummary(int price, String name) {
        String pricemessage=" Nama : "+name;
        pricemessage+="\n Total : Rp " +price;
        return  pricemessage;
    }

    private void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}