package frc.robot.lib.components.roller;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class RollerIOSim implements RollerIO {
    private RollerConstants constants;

    private DCMotorSim motorSim;

    private Voltage voltageGoal;

    public RollerIOSim(RollerConstants constants) {
        this.constants = constants;

        this.motorSim = new DCMotorSim(
            LinearSystemId.createDCMotorSystem(constants.motor(), constants.momentOfInertia().baseUnitMagnitude(), 
            constants.gearRatio()), 
            constants.motor()
        );

        this.voltageGoal = Volts.of(0);
    }

    @Override
    public void updateInputs(RollerIOInputs inputs) {
        voltageGoal = Volts.of(MathUtil.clamp(voltageGoal.baseUnitMagnitude(), -12.0, 12.0));
        motorSim.setInputVoltage(voltageGoal.baseUnitMagnitude());

        inputs.appliedVoltage = Volts.of(motorSim.getInputVoltage());
        inputs.voltageGoal = voltageGoal;

        inputs.position = motorSim.getAngularPosition();
        inputs.velocity = motorSim.getAngularVelocity();

        inputs.supplyCurrent = Amps.of(motorSim.getCurrentDrawAmps());
        inputs.torqueCurrent = Amps.of(motorSim.getCurrentDrawAmps());

        inputs.temperature = Celsius.of(-1);
    }

    @Override
    public void setVoltageGoal(Voltage goal) {
        this.voltageGoal = goal;
    }


    @Override
    public boolean atVoltageGoal() {
        return Math.abs(this.voltageGoal.baseUnitMagnitude() - this.motorSim.getInputVoltage()) < constants.voltageGoalTolerance().baseUnitMagnitude();
    }
}