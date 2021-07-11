package br.edu.utfpr.ppgca.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	public void save(final String FILE_NAME, final String CONTENT, final String FOLDER) throws IOException {
		FileWriter fileWriter = new FileWriter(FOLDER.concat("\\").concat(FILE_NAME).concat(".txt"));
		PrintWriter printer = new PrintWriter(fileWriter);
		printer.printf(CONTENT);
		fileWriter.close();
	}

	public void save(final String FILE_NAME, final String CONTENT) throws IOException {
		save(FILE_NAME, CONTENT, PATH);
	}

	public File[] getFiles() {
		File folder = new File(PATH);
		return folder.listFiles();
	}

	public File[] getFiles(String path) {
		File folder = new File(path);
		return folder.listFiles();
	}

	public void mergeFiles() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"relevanceModel;memoryStrategy;engine;storing;retrieving;oblivion;comparisions;activeBeliefs;pages;utilitySum;cpuEfficiency;memEfficiency\n");
		File[] files = getFiles();
		for (File file : files) {
			stringBuilder.append(getContent(file)).append("\n");
		}
		save("merged".concat(String.valueOf(System.currentTimeMillis())).concat(".txt"), stringBuilder.toString(), "E:\\compilado");
	}

	public void mergeFiles(String path) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(
//				"relevanceModel;memoryStrategy;engine;storing;retrieving;oblivion;comparisions;activeBeliefs;pages;utilitySum;cpuEfficiency;memEfficiency\n");
		File[] files = getFiles(path);
		for (File file : files) {
			stringBuilder.append(getContent(file)).append("\n");
		}
		save("merged".concat(String.valueOf(System.currentTimeMillis())).concat(".txt"), stringBuilder.toString(), path);
	}
	
	

	public String getContent(File file) throws IOException {
		FileReader input = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(input);
		StringBuilder conteudo = new StringBuilder();
		String linha = "";
		while ((linha = bufferedReader.readLine()) != null) {
			conteudo.append(linha).append("\n");
		}
		bufferedReader.close();
		input.close();
		return conteudo.toString();
	}

	public void clear() {
		File[] files = getFiles();
		for (File file : files) {
			file.delete();
		}

	}

}
