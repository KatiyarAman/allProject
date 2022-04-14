package com.quokka.csvupload.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.quokka.csvupload.config.SendSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.quokka.csvupload.config.ApplicationConfig;
import com.quokka.csvupload.entities.PlaFeed;
import com.quokka.csvupload.repository.FileUploadRepository;
import com.quokka.csvupload.service.FileUploadingService;
import com.quokka.csvupload.utils.FileStorageUtils;

@Service
public class FileUploadingServiceImpl implements FileUploadingService {

	@Autowired
	FileStorageUtils fileStorageUtils;

	@Autowired
	private ApplicationConfig config;
	
	@Autowired
	FileUploadRepository fileUploadRepository;

	@Autowired
	SendSMS sendSMS;

	@Override
	public String fileUploading(MultipartFile file) {

		Pair<Boolean, String> storedPair = fileStorageUtils.storeFile(file);

		if (storedPair.getFirst()) {
			// need to read the csv file and then convert in to the entity (PlaFeed)

			// need to pass the director along with fileName
			try (Reader reader = new FileReader(config.getUploadDir() + File.separator + storedPair.getSecond())) {
			CsvToBean<PlaFeed> csvToBean=new CsvToBeanBuilder<PlaFeed>(reader)
					.withType(PlaFeed.class)
					.withIgnoreLeadingWhiteSpace(true)
					.withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
					.build();
			
			List<PlaFeed> plafeed=csvToBean.parse();
			//finally we get the entity object and now  we need to store in our database i order to do this we need to create
			//repositoty
			for(PlaFeed plaFeed1:plafeed){
				this.sendSMS.sendSms(plaFeed1.getMsn(),"9149065796");
				this.fileUploadRepository.save(plaFeed1);

			}

			//lets check it
				
			}catch (Exception e) {
				return "error occured while reading and writing the file";
			}
		}
		return null;
	}

}
