package jonathanmanos.stepman.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jonny on 7/9/2016.
 */
public class StepManCharacter {

    private SharedPreferences mPrefs;

    private static String name;
    private static String color;
    private static String difficulty;
    private static int level;
    private static int steps;
    private static int points;
    private static int spentPoints;
    private static int difficultyValue;
    public static int stepsAtLevelUp;
    private static int pointAdjustor;
    private static int worldLevel;
    private static long unixStartTime;
    private static int hp;
    private static int strength;
    private static int defense;
    private static int magic;
    private static int magicDef;
    private static int speed;

    public StepManCharacter(Context context)
    {
        mPrefs = context.getSharedPreferences("label", 0);
        name = mPrefs.getString("name", "");
        color = mPrefs.getString("color", "");
        steps = mPrefs.getInt("steps", 0);

        difficulty = mPrefs.getString("difficulty", "Easy");
        stepsAtLevelUp = mPrefs.getInt("stepsAtLevelUp", 0);
        difficultyValue = mPrefs.getInt("difficultyValue", 10);
        pointAdjustor = mPrefs.getInt("pointAdjustor", 1);
        worldLevel = mPrefs.getInt("worldLevel", 1);
        unixStartTime = mPrefs.getLong("unixStartTime", 1);

        level = mPrefs.getInt("level", 1);
        points = mPrefs.getInt("points", 5);
        spentPoints = mPrefs.getInt("spentPoints", 0);

        hp = mPrefs.getInt("hp", 10);
        strength = mPrefs.getInt("strength", 10);
        defense = mPrefs.getInt("defense", 10);
        magic = mPrefs.getInt("magic", 10);
        magicDef = mPrefs.getInt("magicDef", 10);
        speed = mPrefs.getInt("speed", 10);
    }

    public String getStepManName(){
        return name;
    }

    public String getStepManColor(){
        return color;
    }

    public String getStepManDifficulty(){
        return difficulty;
    }

    public int getStepManLevel(){
        return level;
    }

    public int getStepManSteps(){
        return steps;
    }

    public int getStepManPoints(){
        return points;
    }

    public int getStepManSpentPoints(){
        return spentPoints;
    }

    public int getStepManDifficultyValue(){
        return difficultyValue;
    }

    public int getStepManStepsAtLevelUp(){
        return stepsAtLevelUp;
    }

    public int getStepManPointAdjustor(){
        return pointAdjustor;
    }

    public int getWorldLevel(){
        return worldLevel;
    }

    public long getStepManUnixStartTime(){
        return unixStartTime;
    }

    public int getStepManHP(){
        return hp;
    }

    public int getStepManStrength(){
        return strength;
    }

    public int getStepManDefense(){
        return defense;
    }

    public int getStepManMagic(){
        return magic;
    }

    public int getStepManMagicDef(){
        return magicDef;
    }

    public int getStepManSpeed(){
        return speed;
    }

    public void setStepManName(String name){
        this.name = name;
    }

    public void setStepManColor(String color){
        this.color = color;
    }

    public void setStepManDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public void setStepManLevel(int level){
        this.level = level;
    }

    public void setStepManSteps(int steps){
        this.steps = steps;
    }

    public void setStepManPoints(int points){
        this.points = points;
    }

    public void setStepManSpentPoints(int spentPoints){
        this.spentPoints = spentPoints;
    }

    public void setStepManDifficultyValue(int difficultyValue){
        this.difficultyValue = difficultyValue;
    }

    public void setStepManStepsAtLevelUp(int stepsAtLevelUp){
        this.stepsAtLevelUp = stepsAtLevelUp;
    }

    public void setStepManStepsAtPointAdjustor(int pointAdjustor){
        this.pointAdjustor = pointAdjustor;
    }

    public void setWorldLevel(int worldLevel){
        this.worldLevel = worldLevel;
    }

    public void setStepManStepsAtUnixStartTime(long unixStartTime){
        this.unixStartTime = unixStartTime;
    }

    public void setStepManHP(int hp){
        this.hp = hp;
    }

    public void setStepManStrength(int strength){
        this.strength = strength;
    }

    public void setStepManDefense(int defense){
        this.defense = defense;
    }

    public void setStepManMagic(int magic){
        this.magic = magic;
    }

    public void setStepManMagicDef(int magicDef){
        this.magicDef = magicDef;
    }

    public void setStepManSpeed(int speed){
        this.speed = speed;
    }

    public void refreshStepMan(Context context){
        mPrefs = context.getSharedPreferences("label", 0);
        name = mPrefs.getString("name", "Bertucci");
        color = mPrefs.getString("color", "Pinky");
        steps = mPrefs.getInt("steps", -2);

        difficulty = mPrefs.getString("difficulty", "SUPER HARD");
        stepsAtLevelUp = mPrefs.getInt("stepsAtLevelUp", 0);
        difficultyValue = mPrefs.getInt("difficultyValue", 10);
        pointAdjustor = mPrefs.getInt("pointAdjustor", 1);
        worldLevel = mPrefs.getInt("worldLevel", 1);
        unixStartTime = mPrefs.getLong("unixStartTime", 1);

        level = mPrefs.getInt("level", 1);
        points = mPrefs.getInt("points", 5);
        spentPoints = mPrefs.getInt("spentPoints", 0);

        hp = mPrefs.getInt("hp", 10);
        strength = mPrefs.getInt("strength", 10);
        defense = mPrefs.getInt("defense", 10);
        magic = mPrefs.getInt("magic", 10);
        magicDef = mPrefs.getInt("magicDef", 10);
        speed = mPrefs.getInt("speed", 10);
    }

    public void saveStepMan(){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("name", name);
        mEditor.putString("color", color);
        mEditor.putString("difficulty", difficulty);
        mEditor.putInt("level", level);
        mEditor.putInt("steps", steps);
        mEditor.putInt("points", points);
        mEditor.putInt("spentPoints", spentPoints);
        mEditor.putInt("difficultyValue", difficultyValue);
        mEditor.putInt("stepsAtLevelUp", stepsAtLevelUp);
        mEditor.putInt("pointAdjustor", pointAdjustor);
        mEditor.putInt("worldLevel", worldLevel);
        mEditor.putLong("unixStartTime", unixStartTime);
        mEditor.putInt("hp", hp);
        mEditor.putInt("strength", strength);
        mEditor.putInt("defense", defense);
        mEditor.putInt("magic", magic);
        mEditor.putInt("magicDef", magicDef);
        mEditor.putInt("speed", speed);
        mEditor.apply();
    }

    public void deleteStepMan(Context context){
        mPrefs = context.getSharedPreferences("label", 0);
        mPrefs.edit().clear().commit();
    }
}
