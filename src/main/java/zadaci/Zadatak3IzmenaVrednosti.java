package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {


    static Dao<Avion, String> avionDao;
    static Dao<Roba, String> robaDao;



    public static void main(String[] args) {


        ConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource , Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);






            List <Roba> robe =robaDao.queryForEq(Roba.POLJE_OPIS , "Plasticna stolica");
              Roba roba = robe.get(0);

            roba.setOpis("Drvena stolica");
            robaDao.update(roba);


            List<Roba>  robae =  robaDao.queryForAll();
           for (Roba r : robae){
               System.out.println(r);
           }



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
