package br.com.haroldo.loja.testes;

import br.com.haroldo.loja.dao.CategoriaDao;
import br.com.haroldo.loja.dao.ClienteDao;
import br.com.haroldo.loja.dao.PedidoDao;
import br.com.haroldo.loja.dao.ProdutoDao;
import br.com.haroldo.loja.modelo.*;
import br.com.haroldo.loja.util.JPAUtil;
import br.com.haroldo.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastraPedido {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Produto produto = produtoDao.buscarPorId(1L);
        Produto produto1 = produtoDao.buscarPorId(2L);
        Produto produto2 = produtoDao.buscarPorId(3L);

        Cliente cliente = clienteDao.buscarPorId(1L);
        em.getTransaction().begin();



        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem( new ItemPedido(10, pedido, produto) );
        pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

        Pedido pedido1 = new Pedido(cliente);
        pedido1.adicionarItem(new ItemPedido(2, pedido1, produto1));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido1);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        List<RelatorioDeVendasVo> relatitorio = pedidoDao.relatorioDeVendas();
        relatitorio.forEach(System.out::println);



        em.close();
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria infrormatica = new Categoria("INFORMATICA");


        Produto celular = new Produto("xiaomi Redmi", "Muito legal",
                new BigDecimal("800"), celulares);
        Produto videgame = new Produto("PS5", "Playstation 5 ", new BigDecimal("4000"), videogames);
        Produto macbook = new Produto("Macbook", "Macbook pro", new BigDecimal("10000"), infrormatica);

        Cliente cliente = new Cliente("Haroldo", "123456");

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);
        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(infrormatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videgame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
