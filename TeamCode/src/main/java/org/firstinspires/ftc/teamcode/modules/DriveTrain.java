package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.DriveTrainConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

public class DriveTrain implements IRobotModule {
    IInputMapping mapping;
    HardwareMap hm;
    DcMotor motor1;
    DcMotor motor2;
    Telemetry telemetry;

    public DriveTrain(HardwareMap hm, IInputMapping mapping, Telemetry telemetry) {
        this.mapping = mapping;
        this.hm = hm;
        this.telemetry = telemetry;
        motor1 = hm.get(DcMotor.class,DriveTrainConfig.MOTOR2);
//        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2 = hm.get(DcMotor.class,DriveTrainConfig.MOTOR1);
//        motor2.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void emergencyStop() {

    }

    private void setPower(double power,double multiplier){
        if(power>0.5 || power <-0.5) {
            motor1.setPower(0);
            motor2.setPower(power);
            telemetry.addData("motor",2);
            telemetry.addData("power",power);
        }else{
            motor1.setPower(power*2);
            motor2.setPower(0);
            telemetry.addData("motor",1);
            telemetry.addData("power",power);
        }


        //        motor1.setPower(power);
//        motor2.setPower(power);
    }

    @Override
    public void loop() {
        if(mapping.speed()){
            setPower(mapping.motorsPower()+mapping.reversePower(),1);
        }else {
            setPower(mapping.motorsPower() * DriveTrainConfig.POWER_MULTIPLIER + mapping.reversePower() * DriveTrainConfig.POWER_MULTIPLIER,DriveTrainConfig.POWER_MULTIPLIER);
        }
    }
}

