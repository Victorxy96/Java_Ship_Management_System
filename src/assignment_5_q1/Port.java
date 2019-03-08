/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment_5_q1;

/**
 * The Port class contains the following field: String namePort, String
 * nationPort, double population, boolean passRequire, double fee, boolean
 * isUsed. The Port class contains the following method: the constructor method
 * Port, getNamePort, setNamePort, getNationPort, setNationPort, getPopulation,
 * setPopulation, isPassRequire, setPassRequire, getFee, setFee, isIsUsed,
 * setIsUsed.
 *
 * @author xy
 */
public class Port {

    /**
     * The name of the port
     */
    public String namePort;

    /**
     * The nation of the port
     */
    public String nationPort;

    /**
     * The population of the port
     */
    public double population;

    /**
     * Whether the port requires the passport
     */
    public boolean passRequire;

    /**
     * The fee of the port
     */
    public double fee;

    /**
     * Whether the port is used before
     */
    public boolean isUsed;

    /**
     * Constructor method for the port. Ensure the ports by their
     * information(name, nation, population, whether require the passport, fee
     * and whether used before) saved in the data base.
     *
     * @param namePort the name of the port
     * @param nationPort the nation of the port
     * @param population the population of the port
     * @param passRequire whether the port requires the passport
     * @param fee the fee of the port
     * @param isUsed whether the port is used before
     */
    public Port(String namePort, String nationPort, double population, boolean passRequire, double fee, boolean isUsed) {
        this.namePort = namePort;
        this.nationPort = nationPort;
        this.population = population;
        this.passRequire = passRequire;
        this.fee = fee;
        this.isUsed = isUsed;
    }

    /**
     * Get the name of the port
     *
     * @return The name of the port
     */
    public String getNamePort() {
        return namePort;
    }

    /**
     * Set the name of the port
     *
     * @param namePort The name of the port
     */
    public void setNamePort(String namePort) {
        this.namePort = namePort;
    }

    /**
     * Get the nation of the port
     *
     * @return The nation of the port
     */
    public String getNationPort() {
        return nationPort;
    }

    /**
     * Set the nation of the port
     *
     * @param nationPort The nation of the port
     */
    public void setNationPort(String nationPort) {
        this.nationPort = nationPort;
    }

    /**
     * Get the population of the port
     *
     * @return The population of the port
     */
    public double getPopulation() {
        return population;
    }

    /**
     * Set the population of the port
     *
     * @param population The population of the port
     */
    public void setPopulation(double population) {
        this.population = population;
    }

    /**
     * Get whether the port requires the passport
     *
     * @return Whether the port requires the passport
     */
    public boolean isPassRequire() {
        return passRequire;
    }

    /**
     * Set whether the port requires the passport
     *
     * @param passRequire Whether the port requires the passport
     */
    public void setPassRequire(boolean passRequire) {
        this.passRequire = passRequire;
    }

    /**
     * Get the fee of the port
     *
     * @return The fee of the port
     */
    public double getFee() {
        return fee;
    }

    /**
     * Set the fee of the port
     *
     * @param fee The fee of the port
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * Get whether the port is used before
     *
     * @return Whether the port is used before
     */
    public boolean isIsUsed() {
        return isUsed;
    }

    /**
     * Set whether the port is used before
     *
     * @param isUsed Whether the port is used before
     */
    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

}
