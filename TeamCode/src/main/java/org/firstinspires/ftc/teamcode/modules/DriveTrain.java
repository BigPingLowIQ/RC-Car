package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.configs.DriveTrainConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

public class DriveTrain implements IRobotModule {
    IInputMapping mapping;
    HardwareMap hm;
    Motors motors;


    public DriveTrain(HardwareMap hm,IInputMapping mapping) {
        this.mapping = mapping;
        this.hm = hm;
        motors = new Motors(hm);

    }

    @Override
    public void emergencyStop() {

    }

    private void setPower(double power){
        motors.setPower(power);
    }

    @Override
    public void loop() {

    }
}

class Motors{
    HardwareMap hm;
    private DcMotor mfr;
    private DcMotor mfl;
    private DcMotor mbr;
    private DcMotor mbl;

    public Motors(HardwareMap hm) {
        this.hm = hm;
        mfr = hm.get(DcMotor.class, DriveTrainConfig.MOTOR_FRONT_RIGHT);
        mfl = hm.get(DcMotor.class, DriveTrainConfig.MOTOR_FRONT_LEFT);
        mbr = hm.get(DcMotor.class, DriveTrainConfig.MOTOR_BACK_RIGHT);
        mbl = hm.get(DcMotor.class, DriveTrainConfig.MOTOR_BACK_LEFT);
        mbr.setDirection(DcMotorSimple.Direction.REVERSE);
        mbl.setDirection(DcMotorSimple.Direction.REVERSE);

        mbr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setPower(double power){
        mfr.setPower(power);
        mfl.setPower(power);
        mbr.setPower(power);
        mbl.setPower(power);
    }

}
