package com.example.carsharing.services;

import com.example.carsharing.models.Car;
import com.example.carsharing.repositories.CarRepository;
import com.example.carsharing.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public CarService(CarRepository carRepository, ClientRepository clientRepository){
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Optional<Car> findById(int id){
        return carRepository.findById(id);
    }

    @Transactional
    public void addCar(Car car){
        clientRepository.findById(car.getClient().getId()).ifPresent(client -> client.getCars().add(car));
        carRepository.save(car);
    }

    @Transactional
    public void updateCar(int id, Car car){
        car.setId(id);
        clientRepository.findById(car.getClient().getId()).ifPresent(client -> client.getCars().add(car));
        carRepository.save(car);
    }
}
