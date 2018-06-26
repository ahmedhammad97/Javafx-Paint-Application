
package paint.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Triangle extends Shape{
    private Point2D thirdPoint;
    
    public Triangle(Point2D startPos, Point2D endPos, Color strockColor) {
        super(startPos, endPos, strockColor);
        double temp = Math.abs(startPos.getX() - endPos.getX());
        if(super.getPosition().getX()<super.getEndPosition().getX()){
            thirdPoint = new Point2D(endPos.getX()-(temp*2), endPos.getY());
        }else{
            thirdPoint = new Point2D(endPos.getX()+(temp*2), endPos.getY());
        }
    }

    public Triangle() {
        
    }
    
    @Override
    public void setTopLeft(Point2D x){
     Point2D temp = x.subtract(this.getPosition());
     this.setPosition(x);
     this.setEndPosition(this.getEndPosition().add(temp));
     this.thirdPoint = this.thirdPoint.add(temp);
     super.setTopLeft(x);
    }
    
    @Override
    protected void getPropertiesToMap(){
        super.getPropertiesToMap();
        super.addToProperties("thirdPointX", thirdPoint.getX());
        super.addToProperties("thirdPointY", thirdPoint.getY());
    }
    
    @Override
    protected void setPropertiesToVariables(){
        super.setPropertiesToVariables();
        thirdPoint = new Point2D(super.getFromMap("thirdPointX"),super.getFromMap("thirdPointY"));
    }
    
    @Override
    public void draw(Canvas canvas){
        double x1 = super.getPosition().getX();
        double y1 = super.getPosition().getY();
        double x2 = super.getEndPosition().getX();
        double y2 = super.getEndPosition().getY();
        double x3 = thirdPoint.getX();
        double y3 = thirdPoint.getY();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokePolygon(new double[]{x1,x2,x3}, new double[]{y1,y2,y3}, 3);
        gc.setFill(super.getFillColor());
        gc.fillPolygon(new double[]{x1,x2,x3}, new double[]{y1,y2,y3}, 3);
    }
    
    
}
