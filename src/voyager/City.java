package voyager;

public class City {
    private double x;
    private double y;
    private String name;
    public City(String name){
        this.name = name;
        this.x=0;
        this.y=0;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public String getName(){
        return this.name;
    }
    public double distance(City target){
        return Math.sqrt( Math.pow(target.getX()+getX(),2)+Math.pow(target.getY()+getY(),2));
    }
}
