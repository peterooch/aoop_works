package utilities;
import java.util.Random;

/**
 * Utilities Interface
 * 
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 * @author Asaf Bereby, ID 208058412, Campus Be'er Sheva
 */
public interface Utilities {
    Random rand = new Random();

    /** Check if a value is between a specific range
     * @param Val value to be checked
     * @param min min limit
     * @param max max limit
     * @return true if the value is in the range, false otherwise
     */
    default public boolean checkValue(double Val, double min, double max){
        return (Val <= max) && (Val >= min);
    }

    default public void correctingMessage(double wrongVal, double correctVal, String varName){
        System.out.println("The value " + wrongVal + " is illegal for " + varName +", therefore has been replaced with " + correctVal);
    }

    default public void errorMessage(double wrongVal, String varName){
        System.out.println("The value " + wrongVal + " for " + varName + " is not suitable");
    }

    default public boolean getRandomBoolean(){
        return rand.nextBoolean();
    }

    default public double getRandomDouble(double min, double max){
        double randomValue = min + (max - min) * rand.nextDouble();
        return randomValue;
    }

    default public int getRandomInt(int min, int max){
        int randomValue = min + rand.nextInt((max - min) + 1);
        return randomValue;
    }

    default public void successMessage(String objName){
        System.out.println(objName + " has been created.");
    }

    /**
     * Prints fancy text like this
     *  ---- FANCY TEXT ----
     * @param msg Message in the middle
     * @param width total text width including the message
     */
    default public void fancyprint(String msg, int width) {
        int dash_count = width - msg.length() - 2;
        String output = "";
        for (int i = 0; i < dash_count / 2; i++)
            output += "-";
        output += " " + msg + " ";
        for (int i = 0; i < dash_count / 2; i++)
            output += "-";
        if (dash_count % 2 == 1)
            output += "-";
        System.out.println(output);
    }
}
