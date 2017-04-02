import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sourceforge.plantuml.SourceStringReader;

public class Main_Class {
	public static void main (String args[])
	{
		if(args!= null)
		{
		
			try
			{
				ParserCode pc = new ParserCode();
				StringBuilder string_b = pc.Read("/Users/nayangoel/Desktop/A");
				System.out.println(string_b.toString());
				SourceStringReader pumlReader = new SourceStringReader(string_b.toString());
				try (FileOutputStream img_out = new FileOutputStream("/Users/nayangoel/Desktop/uml_class.png")) {
	                pumlReader.generateImage(img_out);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			}
			catch(Exception e)
			{
				System.out.println("Error");
			}
		}
	}

}
