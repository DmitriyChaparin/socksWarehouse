package com.example.sockswarehouse.services;

import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;
import com.example.sockswarehouse.model.Socks;
import org.webjars.NotFoundException;

import java.util.Collection;

public  interface SocksService {
    Socks addSocks(Socks sock);

    Socks reduceSocks(Socks sock) throws NotFoundException;

    Socks showSocks(Color color, Size size);

    Collection<Socks> allSocks();
}
