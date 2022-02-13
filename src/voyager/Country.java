package voyager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Country {
    public static void main(String[]args){
        List<City> cities = new ArrayList<>();

        Random ran = new Random();

        for (int i = 0 ; i < 9 ; i ++){
            cities.add(new City(ran.nextInt(50), ran.nextInt(50)));
        }

        System.out.println("Metoda przeszukania w głąb:");
        List<City> locationsDfs = new ArrayList<>(cities);
        long dfsTime = System.currentTimeMillis();
        List<Voyager> dfs = Algorithms.depthFirstSearch(locationsDfs, 0);
        dfs.sort(Voyager::compareTo);
        dfsTime -= System.currentTimeMillis();
        System.out.println("Rezultat :" + dfs.get(0).toString());
        System.out.println("Odleglosc:" + dfs.get(0).getDistance());
        System.out.println("Czas w ms: " + dfsTime*-1+"\n");

        System.out.println("Metoda przeszukania w szerz:");
        List<City> locationsBfs = new ArrayList<>(cities);
        long bfsTime = System.currentTimeMillis();
        List<Voyager> bfs = Algorithms.breadthFirstSearch(locationsBfs, 0);
        bfs.sort(Voyager::compareTo);
        bfsTime -= System.currentTimeMillis();
        System.out.println("Rezultat :" + bfs.get(0).toString());
        System.out.println("Odleglosc:" + bfs.get(0).getDistance());
        System.out.println("Czas w ms: " + bfsTime*-1+"\n");

        System.out.println("Metoda zachlanna:");
        List<City> greedy = new ArrayList<>(cities);
        long greedyTime = System.currentTimeMillis();
        List<City> result = Algorithms.greedyMethode(greedy, 0);
        greedyTime -= System.currentTimeMillis();
        System.out.println("Metoda zachlanna czas w ms: " + -1*greedyTime);
        double distance = 0;
        for (City city: result){
            System.out.println(city.getX()+"  "+city.getY());
            distance += city.distance( result.get(result.indexOf(city)+1));
        }
        System.out.println(distance+"\n");

        System.out.println("Metoda A* :");
        long aStarTime = System.currentTimeMillis();
        Voyager aStar = Algorithms.aStar(cities, 0);
        aStarTime -= System.currentTimeMillis();
        System.out.println("Rezultat :" + aStar.toString());
        System.out.println("Odleglosc:" + aStar.getDistance());
        System.out.println("Czas w ms: " + aStarTime*-1);
    }
}
