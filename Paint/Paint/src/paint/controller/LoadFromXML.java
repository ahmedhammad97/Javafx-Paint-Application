
package paint.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import paint.model.Shape;

public class LoadFromXML {
    private String path;
    private ArrayList myList = new ArrayList<Shape>();
    private HashMap m;
    private boolean success=false;
    
    public LoadFromXML(String path) throws SAXException, ParserConfigurationException, IOException{
        this.path = path;
        doTheJob();
    }
    
    private void doTheJob() throws SAXException, ParserConfigurationException, IOException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom;
        try{dom = db.parse(path);}catch(Exception e){success=false;return;}
        Element doc = dom.getDocumentElement();
        
        Node x = doc.getFirstChild();
        x = x.getNextSibling();
        
        try{
        do{
            if(x.getNodeName().equals("#text")){x=x.getNextSibling();continue;}
            NamedNodeMap tempm = x.getAttributes();
            m = new HashMap<String,Double>();
            for(int i=0;i<tempm.getLength();i++){
                addToMap(tempm.item(i).toString());
            }
            copyMapToList(x.getNodeName());
            x = x.getNextSibling();
        }while(!x.getNextSibling().equals(null));
        }catch(Exception e){}
        success=true;
        
    }
    
    private void copyMapToList(String type){
        Shape temp = new ShapeFactory().createShape(type, m);
        myList.add(temp);
        
    }
    
    private void addToMap(String s){
        int ind = s.indexOf('=');
        String key = s.substring(0, ind);
        double val = Double.parseDouble(s.substring(ind+2, s.length()-1));
        //System.out.println(key + " " +val);
        m.put(key, val);
    }
    
    public ArrayList<Shape> getList(){
        return myList;
    }
    
    public boolean checkSuccess(){
        return success;
    }
}
