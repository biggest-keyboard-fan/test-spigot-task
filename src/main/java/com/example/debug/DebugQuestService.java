package com.example.debug;

import com.example.quests.Quest;
import com.example.quests.QuestService;

import java.util.ArrayList;
import java.util.List;

public class DebugQuestService extends QuestService<TaskDataExample,QuestDataExample> {
    public DebugQuestService(){
        System.out.print("DebugQuestService is initialized");
    }
    @Override
    protected void saveQuests() {
        System.out.print("Saving "+this.getQuestList().size()+" quests.");
    }

    @Override
    protected List<Quest<TaskDataExample,QuestDataExample>> loadQuests() {
        System.out.print("Loading quests..");
        return new ArrayList<>();
    }
}
