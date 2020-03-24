/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_produto;

import dao_situacao.SituacaoDao;
import util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Produto;
import modelo.Situacao;

/**
 *
 * @author Gabriel
 */
public class ProdDao implements IProdDao {

    private static final String SELECT_ALL = "SELECT * FROM produto where descricao ilike ?;";
    private static final String INSERT = "INSERT INTO produto(descricao) values(?);";
    private static final String DELETE = "DELETE FROM produto where id=?";
    private static final String BUSCAR = "SELECT * FROM produto WHERE id=?;";
    private static final String UPDATE = "UPDATE produto set descricao=? WHERE id=?";

    private Object pstmt;
    private Connection conexao;

    @Override
    public ArrayList<Produto> listar(Produto objprod) {

        ArrayList<Produto> listaProduto = new ArrayList<Produto>();

        try {

            //Conexao
            conexao = ConexaoBD.getConexao(); //conexão com o banco
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL); //preparação do comando

            pstmt.setString(1, "%" + objprod.getDescricao() + "%"); //o campo descrição irá completar o ilike da instrução SQL (select_all)

            //executa
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //a cada loop
                Produto novoproduto = new Produto();
                novoproduto.setId(rs.getInt("id"));
                novoproduto.setDescricao(rs.getString("descricao"));
                novoproduto.setQuantidade(rs.getInt("quantidade"));
                
                Situacao objsit=new Situacao();
                objsit.setId(rs.getInt("situacao"));
                
                SituacaoDao objsitdao =new SituacaoDao();
                objsitdao.buscar(objsit);
                
                novoproduto.setSituacao(objsit);

                //add na lista
                listaProduto.add(novoproduto);
            }
            return listaProduto;

        } catch (Exception ex) {

            return listaProduto;

        } finally {

            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void buscar(Produto objprod) {
       
         try {
            //Conexao
            conexao = ConexaoBD.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setInt(1, objprod.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            objprod.setDescricao(rs.getString("descricao"));
            objprod.setQuantidade(rs.getInt("quantidade"));
            
              Situacao objsit=new Situacao();
              objsit.setId(rs.getInt("situacao"));
                
              SituacaoDao objsitdao =new SituacaoDao();
              objsitdao.buscar(objsit);
                
              objprod.setSituacao(objsit);
         

        } catch (Exception e) {

            //
            
        } finally {
            
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean alterar(Produto objprod) {
        try {

            conexao = ConexaoBD.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, objprod.getDescricao());
            pstmt.setInt(2, objprod.getQuantidade());
            pstmt.setInt(3, objprod.getSituacao().getId());
            pstmt.setInt(4, objprod.getId());
            
            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

             try {
                 conexao.close();
             } catch (SQLException ex) {
                 Logger.getLogger(ProdDao.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
    }

    @Override
    public boolean excluir(Produto objprod) {
        
       try {

            conexao = ConexaoBD.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, objprod.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

             try {
                 conexao.close();
             } catch (SQLException ex) {
                 Logger.getLogger(ProdDao.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
    } 

    @Override
    public boolean cadastrar(Produto objprod) {
         try {

            conexao = ConexaoBD.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, objprod.getDescricao());
            pstmt.setInt(1, objprod.getQuantidade());
            pstmt.setInt(1, objprod.getSituacao().getId());

            pstmt.execute();
            
            return true;

        } catch (Exception ex) {

            return false;
            
        } finally {
            
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}


    
