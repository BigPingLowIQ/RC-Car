package org.firstinspires.ftc.teamcode.interfaces.mappings;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    public static double Kp = 0.02;
    public static double Ki = 0;
    public static double Kd = 0.;
    public static double MOTOR_POWER = 0;
    private double initialAngle;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    private double steeringVal = 0;
    private Telemetry telemetry;
    private boolean init = true;

    public DragController(GamepadMaster master, HardwareMap hm, Telemetry telemetry) {
        this.master = master;
        this.hm = hm;
        this.telemetry = telemetry;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";

        imu = hm.get(BNO055IMU.class,"imu");
        imu.initialize(parameters);

        initialAngle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYZ, AngleUnit.RADIANS).firstAngle;

    }

    @Override
    public void loop() {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYZ, AngleUnit.RADIANS);
        if(init){initialAngle = angles.firstAngle;init = false;}

        telemetry.addData("initialAngle",initialAngle);
        telemetry.addData("currentAngle",angles.firstAngle);

        double output = -PIDControl(initialAngle,angles.firstAngle);

        telemetry.addData("output",output);

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
