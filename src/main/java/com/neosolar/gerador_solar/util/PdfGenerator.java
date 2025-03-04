package com.neosolar.gerador_solar.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfGenerator {

    public static void generate(int numberOfGenerators) {
        String fileName = "email_marketing.pdf";
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 14);
                content.newLineAtOffset(50, 750);
                content.showText("Assunto: Configuração de Geradores de Energia Solar");
                content.newLineAtOffset(0, -20);
                content.setFont(PDType1Font.HELVETICA, 12);
                content.showText("Prezados,");
                content.newLineAtOffset(0, -20);
                content.showText("Foram configurados " + numberOfGenerators + " geradores nesta semana.");
                content.newLineAtOffset(0, -20);
                content.showText("Segue em anexo o arquivo CSV com a composição dos geradores.");
                content.newLineAtOffset(0, -20);
                content.showText("Atenciosamente,");
                content.newLineAtOffset(0, -20);
                content.showText("[Seu Nome]");
                content.endText();
            }

            document.save(fileName);
            System.out.println("Arquivo PDF gerado: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
