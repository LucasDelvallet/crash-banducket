package parser;

import java.util.ArrayList;
import java.util.List;

import entities.StackFrame;

public class StackParser {

	public static List<StackFrame> getAllStackElement(String source) {
		String[] stackElementSource = source.split("#([0-9])+( )+");

		ArrayList<StackFrame> stackElements = new ArrayList<>();
		for (String s : stackElementSource) {
			if (!s.isEmpty()) {
				stackElements.add(StackElementParser.parseStackElementFromString(s));
			}
		}

		return stackElements;
	}

}
