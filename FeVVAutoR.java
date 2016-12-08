/*
 *      ALEKSI KOVANEN :: 10442 IRON GIANTS AUTONOMOUS PROGRAM (RED SIDE)
 *      :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *          The following is the code for the autonomous program for the FTC 10442
 *          team the Iron Giants. The program is meant to run the autonomous algorithm
 *          for the robot on the red side of the field in the FTC Velocity Vortex
 *          competition season 2016-2017.
 *          The ideals that went behind the software architecture in the autonomous program
 *          was modularity and abstraction in the form of a command architecture through which
 *          the robot would run its algorithm. The autonomous algorithm is designed as a
 *          series of motor and sensor commands that are read procedurally by the robot's
 *          computer, and executed by its micro controllers.
 *          The following is the source code for said program, with the program's linear
 *          operation mode method and loop and the command methods paired in the same
 *          class, all thoroughly commented for sufficient comprehension.
 *      :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *      UPDATE [ 22 / 11 / 16 ] ::
 *      ::::::::::::::::::::::::::
 *          At the time that this is being written, the command structure and overall program
 *          architecture has been finalized at the end of October, with the planning and
 *          experimental stages having taken place in mid October.
 *          Originally the idea with the dc motors would have been to utilize encoders to
 *          effective measure and pre calculate maneuvers that the robot would have to take
 *          as part of the algorithm.
 *          The motors that were originally used were Matrix motors, that had integrated encoders
 *          and thus provided a method with which the use of such encoders did not come into
 *          conflict with the rotational wheel drive base. However, these motors were cut out
 *          of the picture and replaced with smaller but more functionally effective regular
 *          dc motors.
 *          This meant that the use of the encoders was also put out of the picture, though
 *          the methods that were taken into effect due to this change were conspired before hand
 *          for the very case that encoders were available for use in the first competition.
 *          This method involved finding the rotational speed of the motors, in units of rounds
 *          per minute or rpm. Multiplying this by the circumference of the wheel would give
 *          the distance that that wheel traveled in one minute, and converting that rate to
 *          cycles per millisecond from cycles per minute, and multiplying that value by the amount
 *          of milliseconds that that motor would be activated in the autonomous algorithm would
 *          give me the distance that the robot would travel at that specified time. Alternatively,
 *          the quotient of the distance and cycle per millisecond would equal the time of
 *          execution needed for the motors.
 *          These same mathematics could also be exactly applied to the servos.
 *          Thus, in the absence of encoders this is the method that is being used to calculate the
 *          times during which the motors will be activated to cover measured distances.
 *      :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 */
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class FeVVAutoR extends LinearOpMode
{
    // ROBOT DEVICE MAP
    /*   drive-base
     *   ==========
     *      um ------- rm
     *       | c1   c2 |
     *       |         |
     *       | c3   c4 |
     *      dm ------- lm
     */
    // AUTONOMOUS LINEAR OP MODE PATH MAP    _____
    /*                                      |     |        Each square is ~23.25x~23.25 inches squared.
     *       +-.-.-.-.-.-.-.-.-.-.-+        |     |23.25   The hypotenuse is 32.8805 inches.
     *    0  | . . . . . . . . . . |        |_____|
     *    1  | . . . . . . . . . . |           23.25
     *    2  | . . . . ___ . _____b- <---- first color computation and appropriate button press
     *    3  | . . . ./   \./. . .|-
     *    4  | . . . .\___/k . . .|| <---- knock all while on path for autonomous
     *    5  | . . . . . .|. . . .b- <---- second color computation and appropriate button press
     *    6  | . . . . . .|. . . . -
     *    7  | . . . . . .*. . . . |
     *       +-.-.-.-.-.-.-.-.-.-.-+
     *        a b c d e f g h i j k
     */

    // DC MOTORS --------------
    private DcMotor upmotor;
    private DcMotor downmotor;
    private DcMotor leftmotor;
    private DcMotor rightmotor;
    private DcMotor buttonpresser;

    // SERVOS -----------------
    private Servo center1;
    private Servo center2;
    private Servo center3;
    private Servo center4;

    // SENSORS ----------------
    //private ColorSensor colorsensor;

    // COLORS SENSOR PROCESSING COMMAND METHOD
    /*public void clrsnr_detect()
    {
        // IF THE COLOR BLUE IS SENSED
        if(colorsensor.blue() >= 8)
        {
            buttonpresser.setPower(1.0);
        }
        // IF THE COLOR RED IS SENSED
        else
        {
            buttonpresser.setPower(-1.0);
        }
    }*/
    // SERVO RUN SCRIPT COMMAND METHOD
    public void run_servo(double position)  // universal servo position argument
    {
        // set the ranges of all the servos
        center1.scaleRange(0.0,1.0);
        center2.scaleRange(0.0,1.0);
        center3.scaleRange(0.0,1.0);
        center4.scaleRange(0.0,1.0);

        // all servos are given their unique position value that they run to
        center1.setPosition(position);
        center2.setPosition(1 - position);
        center3.setPosition(position);
        center4.setPosition(1 - position);
    }
    // DC MOTOR RUN SCRIPT COMMAND METHOD
    public void run_dcmotor(float power)
    {
        upmotor.setPower(-power);
        downmotor.setPower(-power);
        leftmotor.setPower(power);
        rightmotor.setPower(power);
    }
    @Override
    public void runOpMode() throws InterruptedException
    {

        boolean bLedOn = false;

        // DCMOTOR HARDWARE MAPPING ROUTES --------------------
        upmotor = hardwareMap.dcMotor.get("up_drive");
        downmotor = hardwareMap.dcMotor.get("down_drive");
        leftmotor = hardwareMap.dcMotor.get("left_drive");
        rightmotor = hardwareMap.dcMotor.get("right_drive");

        rightmotor.setDirection(DcMotor.Direction.REVERSE);
        downmotor.setDirection(DcMotor.Direction.REVERSE);

        // SERVO HARDWARE MAPPING ROUTES ----------------------
        center1 = hardwareMap.servo.get("center_one");
        center2 = hardwareMap.servo.get("center_two");
        center3 = hardwareMap.servo.get("center_three");
        center4 = hardwareMap.servo.get("center_four");

        // SENSOR HARDWARE MAPPING ROUTES ---------------------
        //colorsensor = hardwareMap.colorSensor.get("color_sensor");

        //colorsensor.enableLed(bLedOn);

        waitForStart();
        /*
        while(opModeIsActive())
        {
            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", colorsensor.alpha());
            telemetry.addData("Red  ", colorsensor.red());
            telemetry.addData("Green", colorsensor.green());
            telemetry.addData("Blue ", colorsensor.blue());

            telemetry.update();
        }
        */
        // WHEEL RADIUS --> 1.4 INCHES      CIRCUMFERENCE --> 8.7955 INCHES
        // DC MOTOR RPM --> 152 RPM         RATE --> 22.2819 INCHES/SEC
        // PROCEDURAL SCRIPT FOR LINEAR OP MODE USING COMMANDS
        // COMMANDS :
        //  FORWARD ---------> run_dcmotor([target_distance],[applied_motor_power])
        //  TURN (NUM) ------> run_servo([servo_position)
        //  REGISTER COLOR --> clrsnr_detect([read_sensor_value]);
        // BASE SCRIPT :
        //  0 > INIT
        //  1 > FORWARD >> DISTANCE --> 28.5 inches
        //  2 > SET 45 degrees
        //  3 > FORWARD >> DISTANCE --> 32.8805 inches
        //  4 > SET 45 degrees
        //  5 > FORWARD >> DISTANCE --> 46.5 inches
        //  6 > REGISTER COLOR
        //  7 > PRESS BUTTON OF COLOR
        //  8 > SET 90 degrees
        //  9 > FORWARD >> DISTANCE --> 46.5 inches
        // 10 > REGISTER COLOR
        // 11 > PRESS BUTTON OF COLOR
        // 12 > STOP
        //
        // TIME IS CALCULATED THROUGH THE USE OF THE MOTOR'S RESPECTIVE RPM'S COMBINED
        // WITH THE WHEEL'S CIRCUMFERENCE.

        // SET OF THE COMMANDS USED TO RUN THE DRIVE BASE MOTORS
        run_dcmotor(1);     // RUNS THE MOTOR AT MAXIMUM POWER
        sleep(1279);        // REPEATS PRIOR COMMAND FOR THIS DURATION OF MILLISECONDS
        run_dcmotor(0);     // AFTER TIME PERIOD HAS PASSED, CEASES POWER TRANSFER TO MOTORS

        run_servo(1.0);     // RUNS THE SERVO IN THE LEFT DIRECTION
        sleep(500);         // REPEATS PRIOR COMMAND FOR THIS DURATION OF MILLISECONDS
        run_servo(.5);      // AFTER THE PERIOD HAS PASSED, CEASES ROTATION OF SERVO MOTORS

        run_dcmotor(1);
        sleep(1475);
        run_dcmotor(0);

        run_servo(0.0);
        sleep(500);
        run_servo(.5);

        run_dcmotor(1);
        sleep(2086);
        run_dcmotor(0);

        run_servo(0.0);
        sleep(1000);
        run_servo(.5);

        run_dcmotor(1);
        sleep(2086);
        run_dcmotor(0);
    }
}