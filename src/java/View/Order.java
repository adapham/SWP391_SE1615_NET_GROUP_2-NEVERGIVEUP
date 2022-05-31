/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
    private int OrderID;
    private int AccountID;
    private int ShipperID;
    private Date OrderDate;
    private String Address;
    private String Email;
    private int Status;
    private String Phone;
}
