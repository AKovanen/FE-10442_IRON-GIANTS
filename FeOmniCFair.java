// ALEKSI KOVANEN :: OMNI ROBOT MAIN PROGRAM :: 16 / 9 / 16
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor; // import methods for DC Motors
import com.qualcomm.robotcore.hardware.Gamepad; // import methods for gamepad controls

public class FeOmniCFair
{
    // specific identifications for four dc motors set
    private DcMotor upmotor;    // program id for up drive motor
    private DcMotor downmotor;  // program id for down drive motor
    private DcMotor leftmotor;  // program id for left drive motor
    private DcMotor rightmotor; // program id for right drive motor

    // heart of program in which dcmotor instances are centralized and coordinated
    public FeOmniCFair(DcMotor up_drive, DcMotor down_drive,
                       DcMotor right_drive, DcMotor left_drive)
    {
        // Program motor ids coordinated with names in xml config file for robot.
        // Syntax should be self explanatory as to what motors are tied to which id's.
        upmotor = up_drive;
        downmotor = down_drive;
        leftmotor = left_drive;
        rightmotor = right_drive;

        downmotor.setDirection(DcMotor.Direction.REVERSE);
        leftmotor.setDirection(DcMotor.Direction.REVERSE);
    }
    // primary function of program that routes right and left analog sticks inputs
    public void omnidrive(float righty, float rightx, float lefty)
    {
        // The following requires this diagram of the drive base for easier comprehension.
        //  U ------ R      U --> upward drive motor
        //  |        |      R --> right drive motor
        //  |        |      L --> left drive motor
        //  |        |      D --> down drive motor
        //  L ------ D
        //
        // 20 / 9 / 16 :: omni bot controls explained
        // The controls are managed computationally in the form of the three floating point vars.
        //      - righty (read y axis value of the right analog stick)
        //      - rightx (read x axis value of the right analog stick)
        //      - lefty  (read y axis value of the left analog stick)
        //
        // righty and rightx are used to map power input to the down / up and left / right
        // DcMotors respectively. The values that are read and recorded to those respective
        // float variables, before being implemented through power input, is translated through
        // a sum operation with the lefty float variable, which enables the robot to spin on
        // an axis.
        // To allow this spin, the lefty variable value is either added or subtracted from the
        // given power value depending on which side :
        //      - if the top side of motors (up and right), then add lefty value
        //      - if the bottom side of motors (down and left), then subtract lefty value
        //
        // This addition or subtraction from the given power value at each motor depending on the
        // side allows for the smooth spinning.

        upmotor.setPower(righty+lefty);
        downmotor.setPower(righty-lefty);
        leftmotor.setPower(rightx-lefty);
        rightmotor.setPower(rightx+lefty);
    }
    // source function that routes controller input to previously mentioned omnidrive function
    public void omnidrive(Gamepad gamepad)
    {   // values-to-look-for from gamepad input values laid out to program run time
        omnidrive(gamepad.right_stick_y,gamepad.right_stick_x,gamepad.left_stick_y);
    }
}
