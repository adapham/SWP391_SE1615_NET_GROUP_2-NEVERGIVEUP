/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author ADA
 */
public interface MessDAO {
    public List getIdCus() throws Exception;
    public List getIdCusByEmployee(int employeeID) throws Exception;
   public int getIdEmByIdCus(int IdCustomer) throws Exception;
   public String getMessByID(int IdCustomer, int IdEmployee) throws Exception;
   public int insertMess(int IdCustomer, int IdEmployee, String content) throws Exception;
  public int updateMess(int IdCustomer, int IdEmployee, String content) throws Exception;
}
