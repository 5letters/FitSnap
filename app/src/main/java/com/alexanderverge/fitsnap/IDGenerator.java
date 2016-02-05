package com.alexanderverge.fitsnap;

import java.util.Arrays;

/**
 * Created by Wanjiakun on 2/21/2015.
 */
public class IDGenerator {

    public static int generateID(int[] existingIDs){
        Arrays.sort(existingIDs);
        int id = 0;
        boolean set = false;

        while(!set){
            set = true;
            for(int i: existingIDs){
                if(id == i){ //if id already exsits, then increment and check again
                    id++;
                    set = false;
                    break;
                }
            }
        }

        return id;
    }

}
