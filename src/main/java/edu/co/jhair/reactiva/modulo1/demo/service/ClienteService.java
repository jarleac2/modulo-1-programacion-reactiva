package edu.co.jhair.reactiva.modulo1.demo.service;


import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import edu.co.jhair.reactiva.modulo1.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ClienteService {

    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    public Mono<Cliente> findById(Integer id){
        return clienteRepository.findById(id);
    }
    public Flux<Cliente> findAll(){
        return clienteRepository.findAll();
    }
    public Mono<Cliente> save(Cliente cliente){
        cliente.setActivo(Boolean.TRUE);
        return clienteRepository.save(cliente);
    }
    public Flux<Cliente> findByNombre(String nombre){
        return clienteRepository.findByNombre(nombre);
    }

    public Flux<Cliente> findByNombreActivo(String nombre, boolean activo){
        return clienteRepository.findByNombreAndActivo(nombre, activo);
    }
    public Flux<Cliente> findByActivo(boolean activo){
        return clienteRepository.findByActivo(activo);
    }

    public Mono<Cliente> deleteById(Integer id){
        return clienteRepository.findById(id).flatMap(cliente -> clienteRepository.deleteById(cliente.getId())).thenReturn(null);
    }

    public Mono<Void> deleteAll(){
        return clienteRepository.deleteAll();
    }

    public Mono<Cliente> update(int id, Cliente cliente){
        return clienteRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty()).flatMap(optionalCliente -> {
            if(optionalCliente.isPresent()){
                cliente.setId(id);
                cliente.setApellido(optionalCliente.get().getApellido());
                cliente.setNombre(optionalCliente.get().getNombre());
                cliente.setTelefono(optionalCliente.get().getTelefono());
                return clienteRepository.save(cliente);
            }
            return Mono.empty();
        });
    }
}
