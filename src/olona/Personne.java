package olona;

import data.LierTable;

public class Personne extends LierTable {
    
    String id;
    String nom;

    public String getId() 
    {
        return id;
    }
    public void setId(String id) 
    {
        this.id = id;
    }
    public String getNom() 
    {
        return nom;
    }
    public void setNom(String nom) 
    {
        this.nom = nom;
    }
    public Personne(String name,String id)
    {
        this.setNom(name);
        this.setId(id);
        this.setNomTable("Personne");   
    }
    public Personne()
    {
        this.setNomTable("Personne");   
    }

    @Override
    public String getTableId()
    {
        return this.getId();
    }
    @Override
    public void setTableId(String id)
    {
        this.tableId=id;
    } 
}
