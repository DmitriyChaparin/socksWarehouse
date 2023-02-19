package com.example.sockswarehouse.services.impl;

import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;
import com.example.sockswarehouse.model.Socks;
import com.example.sockswarehouse.services.SocksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksServiceImpl implements SocksService {

    final private FilesServicesImpl filesServices;
    private static Map<Integer, Socks> socks = new HashMap<>();


    public static Integer id = 0;

    public SocksServiceImpl(FilesServicesImpl filesServices) {
        this.filesServices = filesServices;
    }

    @Override
    public Socks addSocks(Socks sock) {
        for (Socks sck : socks.values()) {
            if (sck.equals(sock)) {
                sock.setQuantity(sck.getQuantity() + sock.getQuantity());
                socks.remove(id, sck);
            }
            id++;
        }
        socks.put(id, sock);
        saveToFile();
        return sock;
    }

    @Override
    public Socks reduceSocks(Socks sock) {
        for (Socks sck : socks.values()) {
            if (sck.equals(sock) && sck.getQuantity() >= sock.getQuantity()) {
                sock.setQuantity(sck.getQuantity() - sock.getQuantity());
                socks.remove(id, sck);
                socks.put(id, sock);
                saveToFile();
            } else if (sck.equals(sock) && sck.getQuantity() < sock.getQuantity()) {
                throw new RuntimeException("no");
            } else if (!sck.equals(sock)) {
                throw new RuntimeException("no");
            }
        }


        return sock;
    }

    @Override
    public Socks showSocks(Color color, Size size) {
        for (Socks sck : socks.values()) {
            if (sck.getColor().equals(color) && sck.getSize().equals(size)) {
                return sck;
            }
        }
        return null;
    }

    private void saveToFile() {
        try {
            DataFile dataFile = new DataFile(id, socks);
            String json = new ObjectMapper().writeValueAsString(dataFile);
            filesServices.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class DataFile {
        private Integer id;
        private Map<Integer, Socks> socks;
    }

    @Override
    public Collection<Socks> allSocks() {
        return socks.values();
    }
}