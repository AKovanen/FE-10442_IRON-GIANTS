package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class FeOmniCFair
{
    private DcMotor upmotor;
    private DcMotor downmotor;
    private DcMotor leftmotor;
    private DcMotor rightmotor;

    public FeOmniCFair(DcMotor up_drive, DcMotor down_drive,
                       DcMotor right_drive, DcMotor left_drive)
    {
        upmotor = up_drive;
        downmotor = down_drive;
        leftmotor = left_drive;
        rightmotor = right_drive;

        rightmotor.setDirection(DcMotor.Direction.REVERSE);
        downmotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void omnidrive(float lefty, float righty)
    {
        upmotor.setPower(lefty);
        leftmotor.setPower(lefty);

        downmotor.setPower(righty);
        rightmotor.setPower(righty);
    }
    public void omnidrive(Gamepad gamepad)
    {
        omnidrive(gamepad.right_stick_y, gamepad.left_stick_y);
    }
}
