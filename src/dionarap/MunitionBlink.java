/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

/**
 *
 * @author Fabian
 */
public class MunitionBlink implements Runnable{

	Toolbar toolbar;
	private static final int BLINK_TIME = 300;

	public MunitionBlink(Toolbar toolbar) {
		this.toolbar = toolbar;
	}

        @Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			
			//Falls spieler Ammo holt
			if(Thread.interrupted()){
				toolbar.ammoBlink(false);
				break;
			}
			
			toolbar.ammoBlink(true);
			try {
				Thread.sleep(MunitionBlink.BLINK_TIME);
			} catch (InterruptedException e) {
				toolbar.ammoBlink(false);
			}
			toolbar.ammoBlink(false);
			try {
				Thread.sleep(MunitionBlink.BLINK_TIME);
			} catch (InterruptedException e) {
				toolbar.ammoBlink(false);
			}
            }
        } 
}
