/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uttt.patern.object.Interfaz;

import java.util.ArrayList;

/**
 *
 * @author KarinaVR
 * @param <generico>
 */
public interface IOperaciones<generico> {
    
    boolean insertar (generico obj);
    boolean eliminar (generico obj);
    boolean actualizar (generico obj);
    ArrayList <generico> consultarTodo();
}
