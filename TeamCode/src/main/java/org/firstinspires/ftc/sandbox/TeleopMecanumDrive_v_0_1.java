/*
 * Developer: Apar Mohabansi
 * Date: 10/10/2017
 */
package org.firstinspires.ftc.sandbox;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})

@TeleOp(name = "Concept: TeleopMecanumDrive_v_0_1", group = "Concept")
@Disabled
public class TeleopMecanumDrive_v_0_1 extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {
    // Declare our motors
    // Make sure your ID's match your configuration
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("FL");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("RL");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("FR");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("RR");

    // Reverse the right side motors
    // Reverse left motors if you are using NeveRests
    motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

    waitForStart();

    if (isStopRequested()) return;

    while (opModeIsActive()) {
      double y = -gamepad1.left_stick_y; // Remember, this is reversed!
      double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
      double rx = gamepad1.right_stick_x;

      // Denominator is the largest motor power (absolute value) or 1
      // This ensures all the powers maintain the same ratio, but only when
      // at least one is out of the range [-1, 1]
      double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
      double frontLeftPower = (y + x + rx) / denominator;
      double backLeftPower = (y - x + rx) / denominator;
      double frontRightPower = (y - x - rx) / denominator;
      double backRightPower = (y + x - rx) / denominator;

      motorFrontLeft.setPower(frontLeftPower);
      motorBackLeft.setPower(backLeftPower);
      motorFrontRight.setPower(frontRightPower);
      motorBackRight.setPower(backRightPower);
    }
  }
}