package parser;

import java.util.ArrayList;
import java.util.List;

import entities.StackFrame;

public class StackParser {

	public static List<StackFrame> getAllStackFrame(String source) {
		String[] stackFrameSource = source.split("#([0-9])+( )+");

		ArrayList<StackFrame> stackFrames = new ArrayList<>();
		for (String s : stackFrameSource) {
			if (!s.isEmpty()) {
				stackFrames.add(StackFrameParser.parseStackFrameFromString(s));
			}
		}

		return stackFrames;
	}

}
