package Projeto.Academia.builder;

import Projeto.Academia.controller.DTO.EnderecoDTO;

public final class EnderecoDTOBuilder {
    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;

    private EnderecoDTOBuilder() {
    }

    public static EnderecoDTOBuilder enderecoDTOBuilder() {
        return new EnderecoDTOBuilder();
    }

    public EnderecoDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public EnderecoDTOBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public EnderecoDTOBuilder cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecoDTOBuilder bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoDTOBuilder numero(String numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoDTOBuilder cep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoDTO build() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(id);
        enderecoDTO.setEstado(estado);
        enderecoDTO.setCidade(cidade);
        enderecoDTO.setBairro(bairro);
        enderecoDTO.setNumero(numero);
        enderecoDTO.setCep(cep);
        return enderecoDTO;
    }
}
