package com.example.debug;

public class QuestDataExample {
    private String name; public String getName(){ return name; }
    public QuestDataExample(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: "+name;
    }
}
