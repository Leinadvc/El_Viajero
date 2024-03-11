/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Hotels {
   
   private String name;
   private String address;
   private String classification;
   private String comforts;

    public Hotels(String name, String address, String classification, String comforts) {
        this.name = name;
        this.address = address;
        this.classification = classification;
        this.comforts = comforts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getComforts() {
        return comforts;
    }

    public void setComforts(String comforts) {
        this.comforts = comforts;
    }
   
   
}
