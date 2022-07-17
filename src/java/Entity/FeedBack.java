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
public class FeedBack {
    private int feedbackID;
    private String feedbackContent;
    private int productID;
    private int accountID;
    private String disPlayName;
    private String imageURL;
    private String timeComment;
    private String productName;
}
