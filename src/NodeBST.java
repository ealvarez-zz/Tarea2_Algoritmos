
import java.util.ArrayList;

public class NodeBST extends GenericNode{

    public NodeBST(int value){
        super(value);
    }
    
    public NodeBST(int value, NodeBST node){
        super(value, (GenericNode)node);
    }
    
    public NodeBST getLeftChild(){
        return (NodeBST)this.getChildren().get(0);
    }

    public NodeBST getRightChild(){
        return (NodeBST)this.getChildren().get(1);
    }

    @Override
    public NodeBST getParent(){
        return (NodeBST)this.getParent();
    }

    public void setLeftChild(NodeBST node){
        ArrayList<GenericNode> childrens = this.getChildren();
        childrens.add(0, node);
        this.setChildren(childrens);
    }
    public void setRightChild(NodeBST node){
        ArrayList<GenericNode> childrens = this.getChildren();
        childrens.add(1, node);
        this.setChildren(childrens);
    }
}
