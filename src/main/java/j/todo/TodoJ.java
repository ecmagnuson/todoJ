package j.todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class TodoJ {

    @SuppressWarnings("unused")
    private static void createTextFile(String path, String fileName) throws IOException {
	File f = new File(path + fileName);
	f.getParentFile().mkdirs();
	f.createNewFile();
    }

    private static void add(String line) {

    }

    private static String getContexts(String lines) throws IOException {
	List<String> contexts = new ArrayList<>();
	BufferedReader bufReader = new BufferedReader(new StringReader(lines));
	String line = "";
	while ((line = bufReader.readLine()) != null) {
	    String context = line.substring(line.indexOf('@'));
	    if (!contexts.contains(context)) {
		contexts.add(context);
	    }
	}
	return String.join(", ", contexts);
    }

    private static String readFile(List<String> context, String fileName) throws IOException {
	String home = System.getProperty("user.home");
	StringBuilder sb = new StringBuilder();
	int lineNum = -1; // fix this hackyness

	for (String line : Files.readAllLines(Paths.get(home + "/.todo/" + fileName))) {
	    lineNum++; // line 0 is empty to retrieve empty line when needed
	    if (line.equals("")) {
		continue;
	    }
	    if (line.contains(String.join(", ", context))) {
		sb.append(lineNum + " " + line + "\n");
	    }
	}
	return sb.toString();
    }

    private static void help(Options options) {
	HelpFormatter formatter = new HelpFormatter();
	formatter.printHelp(" ", "", options, "", true);
    }

    public static void main(String[] args) throws ParseException, IOException {
	Options options = new Options();
	options.addOption("a", false, "add an item to the text file.");
	options.addOption("d", false, "remove an item from the text file.");
	options.addOption("lsc", false, "list out all of the contexts.");
	options.addOption("ls", false, "list out all of the items to do.");
	options.addOption("lsd", false, "list out all of the done objects.");
	options.addOption("lsdc", false, "list out all of the done objects with specific context.");
	options.addOption("h", false, "list out the options and what they do.");

	CommandLineParser parser = new DefaultParser();
	CommandLine line = parser.parse(options, args);

	if (line.hasOption("")) {
	    ;
	} else if (line.hasOption(options.getOption("h"))) {
	    help(options);
	} else if (line.hasOption("ls")) {
	    System.out.println(line.getArgList());
	    System.out.println(readFile(line.getArgList(), "todo.txt"));
	} else if (line.hasOption("lsd")) {
	    System.out.println(readFile(line.getArgList(), "done.txt"));
	} else if (line.hasOption("lsc")) {
	    System.out.println(getContexts(readFile(line.getArgList(), "todo.txt")));
	}

	// System.out.println(Arrays.toString(line.getOptions()));

    }
}
