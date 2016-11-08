package main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import entities.Bucket;
import entities.Stack;
import process.BucketCreator;
import process.StackCreator;
import process.StoreStack;

public class Main {

	private static String folderName = "nautilus";
	public static void main(String[] args) {
		List<Bucket> buckets = BucketCreator.GetAllBucketInFolder("./"+folderName+"/nautilus-training/");
		StoreStack storeStack = new StoreStack(buckets);

		try {
			File folder = new File("./"+folderName+"/nautilus-testing/");
			String[] files = folder.list(new FilenameFilter() {
				@Override
				public boolean accept(File current, String name) {
					return new File(current, name).isFile();
				}
			});

			for (String file : files) {
				Stack stack = StackCreator.getStackFromFilePath("./"+folderName+"/nautilus-testing/" + file);

				Bucket b = storeStack.getBestBucket(stack);
				System.out.println(file.substring(0, file.length() - 4) + "  -> " + b.getId());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
