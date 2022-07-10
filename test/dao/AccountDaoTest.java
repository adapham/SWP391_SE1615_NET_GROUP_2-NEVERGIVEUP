/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.impl.AccountDAOImpl;
import Entity.Account;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class AccountDaoTest {
    
    public AccountDaoTest() {
    }

    /**
     * Test of ListAllAccount method, of class AccountDAOImpl.
     */
    @Test
    public void testListAllAccount() {
        System.out.println("ListAllAccount");
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAllAccount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAccount method, of class AccountDAOImpl.
     */
    @Test
    public void testCheckAccount() {
        System.out.println("checkAccount");
        String username = "";
        String password = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.checkAccount(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetDisplayAccountByUsername method, of class AccountDAOImpl.
     */
    @Test
    public void testGetDisplayAccountByUsername() {
        System.out.println("GetDisplayAccountByUsername");
        String username = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.GetDisplayAccountByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetDisplayNameByUsername method, of class AccountDAOImpl.
     */
    @Test
    public void testGetDisplayNameByUsername() {
        System.out.println("GetDisplayNameByUsername");
        String username = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.GetDisplayNameByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetImageURLByUsername method, of class AccountDAOImpl.
     */
    @Test
    public void testGetImageURLByUsername() {
        System.out.println("GetImageURLByUsername");
        String username = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.GetImageURLByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetAccountIDLByUsername method, of class AccountDAOImpl.
     */
    @Test
    public void testGetAccountIDLByUsername() {
        System.out.println("GetAccountIDLByUsername");
        String username = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.GetAccountIDLByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetPasswordByUsername method, of class AccountDAOImpl.
     */
    @Test
    public void testGetPasswordByUsername() {
        System.out.println("GetPasswordByUsername");
        String username = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.GetPasswordByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountByID method, of class AccountDAOImpl.
     */
    @Test
    public void testGetAccountByID() {
        System.out.println("getAccountByID");
        int AccountID = 0;
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.getAccountByID(AccountID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountByAccountID method, of class AccountDAOImpl.
     */
    @Test
    public void testGetAccountByAccountID() {
        System.out.println("getAccountByAccountID");
        int id = 0;
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.getAccountByAccountID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAccount method, of class AccountDAOImpl.
     */
    @Test
    public void testUpdateAccount() {
        System.out.println("updateAccount");
        Account acc = null;
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.updateAccount(acc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePassword method, of class AccountDAOImpl.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        Account acc = null;
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.changePassword(acc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePasswordByEmail method, of class AccountDAOImpl.
     */
    @Test
    public void testUpdatePasswordByEmail() {
        System.out.println("updatePasswordByEmail");
        Account acc = null;
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.updatePasswordByEmail(acc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAllUserName method, of class AccountDAOImpl.
     */
    @Test
    public void testListAllUserName() {
        System.out.println("ListAllUserName");
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAllUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAllEmail method, of class AccountDAOImpl.
     */
    @Test
    public void testListAllEmail() {
        System.out.println("ListAllEmail");
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAllEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAllPhone method, of class AccountDAOImpl.
     */
    @Test
    public void testListAllPhone() {
        System.out.println("ListAllPhone");
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAllPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetAccountByEmail method, of class AccountDAOImpl.
     */
    @Test
    public void testGetAccountByEmail() {
        System.out.println("GetAccountByEmail");
        String email = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.GetAccountByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class AccountDAOImpl.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        String to = "";
        String sub = "";
        String msg = "";
        String user = "";
        String pass = "";
        AccountDAOImpl.send(to, sub, msg, user, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SendMail method, of class AccountDAOImpl.
     */
    @Test
    public void testSendMail() {
        System.out.println("SendMail");
        Account acc = null;
        AccountDAOImpl instance = new AccountDAOImpl();
        String expResult = "";
        String result = instance.SendMail(acc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RegisterAccount method, of class AccountDAOImpl.
     */
    @Test
    public void testRegisterAccount() {
        System.out.println("RegisterAccount");
        Account acc = null;
        AccountDAOImpl instance = new AccountDAOImpl();
        instance.RegisterAccount(acc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandom method, of class AccountDAOImpl.
     */
    @Test
    public void testGetRandom() {
        System.out.println("getRandom");
        AccountDAOImpl instance = new AccountDAOImpl();
        String expResult = "";
        String result = instance.getRandom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfCustomer method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfCustomer() {
        System.out.println("ListAccountOfCustomer");
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmailCustomerByEmail method, of class AccountDAOImpl.
     */
    @Test
    public void testUpdateEmailCustomerByEmail() {
        System.out.println("updateEmailCustomerByEmail");
        String email = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.updateEmailCustomerByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePhoneCustomerByPhone method, of class AccountDAOImpl.
     */
    @Test
    public void testUpdatePhoneCustomerByPhone() {
        System.out.println("updatePhoneCustomerByPhone");
        String Phone = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.updatePhoneCustomerByPhone(Phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAccountById method, of class AccountDAOImpl.
     */
    @Test
    public void testDeleteAccountById() {
        System.out.println("deleteAccountById");
        String accid = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.deleteAccountById(accid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfCustomerWithPagging method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfCustomerWithPagging() {
        System.out.println("ListAccountOfCustomerWithPagging");
        int page = 0;
        int PAGE_SIZE = 0;
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfCustomerWithPagging(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfEmployeeWithPagging method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfEmployeeWithPagging() {
        System.out.println("ListAccountOfEmployeeWithPagging");
        int page = 0;
        int PAGE_SIZE = 0;
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfEmployeeWithPagging(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfShipperWithPagging method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfShipperWithPagging() {
        System.out.println("ListAccountOfShipperWithPagging");
        int page = 0;
        int PAGE_SIZE = 0;
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfShipperWithPagging(page, PAGE_SIZE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TotalAccountOfCustomer method, of class AccountDAOImpl.
     */
    @Test
    public void testTotalAccountOfCustomer() {
        System.out.println("TotalAccountOfCustomer");
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.TotalAccountOfCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TotalAccountOfEmployee method, of class AccountDAOImpl.
     */
    @Test
    public void testTotalAccountOfEmployee() {
        System.out.println("TotalAccountOfEmployee");
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.TotalAccountOfEmployee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TotalAccountOfShipper method, of class AccountDAOImpl.
     */
    @Test
    public void testTotalAccountOfShipper() {
        System.out.println("TotalAccountOfShipper");
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.TotalAccountOfShipper();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TotalAccountOfCustomerByUserName method, of class AccountDAOImpl.
     */
    @Test
    public void testTotalAccountOfCustomerByUserName() {
        System.out.println("TotalAccountOfCustomerByUserName");
        String keysearch = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        int expResult = 0;
        int result = instance.TotalAccountOfCustomerByUserName(keysearch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfCustomerWithPaggingByUserName method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfCustomerWithPaggingByUserName() {
        System.out.println("ListAccountOfCustomerWithPaggingByUserName");
        int page = 0;
        int PAGE_SIZE = 0;
        String keysearch = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfCustomerWithPaggingByUserName(page, PAGE_SIZE, keysearch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfEmployeeWithPaggingByUserName method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfEmployeeWithPaggingByUserName() {
        System.out.println("ListAccountOfEmployeeWithPaggingByUserName");
        int page = 0;
        int PAGE_SIZE = 0;
        String keysearch = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfEmployeeWithPaggingByUserName(page, PAGE_SIZE, keysearch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ListAccountOfShipperWithPaggingByUserName method, of class AccountDAOImpl.
     */
    @Test
    public void testListAccountOfShipperWithPaggingByUserName() {
        System.out.println("ListAccountOfShipperWithPaggingByUserName");
        int page = 0;
        int PAGE_SIZE = 0;
        String keysearch = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        List expResult = null;
        List result = instance.ListAccountOfShipperWithPaggingByUserName(page, PAGE_SIZE, keysearch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AccountDAOImpl.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AccountDAOImpl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of infoAccount method, of class AccountDAOImpl.
     */
    @Test
    public void testInfoAccount() {
        System.out.println("infoAccount");
        String user = "";
        String pass = "";
        AccountDAOImpl instance = new AccountDAOImpl();
        Account expResult = null;
        Account result = instance.infoAccount(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
