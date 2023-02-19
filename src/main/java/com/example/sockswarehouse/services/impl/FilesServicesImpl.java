package com.example.sockswarehouse.services.impl;

import com.example.sockswarehouse.services.FilesServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServicesImpl implements FilesServices {
    @Value("${path.to.data.file}")
    private String dataFailPath;

    @Value("${name.of.data.file}")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFailPath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFailPath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
