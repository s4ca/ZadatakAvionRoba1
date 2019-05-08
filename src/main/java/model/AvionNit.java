package model;

public class AvionNit extends Thread {


    private boolean dozvolaZaPoletanje;


    @Override
    public void run() {
        provera();
        poleti();

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


}
