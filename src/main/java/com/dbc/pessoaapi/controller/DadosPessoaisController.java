package com.dbc.pessoaapi.controller;


import com.dbc.pessoaapi.client.DadosPessoaisClient;
import com.dbc.pessoaapi.dto.DadosPessoaisDTO;
import com.dbc.pessoaapi.service.DadosPessoaisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dados-pessoais")
@RequiredArgsConstructor
@Log
public class DadosPessoaisController {

    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @GetMapping
    public List<DadosPessoaisDTO> listDadosPessoais() {
        List<DadosPessoaisDTO> listaDadosPessoais = dadosPessoaisService.listDadosPessoais();
        log.info("GET concluído");
        return listaDadosPessoais;
    }

    @PostMapping
    public DadosPessoaisDTO create(
            @RequestBody DadosPessoaisDTO dadosPessoaisDTO) throws Exception {
        DadosPessoaisDTO dadoPessoalCriado = dadosPessoaisService.create(dadosPessoaisDTO);
        log.info("POST concluído");
        return dadoPessoalCriado;
    }

    @PutMapping("/{cpf}")
    public DadosPessoaisDTO update(@PathVariable("cpf") String cpf,
                          @RequestBody DadosPessoaisDTO dto) throws Exception {
        DadosPessoaisDTO dadoPessoalAtualizado = dadosPessoaisService.update(cpf, dto);
        log.info("PUT concluído");
        return dadoPessoalAtualizado;
    }

    @GetMapping("/{cpf}")
    public DadosPessoaisDTO getByCpf(@PathVariable("cpf") String cpf){
        DadosPessoaisDTO dadoPorCpf = dadosPessoaisService.getByCpf(cpf);
        log.info("GET byCPF concluído");
        return dadoPorCpf;
    }

    @DeleteMapping("/{cpf}")
    public void delete(@PathVariable("cpf") String cpf){
        dadosPessoaisService.delete(cpf);
        log.info("DELETE concluído");
    }
}
