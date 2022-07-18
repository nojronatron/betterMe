package com.doinWondrs.betterme.helpers;


import java.util.ArrayList;
import java.util.HashMap;

public class TypesOfWorkouts {

    public HashMap<String, String> workoutRoutine;

    public TypesOfWorkouts(String pWType)
    {
        buildWorkout(pWType);
    }




    //TODO: refactor for json format to amplify?
    public void weightliftingWorkouts(){

    }

    public void strengthWorkouts(){

        this.workoutRoutine = new HashMap<>();
        //set excercise routine up [sets, reps/duration, load, rest, jpg/images, mp4/gif]
        workoutRoutine.put("firstRoutine", "3,12,9999,1 min, test.jpg, test.mp4");
        workoutRoutine.put("secondRoutine", "3,12,9999,1 min, test.jpg, test.mp4");
        workoutRoutine.put("thirdRoutine", "3,12,9999,1 min, test.jpg, test.mp4");

    }
    public void hiitWorkouts(){}

    public void crossfitWorkouts(){}
    public void calisthenicsWorkouts(){}
    public void olympicWorkouts(){}

    public void buildWorkout(String wType)//workout type string
    {
        //check string with TypesOfWorkouts class
        //if the are the same, then populate the WokroutPageSecond.java with workouts
        switch (wType) {
            case "Weightlifting Focus":
                weightliftingWorkouts();
                break;
            case "Strength Focus":
                strengthWorkouts();
                break;
            case "Hiit Focus":
                hiitWorkouts();
                break;
            case "Calisthenics Focus":
                calisthenicsWorkouts();
                break;
            case "Crossfit Focus":
                crossfitWorkouts();
                break;
            case "Olympic Focus":
                olympicWorkouts();
                break;

        }//END: swtich : types of workouts
    }//END:buildworkout

}//END: Class
