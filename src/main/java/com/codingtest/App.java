package com.codingtest;

import java.io.File;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        final String originalFile = args[0];
        final String targetFile = args[1];
        final String originalElementId = args[2];

        HtmlDocument originalHtmlDocument = HtmlDocument.fromFile(new File(originalFile));
        Optional<HtmlElement> originalElement = originalHtmlDocument.findElementById(originalElementId);

        originalElement.ifPresent(original -> {
            System.out.println("Original element:");
            System.out.println(original);
            System.out.println("With path: " + original.getPath());

            HtmlDocument targetHtmlDocument = HtmlDocument.fromFile(new File(targetFile));
            HtmlSearch htmlSearch = new HtmlSearch();
            Optional<HtmlElement> mostSimilarElement = htmlSearch.findMostSimilarElement(targetHtmlDocument, original);
            mostSimilarElement.ifPresent(mostSimilar -> {
                System.out.println("The most similar found element:");
                System.out.println(mostSimilar);
                System.out.println("With path: " + mostSimilar.getPath());
            });
        });
    }
}
