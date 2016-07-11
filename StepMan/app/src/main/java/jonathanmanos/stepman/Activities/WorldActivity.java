package jonathanmanos.stepman.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import jonathanmanos.stepman.Controllers.BattleManager;
import jonathanmanos.stepman.Controllers.SoundManager;
import jonathanmanos.stepman.R;

public class WorldActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private static int worldLevel;
    public static boolean needToRecreate;
    private static final float alphaValue = .5F;

    public static BattleManager battleManager;
    public static SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        needToRecreate = false;
        battleManager = new BattleManager(getApplicationContext());
        soundManager = new SoundManager(getApplicationContext());

        mPrefs = getSharedPreferences("label", 0);
        worldLevel = mPrefs.getInt("worldLevel", 1);

        disableButtons();
        showBeatenWorlds();
    }


    public void goToBattle(View view) {
        Button button = (Button)view;
        Intent intent = new Intent(getApplicationContext(), BattleActivity.class);
        startActivity(intent);
        setLevel(Integer.parseInt(button.getText().toString()));
        setNewLevel();
    }

    private void setLevel(int i){
        BattleActivity.level = i;
    }

    private void setNewLevel(){
        BattleActivity.newLevel = true;
    }

    private void disableButtons(){

        if(worldLevel <= 9)
            hideButton((Button) findViewById(R.id.world1button10));
        if(worldLevel <= 8)
            hideButton((Button) findViewById(R.id.world1button9));
        if(worldLevel <= 7)
            hideButton((Button) findViewById(R.id.world1button8));
        if(worldLevel <= 6)
            hideButton((Button) findViewById(R.id.world1button7));
        if(worldLevel <= 5)
            hideButton((Button) findViewById(R.id.world1button6));
        if(worldLevel <= 4)
            hideButton((Button) findViewById(R.id.world1button5));
        if(worldLevel <= 3)
            hideButton((Button) findViewById(R.id.world1button4));
        if(worldLevel <= 2)
            hideButton((Button) findViewById(R.id.world1button3));
        if(worldLevel <= 1)
            hideButton((Button) findViewById(R.id.world1button2));

    }

    private void showBeatenWorlds(){

        if(worldLevel > 1) {
            ((ImageView) findViewById(R.id.world1button1image)).setImageResource(R.drawable.asset_enemy_w11chicken);
            //((Button) findViewById(R.id.world1button1)).setText("");
        }
        if(worldLevel > 2){
            ((ImageView) findViewById(R.id.world1button2image)).setImageResource(R.drawable.asset_enemy_w12paratroopa);
            //((Button) findViewById(R.id.world1button2)).setText("");
        }
        if(worldLevel > 3){
            ((ImageView) findViewById(R.id.world1button3image)).setImageResource(R.drawable.asset_enemy_w13freezard);
            //((Button) findViewById(R.id.world1button3)).setText("");
        }
        if(worldLevel > 4){
            ((ImageView) findViewById(R.id.world1button4image)).setImageResource(R.drawable.asset_enemy_w14infernal);
            //((Button) findViewById(R.id.world1button4)).setText("");
        }
        if(worldLevel > 5){
            ((ImageView) findViewById(R.id.world1button5image)).setImageResource(R.drawable.asset_enemy_w15starwolf);
            //((Button) findViewById(R.id.world1button5)).setText("");
        }
        if(worldLevel > 6){
            ((ImageView) findViewById(R.id.world1button6image)).setImageResource(R.drawable.asset_enemy_w16ridley);
            //((Button) findViewById(R.id.world1button6)).setText("");
        }
        if(worldLevel > 7){
            ((ImageView) findViewById(R.id.world1button7image)).setImageResource(R.drawable.asset_enemy_w17sephiroth);
            //((Button) findViewById(R.id.world1button7)).setText("");
        }
        if(worldLevel > 8){
            ((ImageView) findViewById(R.id.world1button8image)).setImageResource(R.drawable.asset_enemy_w18groudon);
            //((Button) findViewById(R.id.world1button8)).setText("");
        }
        if(worldLevel > 9){
            ((ImageView) findViewById(R.id.world1button9image)).setImageResource(R.drawable.asset_enemy_w19necron);
           // ((Button) findViewById(R.id.world1button9)).setText("");
        }
        if(worldLevel > 10){
            ((ImageView) findViewById(R.id.world1button10image)).setImageResource(R.drawable.asset_enemy_w110masterhand);
            //((Button) findViewById(R.id.world1button10)).setText("");
        }

    }

    private void hideButton(Button button){
        button.setAlpha(alphaValue);
        button.setEnabled(false);
        button.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            // do stuff
        } else {
            //It's an orientation change.
        }
        battleManager = new BattleManager(getApplicationContext());
        soundManager = new SoundManager(getApplicationContext());
    }

    protected void onResume(){
        super.onResume();

        if(needToRecreate)
            this.recreate();

        battleManager = new BattleManager(getApplicationContext());
        soundManager = new SoundManager(getApplicationContext());
    }

    protected void onStop() {
        super.onStop();
    }

}
