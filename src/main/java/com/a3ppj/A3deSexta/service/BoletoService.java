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

    private String randomString(int tamanho) {
        StringBuilder sb = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            sb.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }

    public List<Boleto> listarTodos() {
        return boletoRepository.findAll();
    }

    public Optional<Boleto> buscarPorCodigoHash(String hash) {
        return boletoRepository.findById(hash);
    }

    public Boleto criar(Boleto boleto) {
        boleto.setCodigoHash(randomString(15));   // ID principal
        boleto.setCodigoBarras(randomString(60)); // código de barras

        return boletoRepository.save(boleto);
    }

    public Optional<Boleto> atualizar(String hash, Boleto novoBoleto) {
        return boletoRepository.findById(hash).map(b -> {
            b.setDescricao(novoBoleto.getDescricao());
            b.setValor(novoBoleto.getValor());
            b.setStatusBoleto(novoBoleto.getStatusBoleto());
            b.setDataVencimento(novoBoleto.getDataVencimento());
            return boletoRepository.save(b);
        });
    }

    public boolean deletar(String hash) {
        if (boletoRepository.existsById(hash)) {
            boletoRepository.deleteById(hash);
            return true;
        }
        return false;
    }

    public Optional<Boleto> buscarPorCodigoBarras(String codigoBarras) {
    return boletoRepository.findByCodigoBarras(codigoBarras);
}

}