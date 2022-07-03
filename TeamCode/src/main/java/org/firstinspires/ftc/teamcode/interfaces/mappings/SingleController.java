package org.firstinspires.ftc.teamcode.interfaces.mappings;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;

import javax.crypto.MacSpi;

public class SingleController implements IInputMapping {
    GamepadMaster master;

    public SingleController(GamepadMaster master) {
        this.master = master;
    }

    @Override
    public boolean emergencyStopMapping() {
        return (master.gamepad1().start && master.gamepad1().a) || (master.gamepad2().start && master.gamepad2().a);
    }

    @Override
    public double motorsPower() {
        return master.gamepad1().right_trigger;
    }

    @Override
    public double steering() {
        return master.gamepad1().left_stick_x;
    }

    @Override
    public boolean steeringCalibrate() {return master.gamepad1Down().dPadUpPressed();}

    @Override
    public double reversePower() {
        return -master.gamepad1().left_trigger;
    }

    @Override
    public boolean speed() {
        return master.gamepad1().right_bumper;
    }

    @Override
    public boolean nextTrack() {
        return master.gamepad1Down().dPadRightPressed();
    }

    @Override
    public boolean previousTrack() {
        return master.gamepad1Down().dPadLeftPressed();
    }

    @Override
    public boolean playlist1() {
        return master.gamepad1Down().xPressed();
    }

    @Override
    public boolean playlist2() {
        return master.gamepad1Down().yPressed();
    }

    @Override
    public boolean playlist3() {
        return master.gamepad1Down().bPressed();
    }

    @Override
    public boolean horn() {
        return master.gamepad1Down().aPressed();
    }

    @Override
    public boolean togglePlay() {
        return master.gamepad1Down().dPadDownPressed();
    }
}
