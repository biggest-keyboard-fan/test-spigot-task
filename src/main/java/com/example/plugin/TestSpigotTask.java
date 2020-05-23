package com.example.plugin;

import com.example.debug.DebugQuestService;
import com.example.debug.QuestDataExample;
import com.example.debug.TaskDataExample;
import com.example.quests.Quest;
import com.example.quests.Task;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class TestSpigotTask extends JavaPlugin {
    private DebugQuestService questService; public DebugQuestService getQuestService(){return questService;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            switch (command.getName()){
                case "quest":
                    if(args.length > 0 && args[0].equals("add")){
                        Task[] tasks = new Task[5];
                        for (int i = 0; i < tasks.length; i++) {
                            tasks[i] = new Task(new TaskDataExample(50+i), "Linear"+i,"Description");
                            if(i>0) tasks[i].setNextTasks(tasks[i-1],tasks[i]);
                        }
                        //Linear quest (0 -> 1 -> 2 -> 3 -> 4 -> 5)
                        Quest quest1 = new Quest(Arrays.asList(tasks), new QuestDataExample("linear Quest"));
                        tasks = new Task[5];
                        for (int i = 0; i < tasks.length; i++)
                            tasks[i] = new Task(new TaskDataExample(50+i*2),"Nonlinear"+i,"Description");
                        tasks[0].setNextTasks(tasks[1],tasks[2]);
                        tasks[1].setNextTasks(tasks[3]);
                        tasks[2].setNextTasks(tasks[4]);

                        //non-linear quest
                        //(0 -> 1 ->
                        //         2 -> 4)
                        //         3 -> 5)
                        Quest quest2 = new Quest(Arrays.asList(tasks), new QuestDataExample("non-linear Quest"));
                        questService.AddNewQuests(quest1,quest2);

                        System.out.print("Added two quests.");
                        return true;
                    }
                    else if(args.length > 1 && args[0].equals("info")){
                        List<Quest<TaskDataExample, QuestDataExample>> questList = questService.getQuestList();
                        int questIndex = Integer.parseInt(args[1]);
                        if(questList.size() > questIndex) System.out.print("Quest("+questIndex+"): " + questService.getQuestList().get(questIndex) );
                        else System.out.print("Quest "+questIndex+" doesn't exist");
                        return true;
                    }
                    return false;
            }
        }
        return false;
    }
    @Override
    public void onEnable() {
        questService = DebugQuestService.getInstance();
    }

    @Override
    public void onDisable() {
        questService.onShutdown();
    }
}