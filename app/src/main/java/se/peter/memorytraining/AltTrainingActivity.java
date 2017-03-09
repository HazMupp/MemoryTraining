package se.peter.memorytraining;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class AltTrainingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_alt);
        editText = (EditText)findViewById(R.id.editText_field);
        editText.setVisibility(View.GONE);

    }


    int count = 0;
    TextView textView;
    TextView textView2;
    EditText editText;
    Timer timer = new Timer();
    public boolean running = false;
    boolean isButtonIsCreated = false;
    boolean hasAlreadyRunned;

    String[] theWords = {"Can", "you", "remember", "this", "scentence?"};
    int[] someNumbers = {3,5,3,2,7,9};

    int countBtn = 0; // Använd denna för att se om flera dynamiska knappar har skapats (vilket man inte vill)

    public void startAltCounting(View view) {



        if (hasAlreadyRunned == false){


            Toast.makeText(AltTrainingActivity.this, "Räkning påbörjad",
                    Toast.LENGTH_SHORT).show();

            if (isButtonIsCreated == false) {

                LinearLayout inflatable_button_view = (LinearLayout) findViewById(R.id.inflatable_button_view);
                //Skapa knappen

                final Button myFirstButton = new Button(this);
                myFirstButton.setText("stop counting");

                // In med knappen i vyn vi vill ha den i
                inflatable_button_view.addView(myFirstButton);

                isButtonIsCreated = true;


                myFirstButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Kod som körs när knapp trycks på
                        Toast.makeText(AltTrainingActivity.this, "Räkning avslutad",
                                Toast.LENGTH_SHORT).show();
                        timer.cancel();
                        editText.setVisibility(View.VISIBLE);
                        myFirstButton.setVisibility(View.GONE);
                    }
                });
            }


            textView = (TextView) findViewById(R.id.alt_dynamic_text_view);

            textView2 = (TextView) findViewById(R.id.alt_second_text_view);



            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        public void run() {

                            if (running == false) {
                                textView.setText("" + count);
                                count++;
                                // running = true;

                            }
                        }


                    });
                }
            }, 1000, 1000);
            hasAlreadyRunned = true;



        }

    }




    public void startAlt2Counting(View view) {

        textView = (TextView) findViewById(R.id.alt_dynamic_text_view);

        textView2 = (TextView) findViewById(R.id.alt_second_text_view);

        final Handler handler = new Handler();



        handler.postDelayed(new Runnable() {


            @Override
            public void run() {
                textView.setText("" + count);
                count++;
                textView2.setText(theWords[0]);

            }
        }, 100);

    }

}


