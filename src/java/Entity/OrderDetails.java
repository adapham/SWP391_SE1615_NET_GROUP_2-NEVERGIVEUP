/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class OrderDetails {

    private int orderID;
    private double price;
    private int quantity;
    private double discount;
    private String displayname;
    private String address;
    private String email;
    private String phone;
    private String orderDate;
    private String productName;
    private double total;
    private int status;
    private int productID;
}
