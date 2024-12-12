package com.example.mathquiz;

import android.content.Intent;
import android.graphics.Color;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class SubstractionActivity extends AppCompatActivity {
    int score,noOfQuestions;
    LinearLayout LinearLayout2;
    TextView questionTextView,scoreTextView,answerTextView,timerTextView,finalScoreTextView;
    Button button0,button1,button2,button3,playAgainButton,backButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int correctAnswerLocation;
    public void play(){
        //set question and options
        getNewQuestion();

        //set score
        score = 0;
        noOfQuestions = 0;
        setScore();

        //set answerTextView empty
        answerTextView.setText(" ");

        //set timer
        setTimer();
    }

    private void setTimer() {
        new CountDownTimer(45000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                float c = (((float)score)/((float)noOfQuestions))*100;
                String d = String.format("%.02f", c);
                finalScoreTextView.setVisibility(View.VISIBLE);
                finalScoreTextView.setText("Your are "+d+"% accurate");
                finalScoreTextView.setTextColor(Color.parseColor("#3700B3"));
                questionTextView.setVisibility(View.INVISIBLE);
                LinearLayout2.setVisibility(View.INVISIBLE);
                answerTextView.setText("GameOver!");
                answerTextView.setTextSize(48);
                answerTextView.setTextColor(Color.parseColor("#000000"));
                playAgainButton.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void setScore() {
        scoreTextView.setText(String.valueOf(score)+"/"+ String.valueOf(noOfQuestions));
    }

    private void getNewQuestion() {
        Random random = new Random();
        //setting question
        int a,b;
        do {
            a = random.nextInt(199);
            b = random.nextInt(199);
        } while (a < b);

        String aString = String.valueOf(a);
        String bString = String.valueOf(b);
        questionTextView.setText(aString +"-"+bString+"=?");

        //setting options
        correctAnswerLocation = random.nextInt(3);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==correctAnswerLocation){
                answers.add(a-b);
            }
            else{
                int wrongAnswer = random.nextInt(a-b+20);
                while(wrongAnswer==a-b){
                    wrongAnswer = random.nextInt(a-b+20);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void onOptionsClick(View view){
        if(Integer.toString(correctAnswerLocation).equals(view.getTag().toString())){
            score++;
            answerTextView.setText("Correct!");
            answerTextView.setTextColor(Color.parseColor("#1A980C"));
        }
        else{
            answerTextView.setText("Wrong!");
            answerTextView.setTextColor(Color.parseColor("#B00020"));
        }
        noOfQuestions++;
        setScore();
        getNewQuestion();
    }

    public void onClickPlayAgain(View view){
        finalScoreTextView.setVisibility(View.INVISIBLE);
        answerTextView.setTextSize(30);
        questionTextView.setVisibility(View.VISIBLE);
        LinearLayout2.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        backButton.setVisibility(View.INVISIBLE);
        play();
    }

    public void onClickBackButton(View view){
        Intent i = new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.substraction_activity);
        //layout
        LinearLayout2 = findViewById(R.id.linearLayout2);
        //textviews
        finalScoreTextView = findViewById(R.id.finalScoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerTextView = findViewById(R.id.answerTextView);
        //buttons
        playAgainButton = findViewById(R.id.playAgainButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        backButton = findViewById(R.id.backButton);
        //visibility on create
        finalScoreTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        LinearLayout2.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        play();
    }

}