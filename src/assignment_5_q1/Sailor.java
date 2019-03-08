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
import java.sql.Statement;
import java.util.Scanner;

/**
 * The sailor class contains the following fields: int numSailor, String
 * nameSailor, String dobSailor, String nationSailor, boolean isSupervisor,
 * String supervisor, String position. The sailor class contains the following
 * methods: the constructor method Sailor for the sailors and its overload
 * method for supervisor, getNumSailor, setNumSailor, getNameSailor,
 * setNameSailor, getDobSailor, setDobSailor, getNationSailor, setNationSailor,
 * isIsSupervisor, setIsSupervisor, getSupervisor, setSupervisor, getPosition,
 * setPosition.
 *
 *
 * @author xy
 */
public class Sailor {

    /**
     * The ID number of the sailor
     */
    public int numSailor;

    /**
     * The name of the sailor
     */
    public String nameSailor;

    /**
     * The date of birth of the sailor
     */
    public String dobSailor;

    /**
     * The nation of the sailor
     */
    public String nationSailor;

    /**
     * Whether the sailor is supervisor
     */
    public boolean isSupervisor;

    /**
     * The supervisor of the sailor
     */
    public String supervisor;

    /**
     * The position of the sailor
     */
    public String position;

    /**
     * Constructor for the sailors. Ensure the sailors by the information(ID,
     * name, dob, nationality and the supervisor) saved in the data base. Set
     * the boolean isSupervisor to false and set the position to Sailor.
     *
     * @param numSailor the ID number of the sailor
     * @param nameSailor the name of the sailor
     * @param dobSailor the date of birth of the sailor
     * @param nationSailor the nationality of the sailor
     * @param supervisor the supervisor of the sailor
     */
    public Sailor(int numSailor, String nameSailor, String dobSailor, String nationSailor, String supervisor) {
        this.numSailor = numSailor;
        this.nameSailor = nameSailor;
        this.dobSailor = dobSailor;
        this.nationSailor = nationSailor;
        this.isSupervisor = false;
        this.supervisor = supervisor;
        this.position = "Sailor";
    }

    /**
     * Constructor for the supervisors. Ensure the sailor by the information(ID,
     * name, dob and nationality) saved in the data base. Set the boolean
     * isSupervisor to true, set the position to Supervisor and set the
     * supervisor to N/A as there they have no supervisor.
     *
     * @param numSailor the ID number of the supervisor
     * @param nameSailor the name of the supervisor
     * @param dobSailor the date of birth of the supervisor
     * @param nationSailor the nationality of the supervisor
     */
    public Sailor(int numSailor, String nameSailor, String dobSailor, String nationSailor) {
        this.numSailor = numSailor;
        this.nameSailor = nameSailor;
        this.dobSailor = dobSailor;
        this.nationSailor = nationSailor;
        this.isSupervisor = true;
        this.supervisor = "N/A";
        this.position = "Supervisor";
    }

    /**
     * Get the ID number of the sailor
     *
     * @return The ID number of the sailor
     */
    public int getNumSailor() {
        return numSailor;
    }

    /**
     * Set the ID number of the sailor
     *
     * @param numSailor The ID number of the sailor
     */
    public void setNumSailor(int numSailor) {
        this.numSailor = numSailor;
    }

    /**
     * Get the name of the sailor
     *
     * @return The name of the sailor
     */
    public String getNameSailor() {
        return nameSailor;
    }

    /**
     * Set the name of the sailor
     *
     * @param nameSailor The name of the sailor
     */
    public void setNameSailor(String nameSailor) {
        this.nameSailor = nameSailor;
    }

    /**
     * Get the date of birth of the sailor
     *
     * @return The date of birth of the sailor
     */
    public String getDobSailor() {
        return dobSailor;
    }

    /**
     * Set the date of birth of the sailor
     *
     * @param dobSailor The date of birth of the sailor
     */
    public void setDobSailor(String dobSailor) {
        this.dobSailor = dobSailor;
    }

    /**
     * Get the nation of the sailor
     *
     * @return The nation of the sailor
     */
    public String getNationSailor() {
        return nationSailor;
    }

    /**
     * Set the nation of the sailor
     *
     * @param nationSailor The nation of the sailor
     */
    public void setNationSailor(String nationSailor) {
        this.nationSailor = nationSailor;
    }

    /**
     * Get whether the sailor is supervisor
     *
     * @return Whether the sailor is supervisor
     */
    public boolean isIsSupervisor() {
        return isSupervisor;
    }

    /**
     * Set whether the sailor is supervisor
     *
     * @param isSupervisor Whether the sailor is supervisor
     */
    public void setIsSupervisor(boolean isSupervisor) {
        this.isSupervisor = isSupervisor;
    }

    /**
     * Get the supervisor of the sailor
     *
     * @return The supervisor of the sailor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Set the supervisor of the sailor
     *
     * @param supervisor The supervisor of the sailor
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * Get the position of the sailor
     *
     * @return The position of the sailor
     */
    public String getPosition() {
        return position;
    }

    /**
     * Set the position of the sailor
     *
     * @param position The position of the sailor
     */
    public void setPosition(String position) {
        this.position = position;
    }

}
