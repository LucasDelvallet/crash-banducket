package parser;

import java.util.ArrayList;
import java.util.List;

import entities.StackElement;

public class StackParser {

    public static List<StackElement> getAllStackElement(String source) {
	String[] stackElementSource = source.split("#([0-9])+( )+");
	
	ArrayList<StackElement> stackElements = new ArrayList<>();
	for(String s : stackElementSource) {
	    if(!s.isEmpty()) {
		stackElements.add(new StackElement(s));
	    }
	}
	
	return stackElements;
    }
}
