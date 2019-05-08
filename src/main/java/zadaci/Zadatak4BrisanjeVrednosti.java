package zadaci;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Zadatak4BrisanjeVrednosti {



    static Dao<Avion, String> avionDao;
    static Dao<Roba, String> robaDao;


    public static void main(String[] args) {


        ConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource , Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);




        }catch (Exception e){
            e.printStackTrace();
        }finally {
           if (connectionSource != null){
               try {
                   connectionSource.close();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
        }


    }
}
