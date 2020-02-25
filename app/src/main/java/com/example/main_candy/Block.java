package com.example.main_candy;

import static java.lang.Math.*;

public class Block {
    int type;

    Block(){
        this.type = (int)(random()*6);


    }
    public int getType(){
        return this.type;
    }
    public void setType(int n){
        this.type = n;
    }



}
