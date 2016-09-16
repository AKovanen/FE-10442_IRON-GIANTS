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
    }
    // primary function of program that routes right analog stick input values to motor power
    public void omnidrive(float righty, float rightx)
    {
        // The following requires this diagram of the drive base for easier comprehension.
        //  U ------ R      U --> upward drive motor
        //  |        |      R --> right drive motor
        //  |        |      L --> left drive motor
        //  |        |      D --> down drive motor
        //  L ------ D
        // righty and rightx are variables used to communicate input values from the 'y' and 'x'
        // analog stick rotational axises respectively as registered by drive station in gamepad.
        // The maths and syntax involved is self explanatory of what is being done.
        if(righty < 0)
        {
            rightmotor.setDirection(DcMotor.Direction.REVERSE);
            downmotor.setDirection(DcMotor.Direction.REVERSE);  }
        if(righty > 0)
        {
            upmotor.setDirection(DcMotor.Direction.REVERSE);
            leftmotor.setDirection(DcMotor.Direction.REVERSE);  }
        if(rightx < 0)
        {
            upmotor.setDirection(DcMotor.Direction.REVERSE);
            rightmotor.setDirection(DcMotor.Direction.REVERSE); }
        if(rightx > 0)
        {
            downmotor.setDirection(DcMotor.Direction.REVERSE);
            leftmotor.setDirection(DcMotor.Direction.REVERSE);  }

        // motors relevant to the 'left' side of the robot
        upmotor.setPower(righty);
        leftmotor.setPower(rightx);
        // motors relevant to the 'right' side of the robot
        downmotor.setPower(righty);
        rightmotor.setPower(rightx);
    }
    // source function that routes controller input to previously mentioned omnidrive function
    public void omnidrive(Gamepad gamepad)
    {   // values-to-look-for from gamepad input values laid out to program run time
        omnidrive(gamepad.right_stick_y,gamepad.right_stick_x);
    }
}
