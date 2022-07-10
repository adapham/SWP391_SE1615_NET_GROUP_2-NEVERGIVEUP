/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.FeedBack;
import Entity.Intouch;
import java.util.List;

/**
 *
 * @author admin
 */
public interface FeedbackDAO {

    public List ListFeedBackByProductID(int productID) throws Exception;

    public void InsertFeedBack(FeedBack feeback) throws Exception;

    public void InsertIntouch(Intouch intouch) throws Exception;

    public void deleteFeedbackByProductID(int pID) throws Exception;
}
