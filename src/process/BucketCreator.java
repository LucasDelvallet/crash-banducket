package process;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Bucket;

public class BucketCreator {

    public static List<Bucket> GetAllBucketInFolder(String folder) {
	List<Bucket> buckets = new ArrayList<Bucket>();

	File file = new File(folder);
	String[] directories = file.list(new FilenameFilter() {
	    @Override
	    public boolean accept(File current, String name) {
		return new File(current, name).isDirectory();
	    }
	});

	for (String directory : directories) {
	    Bucket bucket = new Bucket(directory, StackCreator.GetAllStackInFolder(folder + directory));

	    buckets.add(bucket);
	}

	return buckets;
    }
}
