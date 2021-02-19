package com.codingtest;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HtmlDocument {

    private final Document document;

    public static HtmlDocument fromFile(final File htmlFile) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    StandardCharsets.UTF_8.name(),
                    htmlFile.getAbsolutePath());
            return new HtmlDocument(doc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<HtmlElement> findElementById(final String id) {
        return Optional.ofNullable(this.document.getElementById(id))
                .map(HtmlElement::new);
    }

    public List<HtmlElement> getAllElements() {
        return this.document.getAllElements().stream()
                .map(HtmlElement::new)
                .collect(Collectors.toList());
    }

}
