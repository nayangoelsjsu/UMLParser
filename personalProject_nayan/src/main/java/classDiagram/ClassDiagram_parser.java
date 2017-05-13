package classDiagram;

public class ClassDiagram_parser {

    public static void main(String[] args) throws Exception {
      classDiagramGen_main(args);

    }

	private static void classDiagramGen_main(String[] args) throws Exception {
		if (!args[2].equals("generate_classDiagram")) {
		    System.out.println(args[2]+ " is not the correct code. Please enter the correct keyword to generate a Class Diagram for your code. ");
		} else {
		        ClassDiagram_engineParse c_ep = new ClassDiagram_engineParse(args[0], args[1]);
		        c_ep.generateDiagram();
		    
		    }
	}

}