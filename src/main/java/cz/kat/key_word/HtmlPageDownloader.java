package cz.kat.key_word;

import com.aspose.html.HTMLDocument;
import com.aspose.html.converters.Converter;

import com.aspose.html.saving.PdfSaveOptions;
import com.aspose.pdf.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlPageDownloader {
    private File html = new File("download.html");
    private String url = "";

    public String getUrl() {
        return url;
    }

    public Document downloadWebPage(String pageUrl) throws Exception {
        try {
            url = pageUrl;
            URL url = new URL(pageUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            BufferedWriter writer = new BufferedWriter(new FileWriter(html));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }

            reader.close();
            writer.close();
            System.out.println("Success");
        } catch (MalformedURLException ex) {
            System.out.println("malformed URL ex raised");
        } catch (IOException ie) {
            System.out.println("IOEx raised");
        }
        //Hází mi NoClassDefFoundError a nevím proč
        HTMLDocument doc = new HTMLDocument("download.html");
        PdfSaveOptions options = new PdfSaveOptions();

        Converter.convertHTML(doc, options, "htmlToPdf.pdf");
        Document pdf = new Document("htmlToPdf.pdf");
        return pdf;
    }

    public Document downloadPage(String pageURl) {
        url = pageURl;
        //Z nějakého důvodu změní url přeposílanou v pageURL, takže nemůže stránku najít
        HTMLDocument htmlDocument = new HTMLDocument(pageURl);

        PdfSaveOptions options  = new PdfSaveOptions();

        Converter.convertHTML(htmlDocument, options, "htmlToPdf.pdf");
        Document pdf = new Document("htmlToPdf.pdf");
        return pdf;

    }

}
