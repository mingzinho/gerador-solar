package com.neosolar.gerador_solar.util;

import com.neosolar.gerador_solar.model.Generator;
import com.neosolar.gerador_solar.model.Generator.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvGenerator {

    public static void generate(List<Generator> generators) {
        String fileName = "geradores_configurados.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Cabeçalho conforme Anexo C
            writer.append("ID_Gerador,Potencia,ID_Produto,Nome_Produto,Quantidade\n");
            for (Generator gen : generators) {
                for (Component comp : gen.getComponents()) {
                    writer.append(String.valueOf(gen.getGeneratorId())).append(",");
                    writer.append(String.valueOf(gen.getRatedPower())).append(",");
                    writer.append(String.valueOf(comp.getProduct().getId())).append(",");
                    writer.append(comp.getProduct().getName()).append(",");
                    writer.append(String.valueOf(comp.getQuantity())).append("\n");
                }
            }
            writer.flush();
            System.out.println("Arquivo CSV gerado: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
