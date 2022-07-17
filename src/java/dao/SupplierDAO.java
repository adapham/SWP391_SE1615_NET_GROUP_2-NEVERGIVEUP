/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Supplier;
import java.util.List;

/**
 *
 * @author KhacBao
 */
public interface SupplierDAO {
    public List<Supplier> getAllSupplier() throws Exception;
    public int getTotalSupplier() throws Exception;
    public List<Supplier> getAllSupplierBySupID(int supID) throws Exception;
    public List<Supplier> getAllSupplierWithPaging(int page, int PAGE_SIZE) throws Exception;
    public int updateSupplier(Supplier sup) throws Exception;
    public int deleteSupplier(int supID) throws Exception;
    public List<Supplier> getsearchSupplierPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception;
    public int getTotalSupplierByName(String keySearch) throws Exception;
}
