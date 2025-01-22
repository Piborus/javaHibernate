package br.com.haroldo.loja.testes;

import br.com.haroldo.loja.dao.CategoriaDao;
import br.com.haroldo.loja.dao.ClienteDao;
import br.com.haroldo.loja.dao.PedidoDao;
import br.com.haroldo.loja.dao.ProdutoDao;
import br.com.haroldo.loja.modelo.*;
import br.com.haroldo.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PeformanceConsultas {

    public static void  main(String[] ags){
        popularBancoDeDadps();
        EntityManager em = JPAUtil.getEntityManager();
//        Pedido pedido = em.find(Pedido.class,  1L);
//        System.out.println(pedido.getData());
//        System.out.println(pedido.getItemPedido().size());

        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1L);
        em.close();
        System.out.println(pedido.getCliente().getNome());
    }


    private static void  popularBancoDeDadps(){
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videgame = new Produto("PS5", "Playstation 5 ", new BigDecimal("4000"), videogames);
        Produto macbook = new Produto("Macbook", "Macbook pro", new BigDecimal("10000"), informatica);

        Cliente cliente = new Cliente("Haroldo", "123456");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, macbook));
        Pedido pedido1 = new Pedido(cliente);
        pedido1.adicionarItem(new ItemPedido(2, pedido1, videgame));

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);
        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videgame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido1);

        em.getTransaction().commit();
        em.close();


    }
}
