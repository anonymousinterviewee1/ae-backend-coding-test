package com.codingtest;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class ElementSimilarityScoreFunction implements Function<HtmlElement, Long> {

    private final HtmlElement originalElement;

    @Override
    public Long apply(final HtmlElement htmlElement) {
        return Optional.ofNullable(htmlElement)
                .filter(this::tagNameMatches)
                .map(this::getMatchedAttributeScore)
                .orElse(0L);
    }

    private boolean tagNameMatches(final HtmlElement targetElement) {
        return this.originalElement.getTagName().equals(targetElement.getTagName());
    }

    private long getMatchedAttributeScore(final HtmlElement targetHtmlElement) {
        Map<String, String> targetAttributes = targetHtmlElement.getAttributes();
        long matchedAttributeCount = this.originalElement.getAttributes().entrySet().stream()
                .filter(originalAttribute -> Optional.ofNullable(targetAttributes.get(originalAttribute.getKey()))
                        .filter(targetAttribute -> originalAttribute.getValue().equals(targetAttribute))
                        .isPresent())
                .count();
        return matchedAttributeCount;
    }
}
