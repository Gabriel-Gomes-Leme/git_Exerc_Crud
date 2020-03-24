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
import modelo.Produto;

/**
 *
 * @author Gabriel
 */
public class AlterarProdAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Produto objprod = new Produto();

        objprod.setDescricao(request.getParameter("descricao"));
        objprod.setId(Integer.parseInt(request.getParameter("id")));

        ProdDao proddao = new ProdDao();

        proddao.alterar(objprod);

        return "/ControleTipo?acao=Listar";

    }
}
