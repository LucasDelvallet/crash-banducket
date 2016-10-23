package process;

import java.util.ArrayList;
import java.util.List;

import entities.Argument;
import entities.StackElement;

public class StackElementComparator {

	static double getDistance(StackElement se1, StackElement se2) {
		ArrayList<Double> values = new ArrayList<>();

		values.add(StringSimilarity.similarity(se1.addr, se2.addr));
		values.add(StringSimilarity.similarity(se1.method, se2.method));
		values.add(StringSimilarity.similarity(se1.path, se2.path));

		for (Argument arg1 : se1.arguments) {
			for (Argument arg2 : se2.arguments) {
				values.add(StringSimilarity.similarity(arg1.name, arg2.name));
				//values.add(StringSimilarity.similarity(arg1.value, arg2.value));
			}
		}

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
