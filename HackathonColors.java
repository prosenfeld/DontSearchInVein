package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
/*
An attempt to use REV color sensors to find veins. It was unsuccessful. 
 */
@TeleOp
public class HackathonColors extends LinearOpMode {
    ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        waitForStart();
        while(opModeIsActive()){

            telemetry.addData("red",colorSensor.red());
            telemetry.addData("green",colorSensor.green());
            telemetry.addData("blue",colorSensor.blue());
            telemetry.update();

        }

    }
}
