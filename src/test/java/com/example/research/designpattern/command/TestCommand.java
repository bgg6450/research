package com.example.research.designpattern.command;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestCommand {
    @Test
    void given_archerAttackCommand_when_executeByInvoker_then_success() {
        Archer archer = new Archer();
        Command archerAttackCommand = new ArcherAttackCommand(archer);

        Control control = new Control();
        control.setCommand(archerAttackCommand);
        String result = control.fight();

        assertThat(result).isEqualTo("bow");
    }

    @Test
    void given_archerDefenceCommand_when_executeByInvoker_then_success() {
        Archer archer = new Archer();
        Command archerDefenceCommand = new ArcherDefenceCommand(archer);

        Control control = new Control();
        control.setCommand(archerDefenceCommand);
        String result = control.fight();

        assertThat(result).isEqualTo("none");
    }

    @Test
    void given_swordManAttackCommand_when_executeByInvoker_then_success() {
        SwordMan swordMan = new SwordMan();
        Command swordManAttackCommand = new SwordManAttackCommand(swordMan);

        Control control = new Control();
        control.setCommand(swordManAttackCommand);
        String result = control.fight();

        assertThat(result).isEqualTo("sword");
    }

    @Test
    void given_swordManDefenceCommand_when_executeByInvoker_then_success() {
        SwordMan swordMan = new SwordMan();
        Command swordManDefenceCommand = new SwordManDefenceCommand(swordMan);

        Control control = new Control();
        control.setCommand(swordManDefenceCommand);
        String result = control.fight();

        assertThat(result).isEqualTo("shield");
    }
}