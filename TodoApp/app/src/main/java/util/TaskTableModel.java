/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author Alana
 */
public class TaskTableModel extends AbstractTableModel {
    
    String[] columns = {"Nome","Descrição","Prazo","Tarefa Concluída", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList<>();
    

    @Override //retorna quantas linhas vair ter
    public int getRowCount() {
        return tasks.size();
    }

    @Override //retorna quantas colunas vair ter
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    //verifica se coluna é editavel
    public boolean isCellEditable(int rowIndex, int columnIndex){
        /**
         * ou  return columnIndex == 3
         */
        if (columnIndex == 3)
            return true;
        else
          return false ;  
      }
    
    //retorna qual a classe do componente em determinada coluna
    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (tasks.isEmpty()) {
            return Object.class;
        }
        return this.getValueAt(0, columnIndex).getClass();
        
    }

    @Override //retorna informa em uma linha e coluna especifica
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch (columnIndex) {
            case 0: // se a coluna for 1:
                return tasks.get (rowIndex).getName();
            case 1:
                 return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(tasks.get (rowIndex).getDeadline());
            case 3:
                return tasks.get (rowIndex).isIsCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados não encontrados";
        }
        
    }
    
    @Override //converte de objeto pra boolean e ao retornar converte de boolean pra objeto
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tasks.get(rowIndex).setIsCompleted((boolean) aValue);
    }


    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
       
}
