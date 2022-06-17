package org.firstinspires.ftc.teamcode.interfaces.mappings;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

@Config
public class DragController implements IInputMapping, IRobotModule {
    private GamepadMaster master;
    private HardwareMap hm;
    private BNO055IMU imu;
    private Orientation angles;

    public static double integralSum = 0;
    public static double Kp = 0;
    public static double Ki = 0;
    public static double Kd = 0;
    public static double MOTOR_POWER = 0.3;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    private double steeringVal = 0;


    public DragController(GamepadMaster master, HardwareMap hm) {
        this.master = master;
        this.hm = hm;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";

        imu = hm.get(BNO055IMU.class,"imu");
        imu.initialize(parameters);

    }

    @Override
    public void loop() {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYZ, AngleUnit.DEGREES);

        double output = PIDControl(0,angles.firstAngle);

        if(output<-1)output = -1;
        if(output>1)output = 1;
        steeringVal = output;

    }

    private double PIDControl(double reference, double state){
        double error = reference - state;
        integralSum+=error*timer.seconds();
        double derivative = (error-lastError)*timer.seconds();
        lastError = error;
        timer.reset();

        return (error*Kp)+(derivative*Kd)+(integralSum*Ki);
    }

    @Override
    public boolean emergencyStopMapping() {
        return (master.gamepad1().start && master.gamepad1().b) || (master.gamepad2().start && master.gamepad2().b);
    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public double motorsPower() {
        return MOTOR_POWER;
    }

    @Override
    public double steering() {
        return steeringVal;
    }



    @Override
    public boolean toggleSong() {
        return false;
    }

    @Override
    public double reversePower() {
        return 0;
    }

    @Override
    public boolean toggleSong2() {
        return false;
    }

    @Override
    public boolean toggleSong3() {
        return false;
    }

    @Override
    public boolean toggleSong4() {
        return false;
    }

    @Override
    public boolean speed() {
        return false;
    }
}
