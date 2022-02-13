package voyager;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {
    public static List<City> greedyMethode(List<City> cities, int startIndex){
        List<City> result = new ArrayList<>();
        City currentCity = cities.get(startIndex);
        result.add(currentCity);
        cities.remove(startIndex);
        while (cities.size()>0){
            City shortest = new City(99,99);
            for (City city : cities){
                if (currentCity.distance(city) < currentCity.distance(shortest)){
                    shortest = city;
                }
            }
            currentCity = shortest;
            cities.remove(shortest);
            result.add(shortest);
        }
        result.add(result.get(0));
        return result;
    }

    public static List<Voyager> depthFirstSearch(List<City> cities, int startIndex){
        City startingCity = cities.get(startIndex);
        List<Voyager> voyagers = new ArrayList<>();
        voyagers.add(new Voyager(cities, startingCity));
        List<Voyager> completedVoyagers = voyagers.get(0).depthFirstSearch(voyagers);
        completedVoyagers.removeIf(w -> !w.getCitiesToVisit().isEmpty());
        for (Voyager w : completedVoyagers){
            w.addLastCityVisited(startingCity);
        }
        return completedVoyagers;
    }

    public static List<Voyager> breadthFirstSearch(List<City> cities, int startIndex){
        City startingCity = cities.get(startIndex);
        List<Voyager> Voyagers = new ArrayList<>();
        List<Voyager> completedVoyagers = new ArrayList<>();
        Voyagers.add(new Voyager(cities, startingCity));
        while (!Voyagers.isEmpty()){
            Voyager currentVoyager = Voyagers.get(0);
            Voyagers.addAll(currentVoyager.breadthFirstSearch(currentVoyager));
            if (currentVoyager.getCitiesToVisit().isEmpty()){
                completedVoyagers.add(currentVoyager);
            }
            Voyagers.remove(currentVoyager);
        }
        for (Voyager w : completedVoyagers){
            w.addLastCityVisited(startingCity);
        }
        return completedVoyagers;
    }

    public static Voyager aStar (List<City> citiesLocation, int startIndex){
        City startingCity = citiesLocation.get(startIndex);
        List<Voyager> Voyagers = new ArrayList<>();
        Voyagers.add(new Voyager(citiesLocation, startingCity, true));

        while (!Voyagers.get(0).getCitiesToVisit().isEmpty()){
            for (Voyager w : Voyagers.get(0).aStar()){
                Voyagers.add(w);
            }
            Voyagers.remove(0);
            Voyagers.sort(Voyager::Heuristic);
        }

        Voyagers.get(0).addLastCityVisited(startingCity);
        return Voyagers.get(0);
    }
}
