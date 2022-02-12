package voyager;

import java.util.ArrayList;
import java.util.List;

public class Voyager {
    private List<City> citiesToVisit ;
    private List<City> citiesVisited ;
    private City start;
    private double distance;
    private double estimatedDistance;
    private boolean aStar;

    public Voyager(City start, List<City> citiesToVisit, boolean aStar){
        this.start = start;
        this.citiesToVisit = new ArrayList<>();
        this.citiesVisited = new ArrayList<>();
        this.distance = 0;
        this.estimatedDistance = 0;
        this.citiesToVisit = citiesToVisit;
        this.citiesVisited.add(start);
        this.citiesToVisit.remove(start);
        this.aStar = aStar;
        if(aStar)
        {
            City shortest = new City(100,100);
            for (City city : this.citiesToVisit){
                if (start.distance(city) < start.distance(shortest)){
                    shortest = city;
                }
            }
            if (citiesToVisit.size()>1){
                this.estimatedDistance = this.distance + start.distance(shortest) + start.distance(start);
            }
            else {
                this.estimatedDistance = this.distance + start.distance(shortest);
            }
    }
}


    public Voyager(Voyager previous, City nextCity, boolean aStar){
        this.start = previous.getStart();
        this.citiesToVisit = new ArrayList<>(previous.getCitiesVisited());
        this.citiesVisited = new ArrayList<>(previous.citiesVisited);
        this.citiesToVisit.remove(nextCity);
        this.citiesVisited.add(nextCity);
        this.distance = previous.getDistance() + this.citiesVisited.get(citiesVisited.size()-1).distance(nextCity);
        this.estimatedDistance = this.distance ;
        City shortest = new City(100,100);
        for (City city : this.citiesToVisit){
            if (nextCity.distance(city) < nextCity.distance(shortest)){
                shortest = city;
            }
        }
        if (this.citiesToVisit.size()>1){
            this.estimatedDistance = this.distance + nextCity.distance(shortest) + nextCity.distance(start);
        }
        else {
            this.estimatedDistance = this.distance + nextCity.distance(start);
        }
    }

    public Voyager(Voyager previous, City nextNode){
        this.citiesToVisit = new ArrayList<>(previous.getCitiesToVisit());
        this.citiesToVisit.remove(nextNode);
        this.citiesVisited = new ArrayList<>(previous.getCitiesVisited());
        this.distance = previous.getDistance() + this.citiesVisited.get(citiesVisited.size()-1).distance(nextNode);
        this.citiesVisited.add(nextNode);
        this.estimatedDistance = this.distance ;
    }

    public Voyager(Voyager different) {
        this.citiesToVisit = new ArrayList<City>(different.getCitiesToVisit());
        this.citiesVisited = new ArrayList<City>(different.getCitiesVisited());
        this.distance = different.getDistance();
    }

    public void addCityToVisit(City city){
        City temp = getCitiesToVisit().get(getCitiesToVisit().size() - 1);
        this.distance += temp.distance(city);
        this.citiesToVisit.add(city);
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
        return this.distance;}

    public double getEstimatedDistance() {
        return this.estimatedDistance;
    }
    public int compareTo(Voyager voyager) {
        return Double.compare(this.getDistance(), voyager.getDistance());
    }
    public int compareToHeuristic(Voyager voyager) {
        return Double.compare(getEstimatedDistance(), voyager.getEstimatedDistance());
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
        for (City point : voyager.getCitiesToVisit()) {
            Voyager w = new Voyager(this, point);
            childVoyager.add(w);
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
            for (Voyager w : currentWays){
                w.depthFirstSearch(completedWays);
            }
            return completedWays;
        }
    }
    public List<Voyager> aStar(){
        ArrayList<Voyager> children = new ArrayList<>();
        for (City city : this.citiesToVisit){
            children.add( new Voyager(this, city, true));
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
