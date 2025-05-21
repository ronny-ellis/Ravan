package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import data.DAO;
import olona.Personne;

public class Main 
{
    public static void main(String[] args) {
        Personne personne=new Personne();
        //Personne personne2=new Personne("Ranaivo","12");
        List<Object> data;
        try 
        {
            DAO dao=new DAO();
            data=personne.recherches("");
            Object[] objects=data.toArray();
            //Personne[] personnes=(Personne[]) data.toArray();
            for (Object object : objects) 
            {
                personne=(Personne) object;
                System.out.println(personne.getId());
            }
            //dao.save(personne2);
            //personne2.updateTable(" where id='12'");       
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }
}
