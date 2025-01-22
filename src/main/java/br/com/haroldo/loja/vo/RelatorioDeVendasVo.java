package br.com.haroldo.loja.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RelatorioDeVendasVo {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDateTime dataUltimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDateTime dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDateTime getDataUltimaVenda() {
        return dataUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';
    }
}
