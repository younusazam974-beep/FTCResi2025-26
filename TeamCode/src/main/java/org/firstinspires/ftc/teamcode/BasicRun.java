package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "8194 Teleop", group = "8194")
public class BasicRun extends LinearOpMode {
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







    public void runOpMode() throws InterruptedException {
//        try {
        //Telemetry Initialization ################################################################
        telemetry.setAutoClear(false);

        Telemetry.Item timeVal = telemetry.addData("Elapsed Time:", runtime);
        //------------------------------------------------------
        Telemetry.Item SPACER1 = telemetry.addData("---------------------", "-");
        //------------------------------------------------------
        Telemetry.Item G1LYText = telemetry.addData("G1 LY Val:", "-");
        Telemetry.Item G1LXText = telemetry.addData("G1 LX Val:", "-");
        Telemetry.Item G1RXText = telemetry.addData("G1 RX Val:", "-");
        //------------------------------------------------------
        Telemetry.Item SPACER5 = telemetry.addData("---------------------", "-");
        //------------------------------------------------------
        Telemetry.Item G2LYText = telemetry.addData("G2 LY Val:", "-");
        Telemetry.Item G2RYText = telemetry.addData("G2 RY Val:", "-");
        Telemetry.Item G2LTText = telemetry.addData("G2 LTrigger Val:", "-");
        Telemetry.Item G2RTText = telemetry.addData("G2 RTrigger Val:", "-");

        //##########################################################################################

        //Motor Initialization #####################################################################
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

        intakeWheelOne = hardwareMap.get(DcMotorEx.class, "intakeWheelOne");
        intakeWheelOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeWheelOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeWheelOne.setDirection(DcMotorEx.Direction.REVERSE);

        intakeWheelTwo = hardwareMap.get(DcMotorEx.class, "intakeWheelTwo");
        intakeWheelTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeWheelTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeWheelTwo.setDirection(DcMotorEx.Direction.REVERSE);

        outtakeWheelLeft = hardwareMap.get(DcMotorEx.class, "outtakeWheelLeft");
        outtakeWheelLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeWheelLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeWheelLeft.setDirection(DcMotorEx.Direction.FORWARD);

        outtakeWheelRight = hardwareMap.get(DcMotorEx.class, "outtakeWheelRight");
        outtakeWheelRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeWheelRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeWheelRight.setDirection(DcMotorEx.Direction.REVERSE);

        telemetry.addData("Status:", "Initialized");
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            // Velocity Vars
            double leftBackVelocity = leftBackWheel.getVelocity();
            double rightBackVelocity = rightBackWheel.getVelocity();
            double leftFrontVelocity = leftFrontWheel.getVelocity();
            double rightFrontVelocity = rightFrontWheel.getVelocity();

            //Inputs
            double G1LY = -gamepad1.left_stick_y;
            double G1LX = gamepad1.left_stick_x;
            double G1RX = gamepad1.right_stick_x;

            double G2LY = -gamepad2.left_stick_y;
            double G2RY = -gamepad2.right_stick_y;
            double lTrigger = gamepad1.left_trigger;
            double rTrigger = gamepad1.right_trigger;


            //Target Setting;
            double denominator = Math.max(Math.abs(G1LY) + Math.abs(G1LX) + Math.abs(G1RX), 1);
            double frontLeftWheelPower = (G1LY + G1LX + G1RX) / denominator;
            double backLeftWheelPower = (G1LY - G1LX + G1RX) / denominator;
            double frontRightWheelPower = (G1LY - G1LX - G1RX) / denominator;
            double backRightWheelPower = (G1LY + G1LX - G1RX) / denominator;
            double intakeWheelOnePower = lTrigger;
            double outtakeWheelLeftPower = rTrigger;
            double outtakeWheelRightPower = rTrigger;

            //Power Setting
            leftBackWheel.setPower(backLeftWheelPower);
            rightBackWheel.setPower(backRightWheelPower);
            leftFrontWheel.setPower(frontLeftWheelPower);
            rightFrontWheel.setPower(frontRightWheelPower);
            intakeWheelOne.setPower(intakeWheelOnePower);
            outtakeWheelLeft.setPower(outtakeWheelLeftPower);
            outtakeWheelRight.setPower(outtakeWheelRightPower);

            if (gamepad1.x) {
                intakeWheelTwo.setPower(1);
            }

            if (gamepad1.y) {
                intakeWheelTwo.setPower(0);
            }



            //Telemetry Updates
            timeVal.setValue(getRuntime());
            G1LYText.setValue(G1LY);
            G1LXText.setValue(G1LX);
            G1RXText.setValue(G1RX);

            G2LYText.setValue(G2LY);
            G2RYText.setValue(G2RY);
            G2RTText.setValue(rTrigger);
            G2LTText.setValue(lTrigger);

            telemetry.update();

        }
//        } catch (Exception ignored) {


    }
}
