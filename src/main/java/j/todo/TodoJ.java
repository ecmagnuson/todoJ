package j.todo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class TodoJ {

    public static void createTextFile(String path, String name) throws IOException {
	File f = new File(path + name);
	f.getParentFile().mkdirs();
	f.createNewFile();
    }

    private static void help() {
	HelpFormatter formatter = new HelpFormatter();
	formatter.printHelp("", "", setupOptions(), "", true);
    }

    private static Options setupOptions() {
	Options options = new Options();
	options.addOption("a", false, "add an item to the text file.");
	options.addOption("d", false, "remove an item from the text file.");
	options.addOption("lsc", false, "list out all of the contexts.");
	options.addOption("ls", false, "list out all of the items to do.");
	options.addOption("lsd", false, "list out all of the done objects.");
	options.addOption("lsda", false, "list out all of the done objects with specific context.");
	options.addOption("h", false, "list out the options and what they do.");
	return options;
    }

    public static void main(String[] args) throws ParseException, IOException {
	Options options = setupOptions();
	CommandLineParser parser = new DefaultParser();
	CommandLine line = parser.parse(options, args);
	String path = System.getProperty("user.dir");

	help();
	
	if (line.hasOption("a")) {
	    createTextFile(path, "name");
	}
	

	System.out.println(Arrays.toString(line.getOptions()));

    }
}
