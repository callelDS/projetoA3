package com.a3ppj.A3deSexta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a3ppj.A3deSexta.model.Boleto;
import com.a3ppj.A3deSexta.service.BoletoService;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @GetMapping
    public List<Boleto> listarTodos() {
        return boletoService.listarTodos();
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Boleto> buscarPorHash(@PathVariable String hash) {
        Optional<Boleto> boleto = boletoService.buscarPorCodigoHash(hash);
        return boleto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Boleto criar(@RequestBody Boleto boleto) {
        return boletoService.criar(boleto);
    }

    @PutMapping("/{hash}")
    public ResponseEntity<Boleto> atualizar(@PathVariable String hash, @RequestBody Boleto novoBoleto) {
        Optional<Boleto> atualizado = boletoService.atualizar(hash, novoBoleto);
        return atualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<Void> deletar(@PathVariable String hash) {
        if (boletoService.deletar(hash)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/codigobarras/{codigo}")
public ResponseEntity<Boleto> buscarPorCodigoBarras(@PathVariable String codigo) {
    Optional<Boleto> boleto = boletoService.buscarPorCodigoBarras(codigo);
    return boleto.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
}

}