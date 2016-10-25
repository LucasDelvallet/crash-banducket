package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import entities.StackElement;

public class StackElementParser {

	public static StackElement parseStackElementFromString(String source) {
		String addr = "";
		String method = "";
		String arguments = "";
		String path = "";
		String vars = "";
		
		//A NE PAS EFFACER :
		// Voici les regex pour un peu tout : 
		// Méthode : ([a-zA-Z_?]* )
		//			 ((?!([a-z0-9]* in )) [a-zA-Z_?]* \()
		// Arguments de méthode : \((|[\x00-\x27|\x2A-\xAA]*)\)
		// path : (?<=from|at) [a-zA-Z0-9/\-\\.]*
		// vars : ([a-zA-Z0-9_]* = [\x00-\xAA]*)
		if(source.startsWith("0x")){
			addr = source.split(" ")[0];
			method = regexFinder(source, "((?!([a-z0-9]* in )) [a-zA-Z_?]* \\()").replace(" ", "").replace("(", "");
		}else{
			method = regexFinder(source, "([a-zA-Z_?]* )").replace(" ", "").replace("(", "");
		}
		
		arguments = regexFinder(source,"\\((|[\\x00-\\x27|\\x2A-\\xAA]*)\\)");
		path =  regexFinder(source,"(?<=from|at) [a-zA-Z0-9/\\-\\\\.]*");
		vars = regexFinder(source, "([a-zA-Z0-9_]* = [\\x00-\\xAA]*)");

		return new StackElement(source, addr, method, arguments, path, vars);

	}
	
	private static String regexFinder(String source, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		if(m.find()){
			return m.group(0);
		}
		return "";
	}
}
