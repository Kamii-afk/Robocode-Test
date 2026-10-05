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
    boolean nowReverse = true;

    int[] ang = {
        225,
        315,
        45,
        135
    };

    enum headFrom {
        NW,
        NE,
        SE,
        SW
    };
    
    boolean isFacingForward;

    public void run() {

        //colors
        setBodyColor(new Color(120, 50, 255));
        setGunColor(new Color(120, 50, 255));
        setRadarColor(new Color(120, 50, 255));
        setBulletColor(new Color(120, 50, 255));
        setScanColor(new Color (120, 50, 255));

        //Movement
        while(true) {
            if(nowReverse) {
                setAhead(50000);
                isFacingForward = true;
            }else {
                setBack(50000);
                isFacingForward = false;
            }
            setTurnRight(90);
            setTurnGunLeft(360);
            waitFor(new TurnCompleteCondition(this));
            setTurnLeft(180);
            setTurnGunRight(360);
            waitFor(new TurnCompleteCondition(this));
            setTurnRight(180);
            setTurnGunLeft(360);
            waitFor(new TurnCompleteCondition(this));
            setTurnLeft(90);
            waitFor(new TurnCompleteCondition(this));
            nowReverse = !nowReverse;
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    public void onHitRobot(HitRobotEvent e) {
        if(e.isMyFault() && isFacingForward == true) {
            setBack(100);
            isFacingForward = false;
        }else if(e.isMyFault() && isFacingForward == false) {
            setAhead(100);
            isFacingForward = true;
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

    public double calcTurn(double target) {
        this.heading = getHeading();
        double turn = target - heading;

        if(turn > 180) {
            turn -= 360;
        }else if(turn < -180) {
            turn += 360;
        }

        return turn;
    }

    public void onHitWall(HitWallEvent e) {
        if(getPosition() == headFrom.NE) {
            if(getDirection() == headFrom.NE) {
                back(200);
                setTurnRight(calcTurn(ang[0]));
                isFacingForward = false;
            } else {
                setTurnLeft(90);
                setAhead(100);
                isFacingForward = true;
            }
        }else if(getPosition() == headFrom.SE) {
            if(getDirection() == headFrom.SE) {
                back(200);
                setTurnRight(calcTurn(ang[1]));
                isFacingForward = false;
            }else {
                setTurnLeft(90);
                setAhead(100);
                isFacingForward = true;
            }
        }else if(getPosition() == headFrom.SW) {
            if(getDirection() == headFrom.SW) {
                back(200);
                setTurnLeft(calcTurn(ang[2]));
                isFacingForward = false;
            }else {
                setTurnLeft(90);
                setAhead(100);
                isFacingForward = true;
            }
        }else {
            if(getDirection() == headFrom.NW) {
                back(200);
                setTurnLeft(calcTurn(ang[3]));
                isFacingForward = false;
            }else {
                setTurnLeft(90);
                setAhead(100);
                isFacingForward = true;    
            }
        }
    }
}
