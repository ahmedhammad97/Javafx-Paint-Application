
package paint.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle() {
        
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    
    public Rectangle(Point2D startPos, Point2D endPos, Color strockColor) {
        super(startPos, endPos, strockColor);
        width = Math.abs(startPos.getX() - endPos.getX());
        height = Math.abs(startPos.getY() - endPos.getY());
    }
    
    @Override
    protected void getPropertiesToMap(){
        super.getPropertiesToMap();
        super.addToProperties("width", width);
        super.addToProperties("height", height);
    }
    
    @Override
    protected void setPropertiesToVariables(){
        super.setPropertiesToVariables();
        width = super.getFromMap("width");
        height = super.getFromMap("height");
    }
    
    @Override
    public void draw(Canvas canvas){
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokeRect(super.getTopLeft().getX(), super.getTopLeft().getY(), width, height);
        gc.setFill(super.getFillColor());
        gc.fillRect(super.getTopLeft().getX(), super.getTopLeft().getY(), width, height);
    }
    
}
