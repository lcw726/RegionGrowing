public class RegionGrowing {
	private Point[] data;	
	private SetOfPoints PlateauCandicate = new SetOfPoints();
	private SetOfPoints Border = new SetOfPoints();
	private SetOfPoints Agp = new SetOfPoints();
	
	public RegionGrowing(Point[] data){
		this.data = data;
	}
	
	public void seedPicking(int targetVarible, double targetVariblePercentageOfseed, double targetVariblePercentageOfCandicate, double targetVariblePercentageOfGood,int[] vars){
		int plateauNumber = 0;
		int i = 0;
		while(data[i].getValue()[targetVarible] > data[0].getValue()[targetVarible] * targetVariblePercentageOfseed){
			if(!data[i].getIsClassified()){				
				regionGrowing(data[i], data[i], plateauNumber++, 0, targetVarible, targetVariblePercentageOfCandicate, targetVariblePercentageOfGood, vars);
			}
			i++;
		}
		System.out.println("A:");
		System.out.println(PlateauCandicate.toString());
	}
		
	public void regionGrowing(Point seed,Point currentPoint,int plateauNumber,int count,int targetVarible, double targetVariblePercentageOfCandicate, double targetVariblePercentageOfGood, int[] vars){
		//currentPoint.setIsChecked(true);
		if(!currentPoint.getIsClassified()){
			if(currentPoint.getValue()[targetVarible] >= seed.getValue()[targetVarible] * targetVariblePercentageOfCandicate){
				PlateauCandicate.addPoint(currentPoint);
				currentPoint.setIsClassified(true);
				
				if(currentPoint.getValue()[targetVarible] > seed.getValue()[targetVarible] * targetVariblePercentageOfGood){							
				  	Agp.addPoint(currentPoint);
					//System.out.println("Agp:");
					//System.out.println(currentPoint.varibleNamesToString());
					//System.out.println(currentPoint.toString());
				}
				
				//Plateau p = new Plateau(currentPoint, plateauNumber);	
				//System.out.println("currentPoint1:");
				//System.out.println(currentPoint.varibleNamesToString());
				//System.out.println(currentPoint.toString());
				//System.out.println(currentPoint.getIsClassified());


				for(Point next : findNearest(currentPoint, vars)){
					if(next!=null){
						regionGrowing(seed, next, plateauNumber, 0, targetVarible, targetVariblePercentageOfCandicate, targetVariblePercentageOfGood, vars);
					}
				}
			}else if(count==0){
				for(Point next : findNearest(currentPoint, new int[]{1,2})){
					if(next!=null){
						regionGrowing(seed, next, plateauNumber, 1, targetVarible, targetVariblePercentageOfCandicate, targetVariblePercentageOfGood, vars);
					}
				}
			}
		}else{
			Border.addPoint(currentPoint);
		}
	}
	
	public int findDiff(int index){
		int min=data[0].getValue()[index];
		int secMin=data[0].getValue()[index];
		
		for(Point p : data){
			if(p.getValue()[index]<min){
				secMin = min;
				min = p.getValue()[index];
			}
			if(p.getValue()[index]<secMin && p.getValue()[index]>min){
				secMin = p.getValue()[index];
			}
		}
		return (secMin - min);
	}
	
	public void setAgp(SetOfPoints Agp){
		this.Agp = Agp;
	}
	
	public SetOfPoints getAgp(){
		return Agp;
	}

	public Point[] findNearest(Point currentPoint,int[] vars){
		//System.out.println("Hello");
		//System.out.println(findDiff(1));
		//System.out.println(findDiff(2));
		//System.out.println(currentPoint.varibleNamesToString());
		//System.out.println(currentPoint.toString());
		//System.out.println(currentPoint.getIsClassified());
		
		int distance=0;
		
		Point[] temp = new Point[vars.length * 2];
		int j=0;
		for(Point p : data){
			//System.out.println("data check");
			for(int i : vars){
				distance += Math.pow((p.getValue()[i]-currentPoint.getValue()[i])/findDiff(i),2);				
				}
			
			//System.out.println(distance);
			if(Math.sqrt(distance)==1){
				//System.out.println("1111111111111111111111111111111111");
				//System.out.println(Math.abs(p.getPoint()[2]-currentPoint.getPoint()[2]));
				//System.out.println(p.varibleNamesToString());
				//System.out.println("Next Point:");
				//System.out.println(p.toString());
				temp[j++] = p;
			}
			distance=0;
		}
		return temp;
	}
}