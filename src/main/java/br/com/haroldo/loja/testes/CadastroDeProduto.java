package br.com.haroldo.loja.testes;

import br.com.haroldo.loja.dao.CategoriaDao;
import br.com.haroldo.loja.dao.ProdutoDao;
import br.com.haroldo.loja.modelo.Categoria;
import br.com.haroldo.loja.modelo.Produto;
import br.com.haroldo.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        Produto p = produtoDao.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
        todos.forEach(produto -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("xiaomi Redmi");
        System.out.println("Preço do produto: " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");


        Produto celular = new Produto("xiaomi Redmi", "Muito legal",
                new BigDecimal("800"), celulares);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        entityManager.getTransaction().commit();
        entityManager.close();

        //Sem o MERGE
        //entityManager.getTransaction().commit();
        //entityManager.close();

        //Com o MERGE
//        entityManager.flush();
//        entityManager.clear();


//        celulares = entityManager.merge(celulares);
//        celulares.setNome("NOKIA");
//        entityManager.flush();
//        entityManager.clear();
//        entityManager.remove(celulares);
//        entityManager.flush();
    }
}
