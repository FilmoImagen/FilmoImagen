package mx.edu.uttt.patern.object.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author KarinaVR
 */
public class ConexionBD {
    Connection con= null;
    
    public Connection obtenerConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/PELICULAS","root","1234");
            
        }catch(ClassNotFoundException e){
            System.err.println(e.getMessage());
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            return con;
        }
    }    
}
