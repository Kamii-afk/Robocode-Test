/**
 * This model moves in a pattern, complete the entire moveset before shoot
 */

package test;

import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Lunaris - a robot by (your name here)
 */
public class Lunaris extends AdvancedRobot
{
	boolean movingForward;

	public void run() {

		while(true) {
			setAhead(100);
			movingForward = true;
			setTurnRight(100);
			setTurnGunRight(360);
			waitFor(new TurnCompleteCondition(this));
			setBack(100);
			waitFor(new TurnCompleteCondition(this));
			setTurnGunRight(360);
			setTurnLeft(200);
			waitFor(new TurnCompleteCondition(this));
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
		turnGunRight(10);
		turnLeft(20);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}

	public void onHitWall(HitWallEvent e) {
		reverseDirection();
	}

	public void reverseDirection() {
		if(movingForward) {
			setBack(200);
			movingForward = false;
		} else {
			setAhead(200);
			movingForward = true;
		}
	}
}
