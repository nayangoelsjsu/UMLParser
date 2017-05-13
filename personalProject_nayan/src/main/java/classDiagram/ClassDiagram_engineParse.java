package classDiagram;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;

import java.util.*;


public class ClassDiagram_engineParse {
    final String inputCode_file;
    final String outputDiagram_file;
    HashMap<String, Boolean> classInterfacemapping;
    HashMap<String, String> connBtwnClasses;
    String code_gram;
    ArrayList<CompilationUnit> cmpUnit_array;

    ClassDiagram_engineParse(String inputCode_file, String outFile) {
        code_gram = "";
        classInterfacemapping = new HashMap<String, Boolean>();
        connBtwnClasses = new HashMap<String, String>();
        this.inputCode_file = inputCode_file;
        this.outputDiagram_file = inputCode_file + "/" + outFile + ".png";
    }


     private String code_gram_distinct(String code_gram) {
        String[] distinctClass = code_gram.split(",");
        String[] uniqueClass = new LinkedHashSet<String>(
                Arrays.asList(distinctClass)).toArray(new String[0]);
        String distinctCode_gram = String.join(",", uniqueClass);
        return distinctCode_gram;
    }


    private void classInterface_mapper(ArrayList<CompilationUnit> cmpUnit_array) {
        for (int j = 0; j < cmpUnit_array.size(); j++) {
			CompilationUnit cmpUnit = cmpUnit_array.get(j);
			List<TypeDeclaration> cmpUnit_list = cmpUnit.getTypes();
            for (int i = 0; i < cmpUnit_list.size(); i++) {
				Node cmpNode_list = cmpUnit_list.get(i);
				ClassOrInterfaceDeclaration class_Interface = (ClassOrInterfaceDeclaration) cmpNode_list;
                classInterfacemapping.put(class_Interface.getName(), class_Interface.isInterface());
			}
		}
    }

    public void generateDiagram() throws Exception {
        CmpUnit_generator cmp_gen= new CmpUnit_generator();
        cmpUnit_array = cmp_gen.getcmpUnit_array(inputCode_file);
        classInterface_mapper(cmpUnit_array);
        GrammarGenerating gramGen = new GrammarGenerating(classInterfacemapping, connBtwnClasses);
        for (int i = 0; i < cmpUnit_array.size(); i++) {
			CompilationUnit cmpUnit = cmpUnit_array.get(i);
			code_gram += gramGen.gramGenerator(cmpUnit);
		}
        System.out.println("Classes + interface Grammar = "+code_gram);
        
        ClassAssociations_gram classAssoc_gramGen = new ClassAssociations_gram(classInterfacemapping, connBtwnClasses);
        code_gram += classAssoc_gramGen.connectionbtwnClasses();
        code_gram = code_gram_distinct(code_gram);
        System.out.println("Final generated Grammar = " + code_gram);
        ClassDiagram_generator.imageGenerator_fn(outputDiagram_file,code_gram);
        
    }


}
