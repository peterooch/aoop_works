package utilities;

import game.Driving;

/**
 * Program class and entrypoint
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class Program {

	/** Main entrypoint
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {

		Driving driving = new Driving(10, 40, 8);
		driving.startDrive(8);
	}

}
