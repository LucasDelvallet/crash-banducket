package main;

import java.io.IOException;
import java.util.List;

import entities.Bucket;
import entities.Stack;
import entities.StackElement;
import process.BucketCreator;
import process.StackCreator;
import process.StoreStack;

public class Main {

    public static void main(String[] args) {
	List<Bucket> buckets = BucketCreator.GetAllBucketInFolder("./nautilus/nautilus-training/");
	StoreStack storeStack = new StoreStack(buckets);
	try {
	    Stack stack = StackCreator.getStackFromFilePath("./nautilus/nautilus-testing/10.txt"); // res = 1337
	    Bucket b = storeStack.getBestBucket(stack);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
    }
    
}
