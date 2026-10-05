/**
 * This Model Can move long distances in a zigzag patternt
 * While Shoot the enemies
 */

package test;

import robocode.*;
/**
 * Neptune by
 * @author Davyd Miller
 * @author Hisadora Faleiro
 */
import java.awt.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Neptune extends AdvancedRobot{
    
    boolean isFacingForward;
    double searchRadius;

    public void run() {
        //Colors
        setBodyColor(new Color(0, 255, 0));
        setGunColor(new Color(0, 255, 0));
        setRadarColor(new Color(0, 255, 0));
        setBulletColor(new Color(0, 255, 0));
        setScanColor(new Color (0, 255, 00));

        //Movement
        while(true) {
            setAhead(50000);
            isFacingForward = true;
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
        if(isFacingForward) {
            setBack(100);
            isFacingForward = false;
        } else {
            setAhead(100);
            isFacingForward = true;
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
        //New Aim system
        searchRadius = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

        if(e.getDistance() > 100) {
            setTurnGunRight(searchRadius);
            setFire(1);
            return;
        }
        setTurnGunRight(e.getBearing());
        setTurnRight(e.getDistance() - 100);
        setFire(2);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        reverse();
    }
}
