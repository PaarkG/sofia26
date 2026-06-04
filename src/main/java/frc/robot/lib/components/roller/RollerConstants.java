package frc.robot.lib.components.roller;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.MomentOfInertia;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.units.measure.Voltage;

public record RollerConstants (
    int canId, 
    CANBus canBus,

    boolean inverted, 
    double gearRatio, 

    MomentOfInertia momentOfInertia, 
    DCMotor motor,

    Voltage voltageGoalTolerance,
    Current supplyCurrentLimit,

    Time openLoopRampPeriod
) {}