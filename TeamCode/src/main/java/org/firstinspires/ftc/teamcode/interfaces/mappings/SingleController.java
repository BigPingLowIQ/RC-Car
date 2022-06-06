package org.firstinspires.ftc.teamcode.interfaces.mappings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;

public class SingleController implements IInputMapping {
    GamepadMaster master;

    public SingleController(GamepadMaster master) {
        this.master = master;
    }

    @Override
    public boolean emergencyStop() {
        return (master.gamepad1().start && master.gamepad1().a) || (master.gamepad2().start && master.gamepad2().a);
    }

    @Override
    public double motorsPower() {
        return master.gamepad1().right_trigger;
    }

    @Override
    public double steering() {
        return (master.gamepad1().left_stick_x+1)/2;
    }

    @Override
    public boolean toggleSong() {
        return master.gamepad1Down().bPressed();
    }
}
