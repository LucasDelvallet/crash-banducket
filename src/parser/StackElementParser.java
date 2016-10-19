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

		method = split[index];
		do {
			if (split.length > index + 1 && split[index + 1].contains("=")
					&& (!(split[index + 1].startsWith("(") && split[index + 1].endsWith(")")))) {
				//Argument arg = new Argument(split[index + 1].split("=")[0].replace("(", ""),
				//		split[index + 1].split("=")[1].replace(",", "").replace(")", ""));
				//arguments.add(arg);
			}
			index++;
		} while (split.length < index && !split[index].endsWith(")"));

		if (split.length < index && (split[index + 1].equals("from") || split[index + 1].equals("at"))) {
			path = split[index + 2];
		}

		vars = "";

		return new StackElement(source, addr, method, arguments, path, vars);

	}
}
