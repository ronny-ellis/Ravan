package data;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public abstract class LierTable 
{

    private String nomTable;
    Statement stmnt;
    ResultSet result;
    public String tableId;

    public abstract String getTableId();

    public abstract void setTableId(String id);
    
    public  String getNomTable()
    {
        return this.nomTable;
    }

    public String setNomTable(String nom)
    {
        return this.nomTable=nom;
    } 
    
    public void insertTable() throws Exception
    {
        try 
        {
            Connection c=DBConnect.getConnect();       
            Field[] fields=this.getClass().getDeclaredFields();
            String query="INSERT INTO "+this.getNomTable()+"(";
            for (Field field : fields) {
                query=query+
                field.getName()+","
                ;
            }
            char[] sql=query.toCharArray();
            sql[sql.length-1]=')';
            query=String.valueOf(sql)+" values(";
            for (Field field : fields){
                Method[] methods=this.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("get"+field.getName())) {
                        try {
                            query=query+"'"+String.valueOf(method.invoke(this))+"',"; 
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        }
                    }
                }
            }
            char[] sql1=query.toCharArray();
            sql1[sql1.length-1]=')';
            query=String.valueOf(sql1);
            stmnt=c.createStatement();
            stmnt.executeUpdate(query);
            stmnt.close();
            //c.commit();
            c.close();
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
    public Object recherche(String where) throws Exception
    {
        try 
        {
            Connection c=DBConnect.getConnect();
            Field[] fields=this.getClass().getDeclaredFields();
            Method[] methods=this.getClass().getMethods();
            String query="SELECT ";
            for (Field field : fields) {
                query=query+field.getName()+",";
            }
            char[] sql=query.toCharArray();
            sql[sql.length-1]=' ';
            query=String.valueOf(sql);
            query=query+"FROM "+ this.getNomTable();
            query=query+where;
            System.out.println(query);
            stmnt=c.createStatement();
            result=stmnt.executeQuery(query);
            while (result.next()) 
            {
                for (Field field : fields) 
                {
                    for (Method method : methods) 
                    {
                        if (method.getName().equalsIgnoreCase("set"+field.getName())) 
                        {
                            try 
                            {    
                                method.invoke(this,result.getString(field.getName())); 
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw e;
                            }
                        }
                    }
                }
            }
            return this;
        } 
        catch (Exception e) 
        {
            throw e;
        }       
    }
    public List<Object> recherches(String where) throws Exception
    {
        try 
        {
            Connection c=DBConnect.getConnect();
            Field[] fields=this.getClass().getDeclaredFields();
            Method[] methods=this.getClass().getMethods();
            String query="SELECT ";
            for (Field field : fields) {
                query=query+field.getName()+",";
            }
            char[] sql=query.toCharArray();
            sql[sql.length-1]=' ';
            query=String.valueOf(sql);
            query=query+"FROM "+ this.getNomTable();
            query=query+where;
            System.out.println(query);
            stmnt=c.createStatement();
            result=stmnt.executeQuery(query);
            List<Object> data=new ArrayList<Object>();
            while (result.next()) 
            {
                Object instance=this.getClass().getDeclaredConstructor().newInstance();
                for (Field field : fields) 
                {
                    for (Method method : methods) 
                    {
                        if (method.getName().equalsIgnoreCase("set"+field.getName())) 
                        {
                            try 
                            {    
                                method.invoke(instance,result.getString(field.getName()));
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw e;
                            }
                        }
                    }
                }
                data.add(instance);
            }
            c.close();
            return data;
        } 
        catch (Exception e) 
        {
            throw e;
        }       
    }
    public void updateTable(String where) throws Exception
    {
        try 
        {
            Connection c=DBConnect.getConnect();
            Field[] fields=this.getClass().getDeclaredFields();
            Method[] methods=this.getClass().getMethods();
            String query="UPDATE "+this.getNomTable()+" set ";
            for (Field field : fields) 
            {
                for (Method method : methods) 
                {
                    if (method.getName().equalsIgnoreCase("get"+field.getName())) 
                    {
                        query=query+field.getName()+"="+"'"+String.valueOf(method.invoke(this))+"',";    
                    }        
                }
            }
            char[] sql=query.toCharArray();
            sql[sql.length-1]=' ';
            query=String.valueOf(sql);
            stmnt=c.createStatement();
            stmnt.executeUpdate(query+where);
            stmnt.close();
            c.close(); 
        } 
        catch (Exception e) 
        {
            throw e;            
        }        
    }        
}
