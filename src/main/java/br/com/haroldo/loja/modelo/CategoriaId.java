package br.com.haroldo.loja.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoriaId implements Serializable {

    private static final long serialVersionUID = -5898529403548015086L;
    private String tipo;
    private String nome;

    public CategoriaId(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    public CategoriaId() {
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }
}
