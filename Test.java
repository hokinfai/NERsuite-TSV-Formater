
package NERsuiteDictionaryTagger;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException {
		// Change the path to the folder that contains tsv or NERsuite
		// result.features.txt
		String path = "/Users/AlanHo/Documents/DissertationLibrary/TSV";
		String output = "/Users/AlanHo/Documents/DissertationLibrary/Formatted Results/(formatted)";

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".txt") || listOfFiles[i].getName().endsWith(".tsv"))
					new FormatDictionary(path + "/" + listOfFiles[i].getName(), output);
			}
		}

	}
}
