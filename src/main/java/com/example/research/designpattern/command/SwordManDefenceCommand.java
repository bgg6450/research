package com.example.research.designpattern.command;

public class SwordManDefenceCommand implements Command {
    private final SwordMan swordMan;

    public SwordManDefenceCommand(SwordMan swordMan) {
        this.swordMan = swordMan;
    }

    @Override
    public String execute() {
        return swordMan.defense();
    }
}
