package classDiagram;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;

import java.util.*;

public class ClassAttributes_gramGen {
    
    private HashMap<String, Boolean> classInterfacemapping;
    private HashMap<String, String> connBtwnClasses;

    ClassAttributes_gramGen(HashMap<String, Boolean> classInterfacemapping, HashMap<String, String> connBtwnClasses) {
        this.classInterfacemapping = classInterfacemapping;
        this.connBtwnClasses = connBtwnClasses;
    }    

public String classAttributes_gram(String classAttributes,Node cmpUnit_node,ClassOrInterfaceDeclaration classInterface_dec,String shortformOfClassname,String extra,ArrayList<String> publicAtrributes){

  boolean isitNext = false;
        List<BodyDeclaration> members = ((TypeDeclaration) cmpUnit_node).getMembers();
		for (int i = 0; i < members.size(); i++) {
			BodyDeclaration cmpUnit_members = members.get(i);
			if (cmpUnit_members instanceof FieldDeclaration) {
                FieldDeclaration field_cmpUnit = ((FieldDeclaration) cmpUnit_members);
                String scopeDec = scope_Symbols(cmpUnit_members.toStringWithoutComments().substring(0,cmpUnit_members.toStringWithoutComments().indexOf(" ")));
                String belongingClass = bracketReplace_forGram(field_cmpUnit.getType().toString());
                String attrName = field_cmpUnit.getChildrenNodes().get(1).toString();
                if (attrName.contains("="))
                    attrName = field_cmpUnit.getChildrenNodes().get(1).toString().substring(0, field_cmpUnit.getChildrenNodes().get(1).toString().indexOf("=") - 1);

                if (publicAtrributes.contains(attrName.toLowerCase()) && scopeDec.equals("-")) {
                    scopeDec = "+";
                }

                String attrDependencies = "";
                boolean attrDependencies_ifMany = false;
                if (!belongingClass.contains("(")) {
					if (this.classInterfacemapping.containsKey(belongingClass)) {
					    attrDependencies = belongingClass;
					}
				} else {
                    attrDependencies = belongingClass.substring(belongingClass.indexOf("(") + 1,
                            belongingClass.indexOf(")"));
                    attrDependencies_ifMany = true;
                }
                if (this.classInterfacemapping.containsKey(attrDependencies) && attrDependencies.length() > 0 ) {
                    String attrAssociation = "-";

                    if (!this.connBtwnClasses.containsKey(attrDependencies + "-" + shortformOfClassname)) {
                        if (attrDependencies_ifMany)
                            attrAssociation += "*";
                        this.connBtwnClasses.put(shortformOfClassname + "-" + attrDependencies,attrAssociation);
                    } else {
                        attrAssociation = this.connBtwnClasses.get(attrDependencies + "-" + shortformOfClassname);
                        if (attrDependencies_ifMany)
                            attrAssociation = "*" + attrAssociation;
                        this.connBtwnClasses.put(attrDependencies + "-" + shortformOfClassname,attrAssociation);
                    }
                }
                if (scopeDec == "+" || scopeDec == "-") {
                    if (isitNext)
                        classAttributes += "; ";
                    classAttributes += scopeDec + " " + attrName + " : " + belongingClass;
                    isitNext = true;
                }
            }
		}
                return classAttributes;
}

private String bracketReplace_forGram(String gramBrack) {
        gramBrack = gramBrack.replace("<", "(");
        gramBrack = gramBrack.replace("[", "(");
        gramBrack = gramBrack.replace(">", ")");
        gramBrack = gramBrack.replace("]", ")");
        return gramBrack;
    }
    

private String scope_Symbols(String scopeVar) {
        
        if(!scopeVar.equals("private")) {
			if(scopeVar.equals("protected")){
			    return "#";
			}

			else if(scopeVar.equals("public")){
			    return "+";
			}

			else{
			    return "";
			}
		} else {
            return "-";
        }
    }

}

