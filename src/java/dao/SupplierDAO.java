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
}
