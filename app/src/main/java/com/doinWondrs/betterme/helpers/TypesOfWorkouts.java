package com.doinWondrs.betterme.helpers;

import android.widget.TextView;

import com.doinWondrs.betterme.R;

import org.w3c.dom.Text;

public class TypesOfWorkouts {

    public String typeWorkoutString;
    public TypesOfWorkouts(String pWType)
    {
        buildWorkout(pWType);
    }



        //1. sets,
        // 2. reps,
        // 3. jpg/images,
        // 4. mp4/gif,
        // 5. Strings

    //refactor for json format to amplify
    public void weightliftingWorkouts(){
    }
    public void strengthWorkouts(){}
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
