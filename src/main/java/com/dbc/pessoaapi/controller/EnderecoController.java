package com.dbc.pessoaapi.controller;

import com.dbc.pessoaapi.dto.endereco.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.endereco.EnderecoDTO;
import com.dbc.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
@Log
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Retorna uma lista de endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de endereços"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public List<EnderecoDTO> listAll() throws Exception{
        return enderecoService.listAll();
    }

    @ApiOperation(value = "Retorna um endereço por Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idEndereco}")
    public EnderecoDTO getByIdEndereco(@PathVariable("idEndereco") Integer id) throws Exception{
        return enderecoService.getByIdEndereco(id);
    }

    @ApiOperation(value = "Retorna uma lista de endereços por idPessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de endereços por idPessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{idPessoa}/pessoa")
    public List<EnderecoDTO> listByIdPessoa(@PathVariable("idPessoa") Integer id) throws Exception{
        return enderecoService.listByIdPessoa(id);
    }

    @ApiOperation(value = "Cria um novo endereço vinculado à uma pessoa e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço criado"),
            @ApiResponse(code = 201, message = "Retorna um endereço criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/{idPessoa}")
    @Validated
    public ResponseEntity<EnderecoDTO> create(@PathVariable("idPessoa") Integer idPessoa, @Valid @RequestBody EnderecoCreateDTO endereco) throws Exception{
        EnderecoDTO enderecoPronto = enderecoService.create(idPessoa, endereco);
        log.info("POST concluído");
        return new ResponseEntity<>(enderecoPronto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza as informações de um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço atualizado"),
            @ApiResponse(code = 202, message = "Retorna um endereço atualizado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/{idEndereco}")
    @Validated
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") Integer id, @Valid @RequestBody EnderecoCreateDTO enderecoAlterado) throws Exception{
        EnderecoDTO enderecoPronto = enderecoService.update(id, enderecoAlterado);
        log.info("PUT concluído");
        return new ResponseEntity<>(enderecoPronto, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deleta um endereço e retorna as informações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço deletado"),
            @ApiResponse(code = 202, message = "Retorna um endereço deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> delete(@PathVariable("idEndereco") Integer id) throws Exception{
        EnderecoDTO enderecoPronto = enderecoService.delete(id);
        log.info("DELETE concluído");
        return new ResponseEntity<>(enderecoPronto, HttpStatus.ACCEPTED);
    }
}
