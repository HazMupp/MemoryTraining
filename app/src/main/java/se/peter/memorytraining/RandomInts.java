package se.peter.memorytraining;

import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class RandomInts extends AppCompatActivity {


    public RandomInts() {


    }

    public static int createRandomInts(){


        int randomInt = new Random().nextInt(30) + 1;
        return randomInt;

        }

         /*  In memory (ha-ha) of my old coding "skills"    */
    //    int randomInt2 = new Random().nextInt(30) + 1;
    //    int randomInt3 = new Random().nextInt(30) + 1;
    //    int randomInt4 = new Random().nextInt(30) + 1;
    //    int randomInt5 = new Random().nextInt(30) + 1;
    //    int randomInt6 = new Random().nextInt(30) + 1;
    //    int randomInt7 = new Random().nextInt(30) + 1;
    //    int randomInt8 = new Random().nextInt(30) + 1;



}



