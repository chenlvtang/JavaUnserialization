package edu.demo.lesson1;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import edu.demo.makeTemplates.MakeTemplates;

public class TXFilterChain {
    public static void main(String[] args) throws Exception{
        MakeTemplates makeTemplates = new MakeTemplates();
        TemplatesImpl templates = (TemplatesImpl) makeTemplates.makeTemplate();
        //the constructor of TrAXFilter invoke "templates.newTransformer()"
        TrAXFilter txFilter = new TrAXFilter(templates);
    }
}
