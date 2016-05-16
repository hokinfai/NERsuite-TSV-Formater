package NERsuiteDictionaryTagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexMatcher {
	// this program would match the index between tsv and resultfeature;
	// a named-entity that have mulitple annoations will be merged into one;
	// reduce gold-standard's duplicate entities;
	String tsvPath;
	String txtPath;
	List<String> tsvlist = new ArrayList<String>();
	List<String> txtlist = new ArrayList<String>();
	StringBuilder str = new StringBuilder();
	StringBuilder checking = new StringBuilder();

	public IndexMatcher(String tsvPath, String txtPath) throws IOException {
		this.tsvPath = tsvPath;
		this.txtPath = txtPath;
		BufferedReader br = new BufferedReader(new FileReader(tsvPath));
		BufferedReader br2 = new BufferedReader(new FileReader(txtPath));
		try

		{
			// insert tsv file record into arraylist
			String line = br.readLine();

			while (line != null) {
				System.out.println("line: " + line);
				// System.out.println("checking: " + checking.toString());
				if (checking.toString().contains(line)) {
					System.out.println("failed:" + line);
					line = br.readLine();
				} else {

					System.out.println("new term: " + line);
					tsvlist.add(line);

					checking.append(line);
					line = br.readLine();
				}
			}
			// insert txt file record into arraylist
			String line2 = br2.readLine();
			while (line2 != null) {

				txtlist.add(line2);
				line2 = br2.readLine();
			}

		} finally

		{
			br.close();
			br2.close();
			multipleAnnotation();
			match();
			fileWriter();
		}
	}

	public void multipleAnnotation() {
		for (int i = 0; i < tsvlist.size() - 10; i++) {
			String[] split = tsvlist.get(i).split("\t");
			for (int j = i + 1; j < i + 10; j++) {
				String[] newSplit = tsvlist.get(j).split("\t");
				if (split[0].equals(newSplit[0]) && split[1].equals(newSplit[1]) && split[2].equals(newSplit[2])) {
					tsvlist.set(i, split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3] + "/" + newSplit[3]);
					tsvlist.remove(j);
				}

			}

		}
		for (String str : tsvlist)
			System.out.println(str);
	}

	public void match() {

		for (int i = 0; i < tsvlist.size(); i++) {
			String line = tsvlist.get(i);
			String[] getter = line.split("\t");
			String term = getter[2];
			// System.out.println("term: " + term);
			int begIn = Integer.parseInt(getter[0]);
			int endIn = Integer.parseInt(getter[1]);
			// System.out.println(begIn);
			for (int j = 0; j < txtlist.size(); j++) {
				String txtLine = txtlist.get(j);
				String[] txtGetter = txtLine.split("\t");
				String name = txtGetter[2];
				// System.out.println("txt: " + name);
				int beIn = Integer.parseInt(txtGetter[0]);
				int enIn = Integer.parseInt(txtGetter[1]);
				// System.out.println("beIn: " + beIn);
				if (term.trim().equals(name.trim()) && (begIn - beIn) < 5 && (begIn - beIn) >= 0) {
					System.out.println("correct");
					str.append(txtGetter[0] + "\t" + txtGetter[1] + "\t" + txtGetter[2] + "\t" + getter[3] + "\n");
				}
			}
		}
		System.out.println(str);
	}

	public void fileWriter() throws IOException {
		File file = new File("/Users/AlanHo/Documents/DissertationLibrary/NER/Final Match/TSVindex5.txt");
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write(str.toString());
		writer.flush();
		writer.close();
	}
}
