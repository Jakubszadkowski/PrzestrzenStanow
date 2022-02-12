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

    public Voyager(List<City> locationsToVisit , City startingPoint, boolean isAStar) {
        this.start = startingPoint;
        this.citiesVisited = new ArrayList<>();
        this.citiesToVisit = new ArrayList<>();
        this.distance = 0;
        this.citiesToVisit = locationsToVisit;
        this.citiesVisited.add(startingPoint);
        this.citiesToVisit.remove(startingPoint);
        this.aStar = true;
        City shortest = new City(9999,9999);
        for (City p : this.citiesToVisit){
            if (startingPoint.distance(p) < startingPoint.distance(shortest)){
                shortest = p;
            }
        }
        if (locationsToVisit.size()>1){
            this.estimatedDistance = this.distance + startingPoint.distance(shortest) + startingPoint.distance(start);
        }
        else {
            this.estimatedDistance = this.distance + startingPoint.distance(shortest);
        }
    }

    public Voyager(Voyager previousWay, City nextNode, boolean isAStar){
        this.start = previousWay.start;
        this.citiesToVisit = new ArrayList<>(previousWay.getCitiesToVisit());
        this.citiesToVisit.remove(nextNode);
        this.citiesVisited = new ArrayList<>(previousWay.citiesVisited);
        this.distance = previousWay.getDistance() + this.citiesVisited.get(citiesVisited.size()-1).distance(nextNode);
        this.citiesVisited.add(nextNode);
        this.estimatedDistance = this.distance ;
        City shortest = new City(9999,9999);
        for (City p : this.citiesToVisit){
            if (nextNode.distance(p) < nextNode.distance(shortest)){
                shortest = p;
            }
        }
        if (this.citiesToVisit.size()>1){
            this.estimatedDistance = this.distance + nextNode.distance(shortest) + nextNode.distance(start);
        }
        else {
            this.estimatedDistance = this.distance + nextNode.distance(start);

        }
    }

    public Voyager(List<City> locationsToVisit , City startingPoint) {
        this.citiesVisited = new ArrayList<>();
        this.citiesToVisit = new ArrayList<>();
        this.distance = 0;
        this.citiesToVisit = locationsToVisit;
        this.citiesVisited.add(startingPoint);
        this.citiesToVisit.remove(startingPoint);
    }

    public Voyager(Voyager previousWay, City nextNode){
        this.citiesToVisit = new ArrayList<>(previousWay.getCitiesToVisit());
        this.citiesToVisit.remove(nextNode);
        this.citiesVisited = new ArrayList<>(previousWay.citiesVisited);
        this.distance = previousWay.getDistance() + this.citiesVisited.get(citiesVisited.size()-1).distance(nextNode);
        this.citiesVisited.add(nextNode);
        this.estimatedDistance = this.distance ;
    }

    public Voyager(Voyager previousWay) {
        this.citiesToVisit = new ArrayList<>(previousWay.getCitiesToVisit());
        this.citiesVisited = new ArrayList<>(previousWay.getCitiesVisited());
        this.distance = previousWay.getDistance();
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
            for (Voyager voyager : currentWays){
                voyager.depthFirstSearch(completedWays);
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
