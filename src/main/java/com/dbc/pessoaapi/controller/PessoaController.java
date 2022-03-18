package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.pessoa.PessoaCreateDTO;
import com.dbc.pessoaapi.dto.pessoa.PessoaDTO;
import com.dbc.pessoaapi.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Log
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @Value("${nome}")
    private String nomeAppProperties;

    @ApiOperation(value = "Hello world!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o Hello World!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/hello")
    public String hello(){
        log.info("/hello chamado.");
        //emailService.sendEmail();
        return "Hello world! " + nomeAppProperties;
    }

    @ApiOperation(value = "Método de teste")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 202, message = "Aceito"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/teste")
    public ResponseEntity alo(){
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Cria uma nova pessoa e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma pessoa criada"),
            @ApiResponse(code = 201, message = "Retorna uma pessoa criada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    @Validated
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaCreateDTO pessoa) throws Exception{
//        return ResponseEntity.ok(pessoaService.create(pessoa));
        PessoaDTO pessoaCriada = pessoaService.create(pessoa);
        log.info("POST concluído");
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoas"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> list() throws Exception {
        List<PessoaDTO> lista = pessoaService.list();
        return new ResponseEntity<>(lista, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Retorna uma pessoa por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idPessoa}")
    public PessoaDTO getById(@PathVariable("idPessoa") Integer id) throws Exception{
        return pessoaService.getById(id);
    }

    @ApiOperation(value = "Retorna uma lista de pessoas por nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de pessoas listadas por nome"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/byname")
    public List<PessoaDTO> listByName(@RequestParam("nome") String nome) throws Exception{
        return pessoaService.listByName(nome);
    }

    @ApiOperation(value = "Atualiza as informações de uma pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma pessoa atualizada"),
            @ApiResponse(code = 202, message = "Retorna uma pessoa atualizada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idPessoa}")
    @Validated
    public ResponseEntity<PessoaDTO> update(@PathVariable("idPessoa") Integer id, @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws Exception {
        PessoaDTO pessoaAtualizada = pessoaService.update(id, pessoaAtualizar);
        log.info("PUT concluído");
        return new ResponseEntity<>(pessoaAtualizada, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deleta uma pessoa e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma pessoa deletada"),
            @ApiResponse(code = 202, message = "Retorna uma pessoa deletada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> delete(@PathVariable("idPessoa") Integer id) throws Exception {
        PessoaDTO pessoaDeletada = pessoaService.delete(id);
        log.info("DELETE concluído");
        return new ResponseEntity<>(pessoaDeletada, HttpStatus.ACCEPTED);
    }

}
