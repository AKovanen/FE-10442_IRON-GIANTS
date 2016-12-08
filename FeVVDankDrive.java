// ALEKSI KOVANEN :: DANK DRIVE :: 18 / 10 / 16
/*
 *      This is the primary class for the Dank Drive testing opmode for the robot.
 *      At the time that this original comment header block is being written,
 *      the base of the robot has just been built, and testing has been
 *      undertaken with the use of the following code.
 */

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class FeVVDankDrive extends OpMode
{
    Servo center_one;               // These are the four servos that are used to control the
    Servo center_two;               // orientation of the motors in the drive base.
    Servo center_three;
    Servo center_four;

    private DcMotor upmotor;        // These are the motors of the drive base which control the
    private DcMotor downmotor;      // direction and magnitude of the robot's motion.
    private DcMotor leftmotor;
    private DcMotor rightmotor;

    @Override
    public void init()
            // Program routes previously instantiated motor objects to their respective
            // addresses in the configuration file and robot's controller system.
    {
        center_one = hardwareMap.servo.get("center_one");
        center_two = hardwareMap.servo.get("center_two");
        center_three = hardwareMap.servo.get("center_three");
        center_four = hardwareMap.servo.get("center_four");

        upmotor = hardwareMap.dcMotor.get("up_drive");
        downmotor = hardwareMap.dcMotor.get("down_drive");
        leftmotor = hardwareMap.dcMotor.get("left_drive");
        rightmotor = hardwareMap.dcMotor.get("right_drive");

        downmotor.setDirection(DcMotor.Direction.REVERSE);
        leftmotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public void dankdrive(float leftval,      // input value from the left stick y axis motion
                          float rightval,     // input value from the right stick y axis motion
                          float leftt,        // input value from left trigger
                          float rightt)       // input value from right trigger
    {
        upmotor.setPower(rightval);         // motors route power values from the y position of
        rightmotor.setPower(rightval);      // the left and right analog sticks
        downmotor.setPower(leftval);
        leftmotor.setPower(leftval);

        center_one.scaleRange(0.0,1.0);     // ranges defined for the servo motors
        center_two.scaleRange(0.0,1.0);     // 1.0 defines right spin, 0.0 defines left spin
        center_three.scaleRange(0.0,1.0);
        center_four.scaleRange(0.0,1.0);

        double c1 = 0.5;                    // position for the first motor
        double c2 = 0.5;                    // second motor
        double c3 = 0.5;                    // third motor
        double c4 = 0.5;                    // fourth motor

        if(leftt > 0) {  c1 = c2 = 1.0; c3 = c4 = 0.0; }
        if(rightt > 0) { c1 = c2 = 0.0; c3 = c4 = 1.0; }

        center_one.setPosition(c1);         // servos set their positions to respective redefined
        center_two.setPosition(c2);         // position values
        center_three.setPosition(c3);
        center_four.setPosition(c4);
    }
    public void dankdrive(Gamepad gamepad1)
    {
        dankdrive(gamepad1.left_stick_y,        // analog sticks responsible for drive base motors
                    gamepad1.right_stick_y,
                    gamepad1.left_trigger,      // triggers turn the motors in unison
                    gamepad1.right_trigger);
    }
    @Override
    public void loop()
    {
        dankdrive(gamepad1);
    }
}