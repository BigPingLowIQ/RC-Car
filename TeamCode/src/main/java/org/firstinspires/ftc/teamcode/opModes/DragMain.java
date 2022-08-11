package org.firstinspires.ftc.teamcode.opModes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.configs.DriveTrainConfig;
import org.firstinspires.ftc.teamcode.configs.SteeringConfig;
import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;
import org.firstinspires.ftc.teamcode.interfaces.mappings.DragController;
import org.firstinspires.ftc.teamcode.interfaces.mappings.SingleController;
import org.firstinspires.ftc.teamcode.modules.DriveTrain;
import org.firstinspires.ftc.teamcode.modules.Steering;

import java.util.ArrayList;

@TeleOp(name="Drag Racing")
public class DragMain extends OpMode {
    FtcDashboard dash;

    ArrayList<IRobotModule> modules = new ArrayList<>();
    IInputMapping mapping;


    @Override
    public void stop() {

    }

    @Override
    public void init() {
        dash = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry,dash.getTelemetry());


        DragController dc = new DragController(new GamepadMaster(gamepad1,gamepad2),hardwareMap,telemetry);
        mapping = dc;

        if(DriveTrainConfig.ENABLE_MODULE)

        if(SteeringConfig.ENABLE_MODULE)
            modules.add(new Steering(hardwareMap,mapping));
        modules.add(dc);

    }

    @Override
    public void loop() {
        if(mapping.emergencyStopMapping())
            for(IRobotModule module:modules)
                module.emergencyStop();

        for(IRobotModule module:modules)
            module.loop();
    }
}
