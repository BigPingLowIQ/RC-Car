package org.firstinspires.ftc.teamcode.opModes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.configs.DriveTrainConfig;
import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;
import org.firstinspires.ftc.teamcode.interfaces.mappings.DoubleController;
import org.firstinspires.ftc.teamcode.modules.DriveTrain;

import java.util.ArrayList;

@TeleOp(name="Main")
public class Main extends OpMode {
    FtcDashboard dash;

    ArrayList<IRobotModule> modules = new ArrayList<>();
    IInputMapping mapping;


    @Override
    public void init() {
        dash = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry,dash.getTelemetry());

        mapping = new DoubleController(new GamepadMaster(gamepad1,gamepad2));

        if(DriveTrainConfig.ENABLE_MODULE)
            modules.add(new DriveTrain(hardwareMap, mapping));

    }

    @Override
    public void loop() {
        if(mapping.emergencyStop())
            for(IRobotModule module:modules)
                module.emergencyStop();

        for(IRobotModule module:modules)
            module.loop();
    }
}
