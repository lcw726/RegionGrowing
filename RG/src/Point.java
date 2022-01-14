
public class Point {
	private  String[] varibleNames;
	private  int[] value;
	private boolean isClassified;
	public int length;
	
	public Point(){
		setIsClassified(false);
		length = getVaribleNames().length;		
	}
	
	public Point(String[] varibleNames, int[] point){
		setVaribleNames(varibleNames);
		setValue(point);
		setIsClassified(false);
		length = getVaribleNames().length;
	}
	
	public void setVaribleNames(String[] varibleNames){
		this.varibleNames = varibleNames;
	}
	
	public String[] getVaribleNames(){
		return varibleNames;
	}
	
	public void setValue(int[] value){
		this.value = value;
	}
	
	public int[] getValue(){
		return value;
	}
	
	public void setIsClassified(boolean isClassified){
		this.isClassified = isClassified;
	}
	
	public boolean getIsClassified(){
		return isClassified;
	}
	
	public String varibleNamesToString(){
		String temp="";
		for(String i :getVaribleNames()){
			temp+= i +"\t";
		}
		return temp;
	}
	
	public String toString(){
		String temp="";
		for(int i :getValue()){
			temp+= i +"\t";
		}
		return temp;
	}
}
