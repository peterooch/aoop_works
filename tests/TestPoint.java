package tests;

import utilities.Point;

public class TestPoint {

	public static void main(final String[] args) {

		testPoint();

	}

	private static void testPoint() {

		final Point A = new Point(2, 5.5);
		final Point B = new Point(0, 0);
		final Point C = new Point(1000000, 800);
		final Point D = new Point(-1, 5);
		final Point E = new Point(5, -1);
		final Point F = new Point(1000000.01, 5);
		final Point G = new Point(5, 803);
		final Point H = new Point(A.getX(), C.getY());
		H.setX(42);
		H.setY(42);
		H.setX(100000000);
		H.setX(-0.5);
		H.setY(-100);
		H.setY(1000000);
		System.out.print("H: " + H.toString());
		G.setX(H.getX());
		G.setY(H.getY());
		System.out.println("H equals H: " + H.equals(G));
		System.out.println("H equals F: " + H.equals(F));
	}
	
	
}
