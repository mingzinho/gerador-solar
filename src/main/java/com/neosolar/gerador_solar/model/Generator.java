package com.neosolar.gerador_solar.model;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    private int generatorId; // Número de 5 dígitos
    private int ratedPower;  // Potência definida para o gerador (em Watts)
    private List<Component> components = new ArrayList<>();

    public Generator(int generatorId, int ratedPower) {
        this.generatorId = generatorId;
        this.ratedPower = ratedPower;
    }

    public void addComponent(Product product, int quantity) {
        components.add(new Component(product, quantity));
    }

    public int getGeneratorId() {
        return generatorId;
    }

    public int getRatedPower() {
        return ratedPower;
    }

    public List<Component> getComponents() {
        return components;
    }

    // Soma a potência dos componentes (produto da potência pelo número de itens)
    public int getTotalPower() {
        return components.stream()
                .mapToInt(c -> c.getProduct().getPower() * c.getQuantity())
                .sum();
    }

    // Validação da configuração conforme regras:
    // - Contém 1 painel (ou mais) do mesmo modelo, 1 inversor e 1 controlador.
    // - Soma das potências dos componentes igual à potência do gerador.
    public boolean isValidConfiguration() {
        boolean hasPanel = false, hasInversor = false, hasControlador = false;
        Product firstPanel = null;
        for (Component c : components) {
            if (c.getProduct().getType().toString().equals("PAINEL")) {
                hasPanel = true;
                if (firstPanel == null) {
                    firstPanel = c.getProduct();
                } else if (c.getProduct().getId() != firstPanel.getId()) {
                    return false; // Painéis de modelos diferentes não são permitidos.
                }
            } else if (c.getProduct().getType().toString().equals("INVERSOR")) {
                hasInversor = true;
            } else if (c.getProduct().getType().toString().equals("CONTROLADOR")) {
                hasControlador = true;
            }
        }
        return hasPanel && hasInversor && hasControlador && (getTotalPower() == ratedPower);
    }

    // Classe interna para representar cada componente (produto + quantidade)
    public static class Component {
        private Product product;
        private int quantity;

        public Component(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }
        public int getQuantity() {
            return quantity;
        }
    }
}
