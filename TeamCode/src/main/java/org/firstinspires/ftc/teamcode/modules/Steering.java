package org.firstinspires.ftc.teamcode.modules;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.configs.SteeringConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

public class Steering implements IRobotModule {
    IInputMapping mapping;
    HardwareMap hm;
    Servo servo;

    public Steering(HardwareMap hm, IInputMapping mapping) {
        this.mapping = mapping;
        this.hm = hm;
        servo = hm.get(Servo.class, SteeringConfig.STEERING_SERVO);
    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public void loop() {

        servo.setPosition(applyBounds(SteeringConfig.RIGHT_LIMIT, SteeringConfig.LEFT_LIMIT,mapping.steering()));

    }

    private double applyBounds(double lower,double upper, double input){
        if(input==0.5)return SteeringConfig.STRAIGHT;
        return input*(upper-lower)+lower;
    }
}
