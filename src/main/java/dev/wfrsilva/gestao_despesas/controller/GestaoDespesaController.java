package dev.wfrsilva.gestao_despesas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wfrsilva.gestao_despesas.custom_message.ErrorMessage;
import dev.wfrsilva.gestao_despesas.entity.Despesa;
import dev.wfrsilva.gestao_despesas.useCase.BuscarDespesaUseCase;
import dev.wfrsilva.gestao_despesas.useCase.CadastroDespesaUseCase;

@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController {
    
    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;

    @Autowired
    BuscarDespesaUseCase buscarDespesaUseCase;

    @PostMapping("/create")
    public ResponseEntity <?> create(@RequestBody Despesa despesa)
    {
        try
        {
            var result = cadastroDespesaUseCase.execute(despesa);
            return ResponseEntity.ok(result);
        }//try
        catch (IllegalArgumentException e)
        {
            var errorMessage = new ErrorMessage(e.getMessage(), "INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMessage);
            
        }//catch

    }//create
    
}//GestaoDespesaController
