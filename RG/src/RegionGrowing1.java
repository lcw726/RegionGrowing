import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RegionGrowing1 {
	private  int ProblemSpaceLength;
	private  int[][] ProblemSpace;
	private  int[][] ProblemSpaceSortedByArgs;	
	private  String[] ProblemSpaceArgsNames;
	private  List<int[]> PlateauCandicate = new ArrayList<int[]>(0);
	private  List<int[]> Border = new ArrayList<int[]>(0);
	private  List<int[]> Agp = new ArrayList<int[]>(0);

	public void readData(){
		try{
			List<String> lines = Files.readAllLines(Paths.get("test.txt"), StandardCharsets.UTF_8);
			
			ProblemSpaceArgsNames = lines.get(0).split("\t");
			
			ProblemSpaceLength = lines.size()-1;
			ProblemSpace = new int[ProblemSpaceLength][]; 

			for(int i = 1; i<lines.size(); i++){
				String[] temp = lines.get(i).split("\t");
				int[] temp1 = new int[temp.length + 1];
				for(int j = 0; j<temp.length;j++){
					temp1[j] = Integer.parseInt(temp[j]);
				}
				temp1[temp.length]=0;
				ProblemSpace[i-1] = temp1 ;
			}
			
			for(String i : ProblemSpaceArgsNames){
				System.out.print(i+"\t");				
			}
			System.out.print("\n");
			
			for(int i = 0 ;i<ProblemSpaceLength;i++){
				for(int j = 0 ;j<ProblemSpace[i].length;j++){
				System.out.print(ProblemSpace[i][j]+"\t");
				}
				System.out.print("\n");
			}
			
			sortByArgs();
			
			System.out.println("Sorted Data:");
			for(String i : ProblemSpaceArgsNames){
				System.out.print(i+"\t");				
			}
			System.out.print("\n");
			for(int i = 0 ;i<ProblemSpaceSortedByArgs.length;i++){
				for(int j = 0 ;j<ProblemSpaceSortedByArgs[i].length;j++){
				System.out.print(ProblemSpaceSortedByArgs[i][j]+"\t");
				}
				System.out.print("\n");
			}

			seedPicking();
			
			System.out.println("A:");
			for(int i = 0 ;i<PlateauCandicate.size();i++){
				for(int j : PlateauCandicate.get(i)){
				System.out.print(j+"\t");
				}
				System.out.print("\n");
			}
			
			System.out.println("Agp:");
			for(int i = 0 ;i<Agp.size();i++){
				for(int j : Agp.get(i)){
				System.out.print(j+"\t");
				}
				System.out.print("\n");
			}
			
		}
	    catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
				
	public void sortByArgs(){
		ProblemSpaceSortedByArgs = ProblemSpace.clone();
		java.util.Arrays.sort(ProblemSpaceSortedByArgs, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		        return Integer.compare(a[2], b[2]);
		    }
		});
		
		java.util.Arrays.sort(ProblemSpaceSortedByArgs, new java.util.Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				 return Integer.compare(a[1], b[1]);
			}
		});
	}

	public void seedPicking(){
		int plateauNumber = 1;
		for(int j=0; j< ProblemSpaceLength;j++){
			if(ProblemSpaceSortedByArgs[j][0] >= ProblemSpace[0][0] * 0.9){    ///選定NetProfit前%10的資料做為種子
				if(ProblemSpaceSortedByArgs[j][3]==0){     //檢查種子是否已被某高原包含
					System.out.println("Number "+j+" Seed:");
	
				  	regionGrowing(j,j,plateauNumber,0);
				  	plateauNumber++;
				}
			}
		}
		//System.out.println(PlateauCandicate.get(0)[0]);
	}
	
	public void regionGrowing(int seed,int initailpoint,int plateauNumber,int count){
		int arg1diff=0;
		int arg2diff=0;
		int arg1max=0;
		int arg2max=0;
		int arg1min=0;
		int arg2min=0;
		int arg2number=0;
		
		int i = 0;
		while(ProblemSpaceSortedByArgs[i][1] == ProblemSpaceSortedByArgs[0][1]){
			i++;
		}
		arg1diff = ProblemSpaceSortedByArgs[i][1] - ProblemSpaceSortedByArgs[0][1];
		
		int j = 0;
		while(ProblemSpaceSortedByArgs[j][2] == ProblemSpaceSortedByArgs[0][2]){
			j++;
		}
		arg2diff = ProblemSpaceSortedByArgs[j][2] - ProblemSpaceSortedByArgs[0][2];
		//System.out.println("j:"+j);
		//System.out.println("arg2diff:"+arg2diff);
		
		
		
		int[] temp = {initailpoint,ProblemSpaceSortedByArgs[initailpoint][0],ProblemSpaceSortedByArgs[initailpoint][1],ProblemSpaceSortedByArgs[initailpoint][2],plateauNumber};

		
		if(ProblemSpaceSortedByArgs[initailpoint][3]==0){
			if(ProblemSpaceSortedByArgs[initailpoint][0]>ProblemSpaceSortedByArgs[seed][0]*0.45){
				PlateauCandicate.add(temp);
				ProblemSpaceSortedByArgs[initailpoint][3] = 1;
				
				if(ProblemSpaceSortedByArgs[initailpoint][0]>ProblemSpaceSortedByArgs[seed][0]*0.8){							
				  	Agp.add(temp);
				}
				
				arg1max = ProblemSpaceSortedByArgs[ProblemSpaceLength-1][1];
				arg2max = ProblemSpaceSortedByArgs[ProblemSpaceLength-1][2];
				arg1min = ProblemSpaceSortedByArgs[0][1];
				arg2min = ProblemSpaceSortedByArgs[0][2];
				arg2number = ((arg2max - arg2min) / arg2diff) + 1;
				//System.out.println("arg2max:"+arg2max);
				//System.out.println("arg2min:"+arg2min);
				//System.out.println("arg2number:"+arg2number);

				
				if(initailpoint+1<= ProblemSpaceLength-1&&ProblemSpaceSortedByArgs[initailpoint+1][3]==0){					
					regionGrowing(seed,initailpoint+1,plateauNumber,count);
				}
				if(initailpoint-1>=0 &&ProblemSpaceSortedByArgs[initailpoint-1][3]==0){
					regionGrowing(seed,initailpoint-1,plateauNumber,count);
				}
				
				if(initailpoint+arg2number<= ProblemSpaceLength-1&&ProblemSpaceSortedByArgs[initailpoint+arg2number][3]==0){
					regionGrowing(seed,initailpoint+arg2number,plateauNumber,count);
				}
				
				if(initailpoint-arg2number>= 0&&ProblemSpaceSortedByArgs[initailpoint+arg2number][3]==0){
					regionGrowing(seed,initailpoint-arg2number,plateauNumber,count);
				}
				
				System.out.println("Hello");
			}
		}else{
			Border.add(temp);
		}
	}
}