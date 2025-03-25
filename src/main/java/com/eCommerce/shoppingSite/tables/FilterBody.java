package com.eCommerce.shoppingSite.tables;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterBody {
    private String search;
    private String genero;
    private List<String> subCategoria;

    public FilterBody(String search, String genero, List<String> subCategoria) {
        this.search = search;
        this.genero = genero;
        this.subCategoria = subCategoria;
    }

    public FilterBody(String search, List<String> subCategoria) {
        this.search = search;
        this.subCategoria = subCategoria;
    }
}
