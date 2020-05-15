package com.example.debug;

public class TaskDataExample{
    private int xpReward; public int getXpReward(){return xpReward;}
    public TaskDataExample(int xpReward){
        this.xpReward = xpReward;
    }

    @Override
    public String toString() {
        return "XP Reward: "+xpReward;
    }
}