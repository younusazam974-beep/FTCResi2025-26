package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "5709Auto", group = "Blue")
public class Auto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //Motor Declarations
    private DcMotorEx leftBackWheel = null;
    private DcMotorEx rightBackWheel = null;

    private DcMotorEx leftFrontWheel = null;
    private DcMotorEx rightFrontWheel = null;

    private DcMotorEx intakeWheelOne = null;
    private DcMotorEx intakeWheelTwo = null;
    
    private DcMotorEx outtakeWheelLeft = null;
    private DcMotorEx outtakeWheelRight = null;

    public void turn(double pow, int time){
        runtime.reset();
        while (runtime.seconds() < time) {
            leftBackWheel.setPower(pow);
            rightBackWheel.setPower(-pow);
            leftFrontWheel.setPower(pow);
            rightFrontWheel.setPower(-pow);
        }
        //neg = left, pos = right
    }

    public void moveStraight(double pow, int time){
        runtime.reset();
        while (runtime.seconds() < time) {
            leftBackWheel.setPower(pow);
            rightBackWheel.setPower(pow);
            leftFrontWheel.setPower(pow);
            rightFrontWheel.setPower(pow);
        }
    }

    public void stopBot(){
        leftBackWheel.setPower(0);
        rightBackWheel.setPower(0);
        leftFrontWheel.setPower(0);
        rightFrontWheel.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setAutoClear(false);

        Telemetry.Item timeVal = telemetry.addData("Elapsed Time:", runtime);
        //------------------------------------------------------
        Telemetry.Item SPACER1 = telemetry.addData("---------------------", "-");
        //------------------------------------------------------
        Telemetry.Item leftEncode = telemetry.addData("Left Encoder:", 0);
        Telemetry.Item rightEncode = telemetry.addData("Right Encoder:", 0);
        Telemetry.Item leftEncodef = telemetry.addData("Left Encoder (Front):", 0);
        Telemetry.Item rightEncodef = telemetry.addData("Right Encoder (Front):", 0);

        leftBackWheel = hardwareMap.get(DcMotorEx.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotorEx.class, "rightBackWheel");
        leftFrontWheel = hardwareMap.get(DcMotorEx.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotorEx.class, "rightFrontWheel");

        leftBackWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftBackWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBackWheel.setDirection(DcMotorEx.Direction.REVERSE);
        rightBackWheel.setDirection(DcMotorEx.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotorEx.Direction.FORWARD);
        leftFrontWheel.setDirection(DcMotorEx.Direction.REVERSE);

        outtakeWheelLeft = hardwareMap.get(DcMotorEx.class, "outtakeWheelLeft");
        outtakeWheelLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeWheelLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeWheelLeft.setDirection(DcMotorEx.Direction.FORWARD);

        outtakeWheelRight = hardwareMap.get(DcMotorEx.class, "outtakeWheelRight");
        outtakeWheelRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeWheelRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeWheelRight.setDirection(DcMotorEx.Direction.FORWARD);

        intakeWheelOne = hardwareMap.get(DcMotorEx.class, "intakeWheelOne");
        intakeWheelOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeWheelOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeWheelOne.setDirection(DcMotorEx.Direction.FORWARD);

        intakeWheelTwo = hardwareMap.get(DcMotorEx.class, "intakeWheelTwo");
        intakeWheelTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeWheelTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeWheelTwo.setDirection(DcMotorEx.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        moveStraight(0.5, 1);
        turn(0.5, 2);

    }
}
