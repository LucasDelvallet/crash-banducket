package process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import entities.Stack;
import parser.StackParser;

public class StackCreator {

	public static List<Stack> GetAllStackInFolder(String folderName) {
		List<Stack> stacks = new ArrayList<Stack>();

		File folder = new File(folderName + File.separator);
		String[] directories = folder.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});

		for (String directory : directories) {
			try {
				stacks.add(getStackFromFilePath(
						folderName + File.separator + directory + File.separator + "Stacktrace.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return stacks;
	}

	public static Stack getStackFromFilePath(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		String source = new String(encoded);
		return new Stack(source, StackParser.getAllStackElement(source));
	}
}
