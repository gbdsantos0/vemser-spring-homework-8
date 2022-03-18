package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.contato.ContatoCreateDTO;
import com.dbc.pessoaapi.dto.contato.ContatoDTO;
import com.dbc.pessoaapi.entity.Contato;
import com.dbc.pessoaapi.repository.ContatoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ContatoDTO create(Integer idPessoa, ContatoCreateDTO contato) throws Exception{
        contato.setIdPessoa(idPessoa);
        pessoaRepository.getById(contato.getIdPessoa());
        Contato contatoEntity = objectMapper.convertValue(contato, Contato.class);
        Contato contatoCriado = contatoRepository.create(contatoEntity);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoCriado, ContatoDTO.class);

        return contatoDTO;
    }

    public List<ContatoDTO> list(){
        return contatoRepository.list()
                .stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoDTO update(Integer id, ContatoCreateDTO contatoAtualizar) throws Exception {
        pessoaRepository.getById(contatoAtualizar.getIdPessoa());
        Contato contatoEntity = objectMapper.convertValue(contatoAtualizar, Contato.class);
        Contato contatoAtualizado = contatoRepository.update(id, contatoEntity);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoAtualizado, ContatoDTO.class);

        return contatoDTO;
    }

    public ContatoDTO delete(Integer id) throws Exception {

        Contato contatoAtualizado = contatoRepository.delete(id);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoAtualizado, ContatoDTO.class);

        return contatoDTO;
    }

    public List<ContatoDTO> getByIdPessoa(Integer id){
        return contatoRepository.getByIdPessoa(id)
                .stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());
    }
}
