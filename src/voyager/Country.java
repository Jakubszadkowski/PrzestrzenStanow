package voyager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Country {
    public static void main(String[]args){

        List<City> cities = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0 ; i < 15 ; i++){
            cities.add(new City((rand.nextInt(1000)+10)/100, rand.nextInt(1000)/10));
        }
        for(int i = 0 ; i <15 ; i++){
            System.out.println(cities.get(i).getX()+" , "+cities.get(i).getY());
        }
        System.out.println("Greedy result:");
        List<City> greedy = new ArrayList<>(cities);
        long greedyTime = System.nanoTime();
        List<City> result = Algorithms.greedyMethode(greedy, 0);
        greedyTime -= System.nanoTime();
        System.out.println("Greedy time in ns: " + -1*greedyTime);
        double distance = 0;
        for (City p: result){
            System.out.println(p.getX()+"  "+p.getY());
            distance += p.distance( result.get(result.indexOf(p)+1));
        }
        System.out.println(distance);


        System.out.println("\ndepp first search result:");
        List<City> locationsDfs = new ArrayList<>(cities);
        long dfsTime = System.nanoTime();
        List<Voyager> dfs = Algorithms.dfs(locationsDfs, 0);
        dfs.sort(Voyager::compareTo);
        dfsTime -= System.nanoTime();
        System.out.println("Ordered cities :" + dfs.get(0).toString());
        System.out.println("Distance:" + dfs.get(0).getDistance());
        System.out.println("Time in ns: " + dfsTime*-1);

        System.out.println("\nbreath first search result:");
        List<City> locationsBfs = new ArrayList<>(cities);
        long bfsTime = System.nanoTime();
        List<Voyager> bfs = Algorithms.breadthFirstSearch(cities, 0);
        bfs.sort(Voyager::compareTo);
        bfsTime -= System.nanoTime();
        System.out.println("Ordered cities :" + bfs.get(0).toString());
        System.out.println("Distance:" + bfs.get(0).getDistance());
        System.out.println("Time in ns: " + bfsTime*-1);

        System.out.println("\nA* result:");
        long aStarTime = System.nanoTime();
        Voyager aStar = Algorithms.aStar(cities, 0);
        aStarTime -= System.nanoTime();
        System.out.println("Ordered cities :" + aStar.toString());
        System.out.println("Distance:" + aStar.getDistance());
        System.out.println("Time in ns: " + aStarTime*-1);

    }
}
