package com.example.sockswarehouse.services;

public interface FilesServices {
    boolean saveToFile(String json);

    boolean cleanDataFile();
}
