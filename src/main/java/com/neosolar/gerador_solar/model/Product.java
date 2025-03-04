package com.neosolar.gerador_solar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neosolar.gerador_solar.enums.ProductType;

public class Product {

    @JsonProperty("Categoria")
    private String categoria;

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Potencia em W")
    private int power;

    @JsonProperty("Produto")
    private String name;

    // Construtor padrão (necessário para o Jackson)
    public Product() {}

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPower() {
        return power;
    }
    // Determina o tipo com base na string da categoria
    public ProductType getType() {
        if (categoria != null) {
            String cat = categoria.toLowerCase();
            if (cat.contains("painel")) {
                return ProductType.PAINEL;
            } else if (cat.contains("inversor")) {
                return ProductType.INVERSOR;
            } else if (cat.contains("controlador")) {
                return ProductType.CONTROLADOR;
            }
        }
        return null;
    }
}
