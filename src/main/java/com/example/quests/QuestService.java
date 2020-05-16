package com.example.quests;

import com.example.exceptions.InvalidDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Хранит типы данных для заданий (T) и квестов (Q)
//С помощью этого сервиса создаются, изменяются и сохраняются новые квесты/задания
public abstract class QuestService<T,Q> {
    protected abstract void saveQuests();
    protected abstract List<Quest<T,Q>> loadQuests() throws InvalidDataType;

    protected QuestService(){ this.questList = Collections.unmodifiableList(loadQuests()); }

    public void onShutdown(){
        saveQuests();
    }

    private List<Quest<T,Q>> questList; public List<Quest<T,Q>> getQuestList(){return questList;}
    @SafeVarargs
    public final void AddNewQuests(Quest<T,Q>... quests){
        List<Quest<T,Q>> questList = new ArrayList<>(this.questList);
        questList.addAll(Arrays.asList(quests));
        this.questList = Collections.unmodifiableList(questList);
    }
    //https://stackoverflow.com/a/16381390
    @SafeVarargs
    public final Quest<T,Q> createNewQuest(Q data, Task<T>... tasks){
        return new Quest<>(Arrays.asList(tasks),data);
    }
    public Task<T> createNewTask(T data, String name, String description){
        return new Task<>(data, name, description);
    }
    @SafeVarargs
    public final void setNextTasks(Task<T> task, Task<T>... nextTasks){
        task.setNextTasks(nextTasks);
    }
}
