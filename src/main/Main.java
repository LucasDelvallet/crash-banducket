package main;

import java.util.List;

import entities.Bucket;
import process.BucketCreator;

public class Main {

	public static void main(String[] args) {
		List<Bucket> buckets = BucketCreator.GetAllBucketInFolder("./nautilus/nautilus-training/");
	}

}
