package com.a3ppj.A3deSexta.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "boletos")
public class Boleto {

    @Id
    @Column(name = "codigo_barras", length = 60, nullable = false, unique = true)
    private String codigoBarras; // ID aleatório alfanumérico

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; // relacionamento com a tabela usuarios

    @Column(nullable = false)
    private double valor;

    private String descricao;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "status_boleto", length = 20)
    private String statusBoleto = "PENDENTE";

    public Boleto() {}

    public Boleto(String codigoBarras, Usuario usuario, double valor, String descricao,
                  LocalDate dataEmissao, LocalDate dataVencimento, String statusBoleto) {
        this.codigoBarras = codigoBarras;
        this.usuario = usuario;
        this.valor = valor;
        this.descricao = descricao;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.statusBoleto = statusBoleto;
    }

    // Getters e Setters

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatusBoleto() {
        return statusBoleto;
    }

    public void setStatusBoleto(String statusBoleto) {
        this.statusBoleto = statusBoleto;
    }
}