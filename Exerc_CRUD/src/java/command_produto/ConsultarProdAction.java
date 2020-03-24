/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command_produto;

import command.ICommand;
import dao_produto.ProdDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Produto;

/**
 *
 * @author Gabriel
 */
public class ConsultarProdAction implements ICommand {
    
     @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        Produto objprod = new Produto();
       
        ProdDao proddao = new ProdDao();

        objprod.setId(Integer.parseInt(request.getParameter("id")));
        
        proddao.buscar(objprod);

        HttpSession session = request.getSession();

        session.setAttribute("objprod", objprod);
        
        return "/crud_acoes/produto/consultar_prod.jsp";
           
    }
}
