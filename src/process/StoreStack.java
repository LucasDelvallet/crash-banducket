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

			// if(value == bestValue){
			// if(buckets.get(c).getStacks().size() >
			// buckets.get(position).getStacks().size()){
			// position = c;
			// }
			// }

			if (value > bestValue) {
				bestValue = value;
				position = c;
			}
		}

		System.out.print(new DecimalFormat("#.##").format(bestValue*100)+"%  ");

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

			// System.out.println(new DecimalFormat("#.##").format(value*100)+"%
			// ");
		}

		return bestValue;
		// return getSumValues(values) / values.size();
	}

	private double getStackComparisonValue(Stack stackTest, Stack stack) {
		List<StackElement> stackElements = stack.getElements();
		List<StackElement> stackElementTests = stackTest.getElements();

		// Méthode Levenshtein : Score :
		/*
		 * int indexTest = stackTest.getMostSignificantStackElementIndex(); int
		 * index = indexTest;
		 * 
		 * 
		 * 
		 * if(indexTest >= stackElements.size()){ index =
		 * stack.getStackElementWithSpecificScore(stackElementTests.get(
		 * indexTest).score); if(index == -1){ index =
		 * stack.getMostSignificantStackElementIndex(); } }
		 * 
		 * StackElement eT = stackElementTests.get(indexTest); StackElement e =
		 * stackElements.get(index);
		 * 
		 * return StackElementComparator.getDistance(eT, e);
		 */

		// Comparaison de méthode.
		// Utiliser l'offset
		List<Method> methods = new ArrayList<Method>();

		int i = 0;
		for (StackElement e : stackElements) {
			if (!e.method.equals("??")) {
				methods.add(new Method(e.method, i));
			}
			i++;
		}

		List<Method> methodsTest = new ArrayList<Method>();
		i = 0;
		for (StackElement e : stackElementTests) {
			if (!e.method.equals("??")) {
				methodsTest.add(new Method(e.method, i));
			}
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
		Arrays.sort(m1);
		Arrays.sort(m2);
		int i = 0, n = 0, match = 0, offset = 0;
		
		while (i < m1.length && n < m2.length) {
			if (m1[i].name.compareTo(m2[n].name) < 0) {
				i++;
			} else if (m1[i].name.compareTo(m2[n].name) > 0) {
				n++;
			} else {
				match++;
				offset += m1[i].position - m2[n].position;
				i++;
				n++;
			}
		}
		return (double)match / (double)Math.max(m1.length, m2.length);
	}

	private double getSumValues(List<Double> values) {
		double res = 0;

		for (Double value : values) {
			res += value;
		}

		return res;
	}
}
