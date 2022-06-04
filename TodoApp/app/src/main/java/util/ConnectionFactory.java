/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Alana
 */
public class ConnectionFactory {
        public static final String DRIVER = "com.mysql.jdbc.Driver";
        public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
        public static final String USER = "root";
        public static final String PASS = "";
        
        public static Connection getConnection(){
            //try é responsavel pelo tratamento de erros 
            //nessa parte fica o codigo qure acho q pode dar erro
            try {
                Class.forName(DRIVER);//carrega o driver para a aplicação
                return DriverManager.getConnection(URL, USER,PASS);//faz uma conexão levando em conta esses paramentros
            // aqui fica a mensagem de erro
            } catch (Exception ex) {
                throw new RuntimeException("Erro na conexão com o banco de dados", ex);
            }
        }
        
        public static void closeConnection( Connection connection){
            try {
                if (connection != null) {
                    connection.close();
                    
                }
            } catch (Exception ex) {
                 throw new RuntimeException("Erro ao fechar conexão com o banco de dados", ex);
            }
        }
        
            public static void closeConnection( Connection connection, PreparedStatement statement){
            try {
                if (connection != null) {
                    connection.close();
                    }
                
                if (statement!= null ) {
                    statement.close();
                    }
                
            } catch (Exception ex) {
                 throw new RuntimeException("Erro ao fechar conexão com o banco de dados", ex);
            }
        }
            
             public static void closeConnection( Connection connection, PreparedStatement statement, ResultSet resulteSet){
            try {
                if (connection != null) {
                    connection.close();
                    }
                
                if (statement!= null ) {
                    statement.close();
                    }
                
                 if (resulteSet!= null ) {
                    resulteSet.close();
                    }
                
                
            } catch (Exception ex) {
                 throw new RuntimeException("Erro ao fechar conexão com o banco de dados", ex);
            }
        }
}
