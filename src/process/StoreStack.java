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
		
		for(int c=0; c < buckets.size(); c++) {
		    double value = getBucketComparisonValue(stack, buckets.get(c));
		    
		    //System.out.println("Bucket : " + buckets.get(c).getId() + " Valeur : " + value + "%" );
		    
		    if(value > bestValue) {
		    	bestValue = value;
		    	position = c;
		    }
		}
		
	    //System.out.println("Bucket choisi : " + buckets.get(position).getId() + " Valeur : " + bestValue + "%" );
	    
		return buckets.get(position);
    }
    
    private double getBucketComparisonValue(Stack stack, Bucket bucket) {
		ArrayList<Double> values = new ArrayList<>();
		List<Stack> stacks = bucket.getStacks();
		
		for(Stack stackTest : stacks) {
		    values.add(getStackComparisonValue(stack, stackTest));
		}
		
		return getSumValues(values)/values.size();
    }
    
    private double getStackComparisonValue(Stack stackTest, Stack stack) {
		ArrayList<Double> values = new ArrayList<>();
		List<StackElement> stackElements = stack.getElements();
		List<StackElement> stackElementTests = stackTest.getElements();
		
		for(StackElement element : stackElements) {
		    double bestValue = 0;
		    
		    for(StackElement elementTest : stackElementTests) {
				double currentValue = StringSimilarity.similarity(element.getSource(), elementTest.getSource());
				if(currentValue > bestValue) {
				    bestValue = currentValue;
				}
		    }
		    
		    values.add(bestValue);
		}
		
		return getSumValues(values)/values.size();
    }
    
    private double getSumValues(List<Double> values) {
		double res = 0;
		
		for(Double value : values) {
		    res += value;
		}
		
		return res;
    }
}
