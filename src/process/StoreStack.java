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
		
		//System.out.println(bestValue*100+"%");

		return buckets.get(position);
	}

	private double getBucketComparisonValue(Stack stack, Bucket bucket) {
		ArrayList<Double> values = new ArrayList<>();
		List<Stack> stacks = bucket.getStacks();

		double bestValue = 0;
		for (Stack stackTest : stacks) {
			double value = getStackComparisonValue(stack, stackTest);
			values.add(value);
			if (value > bestValue) {
				bestValue = value;
			}
		}
		return bestValue;
		//return getSumValues(values) / values.size();
	}

	private double getStackComparisonValue(Stack stackTest, Stack stack) {
		ArrayList<Double> values = new ArrayList<>();
		List<StackElement> stackElements = stack.getElements();
		List<StackElement> stackElementTests = stackTest.getElements();

		int indexTest = stackTest.getMostSignificantStackElementIndex();
		int index = indexTest; ;
		
		if(indexTest >= stackElements.size()){
			index = stack.getMostSignificantStackElementIndex();
		}
		values.add(StackElementComparator.getDistance(stackElements.get(index), stackElementTests.get(indexTest)));

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
