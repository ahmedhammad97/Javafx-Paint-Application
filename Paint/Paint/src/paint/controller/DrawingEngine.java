package paint.controller;

import paint.model.Shape;


public interface DrawingEngine {
    
    /* redraw all shapes on the canvas */
    public void refresh(Object canvas);
    
    public void addShape(Shape shape);
    
    public void removeShape(Shape shape);
    
    public void updateShape(Shape oldShape, Shape newShape);
    
    /* return the created shapes objects */
    public Shape[] getShapes();
    
    /* limited to 20 steps. You consider these actions in
    * undo & redo: addShape, removeShape, updateShape */
    public void undo();
    public void redo();
    
    /* use the file extension to determine the type,
    * or throw runtime exception when unexpected extension */
    public void save(String path);
    public void load(String path);
    
// *************** bonus functions ****************
    /* return the classes (types) of supported shapes already exist and the
    * ones that can be dynamically loaded at runtime (see Part 4) */
    public java.util.List<Class<? extends Shape>> getSupportedShapes();
    
    /* add to the supported shapes the new shape class (see Part 4) */
    public void installPluginShape(String jarPath);
}