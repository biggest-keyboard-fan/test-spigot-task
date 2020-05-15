package com.example.quests;


import java.util.Collections;
import java.util.List;

public class Quest<T,Q> {
    private List<Task<T>> tasks; public Task<T> getFirstTask(){ return tasks.get(0); }
    private Q data; public Q getData(){ return data; }
    protected Quest(List<Task<T>> tasks, Q data){
        this.tasks = Collections.unmodifiableList(tasks);
        this.data = data;
    }

    @Override
    public String toString() {
        return "Quest"
                +" Data: ["+data+"]"
                +" First task: "+getFirstTask();
    }
}
