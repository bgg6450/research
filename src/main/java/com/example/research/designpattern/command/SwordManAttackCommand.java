package com.example.research.designpattern.command;

public class SwordManAttackCommand implements Command {
    private final SwordMan swordMan;

    public SwordManAttackCommand(SwordMan swordMan) {
        this.swordMan = swordMan;
    }

    @Override
    public String execute() {
        return swordMan.attack();
    }
}
