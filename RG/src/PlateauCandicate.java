
public class PlateauCandicate extends SetOfPoints{	
	//private int minSize;
	private int index;
	//private double inPlateauDifference;
	//private double difference;
	//private int minimium;
	//private double holePercentage;
	//private double holeValue = getPoints().get(0).getValue()[index] * holePercentage;
	//private int holeCountAmount;
	
	public PlateauCandicate(){
		super();
		
	}
	
	public PlateauCandicate(int minSize, double inPlateauDifference){

	}
	
	public boolean sizeCheck(int minSize){
		if(getPoints().size()<minSize){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean diffCheck(double inPlateauDifference){
		int max=getPoints().get(0).getValue()[index];
		int min=getPoints().get(0).getValue()[index];
		
		for(Point p : getPoints()){
			if(p.getValue()[index]>max){
				max=p.getValue()[index];
			}
			 
			if(p.getValue()[index]<min){
				min = p.getValue()[index];
			}				
		}
		
		if(max/min < inPlateauDifference){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean avgCheck(Point[] data, double difference){
		double allAvg=0;
		double plateauAvg=0;
		for(Point p : data){
			allAvg += p.getValue()[index];
		}
		allAvg /= data.length;
		
		for(Point p : getPoints()){
			plateauAvg += p.getValue()[index];
		}
		plateauAvg /= getPoints().size();
		
		if(plateauAvg/allAvg <difference){
			return false;
		}else{
			return true;
		}		
	}
	
	public boolean minCheck(int minimium){		
		int min=getPoints().get(0).getValue()[index];
		
		for(Point p : getPoints()){			 
			if(p.getValue()[index]<min){
				min = p.getValue()[index];
			}				
		}
		
		if (min < minimium){
			return false;
		}else{
			return true;
		}		
	}
	
	public boolean holeCheck(double holePercentage, int holeCountAmount){
		int holeCount=0;
		double holeValue = getPoints().get(0).getValue()[index] * holePercentage;
		for(Point p : getPoints()){
			if(p.getValue()[index]< holeValue){
				holeCount++;
			}
		}
		if(holeCount>holeCountAmount){
			return false;
		}else{
			return true;
		}
	}
}
