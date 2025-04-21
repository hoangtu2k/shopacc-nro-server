package com.example.shopaccnro.shopaccnroserver.service;

import com.example.shopaccnro.shopaccnroserver.entity.Server;
import com.example.shopaccnro.shopaccnroserver.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public List<Server> getAllServer() {
        return serverRepository.getAllServer();
    }

}
