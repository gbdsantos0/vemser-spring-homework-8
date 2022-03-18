package com.dbc.pessoaapi.dto.endereco;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoCreateDTO {
    @ApiModelProperty(value = "Id da pessoa dentro do endereço")
    private Integer idPessoa;
    @ApiModelProperty(value = "logradouro")
    @NotEmpty
    @Size(max=250)
    private String logradouro;
    @ApiModelProperty(value = "numero do endereço")
    @NotNull
    @Min(0)
    private Integer numero;
    @ApiModelProperty(value = "cidade")
    @NotEmpty
    @Size(max=250)
    private String cidade;
    @ApiModelProperty(value = "estado")
    @NotNull
    private String estado;
    @ApiModelProperty(value = "pais")
    @NotNull
    private String pais;
    @ApiModelProperty(value = "tipo do endereço")
    @NotNull
    private String tipo;
    @ApiModelProperty(value = "cep")
    @NotEmpty
    @Size(max=8)
    private String cep;
}
