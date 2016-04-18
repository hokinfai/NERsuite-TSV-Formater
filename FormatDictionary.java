package NERsuiteDictionaryTaggerResultFormatted;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatDictionary {
	private String path;
	private int lines;
	private String name;
	private StringBuilder result = new StringBuilder();

	public FormatDictionary(String path) throws IOException {
		this.path = path;
 
		String[] temName = path.split("/");
		name = temName[temName.length - 1];
		lineCounts();

	}

	public void lineCounts() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(path));
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		reader.close();
		this.lines = lines;
		fileReader();
	}

	public void fileReader() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));

		try {
			String line = br.readLine();
			int counts = 1;

			while (line != null && counts < lines) {
				counts++;
				if (line.isEmpty()) {
					counts++;
					line = br.readLine();

				}
				if (name.endsWith(".txt"))
					format(line);
				else
					formatTSV(line);
				line = br.readLine();
			}
		} finally

		{
			br.close();
		}

	}

	public void format(String sentence) throws IOException {

		String[] output = new String[4];
		String[] spilt = sentence.split("\t");
		output[0] = spilt[0];
		output[1] = spilt[1];
		output[2] = spilt[2];
		if (spilt[6].equals("O") && spilt[7].equals("O")) {
			output[3] = "O";
		} else if (spilt[6].equals("B-anatomicalEntity"))
			output[3] = "B-anatomicalEntity";
		else
			output[3] = "B-taxon";
		result.append(output[0] + "\t" + output[1] + "\t" + output[2] + "\t" + output[3] + "\n");
		System.out.println(output[0] + "\t" + output[1] + "\t" + output[2] + "\t" + output[3]);
		fileWriter();
	}

	public void formatTSV(String sentence) throws IOException {

		sentence = sentence.trim();

		if (sentence.contains("XMI Reader")) {
			String[] spilt = sentence.split("\t");

			System.out.println(spilt[5] + "\t" + spilt[6] + "\t" + spilt[4] + "\t" + spilt[3] + "\n");
			result.append(spilt[5] + "\t" + spilt[6] + "\t" + spilt[4] + "\t" + spilt[3] + "\n");
			fileWriter();
		}
	}

	public void fileWriter() throws IOException {
		File file = new File("/Users/AlanHo/Documents/DissertationLibrary/Formatted Results/(formatted)" + name);
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write(result.toString());
		writer.flush();
		writer.close();
	}
}
