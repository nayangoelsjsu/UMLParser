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


}
