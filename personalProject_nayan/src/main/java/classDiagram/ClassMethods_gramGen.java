package classDiagram;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;

import java.util.*;

public class ClassMethods_gramGen {
	
	private HashMap<String, Boolean> classInterfacemapping;

    ClassMethods_gramGen(HashMap<String, Boolean> classInterfacemapping) {
        this.classInterfacemapping = classInterfacemapping;
    }    

public String classMethod_gram(String classMethods,Node cmpUnit_node,ClassOrInterfaceDeclaration classInterface_dec,String shortformOfClassname,String extra,ArrayList<String> publicAtrributes){

	   boolean isitNext = false;
        List<BodyDeclaration> cmpUnit_membersList = ((TypeDeclaration) cmpUnit_node).getMembers();
		for (int j = 0; j < cmpUnit_membersList.size(); j++) {
			BodyDeclaration cmpUnit_members = cmpUnit_membersList.get(j);
            if (cmpUnit_members instanceof ConstructorDeclaration) {
                ConstructorDeclaration const_cmpUnit = ((ConstructorDeclaration) cmpUnit_members);
                if (!classInterface_dec.isInterface() && const_cmpUnit.getDeclarationAsString().startsWith("public")) {
                    if (isitNext){
                        classMethods += ";";
                    }
                    classMethods += "+ " + const_cmpUnit.getName() + "(";
                    List<Node> const_cmpUnitNode = const_cmpUnit.getChildrenNodes();
					for (int i = 0; i < const_cmpUnitNode.size(); i++) {
						Object methodsForClass = const_cmpUnitNode.get(i);
						if (methodsForClass instanceof Parameter) {
                            Parameter methodArgs = (Parameter) methodsForClass;
                            String argsType = methodArgs.getType().toString();
                            String paramName = methodArgs.getChildrenNodes()
                                    .get(0).toString();
                            classMethods += paramName + " : " + argsType;
                            if (!this.classInterfacemapping.get(shortformOfClassname) && this.classInterfacemapping.containsKey(argsType)) {
                                extra += "[" + shortformOfClassname+ "] uses -.->";
                                extra += this.classInterfacemapping.get(argsType) ? "[<<interface>>;" + argsType+ "]" : "[" + argsType + "]";
                            }
                            extra += ",";
                        }
					}
                    classMethods += ")";
                    isitNext = true;
                }
            }
		}
        
        List<BodyDeclaration> cmpUnit_memebersList = ((TypeDeclaration) cmpUnit_node).getMembers();
		for (int i = 0; i < cmpUnit_memebersList.size(); i++) {
			BodyDeclaration cmpUnit_members = cmpUnit_memebersList.get(i);
			if (cmpUnit_members instanceof MethodDeclaration) {
                MethodDeclaration findmethod_cmpUnitMembers = ((MethodDeclaration) cmpUnit_members);
                if (findmethod_cmpUnitMembers.getDeclarationAsString().startsWith("public") && !classInterface_dec.isInterface()) {
                    if (findmethod_cmpUnitMembers.getName().startsWith("get") || findmethod_cmpUnitMembers.getName().startsWith("set")) {
                        String methodAttributes = findmethod_cmpUnitMembers.getName().substring(3);
                        publicAtrributes.add(methodAttributes.toLowerCase());
                    } else {
                        if (isitNext) {
							classMethods += ";";
						}
                        classMethods += "+ " + findmethod_cmpUnitMembers.getName() + "(";
                        List<Node> methodsForClass_List = findmethod_cmpUnitMembers.getChildrenNodes();
						for (int k = 0; k < methodsForClass_List.size(); k++) {
							Object methodsForClass = methodsForClass_List.get(k);
							if (methodsForClass instanceof Parameter) {
                                Parameter methodArgs = (Parameter) methodsForClass;
                                String argsType = methodArgs.getType()
                                        .toString();
                                String paramName = methodArgs.getChildrenNodes()
                                        .get(0).toString();
                                classMethods += paramName + " : " + argsType;
                                if (!this.classInterfacemapping.get(shortformOfClassname) && this.classInterfacemapping.containsKey(argsType)) {
                                    extra += "[" + shortformOfClassname + "] uses -.->";
                                    extra += this.classInterfacemapping.get(argsType) ? "[<<interface>>;"+ argsType + "]" : "[" + argsType + "]";
                                }
                                extra += ",";
                            } else {
                                String codeInsideMethod[] = methodsForClass.toString().split(" ");
                                for (int j = 0; j < codeInsideMethod.length; j++) {
									String methBodyKey = codeInsideMethod[j];
									if (!this.classInterfacemapping.get(shortformOfClassname) && this.classInterfacemapping.containsKey(methBodyKey)) {
                                        extra += "[" + shortformOfClassname + "] uses -.->";
                                        extra += this.classInterfacemapping.get(methBodyKey) ? "[<<interface>>;" + methBodyKey+ "]" : "[" + methBodyKey + "]";
                                        extra += ",";
                                    }
								}
                            }
						}
                        classMethods += ") : " + findmethod_cmpUnitMembers.getType();
                        isitNext = true;
                    }
                }
            }
		}
		return classMethods;
}
}