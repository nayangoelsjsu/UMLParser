import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;


public class Parsecode {
	
	private StringBuilder umlbuild;
	private HashMap<String,cifd> mapci;
	
	public Parsecode()
	{
		umlbuild = new StringBuilder();		
	}

	public StringBuilder umlgetbuild() {
		return umlbuild;
	}

	public StringBuilder Reader(String p) throws FileNotFoundException
	{
		File files = new File(p);
		umlbuild.append("@umlstart\n");
		if(files.exists())
		{
			System.out.println("files exists");
			System.out.println(files.listFiles().toString());
			for(File file : files.listFiles())
			{
				
			}
			
			umlbuild.append("\n@umlend");
		}
		else
		{
			System.out.println("files does not exists");
		}
		
		return umlbuild;
		
	}

	
	
	

}