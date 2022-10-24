package j.todo;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.io.File;
import java.util.Arrays;

public class TodoJ {
	
	private static Options setupOptions() {
		Options options = new Options();
		options.addOption("a", false, "add an item to the text file.");
		options.addOption("d", false, "remove an item from the text file.");
		options.addOption("lsc", false, "list out all of the contexts.");
		
		
		return options;
	}
	
	public static void createTextFile(String path, String name) throws IOException {
		File f = new File(path + name);
		f.getParentFile().mkdirs();
		f.createNewFile();
	}

	public static void main(String[] args) throws ParseException {
		Options options = setupOptions();
		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(options, args);
		

		if (line.hasOption("a")) {
			System.out.println("A has been called");
			String path = System.getProperty("user.dir");
			try {
				createTextFile(path + "/Test/", "file.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("File added.");
		} else {
			System.out.println("else called");
		}

		System.out.println(Arrays.toString(line.getOptions()));

	}
}
