package com.neosolar.gerador_solar.service;

import com.neosolar.gerador_solar.enums.ProductType;
import com.neosolar.gerador_solar.model.Generator;
import com.neosolar.gerador_solar.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneratorService {

    // URL dos produtos (Anexo A)
    private static final String PRODUCTS_URL = "https://case-1sbzivi17-henriques-projects-2cf452dc.vercel.app/";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Busca os produtos disponíveis a partir da URL.
     */
    public List<Product> fetchProducts() {
        Product[] products = restTemplate.getForObject(PRODUCTS_URL, Product[].class);
        List<Product> productList = new ArrayList<>();
        if (products != null) {
            for (Product p : products) {
                productList.add(p);
            }
        }
        return productList;
    }

    /**
     * Configura os geradores dinamicamente com base nos produtos disponíveis.
     *
     * Para cada combinação de (painel, inversor, controlador):
     *   - Verifica se o inversor e o controlador possuem a mesma potência.
     *   - Verifica se a potência do inversor é um múltiplo da potência do painel.
     * Se sim, determina a quantidade de painéis necessária (n = inverter.getPower() / panel.getPower()).
     *
     * A potência do gerador (ratedPower) é calculada como:
     *   ratedPower = (n × painel.getPower()) + (inversor.getPower() + controlador.getPower())
     *
     * O ID do gerador é gerado automaticamente (aqui iniciando em 10000).
     */
    public List<Generator> configureGenerators() {
        List<Product> products = fetchProducts();
        List<Generator> generators = new ArrayList<>();

        // Filtra os produtos por tipo:
        List<Product> panels = products.stream()
                .filter(p -> p.getType() == ProductType.PAINEL)
                .collect(Collectors.toList());
        List<Product> inverters = products.stream()
                .filter(p -> p.getType() == ProductType.INVERSOR)
                .collect(Collectors.toList());
        List<Product> controllers = products.stream()
                .filter(p -> p.getType() == ProductType.CONTROLADOR)
                .collect(Collectors.toList());

        int generatorIdCounter = 10000; // Geração de IDs

        // Para cada combinação de painel, inversor e controlador:
        for (Product panel : panels) {
            for (Product inverter : inverters) {
                for (Product controller : controllers) {
                    // Regra 1: O inversor e o controlador devem ter a mesma potência.
                    if (inverter.getPower() != controller.getPower()) {
                        continue;
                    }
                    // Regra 2: A potência do inversor deve ser um múltiplo da potência do painel.
                    if (inverter.getPower() % panel.getPower() != 0) {
                        continue;
                    }
                    int panelQuantity = inverter.getPower() / panel.getPower();

                    // Calcula a potência total do gerador:
                    // ratedPower = (painel.getPower() * quantidade) + inversor.getPower() + controlador.getPower()
                    int totalPower = (panel.getPower() * panelQuantity) + inverter.getPower() + controller.getPower();

                    Generator generator = new Generator(generatorIdCounter++, totalPower);
                    generator.addComponent(panel, panelQuantity);
                    generator.addComponent(inverter, 1);
                    generator.addComponent(controller, 1);

                    if (generator.isValidConfiguration()) {
                        generators.add(generator);
                    }
                }
            }
        }
        return generators;
    }
}
