// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.feeder.Feeder;
import frc.robot.subsystems.floor.Floor;
import frc.robot.subsystems.intake.Intake;

public class RobotContainer {
  private final CommandXboxController driverController;
  private final CommandXboxController operatorController;

  private final Intake intake;
  private final Floor floor;
  private final Feeder feeder;

  public RobotContainer() {
    this.driverController = new CommandXboxController(RobotConstants.DRIVER_CONTROLLER_PORT);
    this.operatorController = new CommandXboxController(RobotConstants.OPERATOR_CONTROLLER_PORT);

    this.intake = new Intake();
    this.floor = new Floor();
    this.feeder = new Feeder();

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
