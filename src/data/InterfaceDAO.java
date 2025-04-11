package data;

public interface  InterfaceDAO 
{
    public void save(LierTable lierTable) throws Exception;
    public Object findById(LierTable lierTable,String id)throws Exception;
    public Object[] findAll(LierTable lierTable,Object obj) throws Exception;
    public void remove(LierTable lierTable);
    public void update(LierTable lierTable,String where) throws Exception;
}
