package org.firstinspires.ftc.teamcode.interfaces.mappings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;

import java.security.Key;

public class DoubleController implements IInputMapping {
    GamepadMaster master;

    public DoubleController(GamepadMaster master) {

    }

    @Override
    public boolean emergencyStop() {
        return (master.gamepad1().start && master.gamepad1().a) || (master.gamepad2().start && master.gamepad2().a);
    }

    @Override
    public double motorsPower() {
        return master.gamepad1().right_trigger;
    }
}
