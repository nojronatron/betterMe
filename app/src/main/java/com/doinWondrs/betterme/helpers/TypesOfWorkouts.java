package com.doinWondrs.betterme.helpers;

import android.widget.TextView;

import com.doinWondrs.betterme.R;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class TypesOfWorkouts {

    public String typeWorkoutString;
    //1. sets should be more than 3,
    public ArrayList<Integer> sets;
    // 2. reps should be more ,
    public ArrayList<Integer> reps;
    // 3. jpg/images,
    public ArrayList<String> image;
    // 4. mp4/gif,
    public ArrayList<String> instructionVideo;
    // 5. Strings
    public ArrayList<String> excercise;


    public TypesOfWorkouts(String pWType)
    {
        buildWorkout(pWType);
    }




    //TODO: refactor for json format to amplify?
    public void weightliftingWorkouts(){
        this.sets.add(3);
        this.reps.add(10);
        this.image.add("anImageTest");
        this.instructionVideo.add("aVideoTest");
        this.excercise.add("firstWorkoutTest");
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
