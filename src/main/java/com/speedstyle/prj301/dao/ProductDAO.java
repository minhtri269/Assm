/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.speedstyle.prj301.dao;

import com.speedstyle.prj301.dto.Product;
import com.speedstyle.prj301.dto.ProductSize;
import com.speedstyle.prj301.dto.Size;
import com.speedstyle.prj301.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author AvillX
 */
public class ProductDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;  
   
    private final static String NEWARRIVAL =" Select top (4) *  from dbo.Product ORDER BY product_id DESC";
    
    private final static String ALLPRODUCT = "Select * from dbo.Product";
    private final static String ALLPRODUCT_PART_SEARCH = " Where name LIKE %?%";
    private final static String ALLPRODUCT_PART_CATEGORY = " Where category LIKE ?";

    private final static String PRODUCT_DETAIL = "Select * from dbo.Product Where product_id = ?";
    private final static String SIZE = "Select * from dbo.Size";
    
    private final static String CATEGORY = "Select * from dbo.Category";
    private final static String PRODUCT_QUANTITY = "Select * from dbo.ProductSize Where product_id = ?";
    private final static String SIMILAR_PRODUCT = "Select * from dbo.Product where category = (select category from dbo.Product where product_id = ? )"
                                                        +" except Select * from dbo.Product where product_id = ?";
    
    private final static String SIZEBYID= " select * from dbo.ProductSize where product_id = ?";

    private final static String DELETE =" Delete from dbo.Product where product_id = ?";


    private final static String ADDPRODUCT = "INSERT INTO dbo.Product VALUES (?, ?, ?, ?, ?, ?) ";
    private final static String UPDATEPRODUCT = "UPDATE dbo.Product SET name = ?, category = ?, price = ?, image_link = ?, main_description = ? where product_id = ?";
    private final static String UPDATE_PRODUCT_SIZE = "UPDATE dbo.ProductSize SET size39 = ?, size40 = ?, size41 = ?, size42 = ?, size43 = ?, size44 = ? where product_id = ?";
    private final static String COUNT_PRODUCT = "SELECT COUNT(product_id) FROM dbo.Product";
    private final static String PAGING_PRODUCT = " order by product_id offset ? rows fetch next 6 rows only";

    public List<Product> pagingProduct(int index,String sortCategory,String search) {
        List<Product> list = new ArrayList();
        try {
            String query = ALLPRODUCT;
            if(!sortCategory.equals("")){
                query+= " Where category LIKE '"+sortCategory+"' ";
            }
            if(!search.equals("")){
                query+= " Where name LIKE '%"+search+"%' ";
            }
            query+=PAGING_PRODUCT;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (index - 1) * 6);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProduct(String sortCategory, String search){
        List<Product> list = new ArrayList<>();        
        try{
            String query = ALLPRODUCT;
            if(!sortCategory.equals("")){
                query+= " Where category LIKE '"+sortCategory+"'";
            }
            if(!search.equals("")){
                query+= " Where name LIKE '%"+search+"%'";
            }
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getDouble(4),
                                    rs.getString(5)));
            }
        }catch(Exception e){}
        return list;
    }


    public int CountProduct(){       
        try{
            String query = COUNT_PRODUCT;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        }catch(Exception e){}
        return 0;
    }

    public List<Product> getNewArrival(){
        List<Product> list = new ArrayList<>();       
        try{
            String query = NEWARRIVAL;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getDouble(4),
                                    rs.getString(5)));
            }
        }catch(Exception e){}
        return list;
    }
    public List<Product> getCategory(){
        List<Product> list = new ArrayList<>();       
        try{
            String query = CATEGORY;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getString(1)));
            }
        }catch(Exception e){}
        return list;
    }
    

    public Product getProductByID(String id){       
        try{
            String query = PRODUCT_DETAIL;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getDouble(4),
                                    rs.getString(5),
                                    rs.getString(6));
            }
        }catch(Exception e){}
        return null;
    }

    public List<Size> getSize(){
        List<Size> list = new ArrayList<>();
        try{
            String query = SIZE;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Size(rs.getString(1)));
            }
        }catch(Exception e){}
    return list;
    }
    public ProductSize getProductAllSize( String id){
        try{
            String query = SIZEBYID;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                return new ProductSize(Integer.parseInt(id),  
                                        rs.getInt(2),
                                        rs.getInt(3),
                                        rs.getInt(4),
                                        rs.getInt(5),
                                        rs.getInt(6),
                                        rs.getInt(7));
            }
        }catch(Exception e){}
    return null;
    }
   
    public boolean UpdateProduct(Product p) {
        String query = UPDATEPRODUCT;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, p.getName());          
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getImage_link());
            ps.setString(5, p.getMain_description());
            ps.setInt(6, p.getId());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
        }
        return false;
    }
    public boolean UpdateSizeProduct(ProductSize p) {
        String query = UPDATE_PRODUCT_SIZE;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setInt(1, p.getSize39());
            ps.setInt(2, p.getSize40());
            ps.setInt(3, p.getSize41());
            ps.setInt(4, p.getSize42());
            ps.setInt(5, p.getSize43());
            ps.setInt(6, p.getSize44());
            ps.setInt(7, p.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    public boolean addProduct(Product p) {
        String query = ADDPRODUCT;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());          
            ps.setString(3, p.getCategory());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getImage_link());
            ps.setString(6, p.getMain_description());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
        }
        return false;
    }
    public boolean addSizeProduct(ProductSize p) {
        String query = ADDPRODUCT;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, p.getSize39());
            ps.setInt(3, p.getSize40());
            ps.setInt(4, p.getSize41());
            ps.setInt(5, p.getSize42());
            ps.setInt(6, p.getSize43());
            ps.setInt(7, p.getSize44());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
   

    public List<Product> getSimilarProduct(String id){
        List<Product> list = new ArrayList<>();
        try{
            String query = SIMILAR_PRODUCT;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, id);
            rs = ps.executeQuery();          
            while(rs.next()){
                list.add(new Product(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getDouble(4),
                                    rs.getString(5)));
            }
        }catch(Exception e){}
    return list;
    }

    public ProductSize getSizeProduct(String id){
        try{
            String query = PRODUCT_QUANTITY;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                return new ProductSize(rs.getInt(1),
                                    rs.getInt(2),
                                    rs.getInt(3),
                                    rs.getInt(4),
                                    rs.getInt(5),
                                    rs.getInt(6),
                                    rs.getInt(7));
            }
        }catch(Exception e){}
    return null;
    }

    public void DeleteProduct(String id){
        try{
                String query = DELETE;
                conn = new DBUtils().getConnection();
                ps = conn.prepareStatement(query);
                ps.setString(1, id);
                ps.executeUpdate();         
        }catch(Exception e){}
    }

    public int RandomID() {
        ProductDAO dao = new ProductDAO();
        int ranNum = ThreadLocalRandom.current().nextInt(1,999999);
        Product us = dao.getProductByID(Integer.toString(ranNum));
        if (us==null){
            return ranNum;
        }
        return RandomID();
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        int n = dao.CountProduct();
        System.out.println(n);
//        ProductSize list = dao.getProductAllSize("1");
////        for(Product o :list){
//            System.out.println(list);
////        }
        
    }

   

}
