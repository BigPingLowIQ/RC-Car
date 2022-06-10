package org.firstinspires.ftc.teamcode.modules;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.configs.SteeringConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

public class Steering implements IRobotModule {
    IInputMapping mapping;
    HardwareMap hm;
//    Servo servo;
    DcMotor motor;

    public Steering(HardwareMap hm, IInputMapping mapping) {
        this.mapping = mapping;
        this.hm = hm;
//        servo = hm.get(Servo.class, SteeringConfig.STEERING_SERVO);
//        servo.setDirection(Servo.Direction.REVERSE);

        motor = hm.get(DcMotor.class, SteeringConfig.STEERING_SERVO);
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

//        servo.setPosition(applyBounds(SteeringConfig.RIGHT_LIMIT, SteeringConfig.LEFT_LIMIT,mapping.steering()));

        motor.setTargetPosition((int) (SteeringConfig.STEPS_PER_REVOLUTION * SteeringConfig.STEERING_COEFF * mapping.steering()));
        motor.setPower(1);
    }

    private double applyBounds(double lower,double upper, double input){

        return input*(upper-lower)+lower;
    }
}
