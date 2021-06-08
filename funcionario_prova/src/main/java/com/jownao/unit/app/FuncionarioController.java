package com.jownao.unit.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.jownao.unit.domain.Funcionario;
import com.jownao.unit.domain.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/v1")
    public String home(){
        return "Está API é destinada á prova de progamação avançada! ";
    }
    //Endpoint para cadastro de funcionário pelo corpo da requisição
    @PostMapping("/v1/funcionario")
    public ResponseEntity<Object> cadastroFuncionario (@RequestBody Funcionario funcionario){
        try {
            funcionario.setDataDeCadastro(LocalDate.now());
            Funcionario funcionarioAdd = funcionarioRepository.save(funcionario);
            return ResponseEntity.ok(funcionarioAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    //Endpoint para consultar todos funcionários cadastrados
    @GetMapping("/v1/funcionarios")
    public ResponseEntity<List<Funcionario>> obterFuncionarios(){
        try {
            List funcionarios = funcionarioRepository.findAll();
            if (funcionarios != null){
                return ResponseEntity.ok(funcionarios);
            }else{
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    //Endpoint para consultar funcionario pelo nome
    @GetMapping("v1/funcionario/nome/{FunNome}")
    public ResponseEntity<Object> obterFuncionarioPor(@PathVariable("FunNome") String nome){
        try {
            Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByNomeContaining(nome);
            if (funcionarioOpt.isPresent()){
                return ResponseEntity.ok(funcionarioOpt.get());
            }else{
                return ResponseEntity.badRequest().body("Não há funcionários com esse nome!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro no endpoint");
        }
    }
    //Endpoint para alterar um funcionario pela matricula
    @PutMapping("/v1/funcionario/alterar")
    public ResponseEntity<Object> alterarCliente(@RequestBody Funcionario funcionario){
        try {
            if (funcionarioRepository.existsById(funcionario.getMatricula())){
                Funcionario funcionarioAlterado = funcionarioRepository.save(funcionario);
                return ResponseEntity.ok(funcionarioAlterado);
            } else{
                return ResponseEntity.badRequest().body("Esse cliente não existe para ser alterado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro no endpoint");
        }
    }
    //Endpoint para deletar um funcionario pela matricula
    @DeleteMapping("/v1/funcionario/{funMatricula}")
    public ResponseEntity<Boolean> removerCliente(@PathVariable("funMatricula") long matricula){
        try {
            funcionarioRepository.deleteById(matricula);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(false);
        }
    }
}
