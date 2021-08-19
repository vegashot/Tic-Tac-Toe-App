package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

//import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0; //0:yellow,1:red,2:empty
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;

    public void dropImg(View view){
        ImageView count = (ImageView)view;
        int tapCount=Integer.parseInt(count.getTag().toString());
        if(gameState[tapCount]==2&&gameActive) {
            gameState[tapCount] = activePlayer;
            count.setTranslationY(-2000);
            if (activePlayer == 0) {
                count.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                count.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            count.animate().translationYBy(2000).setDuration(400);
            for (int[] winPoss : winPos) {
                if (gameState[winPoss[0]] == gameState[winPoss[1]] && gameState[winPoss[1]] == gameState[winPoss[2]] && gameState[winPoss[0]] != 2) {
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+ " has won the game !");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView count =(ImageView) gridLayout.getChildAt(i);
            count.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        gameActive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}