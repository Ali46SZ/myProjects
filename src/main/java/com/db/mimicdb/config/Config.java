package com.db.mimicdb.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.*;
import java.io.File;
import java.sql.SQLException;

@Configuration
public class Config {
    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException, IOException {

        String directoryName =Const.directoryName;
        //String directoryName = PATH.concat(this.getClass().getName());

        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
            File file = new File(Const.lastIdPath);
            try{
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("0");
                bw.close();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(-1);
            }
        }


}

}
