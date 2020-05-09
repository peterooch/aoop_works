package utilities;
import java.util.Random;


public interface Utilities {
    Random rand = new Random();

    default public boolean checkValue(double Val, double min, double max){
        return (Val <= max) && (Val >= min);
    }

    default public void correctingMessage(double wrongVal, double correctVal, String varName){
        System.out.print("The value " + wrongVal + " is illegal for " + varName +", therefore has been replaced with " + correctVal + "\n");
    }

    default public void errorMessage(double wrongVal, String varName){
        System.out.print("The value " + wrongVal + " for " + varName + " is not suitable" + "\n");
    }

    default public boolean getRandomBoolean(){
        return rand.nextBoolean();
    }

    default public double getRandomDouble(double min, double max){
        double randomValue = min + (max - min) * rand.nextDouble();
        return randomValue;
    }

    default public int getRandomInt(int min, int max){
        int randomValue = min + (max - min) * rand.nextInt();
        return randomValue;
    }

    default public void successMessage(String objName){
        System.out.print(objName + " has been created.\n");
    }
}
