package org.firstinspires.ftc.teamcode.interfaces.mappings;

import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;


public class DoubleController implements IInputMapping {
    GamepadMaster master;

    public DoubleController(GamepadMaster master) {

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
        return (master.gamepad1().left_stick_x+1)/2;
    }

    @Override
    public boolean steeringCalibrate() {
        return false;
    }
    @Override
    public double reversePower() {
        return 0;
    }

    @Override
    public boolean speed() {
        return false;
    }

    @Override
    public boolean nextTrack() {
        return false;
    }

    @Override
    public boolean previousTrack() {
        return false;
    }

    @Override
    public boolean playlist1() {
        return false;
    }

    @Override
    public boolean playlist2() {
        return false;
    }

    @Override
    public boolean playlist3() {
        return false;
    }

    @Override
    public boolean horn() {
        return false;
    }

    @Override
    public boolean togglePlay() {
        return false;
    }
}
