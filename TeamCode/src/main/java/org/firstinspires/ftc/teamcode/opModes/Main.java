package org.firstinspires.ftc.teamcode.opModes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.configs.DriveTrainConfig;
import org.firstinspires.ftc.teamcode.configs.MusicConfig;
import org.firstinspires.ftc.teamcode.configs.SteeringConfig;
import org.firstinspires.ftc.teamcode.gamepad.GamepadMaster;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;
import org.firstinspires.ftc.teamcode.interfaces.mappings.SingleController;
import org.firstinspires.ftc.teamcode.modules.DriveTrain;
import org.firstinspires.ftc.teamcode.modules.Music;
import org.firstinspires.ftc.teamcode.modules.MusicController;
import org.firstinspires.ftc.teamcode.modules.Steering;

import java.util.ArrayList;

@TeleOp(name="Main")
public class Main extends OpMode {
    FtcDashboard dash;

    ArrayList<IRobotModule> modules = new ArrayList<>();
    IInputMapping mapping;
    Music music;


    @Override
    public void stop() {

    }

    @Override
    public void init() {
        dash = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry,dash.getTelemetry());

        mapping = new SingleController(new GamepadMaster(gamepad1,gamepad2));

        if(DriveTrainConfig.ENABLE_MODULE)
            modules.add(new DriveTrain(hardwareMap, mapping,telemetry));
        if(SteeringConfig.ENABLE_MODULE)
            modules.add(new Steering(hardwareMap,mapping));
        if(MusicConfig.ENABLE_MODULE) {
            modules.add(music = new Music(telemetry));
            modules.add(new MusicController(mapping,music));
        }

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
