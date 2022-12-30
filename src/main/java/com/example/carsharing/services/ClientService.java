package com.example.carsharing.services;

import com.example.carsharing.models.Client;
import com.example.carsharing.repositories.ClientRepository;
import com.example.carsharing.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService implements UserDetailsService{
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(int id){
        return clientRepository.findById(id);
    }

    public Optional<Client> findByName(String name){
        return clientRepository.findByName(name);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByName(username);
        if (client.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new ClientDetails(client.get());
    }
}
