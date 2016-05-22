package NERsuiteDictionaryTagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatDictionary {
	// this program get four columns from tsv and txt files.
	private String path;
	private int lines;
	private String name;
	private StringBuilder result = new StringBuilder();
	private String outputPath;

	public FormatDictionary(String path, String outputPath) throws IOException {
		this.path = path;
		this.outputPath = outputPath;
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
		if (spilt[6].equals("O") && spilt[7].equals("O") && spilt[8].equals("O") && spilt[9].equals("O")) {
			output[3] = "O";
		} else if (spilt[6].contains("anatomicalEntity"))
			output[3] = spilt[6];
		else if (spilt[7].contains("habitat"))
			output[3] = spilt[7];
		else if (spilt[8].contains("quality"))
			output[3] = spilt[8];
		else
			output[3] = spilt[9];
		result.append(output[0] + "\t" + output[1] + "\t" + output[2] + "\t" + spilt[4] + "\t" + output[3] + "\n");
		System.out.println(output[0] + "\t" + output[1] + "\t" + output[2] + "\t" + spilt[4] + "\t" + output[3]);
		fileWriter();
	}

	public void formatTSV(String sentence) throws IOException {

		sentence = sentence.trim();

		if (sentence.contains("_InitialView")) {
			String[] spilt = sentence.split("\t");

			System.out.println(spilt[5] + "\t" + spilt[6] + "\t" + spilt[4] + "\t" + spilt[3] + "\n");
			result.append(spilt[5] + "\t" + spilt[6] + "\t" + spilt[4] + "\t" + spilt[3] + "\n");
			fileWriter();
		}
	}

	public void fileWriter() throws IOException {
		File file = new File(outputPath + name);
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
