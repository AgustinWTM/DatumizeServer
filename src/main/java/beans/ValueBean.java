package beans;

import utils.ValueCRUD;

import java.util.concurrent.TimeUnit;

public class ValueBean extends ValueCRUD{
    private long value;
    private static ValueBean instance = null;
    private Thread preserveThread = null;

    public long getValue() {
        return value;
    }
    private void setValue(long value) {
        this.value = value;
    }

    protected ValueBean(){
        this.instanceSetReady();
    }

    public static ValueBean getInstance(){
        synchronized (ValueBean.class){
            if(instance == null){
                instance = new ValueBean();
            }
        }
        return instance;
    }

    public static void setDownInstance(){
        instance = null;
    }

    private void instanceSetReady(){
        if(value == 0l){
            setValue(Long.valueOf(valueReader()));
        }
//        this.preserveThread = new Thread(autoPreserveTask());
//        this.preserveCaller();
    }

    private void preserveCaller(){
        while(this != null){
            this.preserveThread.run();
        }
    }

    public void resetVal(){
        this.value = 0l;
    }

    public void growVal(long value) {

        // TODO uncoment for simulate a slow process
//        try {
//            TimeUnit.MILLISECONDS.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        this.value += value;
    }

    private Runnable autoPreserveTask(){
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
//        valuerWritter(String.valueOf(this.value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public String toString() {
        return "ValueBean{" +
                "value=" + value +
                '}';
    }
}
