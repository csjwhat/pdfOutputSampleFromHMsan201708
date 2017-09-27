/*
 * This example is part of the iText 7 tutorial.
 */
package tutorial.chapter05;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
//import com.itextpdf.test.annotations.WrapToTest;

/**
 * Simple changing page properties example.
 */
//@WrapToTest
public class C05E04_ChangePage {

    public static final String SRC = "src/main/resources/pdf/ufo.pdf";
    public static final String DEST = "results/chapter05/change_page.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E04_ChangePage().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException {

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));


        float margin = 72;
        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
            PdfPage page = pdfDoc.getPage(i);
            // change page size
            Rectangle mediaBox = page.getMediaBox();
            Rectangle newMediaBox = new Rectangle(mediaBox.getLeft() - margin, mediaBox.getBottom() - margin,
                    mediaBox.getWidth() + margin * 2, mediaBox.getHeight() + margin * 2);
            page.setMediaBox(newMediaBox);
            // add border
            PdfCanvas over = new PdfCanvas(page);
            over.setStrokeColor(Color.GRAY);
            over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
            over.stroke();
            // change rotation of the even pages
            if (i % 2 == 0) {
                page.setRotation(180);
            }
        }

        pdfDoc.close();

    }
}