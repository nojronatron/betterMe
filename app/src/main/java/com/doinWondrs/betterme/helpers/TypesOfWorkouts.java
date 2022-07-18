package com.doinWondrs.betterme.helpers;


import java.util.ArrayList;
import java.util.HashMap;

public class TypesOfWorkouts {

    public HashMap<String, String> workoutRoutine;

    public TypesOfWorkouts(String pWType)
    {
        buildWorkout(pWType);
    }





    public void abdominalWorkouts(){

    }

    public void chestWorkouts(){

        this.workoutRoutine = new HashMap<>();
        //set exercise routine up [what this is: lifting , what muscles groups: legs, gif or image of workout]

        workoutRoutine.put("Bench Press","Barbell Bench press,chest,stuff.gif");
        workoutRoutine.put("secondRoutine","Machine Inner Chest Press,chest,stuff.gif");
        workoutRoutine.put("thirdRoutine","Medicine Ball Supine Chest Throw,chest,stuff.gif");

    }

    public void LegWorkouts(){

    }

    public void armWorkouts(){}
    public void backWorkouts(){}
    public void shoulderWorkouts(){}

    public void buildWorkout(String wType)//workout type string
    {
        //check string with TypesOfWorkouts class
        //if the are the same, then populate the WorkoutPageSecond.java with workouts
        switch (wType) {
            case "Abdominal Focus":
                abdominalWorkouts();
                break;
            case "Chest Focus":
                chestWorkouts();
                break;
            case "Legs Focus":
                LegWorkouts();
                break;
            case "Back Focus":
                backWorkouts();
                break;
            case "Arms Focus":
                armWorkouts();
                break;
            case "Shoulders Focus":
                shoulderWorkouts();
                break;

        }//END: swtich : types of workouts
    }//END:buildworkout

}//END: Class
