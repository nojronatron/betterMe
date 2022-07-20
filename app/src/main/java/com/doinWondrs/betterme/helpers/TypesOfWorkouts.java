package com.doinWondrs.betterme.helpers;


import com.doinWondrs.betterme.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TypesOfWorkouts {

    public HashMap<String, String> workoutRoutine;
    public int gif1, gif2, gif3;

    //Constructor
    public TypesOfWorkouts(String pWType)
    {
        this.workoutRoutine = new HashMap<>();
        buildWorkout(pWType);
    }





    public void abdominalWorkouts(){
        workoutRoutine.put("machine ab twist","twist body and toward toes,abs,R.drawable.ab1");
        workoutRoutine.put("crunches", "lean towards toes,abs,R.drawable.ab2");
        workoutRoutine.put("leg over crunch","one leg over knee and crunch,abs,R.drawable.ab3");
        gif1 = R.drawable.ab1;
        gif2 = R.drawable.ab2;
        gif3 = R.drawable.ab3;

    }

    public void chestWorkouts(){


        //set exercise routine up [what this is: lifting , what muscles groups: legs, gif or image of workout]

        workoutRoutine.put("Bench Press","Barbell Bench press,chest,R.drawable.chest1");
        workoutRoutine.put("Machine Inner Chest Press", "press with machine,chest,R.drawable.chest2");
        workoutRoutine.put("thirdRoutine","Medicine Ball Supine Chest Throw,chest,R.drawable.chest3");
        gif1 = R.drawable.chest1;
        gif2 = R.drawable.chest2;
        gif3 = R.drawable.chest3;

    }

    public void LegWorkouts(){
        workoutRoutine.put("lever altenate leg press", "machine kcik off,leg,R.drawable.legs1");
        workoutRoutine.put("lever hip extension","machine and kickback and away,leg,R.drawable.legs2");
        workoutRoutine.put("lever kneeling leg curl", "kick away and use machine,leg,R.drawable.legs3");
        gif1 = R.drawable.legs1;
        gif2 = R.drawable.legs2;
        gif3 = R.drawable.legs3;
    }

    public void armWorkouts(){


        workoutRoutine.put("Lever overhand triceps dip","use machine and lean 20 degrees forward,triceps,R.drawable.arms1");
        workoutRoutine.put("ring dips", "use rings to dip,tricep,R.drawable.arms2");
        workoutRoutine.put("wrist roller","use a plate and string to rotate extended and roll back in,forarm,R.drawable.arms3");
        gif1 = R.drawable.arms1;
        gif2 = R.drawable.arms2;
        gif3 = R.drawable.arms3;
    }
    public void backWorkouts(){
        workoutRoutine.put("planche","bodyweight,back,R.drawable.back1");
        workoutRoutine.put("chin-ups", "narrow grip,back,R.drawable.back2");
        workoutRoutine.put("bent over dumbell row","dumbells and lean forward,back,R.drawable.back3");
        gif1 = R.drawable.back1;
        gif2 = R.drawable.back2;
        gif3 = R.drawable.back3;

    }
    public void shoulderWorkouts(){
        workoutRoutine.put("barbell front raise","barbell raise parallel to the deck,barbell or dumbell,R.drawable.shoulders1");
        workoutRoutine.put("seated barbell press","sit and press with barbell,shoulder,R.drawable.shoulders2");
        workoutRoutine.put("dumbell front raise","dumbell and paralell to deck,shoulder,R.drawable.shoulders3");

        gif1 = R.drawable.shoulder1;
        gif2 = R.drawable.shoulder2;
        gif3 = R.drawable.shoulder3;
    }

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
