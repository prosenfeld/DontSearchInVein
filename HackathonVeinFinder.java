package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class HackathonVienFinder extends LinearOpMode {
    DcMotor xLeft, xRight, yMotor;
    Servo marker;
    double x = 0;
    double y = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        hardwareSetup();
        waitForStart();
        while(opModeIsActive()){

            if(gamepad1.dpad_up){
                xLeft.setPower(0.3);
                xRight.setPower(0.3);
            } else if(gamepad1.dpad_down){
                xLeft.setPower(-0.3);
                xRight.setPower(-0.3);
            } else if(gamepad1.x){
                xLeft.setPower(0.3);
            } else if(gamepad1.y){
                xRight.setPower(0.3);
            } else {
                xLeft.setPower(0);
                xRight.setPower(0);
            }

            if(gamepad1.dpad_left){
                yMotor.setPower(0.3);
            } else if (gamepad1.dpad_right){
                yMotor.setPower(-0.3);
            } else {
                yMotor.setPower(0);
            }

            telemetry.addData("x",xLeft.getCurrentPosition());
            telemetry.addData("y",yMotor.getCurrentPosition());
            telemetry.update();

        }

    }
    public void hardwareSetup(){
        xLeft = hardwareMap.dcMotor.get("xLeft");
        xRight = hardwareMap.dcMotor.get("xRight");
        yMotor = hardwareMap.dcMotor.get("yMotor");

        xLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        xRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        xLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        yMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        xLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        xRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        yMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        xLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        xRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        yMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void goToPos(int xPos, int yPos){
        xLeft.setTargetPosition(xPos);
        xRight.setTargetPosition(xPos);
        yMotor.setTargetPosition(yPos);

        xLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        xRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        yMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (xLeft.isBusy() && yMotor.isBusy() && xRight.isBusy()){
            idle();
        }
        
        xLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        yMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        xRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        xLeft.setPower(0);
        xRight.setPower(0);
        yMotor.setPower(0);

    }
}
