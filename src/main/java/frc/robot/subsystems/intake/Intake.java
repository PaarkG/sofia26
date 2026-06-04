package frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.RobotConstants.Mode;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIOSim;
import frc.robot.lib.components.roller.RollerIOTalonFX;

public class Intake extends SubsystemBase {
    private Roller topRoller, bottomRoller;

    public Intake() {
        this.topRoller = new Roller(RobotConstants.currentMode == Mode.REAL ? 
            new RollerIOTalonFX(IntakeConstants.TOP_ROLLER_CONSTANTS) : new RollerIOSim(IntakeConstants.TOP_ROLLER_CONSTANTS),
            "Intake/TopRoller");

        this.bottomRoller = new Roller(RobotConstants.currentMode == Mode.REAL ? 
            new RollerIOTalonFX(IntakeConstants.BOTTOM_ROLLER_CONSTANTS) : new RollerIOSim(IntakeConstants.BOTTOM_ROLLER_CONSTANTS),
            "Intake/BottomRoller");
    }

    @Override
    public void periodic() {
        topRoller.periodic();
        bottomRoller.periodic();
    }

    private Command runVoltage(Voltage topVoltage, Voltage bottomVoltage) {
        return runOnce(() -> {
            topRoller.setVoltageGoal(topVoltage);
            bottomRoller.setVoltageGoal(bottomVoltage);
        });
    }

    public Command runIntakeVoltage() {
        return runVoltage(IntakeConstants.TOP_INTAKE_VOLTAGE, IntakeConstants.BOTTOM_INTAKE_VOLTAGE);
    }

    public Command runOuttakeVoltage() {
        return runVoltage(IntakeConstants.TOP_OUTTAKE_VOLTAGE, IntakeConstants.BOTTOM_OUTTAKE_VOLTAGE);
    }

    public Command runShootingVoltage() {
        return runVoltage(IntakeConstants.TOP_SHOOTING_VOLTAGE, IntakeConstants.BOTTOM_INTAKE_VOLTAGE);
    }

    public Command stop() {
        return runVoltage(Volts.of(0), Volts.of(0));
    }
}
