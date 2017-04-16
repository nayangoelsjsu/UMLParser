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
				if(file.getName().endsWith(".java"))
				{
					try 
					{
						CompilationUnit compunit = JavaParser.parse(file);
						cifd classdec = GetClassAndInterface(compunit);
						if (classdec != null) {
							System.out.println(classdec+" is the classdec");
							System.out.println("CIMAP : "+classdec.getName().toString());
						}
						pcit(classdec);
					} 
					catch (Exception error) {
						// TODO Auto-generated catch block
						error.printStackTrace();
					}
				}
			}
			
			umlbuild.append("\n@umlend");
		}
		else
		{
			System.out.println("files does not exists");
		}
		
		return umlbuild;
		
	}

	private void pcit(cifd u) {
		// TODO Auto-generated method stub
		if(!u.isInterface())
		{
			umlbuild.append("class ").append(u.getName()).append("{\n");
			
		}
		else
		{
			umlbuild.append("interface ").append(u.getName()).append("{\n}\n");
		}
		umlbuild.append("}\n");
	}

	
	

}