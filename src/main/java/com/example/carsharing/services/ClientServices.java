package com.example.carsharing.services;

import com.example.carsharing.models.Car;
import com.example.carsharing.models.Client;
import com.example.carsharing.repositories.CarRepository;
import com.example.carsharing.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServices {
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @Autowired
    public ClientServices(ClientRepository clientRepository, CarRepository carRepository) {
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(int id){
        return clientRepository.findById(id).orElse(null);
    }

    public void save(Client client){
        clientRepository.save(client);
    }

    public void update(int id, Client client){
        client.setId(id);
        clientRepository.save(client);
    }

    public void delete(int id){
        clientRepository.deleteById(id);
    }
}
