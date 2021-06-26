package br.edu.utfpr.ppgca.simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

	private static final String PATH = "E:\\resultados";

	private static FileUtil instance;

	private FileUtil() {
	}

	public static FileUtil getInstance() {
		if (instance == null) {
			instance = new FileUtil();
		}
		return instance;
	}

	public void save(final String FILE_NAME, final String CONTENT) throws IOException {
		FileWriter fileWriter = new FileWriter(PATH.concat("\\").concat(FILE_NAME).concat(".txt"));
		PrintWriter printer = new PrintWriter(fileWriter);
		printer.printf(CONTENT);
		fileWriter.close();
	}

}
