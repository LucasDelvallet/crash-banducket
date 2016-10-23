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
			double value = 0;
			
			if (stackElementTests.size() > i) {
				value = StackElementComparator.getDistance(element, stackElementTests.get(i));
				values.add(value);
			}
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
