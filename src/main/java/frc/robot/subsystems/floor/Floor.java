package frc.robot.subsystems.floor;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.Mode;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIOSim;
import frc.robot.lib.components.roller.RollerIOTalonFX;

public class Floor extends SubsystemBase {
    private Roller roller;

    public Floor() {
        this.roller = new Roller(RobotConstants.currentMode == Mode.REAL ? 
            new RollerIOTalonFX(FloorConstants.ROLLER_CONSTANTS) : new RollerIOSim(FloorConstants.ROLLER_CONSTANTS),
            "Floor/Roller");
    }

    @Override
    public void periodic() {
        roller.periodic();
    }

    private Command runVoltage(Voltage voltage) {
        return runOnce(() -> roller.setVoltageGoal(voltage));
    }

    public Command runIntakeVoltage() {
        return runVoltage(FloorConstants.INTAKE_VOLTAGE);
    }

    public Command runOuttakeVoltage() {
        return runVoltage(FloorConstants.OUTTAKE_VOLTAGE);
    }

    public Command runShootingVoltage() {
        return runVoltage(FloorConstants.SHOOTING_VOLTAGE);
    }

    public Command stop() {
        return runVoltage(Volts.of(0));
    }
}
