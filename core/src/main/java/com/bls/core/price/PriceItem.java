package com.bls.core.price;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO javadoc is missing
// TODO use currency type
@JsonInclude(Include.NON_EMPTY)
public class PriceItem {

    @NotEmpty
    private final String name;
    @NotNull
    private final BigDecimal price;

    @JsonCreator
    public PriceItem(@JsonProperty("name") final String name, @JsonProperty("price") final BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }
}
