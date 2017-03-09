package se.peter.memorytraining;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startTraining(View view) {

        Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
        startActivity(intent);
    }

    public void startAltTraining(View view){

        Toast.makeText(this, "Available in premium version", Toast.LENGTH_SHORT).show();

     //   Intent intent2 = new Intent(MainActivity.this, AltTrainingActivity.class);
     //   startActivity(intent2);

    }

    public void showStatistics(View view) {
        Toast.makeText(this, "Available in premium version", Toast.LENGTH_SHORT).show();
    }

}
