package com.dbc.pessoaapi.dto.pessoa;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {
    @ApiModelProperty(value = "Nome da pessoa")
    @NotEmpty
    private String nome;
    @ApiModelProperty(value = "Data de nascimento da pessoa")
    @NotNull
    @Past
    private LocalDate dataNascimento;
    @ApiModelProperty(value = "CPF da pessoa")
    @CPF
    private String cpf;
    @ApiModelProperty(value = "Email da pessoa")
    @Email
    private String email;
}
