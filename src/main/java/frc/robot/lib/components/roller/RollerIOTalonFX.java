package frc.robot.lib.components.roller;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.OpenLoopRampsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Voltage;

public class RollerIOTalonFX implements RollerIO {
    private RollerConstants constants;

    private TalonFX motor;

    private VoltageOut voltageRequest;

    public RollerIOTalonFX(RollerConstants constants) {
        this.constants = constants;

        this.motor = new TalonFX(constants.canId(), constants.canBus());

        TalonFXConfiguration config = new TalonFXConfiguration()
            .withCurrentLimits(new CurrentLimitsConfigs()
                .withSupplyCurrentLimit(constants.supplyCurrentLimit()))
            .withMotorOutput(new MotorOutputConfigs()
                .withInverted(constants.inverted() ? InvertedValue.CounterClockwise_Positive : InvertedValue.Clockwise_Positive)
                .withNeutralMode(NeutralModeValue.Brake))
            .withFeedback(new FeedbackConfigs()
                .withSensorToMechanismRatio(constants.gearRatio()))
            .withOpenLoopRamps(new OpenLoopRampsConfigs()
                .withVoltageOpenLoopRampPeriod(constants.openLoopRampPeriod()));

        this.motor.getConfigurator().apply(config);
    }

    @Override
    public void updateInputs(RollerIOInputs inputs) {
        inputs.appliedVoltage = motor.getMotorVoltage().getValue();
        inputs.voltageGoal = voltageRequest.getOutputMeasure();

        inputs.position = motor.getPosition().getValue();
        inputs.velocity = motor.getVelocity().getValue();

        inputs.supplyCurrent = motor.getSupplyCurrent().getValue();
        inputs.torqueCurrent = motor.getTorqueCurrent().getValue();

        inputs.temperature = motor.getDeviceTemp().getValue();
    }

    @Override
    public void setVoltageGoal(Voltage goal) {
        motor.setControl(voltageRequest.withOutput(goal));
    }

    @Override
    public boolean atVoltageGoal() {
        return Math.abs(voltageRequest.getOutputMeasure().baseUnitMagnitude() - motor.getMotorVoltage().getValueAsDouble()) 
            < constants.voltageGoalTolerance().baseUnitMagnitude();
    }
}