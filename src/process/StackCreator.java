package process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import entities.Bucket;
import entities.Stack;

public class StackCreator {
	
		static List<Stack> listAllStackInFolder(String folderName){
			List<Stack> stacks = new ArrayList<Stack>();
			
			File folder = new File(folderName + "\\");
			String[] directories = folder.list(new FilenameFilter() {
				  @Override
				  public boolean accept(File current, String name) {
				    return new File(current, name).isDirectory();
				  }
				});
			
			for(String directory : directories){
				try {
					byte[] encoded = Files.readAllBytes(Paths.get(folderName + "\\" + directory + "\\Stacktrace.txt"));
					Stack stack = new Stack(new String(encoded));
					stacks.add(stack);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return stacks;
		}
}
