package br.com.haroldo.loja.dao;

import br.com.haroldo.loja.modelo.Pedido;
import br.com.haroldo.loja.modelo.Produto;
import br.com.haroldo.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager entityManager;

    public PedidoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Pedido pedido){
        this.entityManager.persist(pedido);
    }

   public  BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return  entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
   }

    public List<RelatorioDeVendasVo> relatorioDeVendas() {
        String jpql = "SELECT new br.com.haroldo.loja.vo.RelatorioDeVendasVo("
                + "produto.nome, "
                + "SUM(item.quantidade), "
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itemPedido item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY SUM(item.quantidade) DESC";
        return entityManager.createQuery(jpql, RelatorioDeVendasVo.class)
                .getResultList();
}

    }

