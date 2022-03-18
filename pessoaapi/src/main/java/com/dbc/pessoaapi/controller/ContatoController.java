package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.contato.ContatoCreateDTO;
import com.dbc.pessoaapi.dto.contato.ContatoDTO;
import com.dbc.pessoaapi.service.ContatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
@Log
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @ApiOperation(value = "Retorna uma lista de contatos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de contatos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public List<ContatoDTO> list(){
        return contatoService.list();
    }

    @ApiOperation(value = "Retorna uma lista de contatos por idPessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de contatos por idPessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idPessoa}")
    public List<ContatoDTO> getByIdPessoa(@PathVariable("idPessoa") Integer id){
        return contatoService.getByIdPessoa(id);
    }

    @ApiOperation(value = "Cria um novo contato vinculado à uma pessoa e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um contato criado"),
            @ApiResponse(code = 201, message = "Retorna um contato criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/{idPessoa}")
    @Validated
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer idPessoa, @Valid @RequestBody ContatoCreateDTO contato) throws Exception{
        //setar idPessoa para o contato antes de passar para o service
        ContatoDTO contatoPronto = contatoService.create(idPessoa, contato);
        log.info("POST concluído");
        return new ResponseEntity<>(contatoPronto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza as informações de um contato")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um contato atualizado"),
            @ApiResponse(code = 202, message = "Retorna um contato atualizado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idContato}")
    @Validated
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id, @Valid @RequestBody ContatoCreateDTO contato) throws Exception {
        ContatoDTO contatoPronto = contatoService.update(id, contato);
        log.info("PUT concluído");
        return new ResponseEntity<>(contatoPronto, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deleta um contato e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um contato deletado"),
            @ApiResponse(code = 202, message = "Retorna um contato deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> delete(@PathVariable("idContato") Integer id) throws Exception {
        ContatoDTO contatoPronto = contatoService.delete(id);
        log.info("DELETE concluído");
        return new ResponseEntity<>(contatoPronto,HttpStatus.ACCEPTED);
    }
}
