package com.quokka.csvupload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class ApplicationConfig {

	@Value("${upload-dir}")
	private String uploadDir;
}
