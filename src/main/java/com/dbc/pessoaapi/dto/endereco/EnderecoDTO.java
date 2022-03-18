package com.dbc.pessoaapi.dto.endereco;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    @ApiModelProperty(value = "Id do endereço")
    private Integer idEndereco;
}
