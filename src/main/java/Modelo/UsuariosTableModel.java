/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAO.UsuariosDAO;
import Objeto.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stokler
 */
public class UsuariosTableModel extends AbstractTableModel {

    private List<Usuarios> dados = new ArrayList<>();
    private String[] colunas = {"Nome", "Login", "Senha", "Tipo"};
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getLogin();
            case 2:
                return dados.get(linha).getSenha();
            case 3:
                return dados.get(linha).getTipo();
        }
        return null;
    }

    @Override
    public void setValueAt(Object user, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setNome((String) user);
                break;
            case 1:
                dados.get(linha).setLogin(((String) user));
                break;
            case 2:
                dados.get(linha).setSenha((String) user);
                break;
            case 3:
                dados.get(linha).setTipo((String) user);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);

    }

    public void recarregaTabela() {
        this.dados.clear();
        lerDados();
        this.fireTableDataChanged();
    }

    public Usuarios pegaDadosLinha(int linha) {
        return dados.get(linha);
    }

    private void lerDados() {
        UsuariosDAO usu = new UsuariosDAO();

        for (Usuarios us : usu.read()) {
            this.addLinha(us);
        }
        this.fireTableDataChanged();
    }

    private void addLinha(Usuarios us) {
        this.dados.add(us);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
}
