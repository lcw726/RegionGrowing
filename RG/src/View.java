import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class View {
	public static Point[] readData(String dataPath){
		Point[] data=null;
		try{
			List<String> lines = Files.readAllLines(Paths.get(dataPath), StandardCharsets.UTF_8);
			
			String[] varibleNames = lines.get(0).split("\t");
			
			data = new Point[lines.size()-1];

			for(int i = 1; i<lines.size(); i++){
				String[] temp = lines.get(i).split("\t");
				int[] temp1 = new int[temp.length];
				for(int j = 0; j<temp.length;j++){
					temp1[j] = Integer.parseInt(temp[j]);
				}
				data[i-1] = new Point(varibleNames, temp1) ;
			}
			
			System.out.println(data[0].varibleNamesToString());
			
			for(int i = 0 ;i<data.length;i++){
				System.out.println(data[i].toString());
			}
		}
	    catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return data;
	}

	/*
	System.out.print("\n");
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
	
	System.out.print("\n");
	seedPicking();
	
	System.out.println("A:");
	for(String i : ProblemSpaceArgsNames){
		System.out.print(i+"\t");				
	}
	System.out.print("Number of Plateau");
	System.out.print("\n");
	for(int i = 0 ;i<PlateauCandicate.size();i++){
		for(int j : PlateauCandicate.get(i)){
		System.out.print(j+"\t");
		}
		System.out.print("\n");
	}
	
	System.out.print("\n");
	
	System.out.println("Agp:");
	for(String i : ProblemSpaceArgsNames){
		System.out.print(i+"\t");				
	}
	System.out.print("Number of Plateau");
	System.out.print("\n");
	for(int i = 0 ;i<Agp.size();i++){
		for(int j : Agp.get(i)){
		System.out.print(j+"\t");
		}
		System.out.print("\n");
	}
	*/
}
