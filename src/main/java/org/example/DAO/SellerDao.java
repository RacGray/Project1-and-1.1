package org.example.DAO;

import org.example.Model.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDao
{
    Connection conn;
    public SellerDao(Connection conn)
    {
        this.conn = conn;
    }
    public List<Seller> getAllSeller(){
        List<Seller> sellerResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Seller");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                String validName = resultSet.getString("validName");
                int id = resultSet.getInt("id");
                Seller s = new Seller (id, validName);
                sellerResults.add(s);
            }
        }catch(SQLException e){
                e.printStackTrace();
        }
        return sellerResults;
    }

    public void insertSeller(Seller s){
        try{

            PreparedStatement ps = conn.prepareStatement("insert into Seller" +
                    "(validName) values (?)");
            ps.setString(1, s.getValidName());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public Seller getSellerById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from seller where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String validName = rs.getString("validName");
                Seller s = new Seller(id, validName);
                return s;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Seller getSellerByName(String name)
    {
        try{
            PreparedStatement ps = conn.prepareStatement("select * from seller where validName = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String validName = rs.getString("validName");
                int id = rs.getInt("id");
                return new Seller(id, validName);
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /*public Seller getSellerByValidName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from seller where validName = ?");
            ps.setString(1, getSellerByValidName().getValidName());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String validName = rs.getString("validName");
                Seller s = new Seller(validName);
                return s;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }*/


}
