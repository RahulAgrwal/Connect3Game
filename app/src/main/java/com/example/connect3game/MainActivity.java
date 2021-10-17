package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0:Zero , 1:Cross, -1:Blank
    int[] gamestate={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int activeplayer=0;
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,8},{2,5,8},{1,4,7},{2,4,6},{0,4,8}};
    boolean gameactive=true;

    public void dropIn(View view){
        ImageView counter=(ImageView) view;

        int tappedcounter=Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedcounter]==-1 && gameactive)
        {
            counter.setTranslationY(-1500);
            gamestate[tappedcounter] = activeplayer;

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.o);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activeplayer = 0;

            }
            counter.animate().translationY(15).setDuration(200);

            for (int[] check : winningpositions) {
                if (gamestate[check[0]] == gamestate[check[1]] && gamestate[check[1]] == gamestate[check[2]] && gamestate[check[0]] != -1) {
                    //Someone won the game
                    gameactive=false;
                    String winner;
                    if (activeplayer == 1) {
                        winner = "Zero";
                    } else {
                        winner = "Cross";
                    }
                    TextView text=(TextView) findViewById(R.id.TextView);

                    text.setVisibility(View.VISIBLE);
                    text.setText("Winner is "+winner);
                }
            }

        }

    }
    public void Reset(View view){
        TextView text=(TextView) findViewById(R.id.TextView);
        text.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.grid);

        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int j=0;j<gamestate.length;j++){
            gamestate[j]= -1;
        }

        activeplayer=0;
        gameactive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}