package frc.robot.lib.components.roller;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Seconds;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.units.measure.Voltage;

public class RollerIOSparkMax implements RollerIO {
    private RollerConstants constants;

    private SparkMax motor;

    private Voltage voltageGoal;

    public RollerIOSparkMax(RollerConstants constants) {
        this.constants = constants;
        
        this.motor = new SparkMax(constants.canId(), MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();

        config.encoder.positionConversionFactor(constants.gearRatio());
        config.openLoopRampRate(constants.openLoopRampPeriod().in(Seconds));
        config.smartCurrentLimit((int) constants.supplyCurrentLimit().baseUnitMagnitude());
        config.inverted(constants.inverted());
        config.idleMode(IdleMode.kBrake);

        this.motor.configureAsync(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        this.voltageGoal = Volts.of(0);
    }

    @Override
    public void updateInputs(RollerIOInputs inputs) {
        inputs.appliedVoltage = Volts.of(motor.getBusVoltage());
        inputs.voltageGoal = voltageGoal;

        inputs.position = Rotations.of(motor.getEncoder().getPosition());
        inputs.velocity = RotationsPerSecond.of(motor.getEncoder().getVelocity());

        inputs.supplyCurrent = Amps.of(motor.getOutputCurrent());
        inputs.torqueCurrent = Amps.of(motor.getOutputCurrent());

        inputs.temperature = Celsius.of(motor.getMotorTemperature());
    }

    @Override
    public void setVoltageGoal(Voltage goal) {
        this.voltageGoal = goal;

        motor.setVoltage(voltageGoal);
    }

    @Override
    public boolean atVoltageGoal() {
        return Math.abs(voltageGoal.baseUnitMagnitude() - motor.getBusVoltage())
            < constants.voltageGoalTolerance().baseUnitMagnitude();
    }
}
