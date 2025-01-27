package br.com.haroldo.loja.modelo;

import javax.persistence.Embeddable;

// @mbeddable é uma anotação que indica que a classe  que será embutida em outra classe.
@Embeddable
public class DadosPessoais {

    private String nome;

    private String cpf;

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public  DadosPessoais() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
