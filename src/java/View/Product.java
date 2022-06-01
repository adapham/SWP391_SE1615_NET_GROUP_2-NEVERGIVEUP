/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
public class Product {
    private int productID;
    private String productName;
    private int supplierID;
    private int categoryID;
    private int quantity;
    private double unitPrice;
    private double discount;
    private int unitInStock;
    private String description;
    private String imageURL;
    private int isActive;
    private double priceAferDiscount;
}
