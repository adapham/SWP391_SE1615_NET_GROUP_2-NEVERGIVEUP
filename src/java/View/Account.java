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
public class Account {
    private int accountid;
    private String username;
    private String password;
    private String displayname;
    private String address;
    private String email;
    private String phone;
    private String imageURL;
    private int role;    
}
