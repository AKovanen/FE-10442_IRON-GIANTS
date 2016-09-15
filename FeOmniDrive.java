package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
// aleksi kovanen :: 8 / 9 / 16
// NOTE : right drive is plugged into p6 , left is plugged into p5
// NOTE : joy 1 = left analog stick , joy 2 = right analog stick

public class FeOmniDrive extends OpMode
{
    FeOmniCFair myRobotDrive;

    @Override
    public void init()
    {
        /*
            "left" and "right" are references to the left and right aliases
            that were assigned to the leftMotor and rightMotor DcMotor types
            respectively in the FeRobotDrive.java class
        */
        myRobotDrive = new FeOmniCFair(hardwareMap.dcMotor.get("up_drive"),
                hardwareMap.dcMotor.get("down_drive"),
                hardwareMap.dcMotor.get("left_drive"),
                hardwareMap.dcMotor.get("right_drive"));
    }
    @Override
    /* code that is executed when the OpMode is running */
    public void loop()
    {
        /* reference to tankDrive void in FeRobotDrive class */
        myRobotDrive.omnidrive(gamepad1);
    }
    @Override
    /* code that is executed when OpMode is initially disabled */
    public void stop()
    { }
}
