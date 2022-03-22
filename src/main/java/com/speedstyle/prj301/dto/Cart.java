/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.speedstyle.prj301.dto;

import com.speedstyle.prj301.dao.ProductDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avillX
 */
public class Cart {
    public List<ProductCart> products;
    public Cart(){
        products = new ArrayList<>();
    }

    public List<ProductCart> getProductCart() {
        return products;
    }

    public void setProductCart(List<ProductCart> products) {
        this.products = products;
    }
    public int getQuantityById(int id){
        return getProductCartById(id).getQuantity();
    }

    public ProductCart getProductCartById(int id){
        for(ProductCart i: products){
            if (i.getProduct().getId()==id){
                return i; 
            }
        }
        return null;
    }

    public void addProductCart(ProductCart p, int quantity){
        if(getProductCartById(p.getProduct().getId())!=null){
            ProductCart m = getProductCartById(p.getProduct().getId());
            if(m.getSize() == p.getSize()){
                m.setQuantity(m.getQuantity()+ quantity);
            }
        }else
            products.add(p);  
    }

    public void removeProductCart(int id){
        if(getProductCartById(id)!=null){
            products.remove(getProductCartById(id));
        }
    }

    public double getTotalMoney(){
        double t=0;
        for(ProductCart i: products){
            t+=(i.getQuantity()*i.getPrice());
        }
        return t;
    }
    private Product getProductByID(int id,List<Product> list){
        for( Product i:list){
            if(i.getId()==id) return i;    
        }
        return null;
    }
    public Cart (String txt,List<Product> list){ 
        products = new ArrayList<>();     
        try{
            if(txt!=null && txt.length()!=0){
                String[] s = txt.split("_");
                for(String i:s){
                    String[] n = i.split(":");
                        int id = Integer.parseInt(n[0]);
                        int size = Integer.parseInt(n[1]);
                        int quantity = Integer.parseInt(n[2]);
                    Product p = getProductByID(id,list);
                    ProductCart t = new ProductCart (p,size,quantity,p.getPrice());
                    addProductCart(t,quantity);
                }
            }
        }catch(Exception e){
        }   
    } 
    public static void main(String[] args) {
        
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getAllProduct("","");
        String txt ="1:40:1_2:40:4";
//        String[] s = txt.split(",");
//        for(String i:s){
//                    System.out.println(i);}
        Cart cart = new Cart(txt,list);
        List<ProductCart> pCart = cart.getProductCart();
            for(ProductCart o :pCart){
                System.out.println(o.product);
            }
    }
}
