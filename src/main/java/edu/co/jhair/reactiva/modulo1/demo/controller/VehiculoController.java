package edu.co.jhair.reactiva.modulo1.demo.controller;

import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import edu.co.jhair.reactiva.modulo1.demo.repository.VehiculoRepository;
import edu.co.jhair.reactiva.modulo1.demo.service.VehiculoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    VehiculoRepository vehiculoRepository;

    VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/{id}")
    public Mono<Vehiculo> getVehiculoById(@PathVariable Integer id){
        return vehiculoService.findById(id);
    }

    @GetMapping("/")
    public Flux<Vehiculo> getAllVehiculos(){
        return vehiculoService.findAll();
    }

    @GetMapping("/{id}/{marca}")
    public Flux<Vehiculo> getVehiculosByMarca(@PathVariable String marca){
        return vehiculoService.findByMarca(marca);
    }

    @GetMapping("/{id}/{placa}")
    public Flux<Vehiculo> getVehiculosByPlaca(@PathVariable String placa){
        return vehiculoService.findByPlaca(placa);
    }

    @PostMapping("/")
    public Mono<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo){
        return vehiculoService.save(vehiculo);
    }

    @DeleteMapping("/{id}")
    public Mono<Vehiculo> deleteVehiculoById(@PathVariable Integer id){
        return vehiculoService.deleteById(id);
    }

    @DeleteMapping("/")
    public Mono<Void> deleteAllVehiculos(){
        return vehiculoService.deleteAll();
    }


}
