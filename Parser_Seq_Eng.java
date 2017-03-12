import java.util.*;
import java.io.*;

import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.*;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class Parser_Seq_Eng{

    String puml_code;
    final String i_function;
    final String outgoingPath;
    final String i_class;
    final String i_path;

    ArrayList<CompilationUnit> na_array;
    HashMap<String, ArrayList<MethodCallExpr>> map_meth_call;
    HashMap<String, String> class_meth_map;


    Parser_Seq_Eng(String i_path, String i_class, String i_function,String o_file) {
        class_meth_map = new HashMap<String, String>();
        map_meth_call = new HashMap<String, ArrayList<MethodCallExpr>>();
        this.i_function = i_function;
        this.outgoingPath = inPath + "\\" + outFile + ".png";
        this.i_path = inPath;
        this.i_class = i_class;
        puml_code = "@startuml\n";
    }
}