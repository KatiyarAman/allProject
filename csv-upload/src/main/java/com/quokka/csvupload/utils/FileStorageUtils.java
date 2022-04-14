package com.quokka.csvupload.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.quokka.csvupload.config.ApplicationConfig;

@Component
public class FileStorageUtils {

	@Autowired
	private ApplicationConfig config;

	private static Path fileStorageLocation;

	private static final String FileType = "csv";

	@PostConstruct
	public void setPath() {
		fileStorageLocation = Paths.get(config.getUploadDir());
	}

	public Pair<Boolean, String> storeFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		// checking file type
		if (fileName.endsWith("." + FileType)) {
			// removing the suffix .csv
			fileName = fileName.substring(0, fileName.indexOf("."));
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

				return Pair.of(true, fileName);
			} catch (IOException e) {
				return Pair.of(false, "error occured while storing the file");
			}

		} else {
			return Pair.of(false, "Invalid fileType");
		}
	}
}
