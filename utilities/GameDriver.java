package utilities;

import components.Driving;

/**
 * Program class and entrypoint
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */

public class GameDriver {

	/** Main entrypoint
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		Driving driving = new Driving(10, 20);
		driving.drive(20);
	}

}
