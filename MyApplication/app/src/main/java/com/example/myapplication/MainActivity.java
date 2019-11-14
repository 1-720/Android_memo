package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.AnswerListAsyncResponse;
import com.example.myapplication.data.QuestionBank;
import com.example.myapplication.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView question;
    private TextView counter;
    private Button trueBtn;
    private Button falseBtn;
    private ImageButton nextBtn;
    private ImageButton prevBtn;
    private int currentQuestionIndex = 0;
    private List<Question> questionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBtn = findViewById(R.id.next_btn);
        prevBtn = findViewById(R.id.prev_btn);
        trueBtn = findViewById(R.id.true_button);
        falseBtn = findViewById(R.id.false_button);
        counter = findViewById(R.id.counter_text);
        question = findViewById(R.id.question_text);

        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);


        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

                question.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                counter.setText(currentQuestionIndex + " / " + questionArrayList.size());

                Log.d("inside", "processFinished: " + questionArrayList);

            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev_btn:
                if (currentQuestionIndex != 0)
                currentQuestionIndex = (currentQuestionIndex-1) % questionList.size();
                updateQuestion();
                break;
            case R.id.next_btn:
                currentQuestionIndex = (currentQuestionIndex+1) % questionList.size();
                updateQuestion();
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
        }

    }

    private void checkAnswer(boolean b) {
        boolean answerIsTrue = questionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMessageId = 0;
        if (b == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        }else{
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        String q = questionList.get(currentQuestionIndex).getAnswer();
        question.setText(q);
        counter.setText(currentQuestionIndex + " / " + questionList.size());

    }
}
