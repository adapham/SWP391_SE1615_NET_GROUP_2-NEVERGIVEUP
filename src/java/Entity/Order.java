/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Order {

    private int orderID;
    private int accountID;
    private int shipperID;
    private String orderDate;
    private String address;
    private String email;
    private int status;
    private String phone;
}
