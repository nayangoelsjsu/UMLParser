import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;


public class ParserCode {
	
	private StringBuilder umlbuild;
	private HashMap<String,ClassOrInterfaceDeclaration> CIMap;
	
	public ParserCode()
	{
		umlbuild = new StringBuilder();		
	}

	public StringBuilder getBuild_uml() {
		return umlbuild;
	}

	public StringBuilder ReadFile(String file_path) throws FileNotFoundException
	{
		File f = new File(file_path);
		umlbuild.append("@startuml\n skinparam classAttributeIconSize 0\n");
		if(f.exists())
		{
			System.out.println("file is present");
			System.out.println(f.listFiles().toString());
			for(File f : f.listFiles())
			{
				if(f.getName().endsWith(".java"))
				{
					try 
					{
						CompilationUnit comp_unit = JavaParser.parse(f);
						ClassOrInterfaceDeclaration class_inter_dec = GetClassAndInterface(comp_unit);
						System.out.println(class_inter_dec+"----------");
						if (class_inter_dec != null) {
							CIMap.put(class_inter_dec.getName().toString(), class_inter_dec);
							System.out.println("CIMAP : "+class_inter_dec.getName().toString());
						}
						parseClassInterfaceTypes(class_inter_dec);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			umlbuild.append("\n@enduml");
            System.out.print( umlbuild.toString());
		}
		else
		{
			System.out.println("file does not exists");
		}
		
		return umlbuild;
		
	}

	private void ParseClass_Interface_dec(ClassOrInterfaceDeclaration u) {
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

	public ClassOrInterfaceDeclaration getClass_interface(CompilationUnit comp_unit) {
		// TODO Auto-generated method stub
		List<Node> Nlist = comp_unit.getChildNodes(); 
		for(Node node : Nlist)
		{
			if(node instanceof ClassOrInterfaceDeclaration)
			{
				return (ClassOrInterfaceDeclaration)node;
			}
			
		}
		return null;
	}
	

}
