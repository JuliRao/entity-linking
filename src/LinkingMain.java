import java.io.FileNotFoundException;
import java.io.IOException;


public class LinkingMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculate();
		
/*		Info.setConfidence(0.35);
		RunApplication run = new RunApplication("Which companies have more than 1 million employees?");
		run.execute();*/
		
		System.out.println("end");
	}
	
	public static void calculate()
	{
		try {
			
			Info.setConfidence(0.35);
			
			FileAccess.openFile();
			String text;
			while((text = FileAccess.getSentence()) != null)
			{
				RunApplication run = new RunApplication(text);
				run.execute();
				JsonPaser.readFeedback();
				FileAccess.saveResult();
				Info.clear();
			}
			FileAccess.closeFile();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
