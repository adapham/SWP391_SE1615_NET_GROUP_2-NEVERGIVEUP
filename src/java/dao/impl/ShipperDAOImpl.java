/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.ConnectDB;
import dao.ShipperDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author KhacBao
 */
public class ShipperDAOImpl extends ConnectDB implements ShipperDAO{

    public int getTotalShipper() throws Exception{
        String sql = "select COUNT(*) from Shipper";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);

            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return 0;
    }
    
}
