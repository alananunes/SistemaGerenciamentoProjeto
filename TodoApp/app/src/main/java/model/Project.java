
package model;

/**
 *
 * @author Alana
 */
import java.util.Date;

public class Project {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updateAt;


    public Project(int id, String name, String description, Date createdAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

     public Project() {
        //define data de criação no momento q é criado
        this.createdAt = new Date();
        this.updateAt = new Date();
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
