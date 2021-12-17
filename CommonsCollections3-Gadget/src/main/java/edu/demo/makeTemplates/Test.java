package edu.demo.makeTemplates;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

public class Test {
    public static void main(String[] args) throws Exception{
        MakeTemplates myTemplate = new MakeTemplates();
        TemplatesImpl templates = (TemplatesImpl) myTemplate.makeTemplate();
        templates.newTransformer();
    }
}
