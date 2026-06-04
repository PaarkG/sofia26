package frc.robot.lib.components.roller;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.Voltage;

public class Roller {
    private RollerIO io;
    private RollerIOInputsAutoLogged inputs;

    private String logTopic;

    public Roller(RollerIO io, String logTopic) {
        this.io = io;
        this.inputs = new RollerIOInputsAutoLogged();

        this.logTopic = logTopic;
    }

    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs(logTopic, inputs);
    }

    public void setVoltageGoal(Voltage voltage) {
        io.setVoltageGoal(voltage);
    }

    public boolean atVoltageGoal() {
        return io.atVoltageGoal();
    }
}
