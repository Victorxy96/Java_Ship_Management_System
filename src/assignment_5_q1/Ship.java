/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * The Ship class contains the following fields: int numShip, String nameShip,
 * double weight, int year, int capacity. The Ship class contains the following
 * methods: the constructor method Ship, getNumShip, setNumShip, getNameShip,
 * setNameShip, getWeight, setWeight, getYear, setYear, getCapacity,
 * setCapacity.
 *
 * @author xy
 */
public class Ship {

    /**
     * The ID number of the ship
     */
    public int numShip;

    /**
     * The name of the ship
     */
    public String nameShip;

    /**
     * The weight of the ship
     */
    public double weight;

    /**
     * The year built of the ship
     */
    public int year;

    /**
     * The capacity of the ship
     */
    public int capacity;

    /**
     * Constructor method for the ship. Ensure the ship by the information(id,
     * name, weight, year built and capacity) saved in the database. Ensure the
     * sailors including the supervisor and sailors through the createSailor
     * method.
     *
     * @param numShip the ID number of the ship
     * @param nameShip the name of the ship
     * @param weight the weight of the ship
     * @param year the year built of the ship
     * @param capacity the capacity of the ship
     * @throws ClassNotFoundException Do not find the class
     * @throws SQLException SQL error
     */
    public Ship(int numShip, String nameShip, double weight, int year, int capacity) throws ClassNotFoundException, SQLException {
        this.numShip = numShip;
        this.nameShip = nameShip;
        this.weight = weight;
        this.year = year;
        this.capacity = capacity;
    }

    /**
     * Get the ID number of the ship
     *
     * @return The ID number of the ship
     */
    public int getNumShip() {
        return numShip;
    }

    /**
     * Set the ID number of the ship
     *
     * @param numShip The ID number of the ship
     */
    public void setNumShip(int numShip) {
        this.numShip = numShip;
    }

    /**
     * Get the name of the ship
     *
     * @return The name of the ship
     */
    public String getNameShip() {
        return nameShip;
    }

    /**
     * Set the name of the ship
     *
     * @param nameShip The name of the ship
     */
    public void setNameShip(String nameShip) {
        this.nameShip = nameShip;
    }

    /**
     * Get the weight of the ship
     *
     * @return The weight of the ship
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the weight of the ship
     *
     * @param weight The weight of the ship
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Get the year built of the ship
     *
     * @return The year built of the ship
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year built of the ship
     *
     * @param year The year built of the ship
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the capacity of the ship
     *
     * @return The capacity of the ship
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of the ship
     *
     * @param capacity The capacity of the ship
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
