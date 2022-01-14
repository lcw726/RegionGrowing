import java.util.*;

public class SetOfPoints {
	private int number;
	private List<Point> points = new ArrayList<Point>(0);
	
	public SetOfPoints(){
		
	}
	
	public SetOfPoints(List<Point> points, int number){
		setPoints(points);
		setNumber(number);
	}
	
	public void setPoints(List<Point> points){
		this.points = points;
	}
	
	public void addPoint(Point point){
		this.points.add(point);
	}
	
	public List<Point> getPoints(){
		return points;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}
	
	public String toString(){
		String temp=getPoints().get(0).varibleNamesToString() + "\n";
		for(Point p :getPoints()){
			temp+= p.toString() + "\n";
		}
		return temp;
	}
}
