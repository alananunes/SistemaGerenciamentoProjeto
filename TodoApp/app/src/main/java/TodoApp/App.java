package TodoApp;


import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        
        /**
         *  Connection c = ConnectionFactory.getConnection();
        ConnectionFactory.closeConnection(c);
         */
        
        ProjectController pc = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto Teste");
        project.setDescription("description");
        pc.save(project);
        
        /**
         
        project.setName("Novo nome do Projeto Teste");
        pc.update(project);
        
        List<Project> projects = pc.getAll();
        System.out.println("Total de projetos = " + projects.size());
         
         * 
         */
        
       
        
    }
}
