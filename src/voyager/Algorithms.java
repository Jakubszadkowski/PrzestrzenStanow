package voyager;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {
    public static List<City> greedyMethode(List<City> citiesLocation, int startIndex){
        List<City> result = new ArrayList<>();
        City currentCity = citiesLocation.get(startIndex);
        result.add(currentCity);
        citiesLocation.remove(startIndex);
        while (citiesLocation.size()>0){
            City shortest = new City(999,999);
            for (City city : citiesLocation){
                if (currentCity.distance(city) < currentCity.distance(shortest)){
                    shortest = city;
                }
            }
            currentCity = shortest;
            citiesLocation.remove(shortest);
            result.add(shortest);
        }
        result.add(result.get(0));
        return result;
    }

    public static List<Voyager> dfs(List<City> citiesLocation, int startIndex){
        City startingPoint = citiesLocation.get(startIndex);
        List<Voyager> ways = new ArrayList<>();
        ways.add(new Voyager(citiesLocation, startingPoint));
        List<Voyager> completedWays = ways.get(0).depthFirstSearch(ways);
        completedWays.removeIf(w -> !w.getCitiesToVisit().isEmpty());
        for (Voyager w : completedWays){
            w.addLastCityVisited(startingPoint);
        }
        return completedWays;
    }

    public static List<Voyager> breadthFirstSearch(List<City> citiesLocation, int startIndex){
        City startingPoint = citiesLocation.get(startIndex);
        List<Voyager> ways = new ArrayList<>();
        List<Voyager> completedWays = new ArrayList<>();
        ways.add(new Voyager(citiesLocation,startingPoint,false));
        while (!ways.isEmpty()){
            Voyager currentWay = ways.get(0);
            ways.addAll(currentWay.breadthFirstSearch(currentWay));
            if (currentWay.getCitiesToVisit().isEmpty()){
                completedWays.add(currentWay);
            }
            ways.remove(currentWay);
        }
        for (Voyager w : completedWays){
            w.addLastCityVisited(startingPoint);
        }
        return completedWays;
    }

    public static Voyager aStar (List<City> citiesLocation, int startIndex){
        City startingPoint = citiesLocation.get(startIndex);
        List<Voyager> ways = new ArrayList<>();
        ways.add(new Voyager( citiesLocation,startingPoint, true));

        while (!ways.get(0).getCitiesToVisit().isEmpty()){
            for (Voyager w : ways.get(0).aStar()){
                ways.add(w);
            }
            ways.remove(0);
            ways.sort(Voyager::compareToHeuristic);
        }

        ways.get(0).addLastCityVisited(startingPoint);
        return ways.get(0);
    }
}
