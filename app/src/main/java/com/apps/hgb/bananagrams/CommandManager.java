package com.apps.hgb.bananagrams;

import java.util.Stack;

/**
 * Created by Hunt on 11/16/2014.
 */
public class CommandManager {

    private Stack<Command> commandStack;
    private GameData gameData;

    public CommandManager(Stack<Command> commands, GameData gameData)
    {
        this.commandStack = commands;
        this.gameData = gameData;
    }

    public void Back()
    {
        Command c = commandStack.pop();
        c.Backward();
    }
}
