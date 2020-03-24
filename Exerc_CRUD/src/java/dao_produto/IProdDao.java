/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_produto;

import java.util.ArrayList;
import modelo.Produto;
/**
 *
 * @author Gabriel
 */
public interface IProdDao {
    
    public ArrayList<Produto> listar(Produto objprod);

    public void buscar(Produto objprod);

    public boolean alterar(Produto objprod);

    public boolean excluir(Produto objprod);
    
    public boolean cadastrar(Produto objprod);
}
