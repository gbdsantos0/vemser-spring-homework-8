package com.dbc.pessoaapi.dto.pessoa;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PessoaDTO extends PessoaCreateDTO{
    @ApiModelProperty(value = "Id da pessoa")
    private Integer idPessoa;
}
