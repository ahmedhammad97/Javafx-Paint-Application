
package paint.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import paint.model.*;


public class FXMLDocumentController implements Initializable, DrawingEngine {
  
    /***FXML VARIABLES***/
    @FXML
    private Button DeleteBtn;

    @FXML
    private ComboBox<String> ShapeBox;

    @FXML
    private Button UndoBtn;

    @FXML
    private Button RedoBtn;

    @FXML
    private ColorPicker ColorBox;

    @FXML
    private Button SaveBtn;
    
    @FXML
    private Button MoveBtn;
    
    @FXML
    private Button RecolorBtn;
    
    @FXML
    private Button LoadBtn;
    
    @FXML
    private GridPane After;
    
    @FXML
    private Pane Before;
    
    @FXML
    private Pane PathPane;
    
    @FXML
    private TextField PathText;

    @FXML
    private Button StartBtn;
    
    @FXML
    private Button ResizeBtn;
    
    @FXML
    private Button ImportBtn;
    
    @FXML
    private Button PathBtn;
    
    @FXML
    private Canvas CanvasBox;
    
    @FXML
    private Button CopyBtn;
    
    @FXML
    private Label Message;
    
    @FXML
    private ListView ShapeList;
    
    
    
    /***CLASS VARIABLES***/
    private Point2D start;
    private Point2D end;
    
    //SINGLETON DP
    private static ArrayList<Shape> shapeList = new ArrayList<Shape>();
    
    private boolean move=false;
    private boolean copy=false;
    private boolean resize=false;
    private boolean save=false;
    private boolean load=false;
    private boolean importt =false;
    
