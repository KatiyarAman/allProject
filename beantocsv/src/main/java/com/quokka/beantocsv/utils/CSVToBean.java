package com.quokka.beantocsv.utils;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.quokka.beantocsv.model.CustomCSVMappingStrategy;

@Component
public class CSVToBean {
	@Value("${upload-dir}")
	public String uploadDir;

	// what we need list of data that are coming (Rows),
	// and we need to store the file in our system lets create application config
	public <T> Pair<Boolean, String> writeFile(List<T> rows, Class<T> className) {
		String fileName = "aman_pla_feed";

		// we need to write the data inside the file
		try (Writer writer = Files.newBufferedWriter(Paths.get(uploadDir + File.separator + fileName))) {

			final CustomCSVMappingStrategy<T> mapping = new CustomCSVMappingStrategy<>();

			mapping.setType(className);

			StatefulBeanToCsv<T> statefulBeanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withMappingStrategy(mapping).build();

			statefulBeanToCsv.write(rows);
			return Pair.of(true, "successfully");
		} catch (Exception e) {
			return Pair.of(false, "Internal Server error");
		}

	}
}
