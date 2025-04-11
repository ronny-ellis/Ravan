package data;

public class DAO implements InterfaceDAO
{
    public void save(LierTable lierTable) throws Exception{
        try 
        {
            lierTable.insertTable();  
        } catch (Exception e) {
           throw e;
        }
        
    }
    public Object findById(LierTable lierTable,String id) throws Exception{
        try 
        {
            return lierTable.recherche(" where id ='"+id+"'"); 
        } 
        catch (Exception e) 
        {
            throw e;
        }
        
    }
    public Object[] findAll(LierTable lierTable,Object obj) throws Exception{

        try 
        {
            return lierTable.recherches("", obj).toArray();
        } 
        catch (Exception e) 
        {
            throw e;   
        }
    }
    public void remove(LierTable lierTable){
        
    }
    public void update(LierTable lierTable, String where)throws Exception
    {
        try 
        {
            lierTable.updateTable(where);
        } 
        catch (Exception e) 
        {
            throw e;
        }
    }
}