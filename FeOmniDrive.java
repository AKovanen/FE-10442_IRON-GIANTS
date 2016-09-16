// ALEKSI KOVANEN :: OMNI ROBOT MAIN DRIVE CLASS :: 16 / 9 / 16

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

// FeOmniDrive is the main class that drive station looks for when registering and executing the
// opmode, or operation mode. The code from the previous FeOmniCFair java program is referenced
// and coordinated through here, and this class is considered the program interface between
// the main code : FeOmniCFair, and the robot's robot controller itself.

public class FeOmniDrive extends OpMode
{
    // FeOmniCFair class instantiated under 'myRobotDrive'
    // with methods and functions for use in interface.
    FeOmniCFair myRobotDrive;

    @Override // <-- while this is not necessarily necessary, it is a good habit to have though.
    // code that is executed when program is initiated
    public void init()
    {   // program init process coordinating provided DcMotor instants' names w/ xml config file

        myRobotDrive = new FeOmniCFair(hardwareMap.dcMotor.get("up_drive"),
                hardwareMap.dcMotor.get("down_drive"),
                hardwareMap.dcMotor.get("left_drive"),
                hardwareMap.dcMotor.get("right_drive"));
    }
    @Override
    // code that is executed when the OpMode is running
    public void loop()
    {
        // reference to tankDrive void in FeRobotDrive class
        myRobotDrive.omnidrive(gamepad1);
    }
    @Override
    // code that is executed when OpMode is initially disabled
    public void stop()
    { }
}
