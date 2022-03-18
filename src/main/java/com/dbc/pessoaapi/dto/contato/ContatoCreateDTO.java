package com.dbc.pessoaapi.dto.contato;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoCreateDTO {
    @ApiModelProperty(value = "Id da pessoa do contato")
    Integer idPessoa;

    @ApiModelProperty(value = "tipo de contato")
    @NotNull
    String tipoContato;

    @ApiModelProperty(value = "numero do contato")
    @NotEmpty
    @Size(max=13)
    String numero;

    @ApiModelProperty(value = "descrição do contato")
    @NotEmpty
    String descricao;
}
