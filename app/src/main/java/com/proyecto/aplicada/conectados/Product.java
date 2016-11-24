package com.proyecto.aplicada.conectados;

/**
 * Created by Garro on 21/11/2016.
 */

public class Product {
    private int id;
    private String name;
    private String price;
    private String descripcion;

    public Product(int id, String name, String price, String descripcion) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.descripcion = descripcion;
    }
    public Product( String name, String price, String descripcion) {

        this.name = name;
        this.price = price;
        this.descripcion = descripcion;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String  price) {
        this.price = price;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
