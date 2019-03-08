/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Cruise class contains the following field: int numCruise, String
 * sailDate, String returnDate, Ship ship, arraylist ports, arraylist pass,
 * arraylist sailors.The Cruise class contains the following method: the
 * constructor method Cruise, getNumCruise, setNumCruise, getSailDate,
 * setSailDate, getSailors, setSailors getReturnDate, setReturnDate, getShip,
 * setShip, getPorts, setPorts, getPass, setPass, setSailors, getSailors.
 *
 *
 *
 * @author xy
 */
public class Cruise {

    /**
     * The ID number of cruise
     */
    public int numCruise;

    /**
     * The sail date of cruise
     */
    public String sailDate;

    /**
     * The return date of cruise
     */
    public String returnDate;

    /**
     * The ship of the cruise
     */
    public Ship ship;

    /**
     * Ports of route
     */
    public ArrayList<Port> ports = new ArrayList<>();

    /**
     * Sailors of route
     */
    public ArrayList<Sailor> sailors = new ArrayList<>();

    /**
     * Passengers of route
     */
    public ArrayList<Passenger> pass = new ArrayList<>();

    /**
     * The Cruise constructor method
     *
     * @param numCruise The ID number of this cruise
     * @param sailDate The sail date of this cruise
     * @param returnDate The return date of this cruise
     * @param ship The ship of the cruise
     * @param ports Ports of this route
     * @param sailors Sailors of this route
     * @param pass Passengers of this route
     */
    public Cruise(int numCruise, String sailDate, String returnDate, Ship ship, ArrayList<Port> ports, ArrayList<Sailor> sailors, ArrayList<Passenger> pass) {
        this.numCruise = numCruise;
        this.sailDate = sailDate;
        this.returnDate = returnDate;
        this.ship = ship;
        this.ports = ports;
        this.sailors = sailors;
        this.pass = pass;
    }

    /**
     * Get the ID number of cruise
     *
     * @return The ID number of this cruise
     */
    public int getNumCruise() {
        return numCruise;
    }

    /**
     * Set the ID number of cruise
     *
     * @param numCruise The ID number of cruise
     */
    public void setNumCruise(int numCruise) {
        this.numCruise = numCruise;
    }

    /**
     * Get the sail date of cruise
     *
     * @return The sail date of cruise
     */
    public String getSailDate() {
        return sailDate;
    }

    /**
     * Set the sail date of cruise
     *
     * @param sailDate The sail date of this cruise
     */
    public void setSailDate(String sailDate) {
        this.sailDate = sailDate;
    }

    /**
     * Get the return date of cruise
     *
     * @return The return date of this cruise
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Set the return date of cruise
     *
     * @param returnDate The return date of this cruise
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Get the ship of the cruise
     *
     * @return The ship of the cruise
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Set the ship of the cruise
     *
     * @param ship input ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Get ports of route
     *
     * @return Ports of route
     */
    public ArrayList<Port> getPorts() {
        return ports;
    }

    /**
     * Set ports of route
     *
     * @param ports Ports of route
     */
    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    /**
     * Get passengers of route
     *
     * @return Passengers of route
     */
    public ArrayList<Passenger> getPass() {
        return pass;
    }

    /**
     * Set passengers of route
     *
     * @param pass Passengers of route
     */
    public void setPass(ArrayList<Passenger> pass) {
        this.pass = pass;
    }

    /**
     * Get sailors of route
     *
     * @return Sailors of route
     */
    public ArrayList<Sailor> getSailors() {
        return sailors;
    }

    /**
     * Set sailors of route
     *
     * @param sailors Sailors of route
     */
    public void setSailors(ArrayList<Sailor> sailors) {
        this.sailors = sailors;
    }

}
