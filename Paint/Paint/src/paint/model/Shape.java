
package paint.model;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.Interpolatable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.*;


public abstract class Shape implements iShape, java.lang.Cloneable{
    
    private Point2D startPosition;
    private Point2D endPosition;
    private Point2D topLeft;
    private Color color;
    private Color fillColor;
    private Map properties = new HashMap<String,Double>();
    
    public Shape(){
        //Variables will be set by the Properties map.
    }
    
    public Shape(Point2D startPos, Point2D endPos , Color strockColor){
        this.color = strockColor;
        this.startPosition = startPos;
        this.endPosition = endPos;
        this.fillColor = Color.TRANSPARENT;
        this.topLeft = calculateTopLeft();
    }
    
    /*public Shape(Point2D startPos, Point2D endPos, Color strockColor, Color fillColor){
        this.color = strockColor;
        this.startPosition = startPos;
        this.endPosition = endPos;
        this.fillColor = fillColor;
    }*/
    
    @Override
    public void setPosition(Point2D position) {
        this.startPosition = position;
    }
    
    public void setEndPosition(Point2D position) {
        this.endPosition = position;
    }

    @Override
    public Point2D getPosition() {
        return this.startPosition;
    }
    
    public Point2D getEndPosition() {
        return this.endPosition;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
        setPropertiesToVariables();
    }
    
    protected void setPropertiesToVariables(){
        double startX,startY,endX,endY,topLeftX,topLeftY;
        startX = (double) properties.get("startPositionX");
        startY = (double) properties.get("startPositionY");
        
        endX = (double) properties.get("endPositionX");
        endY = (double) properties.get("endPositionY");
        
        topLeftX = (double) properties.get("topLeftX");
        topLeftY = (double) properties.get("topLeftY");
        
        startPosition = new Point2D(startX, startY);
        endPosition = new Point2D(endX, endY);
        topLeft = new Point2D(topLeftX, topLeftY);
        
        Double strockR,strockG,strockB,fillR,fillG,fillB;
        strockR = (Double) properties.get("strockR");
        strockG = (Double) properties.get("strockG");
        strockB = (Double) properties.get("strockB");
        
        fillR = (Double) properties.get("fillR");
        fillG = (Double) properties.get("fillG");
        fillB = (Double) properties.get("fillB");
        
        color = Color.color(strockR,strockG,strockB);
        
        fillColor = Color.color(fillR, fillG, fillB);
        
    }
    
    protected double getFromMap(String s){
        try{return (double)properties.get(s);}catch(Exception e){
            System.out.println("Error, can't find this property.");
        }
        return Double.POSITIVE_INFINITY;
    }
    
    @Override
    public Map<String, Double> getProperties() {
        getPropertiesToMap();
        return this.properties;
    }
    
    protected void getPropertiesToMap(){
        properties.put("startPositionX", startPosition.getX());
        properties.put("startPositionY", startPosition.getY());
        
        properties.put("endPositionX", endPosition.getX());
        properties.put("endPositionY", endPosition.getY());
        
        properties.put("topLeftX", topLeft.getX());
        properties.put("topLeftY", topLeft.getY());
        
        properties.put("strockR", color.getRed());
        properties.put("strockG", color.getGreen());
        properties.put("strockB", color.getBlue());
        
        properties.put("fillR", fillColor.getRed());
        properties.put("fillG", fillColor.getGreen());
        properties.put("fillB", fillColor.getBlue());
    }

    public void addToProperties(String s, Double x){
        properties.put(s, x);
    }
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    @Override
    public void draw(Canvas canvas) {
        
    }
    
    

    @Override
    public Shape clone() throws CloneNotSupportedException{
        return cloneShape();
    }
    
    public Shape cloneShape() throws CloneNotSupportedException {
        Shape temp = null;
        temp = (Shape) super.clone();
        return temp;
    }
    
    public Point2D calculateTopLeft(){
        double x = Math.min(this.getPosition().getX(), this.getEndPosition().getX());
        double y = Math.min(this.getPosition().getY(), this.getEndPosition().getY());
        return new Point2D(x,y);
    }
    
    public Point2D getTopLeft(){
        return topLeft;
    }
    
    public void setTopLeft(Point2D pos){
        this.topLeft = pos;
    }
    
    
    
}
