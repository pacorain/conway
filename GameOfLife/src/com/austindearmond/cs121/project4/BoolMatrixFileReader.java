package com.austindearmond.cs121.project4;

import java.io.*;

public class BoolMatrixFileReader {
	FileInputStream inputStream;
	BufferedReader reader;
	int width = 0;
	int height = 0;
	char t = 'X';
	
	public BoolMatrixFileReader(String s) throws IOException {
		File file = new File(s);
		inputStream = new FileInputStream(file);
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		reader = new BufferedReader(streamReader);
	}
	
	public void setWidth(int value) {
		width = value;
	}
	
	public void setHeight(int value) {
		height = value;
	}
	
	public void setTrueCharacter(char value) {
		t = value;
	}


	public boolean[][] readFile() {
		// Put file contents into a string
		String contents = null;
		try {
			contents = reader.readLine();
			for (String l = reader.readLine(); l != null; l = reader.readLine()) {
				contents += "\n" + l;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Set parameters that haven't yet been set.
		if (width == 0) {
			width = contents.substring(0, contents.indexOf('\n')).length();
		}
		if (height == 0) {
			height = contents.length() - contents.replace("\n", "").length()
					+ 1;
		}
		// Initialize the boolean array
		boolean[][] result = new boolean[height][width];
		// Fill each cell appropriately
		int origin = 0;
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				if (contents.charAt(origin + j) != '\n')
					result[i][j] = contents.charAt(origin + j) == t;
				else
					break;
			}
			origin = contents.indexOf('\n', origin + 1) + 1;
		}
		close();
		return result;
	}

	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
}