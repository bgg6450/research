package com.example.research.designpattern.command;

public class ArcherAttackCommand implements Command {
    private final Archer archer;

    public ArcherAttackCommand(Archer archer) {
        this.archer = archer;
    }

    @Override
    public String execute() {
        return archer.attack();
    }
}
