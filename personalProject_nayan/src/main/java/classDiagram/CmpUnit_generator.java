package classDiagram;

import com.github.javaparser.ast.*;
import com.github.javaparser.*;

import java.io.*;
import java.util.*;

public class CmpUnit_generator {

public ArrayList<CompilationUnit> getcmpUnit_array(String inputCode_file) throws Exception{
        File inputTestClassFolder = new File(inputCode_file);
        ArrayList<CompilationUnit> cmpUnit_array = new ArrayList<CompilationUnit>();
        File[] classFiles = inputTestClassFolder.listFiles();
		for (int i = 0; i < classFiles.length; i++) {
			final File testClasses = classFiles[i];
			if (testClasses.getName().endsWith(".java") && testClasses.isFile()) {
                FileInputStream testClass = new FileInputStream(testClasses);
                CompilationUnit cmpUnit;
                    cmpUnit = JavaParser.parse(testClass);
                	cmpUnit_array.add(cmpUnit);
                    testClass.close();
            }
		}
        return cmpUnit_array;
    }

    }