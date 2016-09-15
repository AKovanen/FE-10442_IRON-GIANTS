package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
// aleksi kovanen :: 8 / 9 / 16
// NOTE : right drive is plugged into p6 , left is plugged into p5
// NOTE : joy 1 = left analog stick , joy 2 = right analog stick

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
        myRobotDrive.omnidrive(gamepad1);
    }
    @Override
    public void stop()
    { }
}
