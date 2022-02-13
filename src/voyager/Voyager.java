package voyager;

import java.util.ArrayList;
import java.util.List;

public class Voyager implements Comparable<Voyager> {
    private List<City> citiesToVisit ;
    private List<City> citiesVisited ;
    private City start;
    private double distance;
    private double estimatedDistance;
    private boolean aStar;

    public Voyager(List<City> citiesToVisit , City start, boolean isAStar) {
        this.start = start;
        this.citiesVisited = new ArrayList<>();
        this.citiesToVisit = new ArrayList<>();
        this.distance = 0;
        this.citiesToVisit = citiesToVisit;
        this.citiesVisited.add(this.start);
        this.citiesToVisit.remove(this.start);
        this.aStar = true;
        City shortest = new City(99,99);
        for (int i = 0 ; i < this.citiesToVisit.size();i++){
            if (this.start.distance(this.citiesToVisit.get(i)) < this.start.distance(shortest)){
                shortest = this.citiesToVisit.get(i);
            }
        }
        if (this.citiesToVisit.size()>1){
            this.estimatedDistance = this.distance + this.start.distance(shortest) + this.start.distance(start);
        }
        else {
            this.estimatedDistance = this.distance + this.start.distance(shortest);
        }
    }

    public Voyager(Voyager previousWay, City nextCity, boolean isAStar){
        this.start = previousWay.start;
        this.citiesToVisit = new ArrayList<>(previousWay.getCitiesToVisit());
        this.citiesToVisit.remove(nextCity);
        this.citiesVisited = new ArrayList<>(previousWay.citiesVisited);
        this.distance = previousWay.getDistance() + this.citiesVisited.get(citiesVisited.size()-1).distance(nextCity);
        this.citiesVisited.add(nextCity);
        this.estimatedDistance = this.distance ;
        City shortest = new City(99,99);
        for (int i = 0 ; i < this.citiesToVisit.size();i++){
            if (nextCity.distance(this.citiesToVisit.get(i)) < nextCity.distance(shortest)){
                shortest = this.citiesToVisit.get(i);
            }
        }
        if (this.citiesToVisit.size()>1){
            this.estimatedDistance = this.distance + nextCity.distance(shortest) + nextCity.distance(start);
        }
        else {
            this.estimatedDistance = this.distance + nextCity.distance(start);

        }
    }

    public Voyager(List<City> locationsToVisit , City start) {
        this.citiesVisited = new ArrayList<>();
        this.citiesToVisit = new ArrayList<>();
        this.distance = 0;
        this.citiesToVisit = locationsToVisit;
        this.citiesVisited.add(start);
        this.citiesToVisit.remove(start);
    }

    public Voyager(Voyager previousVoyager, City nextCity){
        this.citiesToVisit = new ArrayList<>(previousVoyager.getCitiesToVisit());
        this.citiesToVisit.remove(nextCity);
        this.citiesVisited = new ArrayList<>(previousVoyager.citiesVisited);
        this.distance = previousVoyager.getDistance() + this.citiesVisited.get(citiesVisited.size()-1).distance(nextCity);
        this.citiesVisited.add(nextCity);
        this.estimatedDistance = this.distance ;
    }

    public Voyager(Voyager previousVoyager) {
        this.citiesToVisit = new ArrayList<>(previousVoyager.getCitiesToVisit());
        this.citiesVisited = new ArrayList<>(previousVoyager.getCitiesVisited());
        this.distance = previousVoyager.getDistance();
    }


    public City getStart(){
        return this.start;
    }
    public List<City> getCitiesToVisit(){
        return this.citiesToVisit;
    }
    public List<City> getCitiesVisited(){
        return this.citiesVisited;
    }
    public double getDistance(){
        return this.distance;
    }

    public double getEstimatedDistance() {
        return this.estimatedDistance;
    }
    public int compareTo(Voyager voyager) {
        return Double.compare(this.getDistance(), voyager.getDistance());
    }
    public int Heuristic(Voyager voyager) {
        return Double.compare(this.getEstimatedDistance(), voyager.getEstimatedDistance());
    }
    public City lastCityVisited(){
        return getCitiesVisited().get(getCitiesVisited().size()-1);
    }
    public City lastCityToVisit(){
        return getCitiesToVisit().get(getCitiesToVisit().size()-1);
    }
    public void addLastCityVisited(City endPoint){
        this.distance += lastCityVisited().distance(endPoint);
        getCitiesVisited().add(endPoint);
    }
    public List<Voyager> breadthFirstSearch(Voyager voyager){
        List<Voyager> childVoyager = new ArrayList<>();
        for (int i = 0 ; i < voyager.getCitiesToVisit().size();i++) {
            Voyager v = new Voyager(this, voyager.getCitiesToVisit().get(i));
            childVoyager.add(v);
        }
        return childVoyager;
    }
    public List<Voyager> depthFirstSearch(List<Voyager> completedWays){
        if (getCitiesToVisit().isEmpty()){
            completedWays.add( new Voyager(this));
            return completedWays;
        }else {
            List<Voyager> currentWays = new ArrayList<>();
            for (City city : getCitiesToVisit()){
                Voyager temp = new Voyager(this,city);
                currentWays.add(temp);
            }
            for (Voyager voyager : currentWays){
                voyager.depthFirstSearch(completedWays);
            }
            return completedWays;
        }

    }
    public List<Voyager> aStar(){
        ArrayList<Voyager> children = new ArrayList<>();
        for (City p : this.citiesToVisit){
            children.add( new Voyager(this, p, true));
        }
        return children;
    }
    @Override
    public String toString() {
        String output = "";
        for (City city : this.getCitiesVisited()){
            output += "X = "+city.getX()+", Y = "+city.getY()+"\n";
        }
        return output;
    }
}
