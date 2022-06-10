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
    DcMotor motor;


    public DriveTrain(HardwareMap hm,IInputMapping mapping) {
        this.mapping = mapping;
        this.hm = hm;
        motor = hm.get(DcMotor.class,DriveTrainConfig.MOTOR);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void emergencyStop() {

    }

    private void setPower(double power){
        motor.setPower(power);
    }

    @Override
    public void loop() {
        if(mapping.speed()){
            setPower(mapping.motorsPower()+mapping.reversePower());
        }else {
            setPower(mapping.motorsPower() * DriveTrainConfig.POWER_MULTIPLIER + mapping.reversePower() * DriveTrainConfig.POWER_MULTIPLIER);
        }
    }
}

