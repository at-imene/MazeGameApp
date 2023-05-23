package com.matheducation.mazejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView currentChamber;
    Button leftBtn, rightBtn, downBtn, upBtn;
    MazeGame game;
    final int[] direction = {0};
    GridView gridView;
    GridAdapter gridAdapter;
    int[][] maze = {
            {10, 8, 10, 9},
            {28, 1, 0, 12},
            {12, 10, 9, 13},
            {6, 5, 6, 5}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        
        game = new MazeGame(maze);
        gridAdapter.setColoredPosition(game.getCurrentChamber());

        System.out.println("Welcome to the Maze Game!");
        setLisners();
        HashMap<String, Integer > possibleDirections =  game.getPossibleDirections();
        changeButtonStates(possibleDirections);
        setCurrentChamberTv();

        gridView.getItemAtPosition(0);
        gridAdapter.getItem(0);

    }

    private void setCurrentChamberTv() {
        currentChamber.setText("Current Chamber number: "+ game.getCurrentChamber());
    }

    void play(){
        // checks if the value in the maze array at the current chamber is not equal to zero
        if (!game.gameOver()) {

            int newChamber = game.getNewChamber(direction[0]);
            if (newChamber != game.getCurrentChamber()) {
                game.setCurrentChamber(newChamber);
            } else {
                Toast.makeText(this, "Invalid move! Try again." , Toast.LENGTH_SHORT).show();
            }
            setCurrentChamberTv();
            gridAdapter.setColoredPosition(game.getCurrentChamber());
            HashMap<String, Integer > possibleDirections =  game.getPossibleDirections();
            changeButtonStates(possibleDirections);

        }
        if(game.gameOver()) {
            Toast.makeText(this, "Congratulations! You reached the alloy chamber (value 0). Game over!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ResultActivity.class));
            finish();
        }
    }
    private void setLisners() {
        leftBtn.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View view) {
                direction[0] = 1;
                play();
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View view) {
                direction[0] = 2;
                play();
            }
        });

        upBtn.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View view) {
                direction[0] = 3;
                play();
            }
        });

        downBtn.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View view) {
                direction[0] = 4;
                play();
            }
        });
    }

    private void changeButtonStates(HashMap<String, Integer> possibleDirections) {
        if(possibleDirections.get("left")== 1){
            leftBtn.setEnabled(true);
        }else{
            leftBtn.setEnabled(false);
        }

        if(possibleDirections.get("right")== 1){
            rightBtn.setEnabled(true);
        }else{
            rightBtn.setEnabled(false);
        }

        if(possibleDirections.get("up")== 1){
            upBtn.setEnabled(true);
        }else{
            upBtn.setEnabled(false);
        }

        if(possibleDirections.get("down")== 1){
            downBtn.setEnabled(true);
        }else{
            downBtn.setEnabled(false);
        }
    }

    private void initViews() {
        currentChamber = findViewById(R.id.currentChanber_tv);
        gridView = findViewById(R.id.gridview);
        gridAdapter = new GridAdapter(this, maze);
        gridView.setAdapter(gridAdapter);

        rightBtn = findViewById(R.id.right_btn);
        leftBtn = findViewById(R.id.left_btn);
        downBtn = findViewById(R.id.down_btn);
        upBtn = findViewById(R.id.up_btn);
    }

    ;
}