package process;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import entities.Bucket;
import entities.ScoreTestCollector;
import entities.Stack;

public class StoreStack {
	private List<Bucket> buckets;

	public StoreStack(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public Bucket getBestBucket(Stack stack) {
		int position = 0;
		double bestValue = 0;
		ScoreTestCollector.reset();
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
		
		//ScoreTestCollector.print();
		//System.out.println(new DecimalFormat("#.##").format(bestValue)+" pts   ");

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
		StackComparator.pointClassifiersTest(stackTest, stack);
		return StackComparator.pointClassifiersWithOffset(stackTest, stack) ;//* StackComparator.levensteinMethod(stackTest, stack) ;
		//return (StackComparator.methodLikelinessMethod(stackTest, stack)
		//		+ StackComparator.levensteinMethod(stackTest, stack)) / 2;
	}

	private double getSumValues(List<Double> values) {
		double res = 0;

		for (Double value : values) {
			res += value;
		}

		return res;
	}
}
