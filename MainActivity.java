package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtmarq;
    Button btnstart, btnstop;
    Button btnCalculate; // Added button for calculator game
    private ExampleAsyncTask exampleTask;
    private CalculatorAsyncTask calculatorTask;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtmarq = findViewById(R.id.txtmarquee);
        btnstart = findViewById(R.id.buttonstart);
        btnstop = findViewById(R.id.buttonstop);
        btnCalculate = findViewById(R.id.buttoncalculate);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exampleTask = new ExampleAsyncTask();
                exampleTask.execute();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtmarq.setSelected(false);
                txtmarq.setVisibility(View.INVISIBLE);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorTask = new CalculatorAsyncTask();
                calculatorTask.execute();
            }
        });
    }

    private class ExampleAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getBaseContext(), "Async Task Started!!!!!!!!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtmarq.setVisibility(View.VISIBLE);
            txtmarq.setSelected(true);
        }
    }

    private class CalculatorAsyncTask extends AsyncTask<Void, Void, Integer> {
        private int operand1;
        private int operand2;
        private String operator;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Generate random operands and operator for the calculator game
            Random random = new Random();
            operand1 = random.nextInt(10) + 1;  // Generate a random number between 1 and 10
            operand2 = random.nextInt(10) + 1;
            operator = random.nextBoolean() ? "+" : "-";  // Randomly select addition or subtraction
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            // Perform the calculation based on the randomly generated operands and operator
            int result;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                default:
                    result = 0;
                    break;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            // Perform any UI updates or actions after the calculation completes
            String message = operand1 + " " + operator + " " + operand2 + " = " + result;
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
