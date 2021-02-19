package com.codingtest;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HtmlSearch {
    public Optional<HtmlElement> findMostSimilarElement(final HtmlDocument htmlDocument, final HtmlElement searchedElement) {
        Map<HtmlElement, Long> elementScoreMap = htmlDocument.getAllElements()
                .stream()
                .collect(Collectors.toMap(Function.identity(), new ElementSimilarityScoreFunction(searchedElement)));

        return elementScoreMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
