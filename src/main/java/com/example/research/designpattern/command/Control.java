package com.example.research.designpattern.command;

public class Control {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public String fight() {
        return command.execute();
    }
}