    //MEMENTO DP
    private Stack primary = new Stack<ArrayList<Shape>>();
    private Stack secondary = new Stack<ArrayList<Shape>>();


    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == StartBtn){
            Before.setVisible(false);
            After.setVisible(true);
        }
        
        Message.setText("");
        if(event.getSource()==DeleteBtn){
            if(!ShapeList.getSelectionModel().isEmpty()){
            int index = ShapeList.getSelectionModel().getSelectedIndex();
            removeShape(shapeList.get(index));
            }else{
                Message.setText("You need to pick a shape first to delete it.");
            }
        }
        
        if(event.getSource()==RecolorBtn){
            if(!ShapeList.getSelectionModel().isEmpty()){
                int index = ShapeList.getSelectionModel().getSelectedIndex();
                shapeList.get(index).setFillColor(ColorBox.getValue());
                refresh(CanvasBox);
            }else{
                Message.setText("You need to pick a shape first to recolor it.");
            }
        }
        
        if(event.getSource()==MoveBtn){
            if(!ShapeList.getSelectionModel().isEmpty()){
                move=true;
                Message.setText("Click on the new top-left position below to move the selected shape.");
            }else{
                Message.setText("You need to pick a shape first to move it.");
            }
        }
        
        if(event.getSource()==CopyBtn){
            if(!ShapeList.getSelectionModel().isEmpty()){
                copy=true;
                Message.setText("Click on the new top-left position below to copy the selected shape.");
            }else{
                Message.setText("You need to pick a shape first to copy it.");
            }
        }
        
        if(event.getSource()==ResizeBtn){
            if(!ShapeList.getSelectionModel().isEmpty()){
                resize=true;
                Message.setText("Click on the new right-button position below to resize the selected shape.");
            }else{
                Message.setText("You need to pick a shape first to copy it.");
            }
        }
        
        if(event.getSource()==UndoBtn){
            if(primary.empty()){Message.setText("We are back to zero point! .. Can Undo nothing more!");return;}
            undo();
        }
        
        if(event.getSource()==RedoBtn){
            if(secondary.empty()){Message.setText("There is no more history for me to get .. Go search history books.");return;}
            redo();
        }
        
        if(event.getSource()==SaveBtn){
            showPathPane();
            save=true;
        }
        
        if(event.getSource()==LoadBtn){
            showPathPane();
            load=true;
        }
        
        if(event.getSource()==ImportBtn){
            showPathPane();
            importt=true;
        }
        
        if(event.getSource()==PathBtn){
            if(PathText.getText().isEmpty()){PathText.setText("You need to set the path of the file.");return;}
            if(save){save=false;save(PathText.getText());}
            else if(load){load=false;load(PathText.getText());}
            else if(importt){importt=false;installPluginShape(PathText.getText());}
            hidePathPane();
        }
    }
    
    public void showPathPane(){
        Message.setVisible(false);
        PathPane.setVisible(true);
    }
    
    public void hidePathPane(){
        PathPane.setVisible(false);
        Message.setVisible(true);
    }
    
    public void startDrag(MouseEvent event){
        start = new Point2D(event.getX(),event.getY());
        Message.setText("");
    }
    public void endDrag(MouseEvent event) throws CloneNotSupportedException{
        end = new Point2D(event.getX(), event.getY());
        if(end.equals(start)){clickFunction();}else{dragFunction();}
    }
    
    public void clickFunction() throws CloneNotSupportedException{
        if(move){move=false;moveFunction();}
        else if(copy){copy=false;copyFunction();}
        else if(resize){resize=false;resizeFunction();}
    }
    
    public void moveFunction(){
        int index = ShapeList.getSelectionModel().getSelectedIndex();
        shapeList.get(index).setTopLeft(start);
        refresh(CanvasBox);
    }
    
    public void copyFunction() throws CloneNotSupportedException{
        int index = ShapeList.getSelectionModel().getSelectedIndex();
        Shape temp = shapeList.get(index).cloneShape();
        if(temp.equals(null)){System.out.println("Error cloning failed!");}
        else{
            shapeList.add(temp);
            shapeList.get(shapeList.size()-1).setTopLeft(start);
            refresh(CanvasBox);
        }
    }
    
    public void resizeFunction(){
        int index = ShapeList.getSelectionModel().getSelectedIndex();
        Color c = shapeList.get(index).getFillColor();
        start = shapeList.get(index).getTopLeft();
        //Factory DP
        Shape temp = new ShapeFactory().createShape(shapeList.get(index).getClass().getSimpleName(),start,end,ColorBox.getValue());
        if(temp.getClass().getSimpleName().equals("Line")){Message.setText("Line doesn't support this command. Sorry :(");return;}
        shapeList.remove(index);
        temp.setFillColor(c);
        shapeList.add(index, temp);
        refresh(CanvasBox);
        
    }
    
    public void dragFunction(){
        String type = ShapeBox.getValue();
        Shape sh;
        //Factory DP
        try{sh = new ShapeFactory().createShape(type,start,end,ColorBox.getValue());}catch(Exception e)
        {Message.setText("Don't be in a hurry! Choose a shape first :'D");return;}
        addShape(sh);
        sh.draw(CanvasBox);
        
    }
    
    
    //Observer DP
    public ObservableList getStringList(){
        ObservableList l = FXCollections.observableArrayList(new ArrayList());
        try{
        for(int i=0;i<shapeList.size();i++){
            String temp = shapeList.get(i).getClass().getSimpleName() + "  (" + (int) shapeList.get(i).getTopLeft().getX() + "," + (int) shapeList.get(i).getTopLeft().getY() + ")";
            l.add(temp);
        }
        }catch(Exception e){}
        return l;
    }
    
    public ArrayList<Shape> cloneList(ArrayList<Shape> l) throws CloneNotSupportedException{
        ArrayList<Shape> temp = new ArrayList<Shape>();
        for(int i=0;i<l.size();i++){
            temp.add(l.get(i).cloneShape());
        }
        return temp;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList shapeList = FXCollections.observableArrayList();
        shapeList.add("Circle");shapeList.add("Ellipse");shapeList.add("Rectangle");shapeList.add("Square");shapeList.add("Triangle");shapeList.add("Line");
        ShapeBox.setItems(shapeList);
        
        ColorBox.setValue(Color.BLACK);
    }

    @Override
    public void refresh(Object canvas) {
        try {
            primary.push(new ArrayList(cloneList(shapeList)));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        redraw((Canvas) canvas);
       ShapeList.setItems((getStringList()));
    }
    
    public void redraw(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 850, 370);
        try{
        for(int i=0;i<shapeList.size();i++){
            shapeList.get(i).draw(canvas);
        }
        }catch(Exception e){}
    }

    @Override
    public void addShape(Shape shape) {
        shapeList.add(shape);
        refresh(CanvasBox);
    }

    @Override
    public void removeShape(Shape shape) {
        shapeList.remove(shape);
        refresh(CanvasBox);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        shapeList.remove(oldShape);
        shapeList.add(newShape);
        refresh(CanvasBox);
    }

    @Override
    public Shape[] getShapes() {
     return (Shape[]) shapeList.toArray();
    }

    @Override
    public void undo() {
        if(secondary.size()<21){
        ArrayList temp = (ArrayList) primary.pop();
        secondary.push(temp);
        
        if(primary.empty()){shapeList = new ArrayList();}
        else{temp = (ArrayList) primary.peek(); shapeList = temp;}
        
        redraw(CanvasBox);
        ShapeList.setItems((getStringList()));
        }else{Message.setText("Sorry, Cannot do more than 20 Undo's :'(");}
    }

    @Override
    public void redo() {
        ArrayList temp = (ArrayList) secondary.pop();
        primary.push(temp);
        
        temp = (ArrayList) primary.peek();
        shapeList = temp;
        
        redraw(CanvasBox);
        ShapeList.setItems((getStringList()));
    }

    @Override
    public void save(String path) {
        if(path.substring(path.length()-4).equals(".xml")){
            SaveToXML x = new SaveToXML(path,shapeList);
            if(x.checkSuccess()){Message.setText("File Saved Successfully");}
            else{Message.setText("Error happened while saving, please check the path and try again!");}
        }
        else if(path.substring(path.length()-5).equals(".json")){
            Message.setText("Sorry, Json is not supported :(");
        }
        else{Message.setText("Wrong file format .. save to either .xml or .json");}
  
    }

    @Override
    public void load(String path) {
        if(path.substring(path.length()-4).equals(".xml")){
            try {
                LoadFromXML l = new LoadFromXML(path);
                if(l.checkSuccess()){
                shapeList = l.getList();
                refresh(CanvasBox);
                Message.setText("File loaded successfully");
                }
                else{Message.setText("Error loading the file .. check the file path and try again!");}
            } catch (SAXException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(path.substring(path.length()-5).equals(".json")){
            Message.setText("Sorry, Json is not supported :(");
        }
        else{Message.setText("Wrong file format .. load from either .xml or .json");}
    }

    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        return null;
    }

    @Override
    public void installPluginShape(String jarPath) {
        Message.setText("Not supported yet.");
    }

    
    
}
