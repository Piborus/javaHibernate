package br.com.haroldo.loja.testes;

import br.com.haroldo.loja.dao.CategoriaDao;
import br.com.haroldo.loja.dao.ProdutoDao;
import br.com.haroldo.loja.loja.Categoria;
import br.com.haroldo.loja.loja.Produto;
import br.com.haroldo.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");



        Produto celular = new Produto("xiaomi Redmi", "Muito legal",
                new BigDecimal("800"), celulares);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        //Sem o MERGE
        //entityManager.getTransaction().commit();
        //entityManager.close();

        entityManager.flush();
        entityManager.clear();

        celulares = entityManager.merge(celulares);
        celulares.setNome("NOKIA");
        entityManager.flush();
        entityManager.clear();
        entityManager.remove(celulares);
        entityManager.flush();

    }
}
