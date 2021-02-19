package com.codingtest;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.jsoup.nodes.Element;

import java.util.*;

@ToString
@AllArgsConstructor
public class HtmlElement {

    private Element element;

    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new HashMap<>();
        this.element.attributes()
                .forEach(attribute -> attributes.put(attribute.getKey(), attribute.getValue()));
        return attributes;
    }

    public String getTagName() {
        return this.element.tagName();
    }

    public String getPath() {
        final String parentElementPath = getParentElementPath();
        return parentElementPath + "/" + this.element.tagName();
    }

    private String getParentElementPath() {
        StringBuilder path = new StringBuilder();
        List<Element> parents = new ArrayList<>(this.element.parents());
        Collections.reverse(parents);
        parents.stream().forEachOrdered(element -> path.append("/" + element.tagName()));
        return path.toString();
    }
}
