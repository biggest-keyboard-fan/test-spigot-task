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

    protected QuestService(){
        List<Quest<T,Q>> questList = Collections.unmodifiableList(loadQuests());
        Quest<T,Q> checkQuest = questList.size() > 0 ? questList.get(0) : null;
        if(checkQuest != null) for (Quest<T,Q> quest:questList) checkQuestTypeMatch(quest,checkQuest);
        this.questList = questList;
    }
    public void onShutdown(){
        saveQuests();
    }
    public void checkQuestTypeMatch(Quest<T,Q> a, Quest<T,Q> b){
        Class ca = a.getData().getClass();
        Object cb = b.getData();
        if(!ca.isInstance(cb))
            throw new InvalidDataType("Quest's "+ca.getName()+" doesn't match "+cb.getClass().getName()+" type.");
        ca = a.getFirstTask().getData().getClass();
        cb = b.getFirstTask().getData();
        if(!ca.isInstance(cb))
            throw new InvalidDataType("Quest's task data "+ca.getName()+" doesn't match "+cb.getClass().getName()+" data type.");
    }
    private List<Quest<T,Q>> questList; public List<Quest<T,Q>> getQuestList(){return questList;}
    @SafeVarargs
    public final void AddNewQuests(Quest<T,Q>... quests) throws InvalidDataType{
        List<Quest<T,Q>> questList = new ArrayList<>(this.questList);
        Quest<T,Q> checkQuest = questList.size() > 0 ? questList.get(0) : quests[0];
        for (Quest<T,Q> quest:quests)
            checkQuestTypeMatch(checkQuest,quest);

        questList.addAll(Arrays.asList(quests));
        this.questList = Collections.unmodifiableList(questList);
    }
}
