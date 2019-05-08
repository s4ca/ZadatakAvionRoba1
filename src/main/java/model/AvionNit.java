package model;

public class AvionNit extends Thread {


    private boolean dozvolaZaPoletanje;
    private Avion avion;

    @Override
    public void run() {
        System.out.println("Provera aviona");
        provera();
        System.out.println("Polece");
        poleti();

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



    }

}
