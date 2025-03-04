package com.neosolar.gerador_solar.controller;

import com.neosolar.gerador_solar.model.Generator;
import com.neosolar.gerador_solar.service.GeneratorService;
import com.neosolar.gerador_solar.util.CsvGenerator;
import com.neosolar.gerador_solar.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generate")
public class GenerationController {

    @Autowired
    private GeneratorService generatorService;

    // Endpoint para gerar o CSV com a configuração dos geradores
    @GetMapping("/csv")
    public String generateCsv() {
        List<Generator> generators = generatorService.configureGenerators();
        CsvGenerator.generate(generators);
        return "Arquivo CSV gerado com " + generators.size() + " geradores configurados.";
    }

    // Endpoint para gerar o PDF com o e-mail para o time de marketing
    @GetMapping("/pdf")
    public String generatePdf() {
        List<Generator> generators = generatorService.configureGenerators();
        PdfGenerator.generate(generators.size());
        return "Arquivo PDF gerado para " + generators.size() + " geradores configurados.";
    }
}
