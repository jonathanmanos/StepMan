package jonathanmanos.stepman.Controllers;

import android.content.Context;
import android.media.MediaPlayer;

import jonathanmanos.stepman.Data.StepManCharacter;
import jonathanmanos.stepman.R;

/**
 * Created by Jonny on 7/10/2016.
 */
public class BattleManager {

    private static int currentHP;
    private static int currentEnemyHP;
    public StepManCharacter stepMan;

    private Context context;

    public BattleManager(Context context){
        currentHP = 999;
        currentEnemyHP = 999;
        stepMan = new StepManCharacter(context);
    }

    public void setCurrentHP(int value){
        currentHP = value;
    }

    public void setCurrentEnemyHP(int value){
        currentEnemyHP = value;
    }

    public int getCurrentHP(){
        return currentHP;
    }

    public int getCurrentEnemyHP(){
        return currentEnemyHP;
    }
}
