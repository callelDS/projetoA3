package com.a3ppj.A3deSexta.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a3ppj.A3deSexta.model.Boleto;
import com.a3ppj.A3deSexta.repository.BoletoRepository;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository boletoRepository;

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private String gerarCodigoBarras() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }

    public List<Boleto> listarTodos() {
        return boletoRepository.findAll();
    }

    public Optional<Boleto> buscarPorCodigo(String codigo) {
        return boletoRepository.findById(codigo);
    }

    public Boleto criar(Boleto boleto) {
        boleto.setCodigoBarras(gerarCodigoBarras());
        return boletoRepository.save(boleto);
    }

    public Optional<Boleto> atualizar(String codigo, Boleto novoBoleto) {
        return boletoRepository.findById(codigo).map(b -> {
            b.setDescricao(novoBoleto.getDescricao());
            b.setValor(novoBoleto.getValor());
            b.setStatusBoleto(novoBoleto.getStatusBoleto());
            b.setDataVencimento(novoBoleto.getDataVencimento());
            return boletoRepository.save(b);
        });
    }

    public boolean deletar(String codigo) {
        if (boletoRepository.existsById(codigo)) {
            boletoRepository.deleteById(codigo);
            return true;
        }
        return false;
    }
}