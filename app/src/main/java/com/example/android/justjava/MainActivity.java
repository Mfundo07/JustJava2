package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.NumberFormat;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.order;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        EditText editTextName = (EditText) findViewById(R.id.edit_text_name);
        String textName = editTextName.getText().toString();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummay(price, hasWhippedCream,hasChocolate,textName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.EMPTY.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java for " + textName);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null ){
            startActivity(intent);
        }


    }
    /**
     * This method is called when the "+" button is clicked
     */
    public void increment(View view){
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);



    }
    /**
     * This method is called when the "-" button is clicked
     */
    public void decrement(View view){
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     * @param addChocolate
     * @param addWhippedcream
     */
    private int calculatePrice(boolean addWhippedcream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedcream == true){
            basePrice += 1;
        }
        if (addChocolate == true){
            basePrice += 2;
        }

        return quantity * basePrice;
    }

    /**
     * Create a summary of the order
     * @param price
     * @param addWhippedCream
     * @return
     * @param addChocolate
     */
    private String createOrderSummay(int price, boolean addWhippedCream, boolean addChocolate, String addName){
        String priceMessage = "Name: " + addName;
        priceMessage += "\nAdd Whipped Cream? "+ addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: "+ quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;

    }



}