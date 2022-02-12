package voyager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Country {
    public static void main(String[]args){

        List<City> cities = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0 ; i < 15 ; i++){
            cities.add(new City(rand.nextDouble(), rand.nextDouble()));
        }

    }
}
