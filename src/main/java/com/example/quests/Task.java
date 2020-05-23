package com.example.quests;

import com.example.exceptions.InvalidDataType;

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
    public Task(T data, String name, String description){
        this.data = data;
        this.name = name;
        this.description = description;
        nextTasks = Collections.unmodifiableList(new ArrayList<>());
    }
    @SafeVarargs
    public final void setNextTasks(Task<T>... nextTasks){
        for (Task<T> task:nextTasks)
            if(!task.getData().getClass().isInstance(data))
                throw new InvalidDataType("Task's data type "+task.getData().getClass().getName()+" doesn't match "+data.getClass().getName()+" type");
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
