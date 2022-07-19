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
    
    public int getTotalFeedBack() throws Exception;
    
    public List<FeedBack> getFeedBackWithPaging(int page, int PAGE_SIZE) throws Exception;
    
    public List<FeedBack> getSearchFeedBackPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception;
    
    public int getTotalFeedBackByName(String keySearch) throws Exception;
    
    public int deleteFeedBackByID(String feedbackid) throws Exception;
}
