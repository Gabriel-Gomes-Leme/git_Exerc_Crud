/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command_produto;

import command.ICommand;
import dao_produto.ProdDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Produto;

/**
 *
 * @author Gabriel
 */
public class ListarProdAction implements ICommand{
     @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ArrayList<Produto> arr = new ArrayList<Produto>();

        Produto objprod = new Produto();

        objprod.setDescricao("");
        
        ProdDao proddao = new ProdDao();

        arr = proddao.listar(objprod);

        HttpSession session = request.getSession();

        session.setAttribute("arrproduto", arr);

        return "/crud_acoes/produto/listar_prod.jsp";
    
}
}
