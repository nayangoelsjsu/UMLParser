package classDiagram;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.*;

public class GrammarGenerating {

    private HashMap<String, Boolean> classInterfacemapping;
    private HashMap<String, String> connBtwnClasses;

GrammarGenerating(HashMap<String, Boolean> classInterfacemapping, HashMap<String, String> connBtwnClasses) {
        this.classInterfacemapping = classInterfacemapping;
        this.connBtwnClasses = connBtwnClasses;
    }    

    private String bracketReplace_forGram(String gramBrack) {
        gramBrack = gramBrack.replace("<", "(");
        gramBrack = gramBrack.replace("[", "(");
        gramBrack = gramBrack.replace(">", ")");
        gramBrack = gramBrack.replace("]", ")");
        return gramBrack;
    }

public String gramGenerator(CompilationUnit cmpUnit) {
        String nameOfClass = "";
        String code_gram = "";
        String classMethods = "";
        String shortformOfClassname = "";
        String extra = ",";
        String classAttributes = "";
        ArrayList<String> publicAtrributes = new ArrayList<String>();
        List<TypeDeclaration> typeOf_cmpUnit = cmpUnit.getTypes();
        Node cmpUnit_node = typeOf_cmpUnit.get(0);
        ClassOrInterfaceDeclaration classInterface_dec = (ClassOrInterfaceDeclaration) cmpUnit_node;
        nameOfClass = classInterface_dec.isInterface() ? "[" + "<<interface>>;" : "[";
        nameOfClass += classInterface_dec.getName();
        shortformOfClassname = classInterface_dec.getName();
        ClassMethods_gramGen classMethods_gram = new ClassMethods_gramGen(classInterfacemapping);
        classMethods += classMethods_gram.classMethod_gram(classMethods,cmpUnit_node,classInterface_dec,shortformOfClassname,extra,publicAtrributes);
         ClassAttributes_gramGen classAttributes_gram = new ClassAttributes_gramGen(classInterfacemapping, connBtwnClasses);
        classAttributes += classAttributes_gram.classAttributes_gram(classAttributes,cmpUnit_node,classInterface_dec,shortformOfClassname,extra,publicAtrributes);
        extra = inheritenceInterface_checker(shortformOfClassname, extra, classInterface_dec);
        code_gram = combineResults(code_gram, nameOfClass, classMethods, classAttributes, extra);
        return code_gram;
    }

private String combineResults(String code_gram, String nameOfClass, String classMethods, String classAttributes,
		String extra) {
	code_gram += nameOfClass;
	if (!classAttributes.isEmpty()) {
	    code_gram += "|" + bracketReplace_forGram(classAttributes);
	}
	if (!classMethods.isEmpty()) {
	    code_gram += "|" + bracketReplace_forGram(classMethods);
	}
	code_gram += "]";
	code_gram += extra;
	return code_gram;
}

private String inheritenceInterface_checker(String shortformOfClassname, String extra, ClassOrInterfaceDeclaration classInterface_dec) {
	if (classInterface_dec.getExtends() != null) {
	    extra += "[" + shortformOfClassname + "] " + "-^ " + classInterface_dec.getExtends();
	    extra += ",";
	}
	if (classInterface_dec.getImplements() != null) {
	    List<ClassOrInterfaceType> interfaceList = (List<ClassOrInterfaceType>) classInterface_dec
	            .getImplements();
	    for (int i = 0; i < interfaceList.size(); i++) {
			ClassOrInterfaceType intface = interfaceList.get(i);
			extra += "[" + shortformOfClassname + "] " + "-.-^ " + "["
	                + "<<interface>>;" + intface + "]";
	        extra += ",";
		}
	}
	return extra;
}

}