package test;

import robocode.*;

public class cosmo extends AdvancedRobot{
    
    public void run() {

        while(true){
            setAhead(200);
            setTurnLeft(100);
            
        }
    }

    public void onHitByBullet() {
        setBack(20);
        setTurnLeft(50);
    }

    public void onHitWall() {
        back(100);
    }
}
