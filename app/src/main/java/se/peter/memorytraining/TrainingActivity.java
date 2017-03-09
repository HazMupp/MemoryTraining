package se.peter.memorytraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class TrainingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                                                                         //Laddar in alla element och gör dem osynliga
        setContentView(R.layout.activity_training);                                                                 //Laddar in alla element och gör dem osynliga
        editText = (EditText)findViewById(R.id.edit_text_field);                                                    //Laddar in alla element och gör dem osynliga
        submitButton = (Button)findViewById(R.id.submit_btn);                                                       //Laddar in alla element och gör dem osynliga
        pointsViewCorrect = (TextView)findViewById(R.id.points_view_correct);                                       //Laddar in alla element och gör dem osynliga
        pointsViewWrong = (TextView)findViewById(R.id.points_view_wrong);                                           //Laddar in alla element och gör dem osynliga
        trainingButton = (Button)findViewById(R.id.training_btn); // Är synlig direkt när aktiviteten startas       //Laddar in alla element och gör dem osynliga
        textView = (TextView)findViewById(R.id.dynamic_text_view);                                                  //Laddar in alla element och gör dem osynliga
        textView2 = (TextView)findViewById(R.id.second_text_view);                                                  //Laddar in alla element och gör dem osynliga
        editText.setVisibility(View.GONE);                                                                          //Laddar in alla element och gör dem osynliga
        submitButton.setVisibility(View.GONE);                                                                      //Laddar in alla element och gör dem osynliga
        pointsViewCorrect.setVisibility(View.GONE);                                                                 //Laddar in alla element och gör dem osynliga
        pointsViewWrong.setVisibility(View.GONE);                                                                   //Laddar in alla element och gör dem osynliga

    } //10 1 7 23 19 8


    Button trainingButton;
    Button submitButton;
 //   Button retryButton;hjkl
    EditText editText;
    TextView textView;
    TextView textView2;
    TextView pointsViewCorrect;
    TextView pointsViewWrong;
    CountDownTimer timer;

    int intConvertedFromString; // Stringen från EditTexten lagras i denna int-variabel, omvandlingen sker i metoden checkEditText().
    int numberOfRightGuesses = 0; // Adderar 1 om användaren skriver in rätt tal i rätt ordning.
    int numberOfWrongGuesses = 0; // Adderar 1 om användaren skriver in fel tal. Gäller även om det är "rätt tal på fel plats".
    int numberOfUserGuesses = 0;  // Adderar 1 för varje gissning användaren gör, oberoende om den är "korrekt" eller "felaktig".
    int currentWord = 0; // Används i checkEditText() för att hålla reda peka på rätt ord i randomNumbers-arrayen
    int numberOfGuessesLeft = 5;
    int i = 0; // Används i räknaren( startCounting()-metoden) för att hålla reda på hur många tal som visats upp









    int distractionInt = new Random().nextInt(16); // Denna används igen i onTick()-metoden

    int[] userGuess = {1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007 }; // Om något av just dessa tal skrivs ut ät något fel, se editText
    int[] randomNumbers = {RandomInts.createRandomInts(), RandomInts.createRandomInts(),  RandomInts.createRandomInts(),
            RandomInts.createRandomInts(), RandomInts.createRandomInts(), RandomInts.createRandomInts()  };

    String[] distraction = {"I", ">>>HOPE<<<", "this", "doesn't", "dIstRaCt", "y0u", "from", "focusing", "0nnn", "you're", "t-t-t-t-taask", // Distraheringsmoment om man vill ha det
            "HOLY COW", "Ronald McDonald", "Metallica", "funny", "Hi, I'm Troy McClure"};
    String userInput; // I denna variabel lagras det som användaren skriver in i EditTexten, och konsekvent ofta byter värde, mer exakt efter varje knapptryckning på "Submit"-knappen



    public void startCounting(View view) {

        trainingButton.setVisibility(View.GONE);

        timer = new CountDownTimer(35000, 1000) {

            public void onTick(long millisUntilFinished) {

                //  textView.setText("seconds remaining: " + millisUntilFinished / 1000 + ". seconds passed: " + (30 - (millisUntilFinished / 1000 ))); // om man vill visa sekund-info
                textView.setText(distraction[distractionInt]);
                distractionInt = new Random().nextInt(16);
                //  distractionInt++;

                long millisPassedSinceStart = (millisUntilFinished / 1000) - 35;


                if (millisPassedSinceStart % 2 == 0) {

                    textView2.setText("" + randomNumbers[i]);
                    i++;
                }

                else if(i == 6) { // här väljs hur många objekt ur arrayen man vill ha

                    onFinish();
                    timer.cancel();
                    textView2.setText("");

                }

                else {

                    textView2.setText("breathe"); // Om man vill visa något mellan talen man ska komma ihåg

                }

            }

            public void onFinish() {
                textView.setText(R.string.done_en);
                editText.setVisibility(View.VISIBLE);         // Gör dessa synliga så man kan skriva in och "submitta" tal
                submitButton.setVisibility(View.VISIBLE);     // Gör dessa synliga så man kan skriva in och "submitta" tal
                i = 0;
            }

        }.start();

    }

    public void checkEditText(View v){      // För att jämföra vad användaren skrivit mot de rätta talen

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE); // To remove keyboard when need // TODO: 2017-03-06


        userInput = editText.getText().toString(); // Hämtar texten i EditText

        if (userInput.equals("")){
            userInput = "0";
        }

        intConvertedFromString = Integer.parseInt(userInput);     // omvandlar siffrorna från String till int

        if ((intConvertedFromString) == (randomNumbers[currentWord]))  {

            editText.setHint(R.string.next_number_en);

            currentWord++;
           userGuess[numberOfUserGuesses] =  intConvertedFromString;
            numberOfUserGuesses++;
        }

        else if (userInput.length() <= 0){

            currentWord++;
            userGuess[numberOfUserGuesses] = 0;
            numberOfUserGuesses++;
            editText.setHint(R.string.next_number_en);

        }

        else {

           // textView2.setText('"' + editText.getText().toString() + '"' + " is not the droid we are looking for");
            userGuess[numberOfUserGuesses] = intConvertedFromString;
            editText.setHint(R.string.next_number_en);
            numberOfUserGuesses++;
            currentWord++;
        }

        if(currentWord == 6
           &&
           numberOfUserGuesses == 6) {

            displayAnswers();


        }
        if (numberOfGuessesLeft != 0) {
            Toast.makeText(TrainingActivity.this, numberOfGuessesLeft + " guesses to go", Toast.LENGTH_SHORT).show();
            numberOfGuessesLeft--;
        }

        else if (numberOfGuessesLeft == 0){
            imm.hideSoftInputFromWindow(submitButton.getWindowToken(), 0);
        }
        editText.getText().clear(); // Rensar EditText på text (detta ser ut att ske exakt när man klickar på knappen, men sker i själva verket efter att texten är inläst)

    }

    public void displayAnswers(){       // Metod för att visa vad som blel rätt och fel

        textView2.setText("Your Numbers: " + userGuess[0] + ", " + userGuess[1] + ", " + userGuess[2] + ", "    // Detta är vad användaren skrev in
                + userGuess[3] + ", " + userGuess[4] + ", " + userGuess[5]);                                            // Detta är vad användaren skrev in

        textView.setText("The right numbers: " + randomNumbers[0] + ", " + randomNumbers[1] + ", " + randomNumbers[2] + ", "    // Detta är de rätta talen
                + randomNumbers[3] + ", " + randomNumbers[4] + ", " + randomNumbers[5]);                                            // Detta är de rätta talen

        editText.setVisibility(View.GONE);       // Ta bort dessa 2 views för att förhindra att ytterligare data matas in
        submitButton.setVisibility(View.GONE);   // Ta bort dessa 2 views för att förhindra att ytterligare data matas in
        pointsViewCorrect.setVisibility(View.VISIBLE);  // Hur mänga rätt användaren hade
        pointsViewWrong.setVisibility(View.VISIBLE);    // Hur många fel användaren hade


        for (int j = 0; j <= 5; j++){  // Int j lokal variabel för just denna for-loop

            // Fämförelseoperating för vad användaren skrev in och om det stämmer med vad som finns i randomNumbers[]-arrayen
            if (userGuess[j] == (randomNumbers[j])) {

                numberOfRightGuesses++; // lägg till 1 i denna variabel för varje rätt svar

            }
            else {
                numberOfWrongGuesses++;     // lägg till 1 i denna variabel för varje fel svar

            }


        }
        pointsViewCorrect.setText("Number of right answers:" + numberOfRightGuesses);    // Visa värdena för rätt svar
        pointsViewWrong.setText("Number of wrong answers:" + numberOfWrongGuesses);      // Visa värdena för fel svar

    }

    public void reloadPage(View view){      // Metod som körs om man trycker på floating action button

        // Startar om tränings-aktiviteten
        recreate();
    }
}