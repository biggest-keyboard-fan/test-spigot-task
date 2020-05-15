package com.example.quests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task<T> {
    private T data; public T getData(){ return data; }
    private String name; public String getName(){ return name; }
    private String description; public String getDescription(){ return description; }
    private List<Task<T>> nextTasks; public List<Task<T>> getNextTasks(){return nextTasks;}
    protected Task(T data, String name, String description){
        this.data = data;
        this.name = name;
        this.description = description;
        nextTasks = Collections.unmodifiableList(new ArrayList<>());
    }
    @SafeVarargs
    protected final void setNextTasks(Task<T>... nextTasks){
        this.nextTasks = Collections.unmodifiableList(Arrays.asList(nextTasks));
    }

    @Override
    public String toString() {
        boolean nonlinear = nextTasks.size() > 1;
        boolean isLast = nextTasks.size() == 0;
        return (nonlinear ? "\n" : "")+"Task "+name+
                //" Description: "+description+
                //" Data: "+data+
                ( !isLast ? " -> "+(nonlinear ? "\n     " : "")+ nextTasks.stream().map(Object::toString).collect(Collectors.joining("\n     ")) : "" );
    }
}
