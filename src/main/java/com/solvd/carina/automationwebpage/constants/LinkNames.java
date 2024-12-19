package com.solvd.carina.automationwebpage.constants;

public enum LinkNames {
    PRODUCTS("https://automationexercise.com/products"),
    HOME("https://automationexercise.com/"),
    LOGIN("https://automationexercise.com/login");

    private final String href;

    LinkNames(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }
}
