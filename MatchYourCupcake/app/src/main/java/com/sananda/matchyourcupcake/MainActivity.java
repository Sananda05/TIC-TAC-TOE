package com.sananda.matchyourcupcake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;
    Boolean gameIsActive = true;

    int[] gameState = {5,5,5,5,5,5,5,5,5};
    int[][] winningPositions={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {
        ImageView cupcake = (ImageView) view;

        int tappedcupcake = Integer.parseInt(cupcake.getTag().toString());
        if (gameState[tappedcupcake] == 5 && gameIsActive) {
            gameState[tappedcupcake]=activePlayer;
            cupcake.setTranslationY(-1000f);

            if (activePlayer == 0) {
                cupcake.setImageResource(R.drawable.redcake);
                activePlayer = 1;
            } else {
                cupcake.setImageResource(R.drawable.greencake);
                activePlayer = 0;
            }
            cupcake.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition:winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=5)
                {
                    gameIsActive=false;
                    String winner = "Green";

                    if(gameState[winningPosition[0]]==0)
                    {
                        winner ="Red";
                    }

                    TextView result = (TextView)findViewById(R.id.result);
                    result.setText(winner + " has won !");

                    LinearLayout resultlayout = (LinearLayout)findViewById(R.id.resultlayout);
                    resultlayout.setVisibility(View.VISIBLE);
                }
                else {
                    Boolean gameOver= true;
                    for(int cupcakeState:gameState)
                    {
                        if(cupcakeState==5)
                            gameOver=false;
                    }
                    if(gameOver)
                    {
                        TextView result=(TextView)findViewById(R.id.result);
                        result.setText("It's a draw");
                        LinearLayout resultlayout = (LinearLayout)findViewById(R.id.resultlayout);
                        resultlayout.setVisibility(View.VISIBLE);
                    }

                }
            }


        }
    }
    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.resultlayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 5;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridlayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
