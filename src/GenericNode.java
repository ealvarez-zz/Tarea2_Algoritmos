import java.util.ArrayList;

/**
*
* @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
*/
public class GenericNode {

    private int _value;
    private GenericNode _parent;
    private ArrayList<GenericNode> _children;

    public GenericNode(int value) {
        this._value = value;
        this._children = new ArrayList<GenericNode>();
        this._parent = null;
    }

    public GenericNode(int value, GenericNode parent) {
        this._value = value;
        this._children = new ArrayList<GenericNode>();
        this._parent = parent;
    }

    public GenericNode(int _value, ArrayList<GenericNode> _children) {
        this._value = _value;
        this._children = _children;
    }

    public GenericNode(int _value, ArrayList<GenericNode> _children, GenericNode parent) {
        this._value = _value;
        this._children = _children;
        this._parent = parent;
    }

    public int getValue() {
        return this._value;
    }

    public ArrayList<GenericNode> getChildren() {
        return this._children;
    }

    public void addChild(GenericNode node) {
        this._children.add(node);
    }

    public void setChildren(ArrayList<GenericNode> _children) {
        this._children = _children;
    }

    public void setValue(int _value) {
        this._value = _value;
    }

    public void setParent(GenericNode _parent) {
        this._parent = _parent;
    }

    public GenericNode getParent() {
        return this._parent;
    }



}
