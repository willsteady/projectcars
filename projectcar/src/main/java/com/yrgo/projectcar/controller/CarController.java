package com.yrgo.projectcar.controller;

import com.yrgo.projectcar.exception.ResourceNotFoundException;
import com.yrgo.projectcar.model.Car;
import com.yrgo.projectcar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CarController {
    @Autowired
    private CarRepository carRepository;
    //get cars
    @GetMapping("cars")
    public List<Car> getAllCars(){
        return this.carRepository.findAll();
    }
    //get car by id
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId)
            throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).
                orElseThrow(() -> new ResourceNotFoundException("Car not found for this id ::" + carId));
        return ResponseEntity.ok().body(car);
    }
    //save car
    @PostMapping("cars")
    public Car createCar (@RequestBody Car car){
        return this.carRepository.save(car);
    }
    //update car
    @PutMapping("cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long carId,
                                         @Valid @RequestBody Car carDetails) throws ResourceNotFoundException{
        Car car = carRepository.findById(carId).
                orElseThrow(() -> new ResourceNotFoundException("Car not found for this id ::" + carId));
        car.setName(carDetails.getName());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setPrice(carDetails.getPrice());
        return ResponseEntity.ok(this.carRepository.save(car));
    }
    //delete car
    @DeleteMapping("cars/{id}")
    public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException{
        Car car = carRepository.findById(carId).
                orElseThrow(() -> new ResourceNotFoundException("Car not found for this id ::" + carId));
        this.carRepository.delete(car);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
