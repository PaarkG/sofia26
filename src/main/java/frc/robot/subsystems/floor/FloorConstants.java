package frc.robot.subsystems.floor;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.lib.components.roller.RollerConstants;

public class FloorConstants {
    public static final RollerConstants ROLLER_CONSTANTS 
        = new RollerConstants(57, CANBus.roboRIO(), false, 1.0,
            KilogramSquareMeters.of(0.04), DCMotor.getKrakenX60(1), 
                Volts.of(0.1), Amps.of(40), Seconds.of(0.1));

    public static final Voltage INTAKE_VOLTAGE = Volts.of(2);
    
    public static final Voltage OUTTAKE_VOLTAGE = Volts.of(-1);

    public static final Voltage SHOOTING_VOLTAGE = Volts.of(12);
}
