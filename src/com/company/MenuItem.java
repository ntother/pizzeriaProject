package com.company;

import java.math.BigDecimal;

public interface MenuItem {
    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    String toString ();

}
