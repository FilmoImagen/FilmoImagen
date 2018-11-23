package Control;

/**
 *
 * @author Karina VR
 */
import Modelo.Films;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO extends Modelo.Configuracion {

    private Connection conexion;
    private PreparedStatement pdst;

    public DAO() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            this.conexion = DriverManager.getConnection(URL, usuario, password);
            this.conexion.setAutoCommit(false);
            System.out.println("Conexion Abierta");
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertVenta(Films regis) {
        boolean respuesta = false;
        int exResp = 0;
        try {
            String sql = "INSERT INTO VENTAS(NOMBRE,TOTAL,PELICULAS) VALUES(?,?,?)";
            pdst = this.conexion.prepareStatement(sql);
            pdst.setString(1, regis.getCliente());
            pdst.setInt(2, regis.getPrecio());
            pdst.setInt(3, regis.getCantidad());
            exResp = pdst.executeUpdate();
            this.conexion.commit();
            if (exResp > 0) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }

    public Films ConsultaVenta(String nombre) {
        Films newcliente = new Films();
        try {
            PreparedStatement consultaVentas;
            ResultSet rs = null;
            consultaVentas = this.conexion.prepareStatement("SELECT * FROM VENTAS WHERE NOMBRE='" + nombre + "'");
            rs = consultaVentas.executeQuery();

            while (rs.next()) {
                newcliente.setCliente(rs.getString(1));
                newcliente.setTotal(rs.getInt(2));
                newcliente.setCantidad(rs.getInt(3));
//           System.out.println(newcliente.getTotal());
            }
            rs.close();
            this.conexion.close();

            return newcliente;
        } catch (SQLException ex) {
            return null;
        } finally {
            return newcliente;
        }
    }

    public boolean deleteVenta(String regis) {
        boolean respuesta = false;
        int exResp = 0;
        try {

            pdst = this.conexion.prepareStatement("DELETE FROM VENTAS WHERE NOMBRE='" + regis + "'");
            exResp = pdst.executeUpdate();
            this.conexion.commit();

            if (exResp > 0) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return respuesta;
        }
    }

    public ArrayList<Films> ConsultaGeneral() {
        Films con = new Films();
        ArrayList<Films> consult = new ArrayList();
        try {
            PreparedStatement ps;
            ResultSet rs = null;
            ps = this.conexion.prepareStatement("SELECT * FROM VENTAS");
            rs = ps.executeQuery();

            while (rs.next()) {
                con = new Films();
                con.setCliente(rs.getString(1));
                con.setTotal(rs.getInt(2));
                con.setCantidad(rs.getInt(3));
                consult.add(con);
            }
            rs.close();
            this.conexion.close();
            return consult;
        } catch (SQLException ex) {
            return null;
        } finally {
            return consult;
        }
    }
}
