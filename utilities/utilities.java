package utilities;
import java.util.Random;


public interface Utilities {

    default public boolean checkValue(double Val, double min, double max){

        if (Val > max || Val < min){
            return false;
        }
        return true;
    }

    default public void correctingMessage(double wrongVal, double correctVal, String varName){
        System.out.print("The value " + wrongVal + "is illegal for" + varName +", therefore has been replaced with" + correctVal );
    }

    default public void errorMessage(double wrongVal, String varName){
        System.out.print("The value " + wrongVal + "for" + varName + "is not suitable");
    }

    default public boolean getRandomBoolean(){
        Random rd = new Random();
        return rd.nextBoolean();
    }

    default public double getRandomDouble(double min, double max){
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return randomValue;
    }

    default public int getRandomInt(int min, int max){
        Random r = new Random();
        int randomValue = min + (max - min) * r.nextInt();
        return randomValue;
    }

    default public void successMessage(String objName){
        System.out.print(objName + "has been created.");
    }
}
