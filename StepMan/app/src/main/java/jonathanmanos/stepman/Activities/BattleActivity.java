package jonathanmanos.stepman.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import jonathanmanos.stepman.R;

public class BattleActivity extends AppCompatActivity {

    public static int level;

    private static String name;
    private static String color;
    private static String difficulty;

    private static int hp;
    private static int strength;
    private static int defense;
    private static int magic;
    private static int magicDef;
    private static int speed;

    private static int enemyHP;
    private static String enemyName;
    private static String enemyType;

    private boolean busy;
    public static boolean newLevel = false;

    private SharedPreferences mPrefs;
    private Handler h;
    private Activity battleActivity;
    private static int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        battleActivity = this;
        orientation = battleActivity.getResources().getConfiguration().orientation;

        mPrefs = getSharedPreferences("label", 0);

        name = WorldActivity.battleManager.stepMan.getStepManName();
        color = WorldActivity.battleManager.stepMan.getStepManColor();
        difficulty = WorldActivity.battleManager.stepMan.getStepManDifficulty();

        hp = WorldActivity.battleManager.stepMan.getStepManHP();
        strength = WorldActivity.battleManager.stepMan.getStepManStrength();
        defense = WorldActivity.battleManager.stepMan.getStepManDefense();
        magic = WorldActivity.battleManager.stepMan.getStepManMagic();
        magicDef = WorldActivity.battleManager.stepMan.getStepManMagicDef();
        speed = WorldActivity.battleManager.stepMan.getStepManSpeed();

        System.out.println("currentHP:" + WorldActivity.battleManager.getCurrentHP());
        if(newLevel) {
            WorldActivity.battleManager.setCurrentHP(hp);
        }

        //testingcode
        /*
        hp = 1000;
        currentHP = hp;
        strength = 1000;
        defense = 1000;
        magic = 1000;
        magicDef = 1000;
        speed = 1000;
        */

        busy = false;

        WorldActivity.soundManager.playBattlePlayer();

        TextView battlename = (TextView)findViewById(R.id.battlename);
        battlename.setText(name);

        TextView battlehp = (TextView)findViewById(R.id.battlehp);
        battlehp.setText("HP: " + WorldActivity.battleManager.getCurrentHP() + "/" + hp);

        ImageView battleimage = (ImageView) findViewById(R.id.battleimage);

        System.out.println("color is: " + color);
        if (color.contentEquals("Black")) {
            battleimage.setImageResource(R.drawable.asset_stepman_stepmanrunning);
        } else if (color.contentEquals("Blue")) {
            battleimage.setImageResource(R.drawable.asset_stepman_stepmanrunning_blue);
        } else if (color.contentEquals("Green")) {
            battleimage.setImageResource(R.drawable.asset_stepman_stepmanrunning_green);
        } else if (color.contentEquals("Red")) {
            battleimage.setImageResource(R.drawable.asset_stepman_stepmanrunning_red);
        } else if (color.contentEquals("Luigi")) {
            battleimage.setImageResource(R.drawable.asset_stepman_luigi);
        }

        ImageView battleenemyimage = (ImageView) findViewById(R.id.battleenemyimage);

