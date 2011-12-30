/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */

public class Node {
    
    private Node _leftChild = null;
    private Node _rightChild = null;
    private int _value = -1;
    
    
    /**
     * Crea un nodo sin hijos
     * 
     * @param value Valor guardado en el nodo
     */
    public Node(int value){
        this._value = value;
    }
    
    
    /**
     * Crea un nodo con hijos
     * 
     * @param value  Valor guardado en el nodo
     * @param leftChild Nodo hijo izquierdo
     * @param rightChild  Nodo hijo derecho
     * 
     */
    public Node(int value, Node leftChild, Node rightChild){
        this._value = value;
        this._leftChild = leftChild;
        this._rightChild = rightChild;
    }
    
    /**
     * 
     * @return Hijo izquierdo del nodo
     */
    public Node getLeftChild(){
        return this._leftChild;
    }
    
    /**
     * 
     * @return Hijo derecho del nodo
     */
    public Node getRightChild(){
        return this._rightChild;
    }
    
    /**
     * 
     * @return valor del nodo
     */
    public int getValue(){
        return this._value;
    }
    
    /**
     * 
     * @param value Nuevo valor para el nodo
     */
    public void setValue(int value){
        this._value = value;
    }
    
    /**
     * 
     * @param leftChild Nuevo hijo izquierdo
     */
    public void setLeftChild(Node leftChild){
        this._leftChild = leftChild;
    }
    
    /**
     * 
     * @param rightChild Nuevo hijo derecho
     */
    public void setRightChild(Node rightChild){
        this._rightChild = rightChild;
    }
    
}
