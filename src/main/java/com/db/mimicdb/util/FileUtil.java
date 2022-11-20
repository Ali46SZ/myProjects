package com.db.mimicdb.util;

import com.db.mimicdb.config.Const;
import com.db.mimicdb.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class FileUtil {

    public  static long getLastId()
    {
        long newId=0;
        File fileToBeModified = new File(Const.lastIdPath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            oldContent = line ;
            long id = Long.parseLong(oldContent);
            newId=id+1;
            String newContent = oldContent.replaceAll(oldContent, newId+"");
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return newId;
    }

    public  static void Save(Object entity,long primaryKey) throws IOException
    {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(getFullPath(primaryKey)), entity);
    }

    public  static String getFullPath(long primaryKey) throws IOException
    {
        return  Const.directoryName + Const.fileSeparator + primaryKey + Const.extentnion;
    }
    public  static Employee getObject(long primaryKey) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(new File(getFullPath(primaryKey)), Employee.class);
        return employee;
    }

    public  static Employee getObjectByFile(File file) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(file, Employee.class);
        return employee;
    }

}
