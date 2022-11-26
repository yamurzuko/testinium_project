package testinium_project.helper;

import java.text.NumberFormat;
import java.text.ParseException;

public class ProductPriceInfo {
    public Number oldPrice;
    public Number newPrice;

    public ProductPriceInfo(String oldPrice, String newPrice) {

        NumberFormat currency = NumberFormat.getInstance();

        try {
            this.oldPrice = currency.parse(oldPrice);
            this.newPrice = currency.parse(newPrice);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }
}
