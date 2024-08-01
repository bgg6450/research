package com.example.research.designpattern.command;

public class AttackCommand implements Command {
    private Soldier soldier;

    public AttackCommand(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public void execute() {
        soldier.attack();
    }
}
