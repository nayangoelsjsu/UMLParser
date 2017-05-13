package classDiagram;

import java.util.*;

public class ClassAssociations_gram{
	
	private HashMap<String, Boolean> classInterfacemapping;
    private HashMap<String, String> connBtwnClasses;

    ClassAssociations_gram(HashMap<String, Boolean> classInterfacemapping, HashMap<String, String> connBtwnClasses) {
        this.classInterfacemapping = classInterfacemapping;
        this.connBtwnClasses = connBtwnClasses;
    }    
	
	public String connectionbtwnClasses() {
        String assocGram = "";
        Set<String> assocKey = this.connBtwnClasses.keySet();
        for (String singularAssoc : assocKey) {
            assocGram = findConn(assocGram, singularAssoc);
            assocGram += ",";
        }
        System.out.println("Association grammar = "+assocGram);
        return assocGram;
    }

	private String findConn(String assocGram, String singularAssoc) {
		String[] assocClasses = singularAssoc.split("-");
		assocGram += this.classInterfacemapping.get(assocClasses[0]) ? "[<<interface>>;" + assocClasses[0] + "]" : "[" + assocClasses[0] + "]";
		assocGram += this.connBtwnClasses.get(singularAssoc);
		assocGram += this.classInterfacemapping.get(assocClasses[1]) ? "[<<interface>>;" + assocClasses[1] + "]" : "[" + assocClasses[1] + "]";
		return assocGram;
	} 

}