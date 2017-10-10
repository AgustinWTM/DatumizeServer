package addValTest;

import org.junit.Assert;
import org.junit.Test;
import servInterfaces.AddValManagerInterface;
import service.AddValManager;

import java.util.concurrent.TimeUnit;


public class AddValManagerTest {
    AddValManagerInterface addValManager = new AddValManager();

    @Test
    public void testAddVal001(){
        long valInit = addValManager.getVal();
        long initIncre = (long) (Math.random() * 30);
        long initIncreSecond = (long) (Math.random() * 45);
        Assert.assertEquals(valInit, 0l);
        Assert.assertTrue(initIncre > 0);
        addValManager.addVal(initIncre);
        Assert.assertEquals(addValManager.getVal(), (valInit + initIncre));
        for (int i = 0; i < 10; i++){
            addValManager.addVal(initIncreSecond);
        }
        Assert.assertEquals(addValManager.getVal(), (initIncre + (initIncreSecond*10)));
    }


    @Test
    public void testAddVal002(){
        long valZero = addValManager.getVal();
        long initIncre = (long) (Math.random() * 30);
        Thread[] resultPile = {null, null, null, null, null, null, null, null, null, null};

        Runnable taskAdd = () -> {
            String name = Thread.currentThread().getName();
                System.out.println("thread on internal loop : "+name+" current val: "+addValManager.getVal());
                for (int i = 0; i < 30000; i++) {
                    addValManager.addVal(initIncre);
                }
        };
        Assert.assertEquals(addValManager.getVal(), valZero);

        try {
            for (int i = 0; i < 10; i++){
                for (int a = 0; a < 5; a++) {
                    resultPile[i] = new Thread(taskAdd);
                    resultPile[i].setName("thread: "+i+1);
                    resultPile[i].start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        while(i < resultPile.length){
            if(resultPile[i]!= null){
                i++;
            }
        }

        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(addValManager.getVal(), (initIncre * 10 * 150000) + valZero);
    }
}
