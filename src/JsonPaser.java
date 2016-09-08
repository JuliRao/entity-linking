import java.io.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonPaser {

	private static String filepath = "data/a.json";
	
	public static void readFeedback() throws IOException
	{
		FileReader reader = new FileReader(new File(filepath));
		JsonReader jsonReader = Json.createReader(reader);
		JsonObject object = jsonReader.readObject();
		
		// read data from the returned json
		JsonArray resourceArray = object.getJsonArray("Resources");
		if(resourceArray == null)
		{
			reader.close();
			return;
		}
		
		for(int i = 0; i < resourceArray.size(); ++i)
		{
			JsonObject res = resourceArray.getJsonObject(i);
			LinkedEntity entity = new LinkedEntity();
			
			entity.word = res.getString("@surfaceForm");
			
			entity.URI = res.getString("@URI");
			entity.offset = Integer.parseInt(res.getString("@offset"));
			entity.support = Integer.parseInt(res.getString("@support"));
			entity.similarityScore = Double.parseDouble(res.getString("@similarityScore"));
			entity.percentageOfSecondRank = Double.parseDouble(res.getString("@percentageOfSecondRank"));
			
			String types = res.getString("@types");
			entity.typeList = types.split(",");
			
			Info.entityList.add(entity);
		}
		
		reader.close();
	}
}
