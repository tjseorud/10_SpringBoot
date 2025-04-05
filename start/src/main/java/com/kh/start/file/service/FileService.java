package com.kh.start.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.exception.FileUploadException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	
	private final Path fileLocation;
	
	public FileService() {
		this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
	}
	
	public String store(MultipartFile file) {
		// 이름 바꾸는 메소드 호출하기 (생략됨)
		String originalFileName = file.getOriginalFilename();
		
		Path tagetLocation = this.fileLocation.resolve(originalFileName);
		log.info("저장경로 {}",tagetLocation);
		try {
			Files.copy(file.getInputStream(), tagetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost/uploads/" + originalFileName;
		}catch (IOException e) {
			throw new FileUploadException("파일이 이상함");
		}
		
	}
}
