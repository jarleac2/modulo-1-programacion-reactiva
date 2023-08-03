package edu.co.jhair.reactiva.modulo1.demo.controller;

import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import edu.co.jhair.reactiva.modulo1.demo.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public Mono<Cliente> getClienteById(@PathVariable Integer id){
        return clienteService.findById(id);
    }

    @GetMapping("/")
    public Flux<Cliente> getAllClientes(){
        return clienteService.findAll();
    }
    @GetMapping("/{nombre}")
    public Flux<Cliente> getClientesByNombre(@PathVariable String nombre){
        return clienteService.findByNombre(nombre);
    }
    @GetMapping("/{id}/{nombre}")
    public Flux<Cliente> getClientesByActivoByNombre(@PathVariable String nombres, @PathVariable Boolean activo){
        return clienteService.findByNombreActivo(nombres, activo);
    }

    @PostMapping("/")
    public Mono<Cliente> createCliente(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/")
    public Mono<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public Mono<Cliente> deleteClienteById(@PathVariable Integer id){
        return clienteService.deleteById(id);
    }

    @DeleteMapping("/")
    public Mono<Void> deleteAllClientes(){
        return clienteService.deleteAll();
    }

}
