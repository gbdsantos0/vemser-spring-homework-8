package com.dbc.pessoaapi.service;

import com.dbc.pessoaapi.dto.endereco.EnderecoCreateDTO;
import com.dbc.pessoaapi.dto.endereco.EnderecoDTO;
import com.dbc.pessoaapi.dto.pessoa.PessoaDTO;
import com.dbc.pessoaapi.entity.Endereco;
import com.dbc.pessoaapi.entity.Pessoa;
import com.dbc.pessoaapi.repository.EnderecoRepository;
import com.dbc.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    public List<EnderecoDTO> listAll() throws Exception{
        return enderecoRepository.listAll().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO getByIdEndereco(Integer id) throws Exception{
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRepository.getByIdEndereco(id), EnderecoDTO.class);
        return enderecoDTO;
    }

    public List<EnderecoDTO> listByIdPessoa(Integer id) throws Exception{
        return enderecoRepository.listByIdPessoa(id).stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idPessoa, EnderecoCreateDTO endereco) throws Exception{
        endereco.setIdPessoa(idPessoa);
        Pessoa pessoa = pessoaRepository.getById(endereco.getIdPessoa());
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaDTO.class);
        emailService.sendEmailMessage(pessoaDTO, "Novo endereço adicionado","Você adicionou com sucesso o seu endereço ao nosso sistema!");
        Endereco enderecoEntity = objectMapper.convertValue(endereco, Endereco.class);
        Endereco enderecoRetorno = enderecoRepository.create(enderecoEntity);


        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRetorno, EnderecoDTO.class);

        return enderecoDTO;
    }

    public EnderecoDTO update(Integer id, EnderecoCreateDTO enderecoAlterado) throws Exception{
        Pessoa pessoa = pessoaRepository.getById(enderecoAlterado.getIdPessoa());
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaDTO.class);
        emailService.sendEmailMessage(pessoaDTO, "Atualização de endereço","Você atualizou com sucesso o seu endereço em nosso sistema!");
        Endereco enderecoEntity = objectMapper.convertValue(enderecoAlterado, Endereco.class);
        Endereco enderecoRetorno = enderecoRepository.update(id,enderecoEntity);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRetorno, EnderecoDTO.class);

        return enderecoDTO;
    }

    public EnderecoDTO delete(Integer id) throws Exception{
        Pessoa pessoa = pessoaRepository.getById(enderecoRepository.getByIdEndereco(id).getIdPessoa());
        PessoaDTO pessoaDTO = objectMapper.convertValue(pessoa, PessoaDTO.class);
        emailService.sendEmailMessage(pessoaDTO, "Exclusão de endereço","Você deletou com sucesso o seu endereço de nosso sistema!");
        Endereco enderecoRetorno = enderecoRepository.delete(id);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRetorno, EnderecoDTO.class);

        return enderecoDTO;
    }
}
