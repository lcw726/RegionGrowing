
public class Main {
	public static void main(String argv[]){
		//RegionGrowing rg = new RegionGrowing();
		//rg.readData();
		RegionGrowing rg = new RegionGrowing(View.readData("test.txt"));
		rg.seedPicking(0, 0.9, 0.45, 0.8, new int[]{1,2});
		
		System.out.println("Agp:");
		System.out.println(rg.getAgp().toString());
	}
}
