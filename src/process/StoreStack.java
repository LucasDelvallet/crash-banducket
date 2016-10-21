package process;

import java.util.ArrayList;
import java.util.List;

import entities.Bucket;
import entities.Stack;
import entities.StackElement;

public class StoreStack {
	private List<Bucket> buckets;

	public StoreStack(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public Bucket getBestBucket(Stack stack) {
		int position = 0;
		double bestValue = 0;

		for (int c = 0; c < buckets.size(); c++) {
			double value = getBucketComparisonValue(stack, buckets.get(c));

			if (value > bestValue) {
				bestValue = value;
				position = c;
			}
		}

		return buckets.get(position);
	}

	private double getBucketComparisonValue(Stack stack, Bucket bucket) {
		ArrayList<Double> values = new ArrayList<>();
		List<Stack> stacks = bucket.getStacks();

		for (Stack stackTest : stacks) {
			values.add(getStackComparisonValue(stack, stackTest));
		}

		return getSumValues(values) / values.size();
	}

	private double getStackComparisonValue(Stack stackTest, Stack stack) {
		ArrayList<Double> values = new ArrayList<>();
		List<StackElement> stackElements = stack.getElements();
		List<StackElement> stackElementTests = stackTest.getElements();

		for (int i = 0; i < stackElements.size(); i++) {

			StackElement element = stackElements.get(i);
			double bestValue = 0;

			if (stackElementTests.size() > i) {
				bestValue = StackElementComparator.getDistance(element, stackElementTests.get(i));

				values.add(bestValue);
			}

			// Ce n'est pas pertinent. Cela fausse les résultats en plus d'être
			// très couteux en energie.
			//
			// for(StackElement element : stackElements) {
			// for(StackElement elementTest : stackElementTests) {
			// double currentValue =
			// StringSimilarity.similarity(element.getSource(),
			// elementTest.getSource());
			// double currentValue = StackElementComparator.getDistance(element,
			// elementTest);
			// if(currentValue > bestValue) {
			// bestValue = currentValue;
			// }
			// }
			// }

		}

		return getSumValues(values) / values.size();
	}

	private double getSumValues(List<Double> values) {
		double res = 0;

		for (Double value : values) {
			res += value;
		}

		return res;
	}
}
