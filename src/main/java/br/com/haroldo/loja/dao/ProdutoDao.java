package br.com.haroldo.loja.dao;

import br.com.haroldo.loja.loja.Produto;

import javax.persistence.EntityManager;

public class ProdutoDao {

    private EntityManager entityManager;

    public ProdutoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto){
        this.entityManager.persist(produto);
    }
}
