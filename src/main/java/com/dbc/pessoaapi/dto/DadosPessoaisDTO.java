package com.dbc.pessoaapi.dto;

import com.dbc.pessoaapi.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisDTO {
    private String nome;
    private String cpf;
    private String rg;
    private String cnh;
    private String nomeMae;
    private String nomePai;
    private String tituloEleitor;
    private Sexo sexo;
}