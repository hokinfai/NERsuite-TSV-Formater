import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException {

		String path = "/Users/AlanHo/Documents/DissertationLibrary/NERsuite/bin/TSV";

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".txt") || listOfFiles[i].getName().endsWith(".tsv"))
					new FormatDictionary(path + "/" + listOfFiles[i].getName());
			}
		}

	}
}
