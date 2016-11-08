package process;

import java.util.ArrayList;
import java.util.List;

import entities.StackFrame;

public class StackFrameComparator {

	static double getDistance(StackFrame se1, StackFrame se2) {
		ArrayList<Double> values = new ArrayList<>();

		// values.add(StringSimilarity.similarity(se1.method, se2.method));
		values.add(StringSimilarity.similarity(se1.addr, se2.addr));
		if (se1.method.equals(se2.method)) {
			values.add((double) 1);
		} else {
			values.add((double) 0);
		}
		values.add(StringSimilarity.similarity(se1.path, se2.path));
		//values.add(StringSimilarity.similarity(se1.arguments, se2.arguments));
		//values.add(StringSimilarity.similarity(se1.vars, se2.vars));

		return getSumValues(values) / values.size();
	}

	private static double getSumValues(List<Double> values) {
		double res = 0;

		for (Double value : values) {
			res += value;
		}

		return res;
	}
}
