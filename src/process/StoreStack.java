package process;

import java.util.ArrayList;
import java.util.List;
import entities.Bucket;
import entities.Stack;
import main.Main;

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
	}

	private double getStackComparisonValue(Stack stackTest, Stack stack) {		
		if(Main.TEST) {
			if(Main.METHODNUMBER == 1) {
			    return StackComparator.levensteinMethod(stackTest, stack);
			} else if(Main.METHODNUMBER == 2) {
			    return StackComparator.methodLikelinessMethod(stackTest, stack);
			} else if(Main.METHODNUMBER == 3) {
			    return StackComparator.pointClassifiers(stackTest, stack);
			} else if(Main.METHODNUMBER == 4) {
			    return StackComparator.levensteinMethod(stackTest, stack)*StackComparator.pointClassifiers(stackTest, stack);
			}
			
			return 0;
		} else {
			return StackComparator.pointClassifiers(stackTest, stack)*StackComparator.levensteinMethod(stackTest, stack);
		}
	}
}
