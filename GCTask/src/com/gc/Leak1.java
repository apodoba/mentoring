package com.gc;
import java.util.*;

public class Leak1 {
    Hashtable<String, Square> hashtable = new Hashtable<String, Square>();
    Square sq[] = new Square[10];

    public static void main(String[] args) {
        // TODO прикладная логика
    	Leak1 testhash = new Leak1();   
        for(int i=0; i<100000; i++) {
            testhash.create(i);  
            testhash.use(i);    
            testhash.release();
        }
    }
    
    // Создание объектов Square.
    void create(int i) {
        for(int j=0; j<10; j++) {
            long index = j+i*10;
            sq[j] = new Square(index);
            hashtable.put(sq[j].num, sq[j]);
        }
    }
    
    // Использование объектов Square.
    void use(int i) {
        for(int j=0; j<10; j++) {
            System.out.print(((Square)(hashtable.get(sq[j].num))).square + " ");
        }
        System.out.println();
    }
    
    void release() {
        for(int j=0; j<10; j++) {
            sq[j] = null;
        }
    }
    
}

