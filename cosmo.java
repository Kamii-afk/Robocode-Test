/**
 * This Model can move while shoot
 * He moves in a zigzag pattern
 * He inverte the current direction based on booleans conditions
 */

package test;

import robocode.*;/**
 * cosmo
 */
public class cosmo extends AdvancedRobot{

    boolean movingForward;

    public void run() {
        while(true) {
            setAhead(4000);
            movingForward = true;
            setTurnLeft(90);
            waitFor(new TurnCompleteCondition(this));
            setTurnRight(90);
            waitFor(new TurnCompleteCondition(this));
            setTurnLeft(180);
            waitFor(new TurnCompleteCondition(this));
            setTurnRight(180);
            waitFor(new TurnCompleteCondition(this));
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitWall(HitWallEvent e) {
        reverse();
    }

    public void reverse() {
        if(movingForward) {
            setBack(100);
            movingForward = false;
        } else {
            setAhead(100);
            movingForward = true;
        }
    }

    public void onHitRobot(HitRobotEvent e) {
        if(e.isMyFault()) {
            setBack(100);
        }
    }
}