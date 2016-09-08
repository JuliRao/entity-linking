import java.util.ArrayList;

public class Info {
	static ArrayList<LinkedEntity> entityList = new ArrayList<LinkedEntity>();
	
	// argument about linking
	private static double confidence;
	
	public static void setConfidence(double d)
	{
		confidence = d;
	}
	
	public static double getConfidence()
	{
		return confidence;
	}
	
	public static void clear()
	{
		entityList.clear();
	}
}
