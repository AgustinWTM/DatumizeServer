package service;

import beans.ValueBean;
import servInterfaces.AddValManagerInterface;

public class AddValManager implements AddValManagerInterface {

    private ValueBean workVal = ValueBean.getInstance();

    @Override
    public void addVal(String valueExtra){
        this.addVal(this.stringValValidator(valueExtra)
                ? Long.valueOf(valueExtra)
                : 0l);
    }

    private int counterFirst = 0;

    @Override
    public synchronized void addVal(long valueExtra){

        counterFirst++;
        System.out.println("counterFirst: "+counterFirst);
        this.workVal.growVal(valueExtra);
    }

    @Override
    public long getVal(){
        return this.workVal.getValue();
    }

    private boolean stringValValidator(String val){
        String regExpStringValid = "\\d+";
        return val.matches(regExpStringValid);
    }

    public void resetVal(){
        this.workVal.resetVal();
    }
}
