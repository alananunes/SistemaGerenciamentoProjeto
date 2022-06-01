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
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Alana
 */
public class ProjectController {
    
    //metodo insert
    public void save(Project project){
        
        String sql = "INSERT INTO project ("
              +  "name," 
              +  "description," 
              +  "createdAt" 
              +  "updateAt) VALUES (?,?,?,?)";
                
        Connection connection       = null;
        PreparedStatement statement = null;
        
        try {
            
         connection = ConnectionFactory.getConnection();
            //prepara o sql
         statement = connection.prepareStatement(sql);
            //seta valor no texto sql
         statement.setString (1,project.getName());
         statement.setString (2,project.getDescription());
         statement.setDate   (3, new Date(project.getCreatedAt().getTime()));
         statement.setDate   (4,  new Date(project.getUpdateAt().getTime()));
         statement.execute();
               
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o projeto "
                    + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
   
        
    }
    public void update(Project project){
        
         String sql = "UPDATE project  SET"
              +  "name =?,"
              +  "description =?," 
              +  "createdA =?t" 
              +  "updateAt =?"
              +  "WHERE id =?";
         
        Connection connection       = null;
        PreparedStatement statement = null;
        
        try {
         //estabelecendo a conexão com o banco de dados   
         connection = ConnectionFactory.getConnection();
         //preparando a query
         statement = connection.prepareStatement(sql);
         statement.setString (1,project.getName());
         statement.setString (2,project.getDescription());
         statement.setDate   (3, new Date(project.getCreatedAt().getTime()));
         statement.setDate   (4,  new Date(project.getUpdateAt().getTime()));
         statement.setInt(5,project.getId());
         
         //executando a query
         statement.execute();
               
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o projeto "
                    + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void removeById(int idProject) throws SQLException{
        String sql = "DELETE FROM project WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //criando a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //prepara o sql
            statement = connection.prepareStatement(sql);
            //seta valor no texto sql
            statement.setInt(1,idProject);
            //executando a query
            statement.execute();
            
        } catch (Exception e) {
             throw new RuntimeException("Erro ao deletar o projeto" + e.getMessage(), e);
        } finally{
            ConnectionFactory.closeConnection(connection,statement);
        }
        
    }
      // buscar todas as tarefas com base no projeto
    public List<Project> getAll(){
        
        String sql = "SELECT * FROM project";
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //Lista de tarefas que será devolvida quando a chamada do método acontecer
       
        
        try{
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             //valor retornado pela execução da query
             resultSet = statement.executeQuery();
             
             //enquanto houverem valores a serem percorridos no meu resulteSet
             while (resultSet.next()) {
                 
               Project project = new Project();
               project.setId(resultSet.getInt("id"));
               project.setName(resultSet.getString("name"));
               project.setDescription(resultSet.getString("description"));
               project.setCreatedAt(resultSet.getDate("createdAt"));
               project.setUpdateAt(resultSet.getDate("updateAt"));
               
               projects.add(project);
               
                
            }
            
        }catch (Exception e) {
             throw new RuntimeException("Erro ao buscar o projeto" + e.getMessage(), e);
        }finally{
            ConnectionFactory.closeConnection(connection,statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do banco de dados
        return projects;
        
    }
    
    
     
    
    
}
