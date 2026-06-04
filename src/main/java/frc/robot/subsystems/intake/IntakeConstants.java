package frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.lib.components.roller.RollerConstants;

public class IntakeConstants {
    public static final RollerConstants TOP_ROLLER_CONSTANTS 
        = new RollerConstants(55, CANBus.roboRIO(), false, 1.0,
            KilogramSquareMeters.of(0.04), DCMotor.getKrakenX60(1), 
                Volts.of(0.1), Amps.of(40), Seconds.of(0.1));

    public static final RollerConstants BOTTOM_ROLLER_CONSTANTS
        = new RollerConstants(56, CANBus.roboRIO(), true, 1.0,
            KilogramSquareMeters.of(0.04), DCMotor.getKrakenX60(1), 
                Volts.of(0.1), Amps.of(40), Seconds.of(0.1));

    public static final Voltage TOP_INTAKE_VOLTAGE = Volts.of(4);
    public static final Voltage BOTTOM_INTAKE_VOLTAGE = Volts.of(8);

    public static final Voltage TOP_OUTTAKE_VOLTAGE = Volts.of(6);
    public static final Voltage BOTTOM_OUTTAKE_VOLTAGE = Volts.of(3);

    public static final Voltage TOP_SHOOTING_VOLTAGE = Volts.of(-3);
    public static final Voltage BOTTOM_SHOOTING_VOLTAGE = Volts.of(-3);
}
