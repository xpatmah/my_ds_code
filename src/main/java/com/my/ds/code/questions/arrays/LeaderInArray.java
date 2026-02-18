package com.my.ds.code.questions.arrays;

import java.util.ArrayList;
import java.util.List;

public class LeaderInArray {

    public static void main(String[] args){
        int[] array = {16,17,4,3,5,2};

        int max = array[array.length-1];

        List<Integer> leaders = new ArrayList<>();

        for(int i = array.length-1;i >= 0 ;i--){
            if(array[i] >= max){
                leaders.addFirst(array[i]);
                max = array[i];
            }
        }
        System.out.println(leaders);
    }
}
