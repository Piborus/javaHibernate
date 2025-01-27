package br.com.haroldo.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Informatica extends Produto {

    private String marca;

    private Integer modelo;

    public Informatica() {
    }

    public Informatica(Integer modelo, String marca) {
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }
}
