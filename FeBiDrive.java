// ALEKSI KOVANEN :: BI ROBOT MAIN DRIVE PROGRAM :: 16 / 9 / 16

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class FeBiDrive extends OpMode
{
    FeBiCFair myRobotDrive;

    @Override
    public void init()
    {
        myRobotDrive = new FeBiCFair(hardwareMap.dcMotor.get("left_drive"),
                hardwareMap.dcMotor.get("right_drive"));
    }
    @Override
    public void loop()
    {
        myRobotDrive.bidrive(gamepad1);
    }
    @Override
    public void stop()
    { }
}
