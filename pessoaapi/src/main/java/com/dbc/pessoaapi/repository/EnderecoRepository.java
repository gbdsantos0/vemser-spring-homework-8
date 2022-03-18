package com.dbc.pessoaapi.repository;

import com.dbc.pessoaapi.entity.Endereco;
import com.dbc.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class EnderecoRepository {
    private static List<Endereco> listaEnderecos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(),1,"Rua São Pedro",115,"São Lourenço do Sul","RS", "Brasil", "casa", "96170-000"));
    }

    public List<Endereco> listAll(){
        return listaEnderecos;
    }

    public Endereco getByIdEndereco(Integer id) throws Exception{
        return listaEnderecos.stream()
                .filter(e -> e.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Este ID não existe"));
    }

    public List<Endereco> listByIdPessoa(Integer id){
        return listaEnderecos.stream()
                .filter(e -> e.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }

    public Endereco create(Endereco endereco){
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }

    public Endereco update(Integer id, Endereco enderecoAlterado) throws Exception {
        Endereco enderecoRecuperado = listaEnderecos.stream()
                .filter(e -> e.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        enderecoRecuperado.setIdPessoa(enderecoAlterado.getIdPessoa());
        enderecoRecuperado.setLogradouro(enderecoAlterado.getLogradouro());
        enderecoRecuperado.setNumero(enderecoAlterado.getNumero());
        enderecoRecuperado.setCidade(enderecoAlterado.getCidade());
        enderecoRecuperado.setEstado(enderecoAlterado.getEstado());
        enderecoRecuperado.setCep(enderecoAlterado.getCep());
        enderecoRecuperado.setPais(enderecoAlterado.getPais());
        enderecoRecuperado.setTipo(enderecoAlterado.getTipo());

        return enderecoRecuperado;
    }

    public Endereco delete(Integer id) throws Exception {
        Endereco enderecoRecuperado = listaEnderecos.stream()
                .filter(e -> e.getIdEndereco().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        listaEnderecos.remove(enderecoRecuperado);

        return enderecoRecuperado;
    }
}
