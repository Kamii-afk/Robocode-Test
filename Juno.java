/**
 * This Model
 */

package test;

import robocode.*;
/**
 * Juno by
 * @author Davyd Miller
 */
import java.awt.*;

public class Juno extends AdvancedRobot{

    double x, y, heading;
    double fieldWidth, fieldHeight;
    boolean stuck;

    int[] ang = {
        45,
        135,
        225,
        315
    };

    enum headFrom {
        NW,
        NE,
        SE,
        SW
    };
    
    boolean movingForward;

    public void run() {

        //colors
        setBodyColor(new Color(120, 50, 255));
        setGunColor(new Color(120, 50, 255));
        setRadarColor(new Color(120, 50, 255));
        setBulletColor(new Color(120, 50, 255));
        setScanColor(new Color (120, 50, 255));

        //Movement
        while(true) {
            setAhead(400);
            setTurnRight(145);
            waitFor(new TurnCompleteCondition(this));
            setBack(400);
            setTurnLeft(200);
            waitFor(new TurnCompleteCondition(this));
            setAhead(200);
            setTurnRight(180);
            waitFor(new TurnCompleteCondition(this));
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitRobot(HitRobotEvent e) {
        if(e.isMyFault() && movingForward == true) {
            setBack(100);
            movingForward = false;
        }else if(e.isMyFault() && movingForward == false) {
            setAhead(100);
            movingForward = true;
        }
    }
    
    public headFrom getDirection() {
        this.heading = getHeading();
        
        if(heading >= 0 && heading < 90) {
            return headFrom.NE;
        } else if (heading >= 90 && heading < 180) {
            return headFrom.SE;
        } else if (heading >= 180 && heading < 270) {
            return headFrom.SW;
        } else if (heading >= 270 && heading < 360) {
            return headFrom.NW;
        }

        return null;
    }

    public headFrom getPosition() {
        this.x = getX();
        this.y = getY();
        this.fieldWidth = getBattleFieldWidth();
        this.fieldHeight = getBattleFieldHeight();

        if(x < (fieldWidth / 2) && y < fieldHeight / 2) {
            return headFrom.SW;
        }else if (x < (fieldWidth / 2) && y > (fieldHeight / 2)) {
            return headFrom.NW;
        }else if (x > (fieldWidth / 2) && y > (fieldHeight / 2)) {
            return headFrom.NE;
        }else {
            return headFrom.SE;
        }
    }

    public void onHitWall(HitWallEvent e) {
        this.heading = getHeading();
        
        if(getPosition() == headFrom.NE) {
            if(getDirection() == headFrom.NE) {
                setBack(50);
                setTurnRight(heading - ang[0]);
                movingForward = false;
            } else {
                setTurnLeft(90);
                setAhead(100);
                movingForward = true;
            }
        }else if(getPosition() == headFrom.SE) {
            if(getDirection() == headFrom.SE) {
                setBack(50);
                setTurnLeft(heading - ang[1]);
                movingForward = false;
            }else {
                setTurnLeft(90);
                setAhead(100);
                movingForward = true;
            }
        }else if(getPosition() == headFrom.SW) {
            if(getDirection() == headFrom.SW) {
                setBack(50);
                setTurnRight(heading - ang[2]);
                movingForward = false;
            }else {
                setTurnLeft(90);
                setAhead(100);
                movingForward = true;
            }
        }else {
            if(getDirection() == headFrom.NW) {
                setBack(50);
                setTurnRight(heading - ang[3]);
                movingForward = false;
            }else {
                setTurnLeft(90);
                setAhead(100);
                movingForward = true;    
            }
        }
    }
}
