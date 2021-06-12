/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BD.Conexao;
import Objeto.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Stokler
 */
public class UsuariosDAO {

    public List<Usuarios> read() {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuarios> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_usuarios");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuarios us = new Usuarios();
                us.setId(rs.getInt("id"));
                us.setNome(rs.getString("nome"));
                us.setLogin(rs.getString("login"));
                us.setSenha(rs.getString("senha"));
                us.setTipo(rs.getString("tipo"));
                usuarios.add(us);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados" + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }

    public void create(Usuarios us) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbl_usuarios (nome, login, senha, tipo) VALUES (?, ?, ?, ?)");
            stmt.setString(1, us.getNome());
            stmt.setString(2, us.getLogin());
            stmt.setString(3, us.getSenha());
            stmt.setString(4, us.getTipo());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuário" + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void update(Usuarios us) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbl_usuarios SET nome = ?, login = ?, senha = ?, tipo = ? WHERE id = ?");
            stmt.setString(1, us.getNome());
            stmt.setString(2, us.getLogin());
            stmt.setString(3, us.getSenha());
            stmt.setString(4, us.getTipo());
            stmt.setInt(5, us.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar usuário" + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void delete(Usuarios us) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tbl_usuarios WHERE id = ?");
            stmt.setInt(1, us.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao deletar usuário" + e);
        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public boolean checaLogin(String login, String senha) {
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean logado = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_usuarios WHERE login = ? AND senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                logado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado" + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }

        return logado;
    }
    
    public Usuarios dadosUsuarios(String login, String senha){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios us = new Usuarios();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_usuarios WHERE login = ? AND senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            while (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setNome(rs.getString("nome"));
                us.setLogin(rs.getString("login"));
                us.setSenha(rs.getString("senha"));
                us.setSenha(rs.getString("tipo"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados" + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return us;
    }
}
