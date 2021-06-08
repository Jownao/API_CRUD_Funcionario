package com.jownao.unit.app;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.jownao.unit.domain.Cliente;
import com.jownao.unit.domain.ClienteRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/v1")
    public String home(){
        return "Esta API é para cadastro de clientes! ";
    }

    @GetMapping("/v2")
    public ResponseEntity<String> home2(){
        try{
            //throw new Exception("Erro forçado para ver o try.catch funcionando na minha API!");
            return ResponseEntity.status(200).body("Esta API é para cadastro de clientes! ");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ocorreu um erro no acesso ao home2");
        }
    }
    @GetMapping("v2/cliente/{id}")
    public ResponseEntity<String> imprimirIdCliente(@PathVariable("id") long id){
        try {
            return ResponseEntity.ok("O id enviado foi: "+id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao enviar o id: "+id);
        }
    }

    @PostMapping("/v1/cliente")
    public ResponseEntity<Cliente> enviarCliente(@RequestBody Cliente cliente){
        try {
            Cliente clienteAdicionado = clienteRepository.save(cliente);
            return ResponseEntity.ok(clienteAdicionado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping("v1/clientes")
    public ResponseEntity<List<Cliente>> obterClientes(){
        try {
            List clientes = clienteRepository.findAll();
            if (clientes != null){
                return ResponseEntity.ok(clientes);
            }else{
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("v1/cliente/id/{id}")
    public ResponseEntity<Object> obterClientePor(@PathVariable("id") long id){
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findById(id);
            if (clienteOpt.isPresent()){
                return ResponseEntity.ok(clienteOpt.get());
            }else{
                return ResponseEntity.badRequest().body("Não há cliente com esse id!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro no endpoint");
        }
    }

    @GetMapping("v1/cliente/nome/{nome}")
    public ResponseEntity<Object> obterCliente(@PathVariable("nome") String nome){
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findByNomeContaining(nome);//findByNomeContainingIgnoreCase
            if (clienteOpt.isPresent()){
                return ResponseEntity.ok(clienteOpt.get());
            }else{
                return ResponseEntity.badRequest().body("Não há cliente com esse nome!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro no endpoint");
        }
    }
    @GetMapping("v1/validacao")
    public ResponseEntity<Object> validarDado(@RequestHeader("token") BigInteger token,
    @RequestHeader("user") String user){
        BigInteger valor = new BigInteger("2312581293182391471213");
        if(token.compareTo(valor) == 0){
            return ResponseEntity.accepted().body(user);
        }else{
            return ResponseEntity.badRequest().body(false);
        }  
    }/*
    @ApiIgnore @PatchMapping("v1/aleatorio")
    public ResponseEntity<Object> umMetodoAleatorio(){
        System.out.println("Método aleatorio");
        return ResponseEntity.ok(true);
    }
    
    @DeleteMapping("v1/")
    public ResponseEntity<Boolean> deleteCliente(){
        try {
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }*/
}
