// ALEKSI KOVANEN :: BI DRIVE BASE ROBOT MAIN CODE :: 16 / 9 / 16

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class FeBiCFair
{
    private DcMotor leftmotor;
    private DcMotor rightmotor;

    public FeBiCFair(DcMotor right_drive, DcMotor left_drive)
    {
        leftmotor = left_drive;
        rightmotor = right_drive;

        rightmotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void bidrive(float lefty, float righty)
    {
        leftmotor.setPower(lefty/4);
        rightmotor.setPower(righty/4);
    }
    public void bidrive(Gamepad gamepad)
    {
        bidrive(gamepad.right_stick_y, gamepad.left_stick_y);
    }
}