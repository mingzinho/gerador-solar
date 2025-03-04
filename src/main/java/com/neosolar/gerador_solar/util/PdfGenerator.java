package com.neosolar.gerador_solar.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

public class PdfGenerator {

    public static void generate(int numberOfGenerators) {
        String fileName = "email_marketing.pdf";

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {

                // Adicionando logo no topo
                PDImageXObject logo = PDImageXObject.createFromFile("neosolar_energia_logo.jpg", document);
                content.drawImage(logo, 220, 720, 150, 50); // Ajuste da posição e tamanho

                // Título centralizado
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 16);
                content.newLineAtOffset(150, 680);
                content.showText("Configuração de Geradores de Energia Solar");
                content.endText();

                // Conteúdo do e-mail
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 12);
                content.newLineAtOffset(50, 630);
                content.showText("Prezados,");
                content.newLineAtOffset(0, -20);
                content.showText("Foram configurados " + numberOfGenerators + " geradores nesta semana.");
                content.newLineAtOffset(0, -20);
                content.showText("Segue em anexo o arquivo CSV com a composição dos geradores.");
                content.newLineAtOffset(0, -20);
                content.showText("Atenciosamente,");
                content.newLineAtOffset(0, -20);
                content.showText("Equipe NeoSolar");
                content.endText();
            }

            document.save(fileName);
            System.out.println("Arquivo PDF gerado: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
