package parser;

import java.util.ArrayList;
import java.util.List;

import entities.Argument;
import entities.StackElement;

public class StackElementParser {

	public static StackElement parseStackElementFromString(String source) {
		String addr = "";
		String method = "";
		List<Argument> arguments = new ArrayList<Argument>();
		String path = "";
		String vars = "";

		String[] split = source.split("\\s+");

		int index = 0;
		if (split[index].startsWith("0x")) {
			addr = split[index];
			index += 3;
		}
		
		//A NE PAS EFFACER :
		// Voici les regex pour un peu tout : 
		// Méthode : (  [a-zA-Z_?]* )|((?!([a-z0-9]* in )) [a-zA-Z_?]* \()
		// Arguments de méthode : \((|[\x00-\x27|\x2A-\xAA]*)\)
		// path : (?<=from|at) [a-zA-Z0-9/\-\\.]*
		
		method = source.split("(  [a-zA-Z_?]* )|((?!([a-z0-9]* in )) [a-zA-Z_?]* \\()")[0].replace(" ", "").replace("(", "");
		arguments.add(new Argument(source.split("\\((|[\\x00-\\x27|\\x2A-\\xAA]*)\\)")[0], ""));
		path =  source.split("(?<=from|at) [a-zA-Z0-9/\\-\\\\.]*")[0];
		//vars = "";

		return new StackElement(source, addr, method, arguments, path, vars);

	}
}
