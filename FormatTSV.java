package NERsuiteDictionaryTagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FormatTSV {
	// this program separate entities in tsv file into single token with BIO annotation. 
	String path;
	private static final String REGEX = "(.*)(\\s|,|\\t|-|\\.)(.*)";
	String name;
	private String outputPath;
	StringBuilder finalResult = new StringBuilder();

	public FormatTSV(String path, String outputPath) throws IOException {
		this.path = path;
		this.outputPath = outputPath;
		String[] temName = path.split("/");
		name = temName[temName.length - 1];
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {

			String line = br.readLine();

			while (line != null) {

				if (name.endsWith(".tsv"))
					formating(line);
				// System.out.println(line);
				line = br.readLine();
			}
		} finally

		{
			br.close();
		}
	}

	public void formating(String line) throws IOException {
		// String abc = "1878 1986 The Amphibians of Kansas. By Hobart M. Smith.
		// The American Midland Naturalist, vol. 15, pp. 377-528, 1934.
		// Citation";
		String[] spliter = line.split("\t");
		String term = spliter[2];
		String annotation = spliter[3];
		int begIn = Integer.parseInt(spliter[0]);
		int endIn = Integer.parseInt(spliter[1]);
		System.out.println(term.length());

		if (spliter[2].matches(REGEX)) {

			ArrayList<String> list = new ArrayList<String>();
			String result = "";
			String pun = "";
			System.out.println("truetrue");
			for (int j = 0; j < term.length(); j++) {
				if (Character.isLetterOrDigit(term.charAt(j))) {
					if (!pun.equals("")) {
						list.add(pun);
						System.out.println("pun1:" + pun);
						pun = "";
					}
					result = result + term.charAt(j);
					System.out.println("result: " + result);
				} else {
					if (!result.equals("")) {
						list.add(result);
						result = "";
					}

					pun = pun + term.charAt(j);
					System.out.println("pun: " + pun);
				}

			}
			list.add(result);

			int[] index = new int[list.size()];
			index[0] = begIn;
			int[] end = new int[list.size()];
			for (int x = 0; x < list.size() - 1; x++) {
				end[x] = index[x] + list.get(x).length();
				index[x + 1] = end[x];
			}
			end[list.size() - 1] = endIn;
			for (int y = 0; y < list.size(); y++) {

				if (!list.get(y).matches("\\s*")) {
					if (y == 0) {
						finalResult.append(index[y] + "\t" + end[y] + "\t" + list.get(y) + "\tB-" + annotation + "\n");
					} else
						finalResult.append(index[y] + "\t" + end[y] + "\t" + list.get(y) + "\tI-" + annotation + "\n");
				}
			}
			System.out.println(finalResult);
		} else {
			finalResult.append(begIn + "\t" + endIn + "\t" + term + "\tB-" + annotation + "\n");
		}
		fileWriter();
	}

	public void fileWriter() throws IOException {
		File file = new File(outputPath + name);
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write(finalResult.toString());
		writer.flush();
		writer.close();
	}
}
