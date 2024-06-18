package cz.kat.key_word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InputController {

    @Autowired
    GoogleSearcher googleSearcher;
    HtmlPageDownloader downloader = new HtmlPageDownloader();
    HTMLConverter converter = new HTMLConverter();

    @GetMapping("/")
    public String renderIndex(@ModelAttribute InputDTO inputDTO) {
        return "index";
    }

    @PostMapping("/")
    public String sendKeyWord(@ModelAttribute InputDTO inputDTO) throws Exception {
        String foundURL = googleSearcher.searchGoogleForKeyWord(inputDTO.getKeyWord());

        downloader.downloadWebPage(foundURL);
        converter.convertHtmlAndSave();

        return "again";
    }
}
