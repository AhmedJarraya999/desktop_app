/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Jihene
 */
public class Stay {
    private int id;
    private int capacity;
    private int users_id;
    private String description;
    private Date startdateav;
    private Date enddateav;
    private String photo;
    public Stay() {
    }

    public Stay(int capacity, int users_id, String description, Date startdateav, Date enddateav) {
        this.capacity = capacity;
        this.users_id = users_id;
        this.description = description;
        this.startdateav = startdateav;
        this.enddateav = enddateav;
    }

    public Stay(int id, int capacity, int users_id, String description, Date startdateav, Date enddateav) {
        this.id = id;
        this.capacity = capacity;
        this.users_id = users_id;
        this.description = description;
        this.startdateav = startdateav;
        this.enddateav = enddateav;
    }
    
     public Stay(int id, int capacity, int users_id, String description, Date startdateav, Date enddateav, String photo) {
        this.id = id;
        this.capacity = capacity;
        this.users_id = users_id;
        this.description = description;
        this.startdateav = startdateav;
        this.enddateav = enddateav;
        this.photo=photo;
    }

    public int getUsers_id() {
        return users_id;
    }

    public Date getStartdateav() {
        return startdateav;
    }

    public Date getEnddateav() {
        return enddateav;
    }

    public String getPhoto() {
        return photo;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public void setStartdateav(Date startdateav) {
        this.startdateav = startdateav;
    }

    public void setEnddateav(Date enddateav) {
        this.enddateav = enddateav;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getusers_id() {
        return users_id;
    }

    public void setusers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getstartdateav() {
        return startdateav;
    }

    public void setstartdateav(Date startdateav) {
        this.startdateav = startdateav;
    }

    public Date getenddateav() {
        return enddateav;
    }

    public void setenddateav(Date enddateav) {
        this.enddateav = enddateav;
    }

    @Override
    public String toString() {
        return "Stay{" + "id=" + id + ", capacity=" + capacity + ", users_id=" + users_id + ", description="
                + description + ", startdateav=" + startdateav + ", enddateav=" + enddateav + '}' + "\n";
    }

}
