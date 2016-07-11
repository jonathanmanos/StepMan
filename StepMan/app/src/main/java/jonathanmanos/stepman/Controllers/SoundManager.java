package jonathanmanos.stepman.Controllers;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

import jonathanmanos.stepman.R;

/**
 * Created by Jonny on 7/10/2016.
 */
public class SoundManager {


    private static MediaPlayer battlePlayer;
    private static MediaPlayer victoryPlayer;
    private static MediaPlayer failurePlayer;
    private static MediaPlayer runAwayPlayer;

    private static MediaPlayer attackStepManPlayer;
    private static MediaPlayer attackEnemyPlayer;

    private Context context;

    public SoundManager(Context context){
        this.context = context;
        battlePlayer = MediaPlayer.create(context, R.raw.battle);
        victoryPlayer = MediaPlayer.create(context, R.raw.victory);
        failurePlayer = MediaPlayer.create(context, R.raw.failure);
        runAwayPlayer = MediaPlayer.create(context, R.raw.boo);
        attackStepManPlayer = MediaPlayer.create(context, R.raw.punches);
        attackEnemyPlayer = MediaPlayer.create(context, R.raw.punches);
    }

    public void setAttackStepManPlayer(int source){
        attackStepManPlayer = MediaPlayer.create(context, source);
    }

    public void setAttackEnemyPlayer(int source){
        attackEnemyPlayer = MediaPlayer.create(context, source);
    }

    public void playAttackEnemyPlayer(){
        attackEnemyPlayer.start();
    }

    public void playAttackStepManPlayer(){
        attackStepManPlayer.start();
    }

    public void playBattlePlayer(){
        battlePlayer.start();
    }

    public void playVictoryPlayer(){
        victoryPlayer.start();
    }

    public void playFailurePlayer(){
        failurePlayer.start();
    }

    public void playRunAwayPlayer(){
        runAwayPlayer.start();
    }

    public void stopBattlePlayer(){
        battlePlayer.stop();
        battlePlayer.prepareAsync();
    }

    public void stopVictoryPlayer(){
        victoryPlayer.stop();
        victoryPlayer.prepareAsync();
    }
}
