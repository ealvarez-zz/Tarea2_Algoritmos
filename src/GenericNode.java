
import java.util.ArrayList;

/**
 *
 * @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
 */
public class GenericNode {

    private int _value;
    private ArrayList<GenericNode> _children;

    public GenericNode(int value) {
        this._value = value;
        this._children = new ArrayList<GenericNode>();
    }

    public GenericNode(int _value, ArrayList<GenericNode> _children) {
        this._value = _value;
        this._children = _children;
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

}
