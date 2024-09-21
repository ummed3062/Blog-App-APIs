package com.nagarro.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();

		String randomId = UUID.randomUUID().toString();
		String randomName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

		String filePath = path + File.separator + randomName;

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath));
		return randomName;
	}

	@Override
	public InputStream getFile(String path, String fileName) throws FileNotFoundException {
		String filePath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(filePath);

//		we can write db logic to return inputStream
		return inputStream;
	}

}
