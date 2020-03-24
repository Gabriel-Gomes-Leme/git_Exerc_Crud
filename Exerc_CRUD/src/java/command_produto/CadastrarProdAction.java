/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command_produto;

import command.ICommand;
import dao_produto.IProdDao;
import dao_produto.ProdDao;
import dao_situacao.SituacaoDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Produto;
import modelo.Situacao;

/**
 *
 * @author Gabriel
 */
public class CadastrarProdAction implements ICommand {
    
    @Override
     public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
         
      try {
            Situacao objsit = new Situacao();
            SituacaoDao situacaodao = new SituacaoDao();
            ArrayList<Situacao> arrsituacao = new ArrayList<>();
            objsit.setDescricao(""); //desta forma virão todos os registros
            arrsituacao = situacaodao.listar(objsit);
            HttpSession session = request.getSession();
            session.setAttribute("arrsituacao", arrsituacao);
        } catch (Exception ex) {
            //nada a fazer
        }

        try {
            Produto objprod = new Produto();
            objprod.setDescricao(request.getParameter("txtdescricao"));
            objprod.setQuantidade(Integer.parseInt(request.getParameter("txtquantidade")));
            Situacao objsit = new Situacao(); //perceba que procisamos criar um objeto situacao que será agregado ao produto
            objsit.setId(Integer.parseInt(request.getParameter("cmbsituacao")));
            objprod.setSituacao(objsit); //aqui acontece a agregação

            if (objprod.getDescricao().length() == 0) {
                return "/crud_acoes/produto/cadastrar_prod.jsp";
            } else {

                ProdDao produtodao = new ProdDao();

                produtodao.cadastrar(objprod);

                return "/crud_acoes/produto/sucesso.jsp";
            }
        } catch (Exception ex) {
            return "/crud_acoes/produto/cadastrar_produto.jsp";

        }
     }
}

