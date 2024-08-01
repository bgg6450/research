package com.example.research.designpattern.command;

public class DefenceCommand implements Command {
    private Soldier soldier;

    public DefenceCommand(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public void execute() {
        soldier.defense();
    }
}
