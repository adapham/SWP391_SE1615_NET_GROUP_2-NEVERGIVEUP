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

/**
 *
 * @author ADA
 */
@Builder
@Getter
@Setter
@ToString
public class Comments {
    private int commentID;
    private String comment;
    private int parentID;
    private int userID;
    private int articleID;
    private Boolean hide;
    private int commentDepth;
    private int childCount;
    
}
