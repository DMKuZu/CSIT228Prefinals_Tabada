package com.csit228.csit228_prefinalstabada.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentView implements Serializable {
    public List<Integer> score;

    public StudentView(List<Integer> score){
        this.score = new ArrayList<>(score);
    }
}
