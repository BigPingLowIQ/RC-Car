package org.firstinspires.ftc.teamcode.interfaces.mappings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;

public class SingleController implements IInputMapping {
    Gamepad gamepad1,gamepad2;

    public SingleController(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    @Override
    public boolean emergencyStop() {
        return false;
    }

    @Override
    public double motorsPower() {
        return 0;
    }
}
