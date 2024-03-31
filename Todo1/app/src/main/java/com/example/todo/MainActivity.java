package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView titleTextView, scoreTextView, timerTextView, questionTextView;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private int questionIndex = 0;
    private int score = 0;

    private CountDownTimer timer;

    private String[] questions = {
            "Question 1: What is the capital of Palestine?",
            "Question 2: When did the Palestinian Nakba happen?",
            "Question 3: What this lab name?",
            "Question 4: What language is used in the Android app lab?",
            "Question 5: What the name of Teaching Assistant?",
    };

    private String[] correctAnswers = {
            "Jerusalem",
            "1948",
            "Android",
            "Java",
            "Eng.Mohammad",

    };

    private String[][] choices = {
            {"Jenin" , "Amman" ,"Jerusalem"," Ramallah"},
            {"1945" ,"1948","1956","1967"},
            {"Android", "C", "Java", "Network"},
            {"Java", "Python", "C++", "C"},
            {"Eng.Tarek", "Eng.Enas", "Eng.Mohammad", "Eng.Katy"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.titleTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);

        answerBtn1 = findViewById(R.id.answer1Button);
        answerBtn2 = findViewById(R.id.answer2Button);
        answerBtn3 = findViewById(R.id.answer3Button);
        answerBtn4 = findViewById(R.id.answer4Button);

        updateIndex();

        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                checkAnswer(false);
            }
        }.start();
    }

    private void updateIndex() {
        questionTextView.setText(questions[questionIndex]);
        answerBtn1.setText(choices[questionIndex][0]);
        answerBtn2.setText(choices[questionIndex][1]);
        answerBtn3.setText(choices[questionIndex][2]);
        answerBtn4.setText(choices[questionIndex][3]);
    }

    public void onAnswerClick(View view) {
        timer.cancel();
        Button clickedButton = (Button) view;
        String selectedAnswer = clickedButton.getText().toString();
        checkAnswer(selectedAnswer.equals(correctAnswers[questionIndex]));
    }

    private void checkAnswer(boolean isCorrect) {
        if (isCorrect) {
            score++;
        }

        questionIndex++;

        if (questionIndex < questions.length) {
            updateIndex();
            timer.start();
        } else {
            showResult();
        }
    }

    private void showResult() {
        setContentView(R.layout.activity_result);

        TextView feedbackTextView = findViewById(R.id.feedbackTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        Button resetButton = findViewById(R.id.resetButton);

        if (score >= 4) {
            feedbackTextView.setText("You Won!");
        } else {
            feedbackTextView.setText("You Lost!");
        }

        resultTextView.setText(score + "/5");

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
    }

    private void restart() {
        questionIndex = 0;
        score = 0;
        setContentView(R.layout.activity_main);
        onCreate(null);
    }


}
