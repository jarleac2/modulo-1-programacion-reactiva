package edu.co.jhair.reactiva.modulo1.demo.controller;

import edu.co.jhair.reactiva.modulo1.demo.model.Mantenimiento;
import edu.co.jhair.reactiva.modulo1.demo.service.MantenimientoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService){
        this.mantenimientoService = mantenimientoService;
    }
    @GetMapping("/")
    public Flux<Mantenimiento> getAllMantenimientos(){
        return mantenimientoService.findAll();
    }

    @PostMapping("/")
    public Mono<Mantenimiento> saveMantenimiento(@RequestBody Mantenimiento mantenimiento){
    return mantenimientoService.saveMantenimiento(mantenimiento);
    }

    @GetMapping("/{id}")
    public Mono<Mantenimiento> getMantenimientoById(@PathVariable Integer id){
        return mantenimientoService.findById(id);
    }
}