        if(level == 1)
        {
            enemyHP = 100;
            enemyType = "normal";
            enemyName = "Chicken";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w11chicken);
        }
        if(level == 2)
        {
            enemyHP = 200;
            enemyType = "asset_battle_fire";
            enemyName = "Paratroopa";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w12paratroopa);
        }
        if(level == 3)
        {
            enemyHP = 500;
            enemyType = "asset_battle_ice";
            enemyName = "Freezard";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w13freezard);
        }
        if(level == 4)
        {
            enemyHP = 1000;
            enemyType = "normal";
            enemyName = "Infernal";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w14infernal);
        }
        if(level == 5)
        {
            enemyHP = 1800;
            enemyType = "asset_battle_ice";
            enemyName = "Star Wolf";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w15starwolf);
        }
        if(level == 6)
        {
            enemyHP = 3000;
            enemyType = "asset_battle_fire";
            enemyName = "Ridley";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w16ridley);
        }
        if(level == 7)
        {
            enemyHP = 4500;
            enemyType = "normal";
            enemyName = "Sephiroth";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w17sephiroth);
        }
        if(level == 8)
        {
            enemyHP = 6000;
            enemyType = "asset_battle_fire";
            enemyName = "Groudon";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w18groudon);
        }
        if(level == 9)
        {
            enemyHP = 8000;
            enemyType = "asset_battle_ice";
            enemyName = "Necron";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w19necron);
        }
        if(level == 10)
        {
            enemyHP = 9999;
            enemyType = "normal";
            enemyName = "Master Hand";
            battleenemyimage.setImageResource(R.drawable.asset_enemy_w110masterhand);
        }
        if(newLevel){
            WorldActivity.battleManager.setCurrentEnemyHP(enemyHP);
            newLevel = false;
        }

        TextView battleenemy = (TextView)findViewById(R.id.battleenemy);
        battleenemy.setText(enemyName);

        TextView battleenemyhp = (TextView)findViewById(R.id.battleenemyhp);
        battleenemyhp.setText("HP: " + WorldActivity.battleManager.getCurrentEnemyHP() + "/" + enemyHP);

        h = new Handler();
    }

    public void enemyAttack() {
        if(WorldActivity.battleManager.getCurrentEnemyHP() <= 0)
            updatePage();
        else{
            WorldActivity.soundManager.setAttackEnemyPlayer(R.raw.punches);
            WorldActivity.soundManager.playAttackEnemyPlayer();
            TextView battleenemyhp = (TextView)findViewById(R.id.battleenemyhp);
            battleenemyhp.setText("HP: " + WorldActivity.battleManager.getCurrentEnemyHP() + "/" + enemyHP);

            final ImageView myanimation = (ImageView) findViewById(R.id.myanimation);
            myanimation.setImageResource(R.drawable.asset_battle_pow);
            myanimation.setAlpha(.6F);

            int attack = 0;
            Random rand = new Random();

            if (level == 1) {
                attack = 15;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(4);
            }
            if (level == 2) {
                attack = 20;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(6);
            }
            if (level == 3) {
                attack = 30;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(10);
            }
            if (level == 4) {
                attack = 40;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(14);
            }
            if (level == 5) {
                attack = 50;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(18);
            }
            if (level == 6) {
                attack = 60;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(20);
            }
            if (level == 7) {
                attack = 70;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(25);
            }
            if (level == 8) {
                attack = 80;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(30);
            }
            if (level == 9) {
                attack = 90;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(40);
            }
            if (level == 10) {
                attack = 100;
                attack -= (defense + magicDef)/2;
                if(attack < 1)
                    attack = 1;
                attack += rand.nextInt(50);
            }

            if(attack < 1)
                attack = 1;

            int damage = WorldActivity.battleManager.getCurrentHP() - attack;
            WorldActivity.battleManager.setCurrentHP(damage);

            TextView battlepopup = (TextView)findViewById(R.id.battlepopup);
            battlepopup.setText(Integer.toString(attack));

            Runnable shake1 = new Runnable() {
                @Override
                public void run() {
                    myanimation.setPadding(80,0,0,0);
                }
            };
            Runnable shake2 = new Runnable() {
                @Override
                public void run() {
                    myanimation.setPadding(0,80,0,0);
                }
            };
            Runnable shake3 = new Runnable() {
                @Override
                public void run() {
                    myanimation.setPadding(0,0,80,0);
                }
            };
            Runnable shake4 = new Runnable() {
                @Override
                public void run() {
                    myanimation.setPadding(0,0,0,80);
                }
            };
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    ImageView myanimation = (ImageView) findViewById(R.id.myanimation);
                    myanimation.setImageResource(android.R.color.transparent);

                    TextView battlepopup = (TextView)findViewById(R.id.battlepopup);
                    battlepopup.setText("");


                    updatePage();
                }
            };
            h.postDelayed(shake1,300);
            h.postDelayed(shake2,600);
            h.postDelayed(shake3,900);
            h.postDelayed(shake4,1200);
            h.postDelayed(r,1500);
        }
    }

    public void updatePage() {

        if(WorldActivity.battleManager.getCurrentEnemyHP() <= 0)
            WorldActivity.battleManager.setCurrentEnemyHP(0);
        else if(WorldActivity.battleManager.getCurrentHP() <= 0)
            WorldActivity.battleManager.setCurrentHP(0);
        else {
            busy = false;
            enableButtons();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }

        TextView battlehp = (TextView)findViewById(R.id.battlehp);
        battlehp.setText("HP: "+ WorldActivity.battleManager.getCurrentHP() + "/" + hp);

        TextView battleenemyhp = (TextView)findViewById(R.id.battleenemyhp);
        battleenemyhp.setText("HP: "+ WorldActivity.battleManager.getCurrentEnemyHP() + "/" + enemyHP);

        if(WorldActivity.battleManager.getCurrentHP() == 0)
        {
            ImageView myanimation = (ImageView) findViewById(R.id.myanimation);
            myanimation.setImageResource(R.drawable.asset_battle_skull);
            myanimation.setAlpha(.9F);

            Runnable skull = new Runnable() {
                @Override
                public void run(){
                    ImageView myanimation = (ImageView) findViewById(R.id.myanimation);
                    myanimation.setImageResource(android.R.color.transparent);
                    ImageView battleimage = (ImageView) findViewById(R.id.battleimage);
                    battleimage.setImageResource(android.R.color.transparent);
                }
            };
            Runnable youfailed = new Runnable() {
                @Override
                public void run(){
                    WorldActivity.soundManager.stopBattlePlayer();
                    WorldActivity.soundManager.playFailurePlayer();
                    ImageView battleimage = (ImageView) findViewById(R.id.battleimage);
                    battleimage.setImageResource(R.drawable.asset_battle_failure);
                }
            };
            Runnable backtoworld = new Runnable() {
                @Override
                public void run(){
                    finish();
                }
            };
            h.postDelayed(skull, 1000);
            h.postDelayed(youfailed, 2000);
            h.postDelayed(backtoworld, 4000);
        }

        if(WorldActivity.battleManager.getCurrentEnemyHP() == 0){
            WorldActivity.soundManager.stopBattlePlayer();
            WorldActivity.soundManager.playVictoryPlayer();
            ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
            enemyanimation.setImageResource(R.drawable.asset_battle_skull);
            enemyanimation.setAlpha(.9F);

            Runnable skull = new Runnable() {
                @Override
                public void run(){
                    ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
                    enemyanimation.setImageResource(android.R.color.transparent);
                    ImageView battleenemyimage = (ImageView) findViewById(R.id.battleenemyimage);
                    battleenemyimage.setImageResource(android.R.color.transparent);
                }
            };
            Runnable youdidit = new Runnable() {
                @Override
                public void run(){
                    ImageView battleenemyimage = (ImageView) findViewById(R.id.battleenemyimage);
                    battleenemyimage.setImageResource(R.drawable.asset_battle_youdidit);
                }
            };
            Runnable backtoworld = new Runnable() {
                @Override
                public void run(){
                    WorldActivity.soundManager.stopVictoryPlayer();

                    if(level == WorldActivity.battleManager.stepMan.getWorldLevel())
                        WorldActivity.battleManager.stepMan.setWorldLevel(level + 1);
                    WorldActivity.needToRecreate = true;
                    finish();
                }
            };
            h.postDelayed(skull, 1000);
            h.postDelayed(youdidit, 2000);
            h.postDelayed(backtoworld, 4000);
        }
    }

    public void blazeKick(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        busy = true;
        disableButtons();
        WorldActivity.soundManager.setAttackStepManPlayer(R.raw.fireball);
        WorldActivity.soundManager.playAttackStepManPlayer();
        final ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
        enemyanimation.setImageResource(R.drawable.asset_battle_fire);
        enemyanimation.setAlpha(.8F);

        int attack = 0;
        if (enemyType.equals("normal")) {
            attack = magic;
        }
        else if (enemyType.equals("asset_battle_ice")) {
            attack = magic + speed + strength;
        }
        else if (enemyType.equals("asset_battle_fire")) {
            attack = magic / 2;
        }
        if((int)(Math.random() * 10) == 0)
        {
            attack *= 2;
        }
        attack += (int)(Math.random() * 10 + 1);
        int damage = WorldActivity.battleManager.getCurrentEnemyHP() - attack;
        WorldActivity.battleManager.setCurrentEnemyHP(damage);

        TextView battlepopupenemy = (TextView)findViewById(R.id.battlepopupenemy);
        battlepopupenemy.setText(Integer.toString(attack));

        Runnable shake1 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(0,0,80,0);
            }
        };
        Runnable shake2 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(80,0,0,0);
            }
        };
        Runnable shake3 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(0,0,80,0);
            }
        };
        Runnable shake4 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(80,0,0,0);
            }
        };
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
                enemyanimation.setImageResource(android.R.color.transparent);

                TextView battlepopupenemy = (TextView)findViewById(R.id.battlepopupenemy);
                battlepopupenemy.setText("");

                enemyAttack();
            }
        };
        h.postDelayed(shake1,200);
        h.postDelayed(shake2,400);
        h.postDelayed(shake3,600);
        h.postDelayed(shake4,800);
        h.postDelayed(r, 1000);
    }
    public void iceStrike(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        busy = true;
        disableButtons();
        WorldActivity.soundManager.setAttackStepManPlayer(R.raw.fireball);
        WorldActivity.soundManager.playAttackStepManPlayer();
        ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
        enemyanimation.setImageResource(R.drawable.asset_battle_icecube);
        enemyanimation.setAlpha(.8F);

        int attack = 0;
        if(enemyType.equals("normal")) {
            attack = magic;
        }
        else if(enemyType.equals("asset_battle_ice")) {
            attack = magic/2;
        }
        else if(enemyType.equals("asset_battle_fire")) {
            attack = magic + speed + strength;
        }
        if((int)(Math.random() * 10) == 0)
        {
            attack *= 2;
        }
        attack += (int)(Math.random() * 10 + 1);

        int damage = WorldActivity.battleManager.getCurrentEnemyHP() - attack;
        WorldActivity.battleManager.setCurrentEnemyHP(damage);


        TextView battlepopupenemy = (TextView)findViewById(R.id.battlepopupenemy);
        battlepopupenemy.setText(Integer.toString(attack));

        Runnable r = new Runnable() {
            @Override
            public void run(){
                ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
                enemyanimation.setImageResource(android.R.color.transparent);

                TextView battlepopupenemy = (TextView)findViewById(R.id.battlepopupenemy);
                battlepopupenemy.setText("");

                enemyAttack();
            }
        };
        h.postDelayed(r, 1000);
    }

    public void megaPunch(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        busy = true;
        disableButtons();
        WorldActivity.soundManager.setAttackStepManPlayer(R.raw.punches);
        WorldActivity.soundManager.playAttackStepManPlayer();
        final ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
        enemyanimation.setImageResource(R.drawable.asset_battle_punch);
        enemyanimation.setAlpha(.8F);

        int attack = 0;
        if (enemyType.equals("normal")) {
            attack = strength * 2;
        } else if (enemyType.equals("asset_battle_ice")) {
            attack = strength;
        } else if (enemyType.equals("asset_battle_fire")) {
            attack = strength;
        }
        if((int)(Math.random() * 10) == 0)
        {
            attack *= 2;
        }
        attack += (int)(Math.random() * 10 + 1);

        int damage = WorldActivity.battleManager.getCurrentEnemyHP() - attack;
        WorldActivity.battleManager.setCurrentEnemyHP(damage);

        TextView battlepopupenemy = (TextView)findViewById(R.id.battlepopupenemy);
        battlepopupenemy.setText(Integer.toString(attack));

        Runnable shake1 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(80,0,0,0);
            }
        };
        Runnable shake2 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(0,80,0,0);
            }
        };
        Runnable shake3 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(0,0,80,0);
            }
        };
        Runnable shake4 = new Runnable() {
            @Override
            public void run() {
                enemyanimation.setPadding(0,0,0,80);
            }
        };
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ImageView enemyanimation = (ImageView) findViewById(R.id.enemyanimation);
                enemyanimation.setImageResource(android.R.color.transparent);

                TextView battlepopupenemy = (TextView)findViewById(R.id.battlepopupenemy);
                battlepopupenemy.setText("");

                enemyAttack();
            }
        };
        h.postDelayed(shake1,300);
        h.postDelayed(shake2,600);
        h.postDelayed(shake3,900);
        h.postDelayed(shake4,1200);
        h.postDelayed(r,1500);
    }

    public void goToWorld(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        busy = true;
        WorldActivity.soundManager.stopBattlePlayer();
        WorldActivity.soundManager.playRunAwayPlayer();
        final ImageView battleimage = (ImageView) findViewById(R.id.battleimage);

        Runnable shake1 = new Runnable() {
            @Override
            public void run() {
                battleimage.setPadding(0,0,200,0);
            }
        };
        Runnable shake2 = new Runnable() {
            @Override
            public void run() {
                battleimage.setPadding(0,0,400,0);
            }
        };
        Runnable shake3 = new Runnable() {
            @Override
            public void run() {
                battleimage.setPadding(0,0,600,0);
            }
        };
        Runnable shake4 = new Runnable() {
            @Override
            public void run() {
                battleimage.setPadding(0,0,800,0);
            }
        };
        Runnable r = new Runnable() {
            @Override
            public void run() {
                finish();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
        };
        h.postDelayed(shake1,400);
        h.postDelayed(shake2,800);
        h.postDelayed(shake3,1200);
        h.postDelayed(shake4,1600);
        h.postDelayed(r,2000);
    }

    private void disableButtons(){
        float alphaValue = .65F;

        Button blazekickbutton = (Button) findViewById(R.id.blazekickbutton);
        blazekickbutton.setAlpha(alphaValue);
        blazekickbutton.setEnabled(false);
        Button icestrikebutton = (Button) findViewById(R.id.icestrikebutton);
        icestrikebutton.setAlpha(alphaValue);
        icestrikebutton.setEnabled(false);
        Button megapunchbutton = (Button) findViewById(R.id.megapunchbutton);
        megapunchbutton.setAlpha(alphaValue);
        megapunchbutton.setEnabled(false);
        Button fleebutton = (Button) findViewById(R.id.fleebutton);
        fleebutton.setAlpha(alphaValue);
        fleebutton.setEnabled(false);

    }

    private void enableButtons(){
        Button blazekickbutton = (Button) findViewById(R.id.blazekickbutton);
        blazekickbutton.setAlpha(1F);
        blazekickbutton.setEnabled(true);
        Button icestrikebutton = (Button) findViewById(R.id.icestrikebutton);
        icestrikebutton.setAlpha(1F);
        icestrikebutton.setEnabled(true);
        Button megapunchbutton = (Button) findViewById(R.id.megapunchbutton);
        megapunchbutton.setAlpha(1F);
        megapunchbutton.setEnabled(true);
        Button fleebutton = (Button) findViewById(R.id.fleebutton);
        fleebutton.setAlpha(1F);
        fleebutton.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(!busy){
            System.out.println("yoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            busy = true;
            WorldActivity.soundManager.stopBattlePlayer();
            WorldActivity.soundManager.playRunAwayPlayer();
            final ImageView battleimage = (ImageView) findViewById(R.id.battleimage);

            Runnable shake1 = new Runnable() {
                @Override
                public void run() {
                    battleimage.setPadding(0,0,200,0);
                }
            };
            Runnable shake2 = new Runnable() {
                @Override
                public void run() {
                    battleimage.setPadding(0,0,400,0);
                }
            };
            Runnable shake3 = new Runnable() {
                @Override
                public void run() {
                    battleimage.setPadding(0,0,600,0);
                }
            };
            Runnable shake4 = new Runnable() {
                @Override
                public void run() {
                    battleimage.setPadding(0,0,800,0);
                }
            };
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    finish();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                }
            };
            h.postDelayed(shake1,400);
            h.postDelayed(shake2,800);
            h.postDelayed(shake3,1200);
            h.postDelayed(shake4,1600);
            h.postDelayed(r,2000);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            WorldActivity.soundManager.stopBattlePlayer();
            WorldActivity.soundManager.stopVictoryPlayer();
            finish();
        } else {

        }
    }

    protected void onStop() {
        super.onStop();
    }
}
