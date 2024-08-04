package com.example.research.designpattern.command;

public class ArcherDefenceCommand implements Command {
    private final Archer archer;

    public ArcherDefenceCommand(Archer archer) {
        this.archer = archer;
    }

    @Override
    public String execute() {
        return archer.defense();
    }
}
