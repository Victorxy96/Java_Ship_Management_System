/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The passenger class contains the following field: int numPassenger, String
 * namePassenger, String homePassenger, String nationPassenger, String
 * dobPasseger, double money, double rate, int age. The passenger class contains
 * the following method: the constructor method Passenger, generateRate, setAge
 * and its overload method, getNumPassenger, setNumPassenger, getNamePassenger,
 * setNamePassenger, getHomePassenger, setHomePassenger, getNationPassenger,
 * setNationPassenger, getDobPasseger, setDobPasseger, getMoney, setMoney,
 * getRate, setRate, getAge, setAge.
 *
 * @author xy
 */
public class Passenger {

    /**
     * The ID number of the passenger
     */
    public int numPassenger;

    /**
     * The name of the passenger
     */
    public String namePassenger;

    /**
     * The home address of the passenger
     */
    public String homePassenger;

    /**
     * The nation of the passenger
     */
    public String nationPassenger;

    /**
     * The date of birth of the passenger
     */
    public String dobPasseger;

    /**
     * The money spent by the passenger
     */
    public double money;

    /**
     * Rate of passenger
     */
    public double rate;

    /**
     * Age of the passenger
     */
    public int age;

    /**
     * Constructor method for the passenger. Ensure the passenger by the money
     * they spent on the cruise and their information(ID number, name, home
     * address, nation) saved in the database. The rating of the passenger is
     * generated randomly through the generateRate method. The age of the
     * passenger is calculated by his/her date of birth saved in the database.
     *
     * @param numPassenger the ID number of the passenger
     * @param namePassenger the name of the passenger
     * @param homePassenger the home address of the passenger
     * @param nationPassenger the nation of the passenger
     * @param dobPasseger the date of birth of the passenger
     * @param money the money spent by the passenger
     */
    public Passenger(int numPassenger, String namePassenger, String homePassenger, String nationPassenger, String dobPasseger, double money) {
        this.numPassenger = numPassenger;
        this.namePassenger = namePassenger;
        this.homePassenger = homePassenger;
        this.nationPassenger = nationPassenger;
        this.dobPasseger = dobPasseger;
        this.money = money;
        generateRate();
        setAge(dobPasseger);
    }

    /**
     * Generate the the rating of the passengers for 6 questions randomly from
     * 0-10, and set their average number as the final rating for this cruise.
     */
    public void generateRate() {

        DecimalFormat df = new DecimalFormat("0.0");
        Random rand = new Random();
        // generate the answer for the 6 questions
        int[] response = new int[6];
        int sum = 0;
        for (int i = 0; i < response.length; i++) {
            response[i] = rand.nextInt(10) + 1;
            sum += response[i];
        }
        // calculate the final rate score on average
        this.rate = Double.parseDouble(df.format((sum * 1.0) / response.length));
    }

    /**
     * Set the age of the passenger based on their date of birth savedin the
     * data base.
     *
     * @param str the date of birth of the passenger
     */
    public void setAge(String str) {
        String[] strs = str.split("/");
        int yearBorn = Integer.parseInt(strs[0]);
        this.age = 2018 - yearBorn + 1;
    }

    /**
     * Get the ID number of the passenger
     *
     * @return The ID number of the passenger
     */
    public int getNumPassenger() {
        return numPassenger;
    }

    /**
     * Set the ID number of the passenger
     *
     * @param numPassenger The ID number of the passenger
     */
    public void setNumPassenger(int numPassenger) {
        this.numPassenger = numPassenger;
    }

    /**
     * Get the name of the passenger
     *
     * @return The name of the passenger
     */
    public String getNamePassenger() {
        return namePassenger;
    }

    /**
     * Set the name of the passenger
     *
     * @param namePassenger The name of the passenger
     */
    public void setNamePassenger(String namePassenger) {
        this.namePassenger = namePassenger;
    }

    /**
     * Get the home address of the passenger
     *
     * @return The home address of the passenger
     */
    public String getHomePassenger() {
        return homePassenger;
    }

    /**
     * Set the home address of the passenger
     *
     * @param homePassenger The home address of the passenger
     */
    public void setHomePassenger(String homePassenger) {
        this.homePassenger = homePassenger;
    }

    /**
     * Get the nation of the passenger
     *
     * @return The nation of the passenger
     */
    public String getNationPassenger() {
        return nationPassenger;
    }

    /**
     * Set the nation of the passenger
     *
     * @param nationPassenger The nation of the passenger
     */
    public void setNationPassenger(String nationPassenger) {
        this.nationPassenger = nationPassenger;
    }

    /**
     * Get the date of birth of the passenger
     *
     * @return The date of birth of the passenger
     */
    public String getDobPasseger() {
        return dobPasseger;
    }

    /**
     * Set the date of birth of the passenger
     *
     * @param dobPasseger The date of birth of the passenger
     */
    public void setDobPasseger(String dobPasseger) {
        this.dobPasseger = dobPasseger;
    }

    /**
     * Get the money spent by the passenger
     *
     * @return The money spent by the passenger
     */
    public double getMoney() {
        return money;
    }

    /**
     * Set the money spent by the passenger
     *
     * @param money The money spent by the passenger
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Get rate of passenger
     *
     * @return Rate of passenger
     */
    public double getRate() {
        return rate;
    }

    /**
     * Set rate of passenger
     *
     * @param rate Rate of passenger
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Get age of the passenger
     *
     * @return Age of the passenger
     */
    public int getAge() {
        return age;
    }

    /**
     * Set age of the passenger from user input
     *
     * @param age Age of the passenger
     */
    public void setAge(int age) {
        this.age = age;
    }

}
