/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Account;
import java.util.List;


/**
 *
 * @author admin
 */
public interface AccountDAO {

    public List ListAllAccount() throws Exception;

    public int checkAccount(String username, String password) throws Exception;

    public Account GetDisplayAccountByUsername(String username) throws Exception;

    public Account GetDisplayNameByUsername(String username) throws Exception;

    public Account GetImageURLByUsername(String username) throws Exception;

    public Account GetAccountIDLByUsername(String username) throws Exception;

    public Account GetPasswordByUsername(String username) throws Exception;

    public List getAccountByID(int AccountID) throws Exception;

    public Account getAccountByAccountID(int id) throws Exception;

    public int updateAccount(Account acc) throws Exception;

    public int changePassword(Account acc) throws Exception;

    public int updatePasswordByEmail(Account acc) throws Exception;

    public List ListAllUserName() throws Exception;

    public List ListAllEmail() throws Exception;

    public List ListAllPhone() throws Exception;

    public Account GetAccountByEmail(String email) throws Exception;

    //public  void send(String to, String sub,String msg, final String user, final String pass) throws Exception;

    public String SendMail(Account acc) throws Exception;

    public void RegisterAccount(Account acc) throws Exception;

    public String getRandom() throws Exception;

    public List ListAccountOfCustomer() throws Exception;

    public int updateEmailCustomerByEmail(String email) throws Exception;

    public int updatePhoneCustomerByPhone(String Phone) throws Exception;

    public int deleteAccountById(String accid) throws Exception;

    public List ListAccountOfCustomerWithPagging(int page, int PAGE_SIZE) throws Exception;

    public List ListAccountOfEmployeeWithPagging(int page, int PAGE_SIZE) throws Exception;

    public List ListAccountOfShipperWithPagging(int page, int PAGE_SIZE) throws Exception;

    public int TotalAccountOfCustomer() throws Exception;

    public int TotalAccountOfEmployee() throws Exception;

    public int TotalAccountOfShipper() throws Exception;

    public int TotalAccountOfCustomerByUserName(String keysearch) throws Exception;

    public List ListAccountOfCustomerWithPaggingByUserName(int page, int PAGE_SIZE, String keysearch) throws Exception;

    public List ListAccountOfEmployeeWithPaggingByUserName(int page, int PAGE_SIZE, String keysearch) throws Exception;

    public List ListAccountOfShipperWithPaggingByUserName(int page, int PAGE_SIZE, String keysearch) throws Exception;

    public Account infoAccount(String user, String pass) throws Exception;
}
