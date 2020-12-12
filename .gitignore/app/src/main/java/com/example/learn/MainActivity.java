//package com.example.learn;
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.learn;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v1.2.0.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    int quantity = 2;

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"You Cannot Have More Than 100 Coffees", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity = quantity + 1;
            display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this,"You Cannot Have less Than 1 Coffees", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity = quantity - 1;
            display(quantity);

    }

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        Boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        Boolean haschocolate = chocolateCheckBox.isChecked();

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"CCD Order For "+name);
        intent.putExtra(Intent.EXTRA_TEXT,""+createOrderSummary(hasWhippedCream,haschocolate,name));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private String createOrderSummary(Boolean whippedCream, Boolean chocolate, String name){
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\nAdd Whipped Cream "+whippedCream;
        priceMessage += "\nAdd Chocolate Cream "+chocolate;
        priceMessage += "\nQuantity: "+quantity;
        priceMessage += "\nTotal: $"+calculatePrice(whippedCream,chocolate);
        priceMessage += "\n"+getString(R.string.thank_you);
        return priceMessage;

    }

    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int basePrice = 5;

        if (whippedCream){
            basePrice = basePrice + 1;
        }

        if (chocolate) {
            basePrice = basePrice + 2;
        }

        int price = quantity *basePrice;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }

}