package cz.kat.key_word;

import jakarta.validation.constraints.NotEmpty;

public class InputDTO {

    @NotEmpty
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
