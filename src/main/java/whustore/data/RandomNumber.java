package whustore.data;

import java.util.Random;

public class RandomNumber {
    public static int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(1147483640) + 100000000;
    }
}
