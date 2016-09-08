import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class FileAccess {

	static String inputFileName = "data/tst.txt";
	static String outputFileName = "data/result.txt";
	
	static private PrintWriter writer;
	static private BufferedReader reader;
	
	static private String sentence;
	
	static private int line = 0;
	
	public static void openFile() throws FileNotFoundException
	{
		File input = new File(inputFileName);
		reader = new BufferedReader(new FileReader(input));
		
		File output = new File(outputFileName);
		writer = new PrintWriter(output);
	}
	
	public static void closeFile() throws IOException
	{
		reader.close();
		writer.flush();
		writer.close();
	}
	
	public static String getSentence() throws IOException
	{
		String tempString;
		while ((tempString = reader.readLine()) != null && tempString.length() == 0);
		++line;
		System.out.println(tempString);
		sentence = tempString;
		return sentence;
	}
	
	public static void saveResult()
	{
		int cnt = 0;
		writer.println(sentence);
		for(LinkedEntity entity : Info.entityList)
		{
			if(entity.support <= 4000 || entity.percentageOfSecondRank < 0.001)
			{
				writer.println(entity.word + "\tsupport: " + entity.support + "\tsimilarity: " + entity.similarityScore + "\tsecondRank: " + entity.percentageOfSecondRank);

				/*				writer.print("\t");
				for(String type : entity.typeList)
					writer.print(type + " ");
				writer.println();*/
			}
		}
		writer.println();
	}
}
