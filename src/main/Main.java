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
	public static boolean TEST = false;
	public static int TESTNUMBER = 13;
	public static int METHODNUMBER;

	public static void main(String[] args) {
		String folderName = "";

		if (TEST) {
			folderName = "eval/test" + TESTNUMBER;
		} else {
			folderName = "nautilus";
		}

		List<Bucket> buckets = BucketCreator.GetAllBucketInFolder("./" + folderName + "/nautilus-training/");
		StoreStack storeStack = new StoreStack(buckets);

		try {
			File folder = new File("./" + folderName + "/nautilus-testing/");
			String[] files = folder.list(new FilenameFilter() {
				@Override
				public boolean accept(File current, String name) {
					return new File(current, name).isFile();
				}
			});

			if (TEST) {
				for (int i = 1; i < 5; i++) {
					System.out.println("Methode " + i);
					METHODNUMBER = i;
					for (String file : files) {
						Stack stack = StackCreator
								.getStackFromFilePath("./" + folderName + "/nautilus-testing/" + file);

						Bucket b = storeStack.getBestBucket(stack);
						System.out.println(file.substring(0, file.length() - 4) + "  -> " + b.getId());
					}
				}
			} else {
				for (String file : files) {
					Stack stack = StackCreator.getStackFromFilePath("./" + folderName + "/nautilus-testing/" + file);

					Bucket b = storeStack.getBestBucket(stack);
					System.out.println(file.substring(0, file.length() - 4) + "  -> " + b.getId());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
