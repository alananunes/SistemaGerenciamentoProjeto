/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Alana
 */
public class TaskController {
    
    //metodo insert
    public void save(Task task){
        
        String sql = "INSERT INTO task ("
              + "idProject,"
              +  "name," 
              +  "description," 
              +  "notes," 
              +  "isCompleted," 
              +  "deadline," 
              +  "createdAt" 
              + "updateAt) VALUES (?,?,?,?,?,?,?,?)";
                
        Connection connection       = null;
        PreparedStatement statement = null;
        
        try {
            
         connection = ConnectionFactory.getConnection();
            //prepara o sql
         statement = connection.prepareStatement(sql);
            //seta valor no texto sql
         statement.setInt(1,task.getIdProject());
         statement.setString (2,task.getName());
         statement.setString (3,task.getDescription());
         statement.setBoolean(4,task.isCompleted());
         statement.setString (5,task.getNotes());
         statement.setDate   (6,  new Date(task.getDeadline().getTime()));
         statement.setDate   (7, new Date(task.getCreatedAt().getTime()));
         statement.setDate   (8,  new Date(task.getUpdateAt().getTime()));
         statement.execute();
               
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                    + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
   
        
    }
    public void update(Task task){
        
         String sql = "UPDATE task  SET"
              + "idProject =?,"
              +  "name =?," 
              +  "description =?," 
              +  "notes =?," 
              +  "isCompleted =?," 
              +  "deadline =?," 
              +  "createdA =?t" 
              + "updateAt =?"
              + " WHERE id =?";
         
        Connection connection       = null;
        PreparedStatement statement = null;
        
        try {
         //estabelecendo a conexão com o banco de dados   
         connection = ConnectionFactory.getConnection();
         //preparando a query
         statement = connection.prepareStatement(sql);
         statement.setInt(1,task.getIdProject());
         statement.setString (2,task.getName());
         statement.setString (3,task.getDescription());
         statement.setBoolean(4,task.isCompleted());
         statement.setString (5,task.getNotes());
         statement.setDate   (6,  new Date(task.getDeadline().getTime()));
         statement.setDate   (7, new Date(task.getCreatedAt().getTime()));
         statement.setDate   (8,  new Date(task.getUpdateAt().getTime()));
         statement.setInt(9,task.getId());
         
         //executando a query
         statement.execute();
               
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a tarefa "
                    + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void removeById(int taskId) throws SQLException{
        String sql = "DELETE FROM task WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //criando a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //prepara o sql
            statement = connection.prepareStatement(sql);
            //seta valor no texto sql
            statement.setInt(1,taskId);
            //executando a query
            statement.execute();
            
        } catch (Exception e) {
             throw new RuntimeException("Erro ao deletar a tarefa" + e.getMessage(), e);
        } finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
        
    }
      // buscar todas as tarefas com base no projeto
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM task WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //Lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try{
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             //Setando o valor que corresponde ao filtro de busca
             statement.setInt(1,idProject);
             //valor retornado pela execução da query
             resultSet = statement.executeQuery();
             
             //enquanto houverem valores a serem percorridos no meu resulteSet
             while (resultSet.next()) {
                 
               Task task = new Task();
               task.setId(resultSet.getInt("id"));
               task.setIdProject(resultSet.getInt("idProject"));
               task.setName(resultSet.getString("name"));
               task.setDescription(resultSet.getString("description"));
               task.setNotes(resultSet.getString("notes"));
               task.setCompleted(resultSet.getBoolean("completed"));
               task.setDeadline(resultSet.getDate("deadline"));
               task.setCreatedAt(resultSet.getDate("createdAt"));
               task.setUpdateAt(resultSet.getDate("updateAt"));
               
               tasks.add(task);
               
                
            }
            
        }catch (Exception e) {
             throw new RuntimeException("Erro ao buscar a  tarefa" + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection,statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
        
    }
    
    
     
    
    
}
