package process;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Bucket;
import entities.Method;
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

			if (value == bestValue) {
				if (buckets.get(c).getStacks().size() > buckets.get(position).getStacks().size()) {
					position = c;
				}
			}

			if (value > bestValue) {
				bestValue = value;
				position = c;
			}
		}

		// System.out.print(new DecimalFormat("#.##").format(bestValue*100)+"%
		// ");

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
		// return getSumValues(values) / values.size();
	}

	private double getStackComparisonValue(Stack stackTest, Stack stack) {
		return (methodLikelinessMethod(stackTest, stack) + levensteinMethod(stackTest, stack)) / 2;
	}

	public double levensteinMethod(Stack stackTest, Stack stack) {
		List<StackElement> stackElements = stack.getElements();
		List<StackElement> stackElementTests = stackTest.getElements();

		int indexTest = stackTest.getMostSignificantStackElementIndex();
		int index = indexTest;

		if (indexTest >= stackElements.size()) {
			index = stack.getStackElementWithSpecificScore(stackElementTests.get(indexTest).score);
			if (index == -1) {
				index = stack.getMostSignificantStackElementIndex();
			}
		}

		StackElement eT = stackElementTests.get(indexTest);
		StackElement e = stackElements.get(index);

		return StackElementComparator.getDistance(eT, e);
	}

	public double methodLikelinessMethod(Stack stackTest, Stack stack) {
		List<StackElement> stackElements = stack.getElements();
		List<StackElement> stackElementTests = stackTest.getElements();
		List<Method> methods = new ArrayList<Method>();
		List<Method> methodsTest = new ArrayList<Method>();

		int i = 0;
		for (StackElement eS : stackElements) {
			methods.add(new Method(eS.method, i));
			i++;
		}

		i = 0;
		for (StackElement eS : stackElementTests) {
			methodsTest.add(new Method(eS.method, i));
			i++;
		}

		Method[] m = methods.stream().toArray(Method[]::new);
		Method[] mT = methodsTest.stream().toArray(Method[]::new);

		if (methods.size() == 0 && methodsTest.size() == 0) {
			return 1;
		}
		if (methods.size() == 0 || methodsTest.size() == 0) {
			return 0;
		}

		return getPercentThatMatch(m, mT);
	}

	public double getPercentThatMatch(Method[] m1, Method[] m2) {
		int i = 0, n = 0, match = 0;
		while (i < m1.length && n < m2.length) {
			if (m1[i].name.compareTo(m2[n].name) == 0) {
				match++;
			}
			i++;
			n++;
		}

		return ((double) match / ((double) Math.max(m1.length, m2.length)));
	}

	private double getSumValues(List<Double> values) {
		double res = 0;

		for (Double value : values) {
			res += value;
		}

		return res;
	}
}
