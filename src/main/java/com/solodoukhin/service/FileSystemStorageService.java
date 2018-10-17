package com.solodoukhin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Author: Solodoukhin Viktor
 * Date: 21.09.18
 * Description: Implementation of the StorageService interface for storing files on disk
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final static Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try
        {
            Files.createDirectory(rootLocation);
        } catch (IOException e){
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        logger.info("Call to FileSystemStorageService.store");
        try{
            if(file.isEmpty()) {
                logger.error("Failed to store empty file.");
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }

            if(!Files.exists(this.rootLocation.resolve(file.getOriginalFilename())))
            {
                System.out.println("File doesn't exist yet. We will create it : " + file.getOriginalFilename());
                Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
                return file.getOriginalFilename();
            }
            else
            {
                String fileName;
                String[] tmp;
                if(file.getOriginalFilename() != null)
                {
                    // if file = 'brol.foo.bar.txt' => 'brol.foo.bar2.txt'
                    tmp = file.getOriginalFilename().split("\\.");

                    for(int i = 0; i<10; i++)   // Try to find a file name 10 times
                    {
                        if(tmp.length >= 2)
                        {
                            fileName = String.join(".", Arrays.copyOf(tmp, tmp.length -1)) + i + "." + tmp[tmp.length - 1];

                            if(!Files.exists(this.rootLocation.resolve(fileName)))
                            {
                                logger.info("We will create the file with name = " + fileName);
                                Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
                                return fileName;
                            }
                        }
                        else
                        {
                            logger.error("Split file name on '.' created unusable array : " + Arrays.asList(tmp));
                            return null;
                        }
                    }
                }
                else
                {
                    logger.error("Original file name not found....");
                    return null;
                }
            }
        }
        catch (IOException e)
        {
            logger.error("Failed to store file.", e);
            throw new StorageException("Failed to store file = " + file.getOriginalFilename(), e);
        }
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
