package frc.robot.lib.components.roller;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;

public interface RollerIO {
    @AutoLog
    public static class RollerIOInputs {
        public Voltage appliedVoltage;
        public Voltage voltageGoal;

        public Angle position;
        public AngularVelocity velocity;

        public Current supplyCurrent;
        public Current torqueCurrent;

        public Temperature temperature;
    }

    public void updateInputs(RollerIOInputs inputs);

    public void setVoltageGoal(Voltage goal);

    public boolean atVoltageGoal();
}
