package SymbolTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Jon Koenes
 */
public class SymbolTable {

    LinkedList<Row> items = new LinkedList<>();
    LinkedList<SymbolTable> children = new LinkedList<>();
    SymbolTable parent;
    int nestLevel;
    int size = 1;
    String tableName;
    
    //main constructor needs a parent, the nesting level, and the name of the table
    public SymbolTable(SymbolTable in_parent, int in_nest, String name){
        parent = in_parent;
        nestLevel = in_nest;
        tableName = name;
    }
    public SymbolTable(){} //empty constructor if needed
    
    public String getName(){
        return tableName;
    }
    
    public int getNestingLevel(){
        return nestLevel;
    }
    
    public SymbolTable getParent(){
        return parent;
    }
    
    public int getSize(){
        return size;
    }
    
    public void setName(String name){
        tableName = name;
    }
    public void setNestingLevel(int level){
        nestLevel = level;
    }
    public void setParent(SymbolTable newParent){
        parent = newParent;
    }
    
    /*
    This method is just a skeleton until I know what Skylar's row class is like
    Every variable has size 1, so size is increased by one each time we make a new row, 
    with 1 being the starting size to save room for the display register value later
    */
    public void addRow(String ID, String kind, String type, String returnValues, String inputParameters){
        Row newRow = new Row(ID, kind, type, size++, 1, returnValues, inputParameters);
        items.add(newRow);
    }
    
    /*
    This method will find and return the row for information regarding an ID, following 
    the scope hierarchy. If no row with that particular ID is found, then null is returned
    */
    public Row findVariable(String id){
        
        Row returnRow;
        ListIterator<Row> it = items.listIterator();
        
        while (it.hasNext()){
            returnRow = it.next();
            if(returnRow.getID() == id){
                return returnRow;
            }
        }
        //the row we're looking for isn't in our list, we'll check our parent
        if(parent == null){ //have no parent, so variable doesn't exist
            return null;
        }
        else{
            return parent.findVariable(id);
        }   
    }
    
    public SymbolTable makeNewTable(String name)
    {
        SymbolTable newTable = new SymbolTable(this, nestLevel + 1, name);
        children.add(newTable);
        return newTable;
    }

}
