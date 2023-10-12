package api.workout.arquivo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class ArquivoPdf {

    public void tranformarEmPdf(final String enderecoArquivo){
        try (FileInputStream pdfFile = new FileInputStream(enderecoArquivo)){

            PDDocument document = PDDocument.load(pdfFile);

            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(document);

            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String base64Pdf(final String enderecoArquivo) {
        try {
            File pdfFile = new File(enderecoArquivo);
            FileInputStream fileInputStream = new FileInputStream(pdfFile);
            byte[] pdfBytes = new byte[(int) pdfFile.length()];
            fileInputStream.read(pdfBytes);
            fileInputStream.close();

            byte[] base64Encoded = Base64.getEncoder().encode(pdfBytes);

            return new String(base64Encoded);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
