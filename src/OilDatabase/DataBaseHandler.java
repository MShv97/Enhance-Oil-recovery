/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OilDatabase;

import OilFieldsList.OilField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MSh
 */
public class DataBaseHandler {

    private static DataBaseHandler handler = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DataBaseHandler() {
        createConnection();
        setupOilTable();
    }

    public static DataBaseHandler getInstance() {
        if (handler == null) {
            handler = new DataBaseHandler();
        }
        return handler;
    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    void setupOilTable() {
        String Table_Name = "OIL_TABLE";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, Table_Name.toUpperCase(), null);
            if (!tables.next()) {
                String s = "CREATE TABLE OIL_TABLE("
                        + "	ID INTEGER primary key,\n"
                        + "	NAME varchar(100),\n"
                        + "	API float,\n"
                        + "	VICOSITY float,\n"
                        + "	K float,\n"
                        + "	SATURATION float,\n"
                        + "	DEPTH float,\n"
                        + "	THICKNESS float,\n"
                        + "	TEMPRATURE float,\n"
                        + "	LITHOLOGY varchar(100),\n"
                        + "	GASCAP boolean)";
                stmt.execute(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Can't Create database", "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {

        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception at execQuery:dataHandler\n" + ex.getLocalizedMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
        }
    }

    public boolean execDelete(OilField of) {
        try {
            String deleteStmt = "Delete FROM OIL_TABLE WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(deleteStmt);
            stmt.setString(1, Integer.toString(of.getId()));
            int res = stmt.executeUpdate();
            return (res > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean execUpdate(OilField of) {
        try {
            String update = "UPDATE OIL_TABLE SET NAME=?, API=?, VICOSITY=?, K=?, SATURATION=?, DEPTH=?,THICKNESS=?, TEMPRATURE=?, LITHOLOGY=?, GASCAP=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setString(1, of.getName());
            stmt.setString(2, Float.toString(of.getApi()));
            stmt.setString(3, Float.toString(of.getVicosity()));
            stmt.setString(4, Float.toString(of.getK()));
            stmt.setString(5, Float.toString(of.getSaturation()));
            stmt.setString(6, Float.toString(of.getDepth()));
            stmt.setString(7, Float.toString(of.getThickness()));
            stmt.setString(8, Float.toString(of.getTemprature()));
            stmt.setString(9, of.getLithology());
            stmt.setString(10, of.getGasCap().equals("Yes") ? "true" : "false");
            stmt.setString(11, Integer.toString(of.getId()));
            int res = stmt.executeUpdate();
            return (res > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

}
