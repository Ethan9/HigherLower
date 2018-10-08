package com.example.ethyl.higherlower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Local variables
    private int currentImageIndex = 0;
    private int[] mImageNames;
    private ImageView mImageView;
    private FloatingActionButton mHigherButton;
    private FloatingActionButton mLowerButton;
    private ListView mDiceThrowList;
    private ArrayAdapter mAdapter;
    private ArrayList mDiceThrows;
    private int oldResult;
    private TextView mScore;
    private TextView mHighScore;
    private int score = 0;
    private int highscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScore = findViewById(R.id.text_Score);
        mHighScore = findViewById(R.id.text_Highscore);
        mHigherButton = findViewById(R.id.button_Higher);
        mLowerButton = findViewById(R.id.button_Lower);
        mImageView = findViewById(R.id.image_Dice);
        mDiceThrowList = findViewById(R.id.list_Dicethrow);


        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        // Define what happens when the user clicks the "higher" button
        mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newResult = diceRoll();
                if (newResult > oldResult) {
                    Snackbar snackbar = Snackbar
                            .make(v, "You win!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    score++;
                    mScore.setText(score + "");
                }
                else {
                    if(score > highscore){
                        highscore = score;
                        mHighScore.setText(highscore + "");
                    }
                    score = 0;
                    mScore.setText(score + "");
                    mDiceThrows.clear();
                    Snackbar snackbar = Snackbar
                            .make(v, "You lose!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                oldResult = newResult;

            }
        });

        // Define what happens when the user clicks the "lower" button
        mLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newResult = diceRoll();
                if (newResult < oldResult) {
                    Snackbar snackbar = Snackbar
                            .make(v, "You win!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    score++;
                    mScore.setText(score + "");

                }
                else {
                    if(score > highscore){
                        highscore = score;
                        mHighScore.setText(highscore + "");
                    }
                    score = 0;
                    mScore.setText(score + "0");
                    mDiceThrows.clear();
                    Snackbar snackbar = Snackbar
                            .make(v, "You lose!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                oldResult = newResult;

            }
        });

        mDiceThrows = new ArrayList();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDiceThrows);
        mDiceThrowList.setAdapter(mAdapter);
        oldResult = diceRoll();

    }
    protected int diceRoll() {
        //Function to randomly select an image and display it
        Random r = new Random();
        int result = r.nextInt(6);
        int randomID = mImageNames[result];
        mImageView.setImageResource(randomID);
        ++result;
        mDiceThrows.add("Throw is " + result);
        mAdapter.notifyDataSetChanged();

        return result;

    }

}
