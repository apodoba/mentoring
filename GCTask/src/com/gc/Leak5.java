package com.gc;

public class Leak5{

    static Leak5 self;

    private int field1 = 0;
    private int field2 = 0;

    public Leak5(boolean fail) throws Exception{
        self = this;
        field1 = 1;
        if (fail) {
            throw new Exception();
        }
        field2 = 1;
    }

    public boolean isConsistent(){
        return field1 == field2;
    }

    public static void main(String[] args){
    	Leak5 leak = null;
        try {
        	leak = new Leak5(true);
        } catch (Exception ex){
            
        }
        // Task5.self not null
    }
}
