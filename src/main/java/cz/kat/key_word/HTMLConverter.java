package cz.kat.key_word;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

public class HTMLConverter {
    GoogleSearcher searcher = new GoogleSearcher();
    HtmlPageDownloader downloader = new HtmlPageDownloader();
    private Document docToSave;

    public void convertHtmlAndSave() throws Exception {

        String keyWord = searcher.getKeyWord();
        docToSave = downloader.downloadWebPage(keyWord);

        docToSave.save(keyWord + ".xml", SaveFormat.Xml);
    }
}
