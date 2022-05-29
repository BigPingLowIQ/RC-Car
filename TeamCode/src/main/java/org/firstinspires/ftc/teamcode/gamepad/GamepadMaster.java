package org.firstinspires.ftc.teamcode.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadMaster {
    private GamepadEx gamepad1,gamepad2;

    public GamepadMaster(Gamepad gamepad1,Gamepad gamepad2) {
        this.gamepad1 = new GamepadEx(gamepad1);
        this.gamepad2 = new GamepadEx(gamepad2);
    }

    public Gamepad gamepad1() {
        return gamepad1.getGamepad();
    }

    public Gamepad gamepad2() {
        return gamepad2.getGamepad();
    }

    public GamepadEx gamepad1Down(){
        return gamepad1;
    }
    public GamepadEx gamepad2Down(){
        return gamepad2;
    }

}
