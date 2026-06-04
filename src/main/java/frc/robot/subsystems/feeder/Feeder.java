package frc.robot.subsystems.feeder;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.Mode;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIOSim;
import frc.robot.lib.components.roller.RollerIOTalonFX;

public class Feeder extends SubsystemBase {
    private Roller roller;

    public Feeder() {
        this.roller = new Roller(RobotConstants.currentMode == Mode.REAL ? 
            new RollerIOTalonFX(FeederConstants.ROLLER_CONSTANTS) : new RollerIOSim(FeederConstants.ROLLER_CONSTANTS),
            "Feeder/Roller");
    }

    @Override
    public void periodic() {
        roller.periodic();
    }

    private Command runVoltage(Voltage voltage) {
        return runOnce(() -> roller.setVoltageGoal(voltage));
    }

    public Command runIntakeVoltage() {
        return runVoltage(FeederConstants.INTAKE_VOLTAGE);
    }

    public Command runOuttakeVoltage() {
        return runVoltage(FeederConstants.OUTTAKE_VOLTAGE);
    }

    public Command runShootingVoltage() {
        return runVoltage(FeederConstants.SHOOTING_VOLTAGE);
    }

    public Command stop() {
        return runVoltage(Volts.of(0));
    }
}
