package com.neosolar.gerador_solar.scheduler;

import com.neosolar.gerador_solar.model.Generator;
import com.neosolar.gerador_solar.service.GeneratorService;
import com.neosolar.gerador_solar.util.CsvGenerator;
import com.neosolar.gerador_solar.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneratorJob {

    @Autowired
    private GeneratorService generatorService;

    // Agendado para toda segunda-feira às 8h (ajuste o cron conforme necessário)
    @Scheduled(cron = "0 0 8 * * MON")
    public void executeJob() {
        // Configura os geradores com base nos produtos disponíveis
        List<Generator> generators = generatorService.configureGenerators();

        // Gera o arquivo CSV e o PDF
        CsvGenerator.generate(generators);
        PdfGenerator.generate(generators.size());

        // Log para indicar a execução
        System.out.println("Job semanal executado. Geradores configurados: " + generators.size());
    }
}
