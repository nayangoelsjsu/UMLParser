package umlparser.umlparser;

public class Parser_UML {

    public static void main(String[] args) throws Exception {
        if (args[0].equals("class")) {
            ParseEngine PEngine = new ParseEngine(args[1], args[2]);
            PEngine.start();
        } 

    }

}