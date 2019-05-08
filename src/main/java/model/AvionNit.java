package model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

public class AvionNit extends Thread {

    static Dao<Avion, String> avionDao;
    static Dao<Roba, String> robaDao;


    private boolean dozvolaZaPoletanje;
    private Avion avion;

    @Override
    public void run() {
        System.out.println(avionDao + "Provera aviona");
        provera();
        System.out.println(avionDao +"Polece");
        poleti();
        System.out.println(avionDao + "Zavrsio");

    }


    public AvionNit( Avion avion) {
        this.avion = avion;
    }

    public boolean isDozvolaZaPoletanje() {
        return dozvolaZaPoletanje;
    }

    public void setDozvolaZaPoletanje(boolean dozvolaZaPoletanje) {
        this.dozvolaZaPoletanje = dozvolaZaPoletanje;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    private void provera(){
        int temp = (int)(Math.random() * 2000);
        try {
            sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public synchronized boolean traziDozvoluZaPoletanje() {

        if(dozvolaZaPoletanje)
        {
            dozvolaZaPoletanje=false;
            return true;
        }
        return false;
    }


    private void poleti() {
        boolean dobioDozvolu;
        do {

            dobioDozvolu = dozvolaZaPoletanje;


            if (!dobioDozvolu) {
                try {

                    this.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!dobioDozvolu);

        try {
            this.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        ConnectionSource connectionSource = null;

        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource , Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);


            List<Avion> avion = avionDao.queryForAll();
            for (Avion a : avion) {
                AvionNit a1 = new AvionNit(a);
                a1.start();
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
