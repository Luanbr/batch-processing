package com.lbr.batchprocessing.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
	private FileUtils() {
	}
		
	public static void deleteOutputFiles(String outputDirectory) throws IOException {
		Path outputPath = Paths.get(outputDirectory);

		Files.walk(outputPath)
			.map(Path::toFile)
			.forEach(File::delete);
	}
}



