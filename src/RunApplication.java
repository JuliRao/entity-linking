import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

// get the linking result via command
public class RunApplication {

	private String text;
	
	RunApplication()
	{
		text = null;
	}
	
	RunApplication(String sentence)
	{
		text = sentence;
	}
	
	public void setText(String sentence)
	{
		text = sentence;
	}
	
	public String getText()
	{
		return text;
	}
	
	// run the spotlight program from Web service
	public void execute()
	{
		text.replace('"', '\"');
		String cmd = "curl" +
				" --data-urlencode \"text=" + text + "\"" +
				" --data \"confidence=" + Info.getConfidence() + "\"" +
				" -H \"Accept: application/json\"" + " http://spotlight.sztaki.hu:2222/rest/annotate"
				+ " > data/a.json";
		
		try {
			File cmdFile = new File("data/a.sh");
			PrintWriter writer = new PrintWriter(cmdFile);
			writer.println(cmd);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//System.out.println(cmd);
		
		try {
			Process p = Runtime.getRuntime().exec("./data/a.sh"); 
			p.waitFor();
			
/*			BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));		
			String line;
			while((line = output.readLine()) != null){
				System.out.println(line);
			}*/
		} catch (IOException e) {
		e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
