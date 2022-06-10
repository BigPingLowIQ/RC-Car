package org.firstinspires.ftc.teamcode.configs;

import com.acmerobotics.dashboard.config.Config;

@Config
public class SteeringConfig {
    public static boolean ENABLE_MODULE = true;

    public static String STEERING_SERVO = "steer-servo";

    public static double RIGHT_LIMIT = .32;
    public static double LEFT_LIMIT = 0.6;
    public static double STRAIGHT = 0.46;

    public static double STEERING_COEFF = 0.15;
    public static double STEPS_PER_REVOLUTION = 3895.9;

}
