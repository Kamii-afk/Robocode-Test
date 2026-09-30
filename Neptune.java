/**
 * This Model Can move long distances in a zigzag patternt
 * While Shoot the enemies
 */

package test;

import robocode.*;
/**
 * Neptune by
 * @author Davyd Miller
 */
import java.awt.*;

public class Neptune extends AdvancedRobot{
    
    boolean movingForward;

    public void run() {
        //Colors
        setBodyColor(new Color(0, 255, 0));
        setGunColor(new Color(0, 255, 0));
        setRadarColor(new Color(0, 255, 0));
        setBulletColor(new Color(0, 255, 0));
        setScanColor(new Color (0, 255, 00));

        //Movement
        while(true) {
            setAhead(40000);
            movingForward = true;
            setTurnRight(90);
            setTurnGunRight(360);
            waitFor(new TurnCompleteCondition(this));
            setTurnGunLeft(360);
            setTurnLeft(180);
            waitFor(new TurnCompleteCondition(this));
            setTurnRight(180);
            waitFor(new TurnCompleteCondition(this));
        }
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
            reverse();
        } else {
            setTurnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        reverse();
    }
}
