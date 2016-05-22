
package NERsuiteDictionaryTagger;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException {
		// Change the path to the folder that contains tsv or NERsuite
		// result.features.txt
		String path = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/Result";
		String output = "/Users/AlanHo/Documents/DissertationLibrary/NER/Formatted Results(first)/(formatted)";
		String TSVformat = "/Users/AlanHo/Documents/DissertationLibrary/NER/TSV formatted/";
		String TSVpath = "/Users/AlanHo/Documents/DissertationLibrary/NER/Formatted Results(first)/";
		String indexPath = "/Users/AlanHo/Documents/DissertationLibrary/NER/NewFormatter/";
		String originalTSV = "/Users/AlanHo/Documents/University of Manchester/Dissertation/TSV";
		new IndexMatcher("/Users/AlanHo/Documents/DissertationLibrary/NER/TSV formatted/Pair1.tsv",
				"/Users/AlanHo/Documents/DissertationLibrary/NER/Formatted Results(first)/Pair1.txt")
						.fileWriter("/Users/AlanHo/Documents/DissertationLibrary/NER/Final Match/TSVindex1.txt");

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				// if (listOfFiles[i].getName().endsWith(".txt"))
				// new FormatDictionary(path + "/" + listOfFiles[i].getName(),
				// output);
				// else if (listOfFiles[i].getName().endsWith(".tsv"))
				// new FormatDictionary(originalTSV + "/" +
				// listOfFiles[i].getName(), output);
				// new FormatTSV(TSVpath + listOfFiles[i].getName(), TSVformat);

			}
		}
	}

}
