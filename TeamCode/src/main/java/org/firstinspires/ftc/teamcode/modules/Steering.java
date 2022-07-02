package org.firstinspires.ftc.teamcode.modules;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.vuforia.Device;

import org.firstinspires.ftc.teamcode.configs.SteeringConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

public class Steering implements IRobotModule {
    IInputMapping mapping;
    HardwareMap hm;
//    Servo servo;
    DcMotor motor;
    private boolean isCalibrating = false;
    private double startPosition = 0;

    public Steering(HardwareMap hm, IInputMapping mapping) {
        this.mapping = mapping;
        this.hm = hm;
//        servo = hm.get(Servo.class, SteeringConfig.STEERING_SERVO);
//        servo.setDirection(Servo.Direction.REVERSE);

        motor = hm.get(DcMotor.class, SteeringConfig.STEERING_SERVO);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public void loop() {


        if(mapping.steeringCalibrate())
            isCalibrating=!isCalibrating;

        if(isCalibrating) {
            startPosition += mapping.steering() * 2;
            motor.setTargetPosition((int) (startPosition));
        }else {
            motor.setTargetPosition((int) (startPosition + SteeringConfig.STEPS_PER_REVOLUTION * SteeringConfig.STEERING_COEFF * mapping.steering()));
        }
        motor.setPower(1);
    }

    private double applyBounds(double lower,double upper, double input){

        return input*(upper-lower)+lower;
    }
}
