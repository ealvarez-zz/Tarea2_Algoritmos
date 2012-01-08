public class NodeBST extends GenericNode{

    public NodeBST(int value){
        super(value);
        this.getChildren().add(0, null);
        this.getChildren().add(1, null);
    }
    
    public NodeBST(int value, NodeBST node){
        super(value, (GenericNode)node);
        this.getChildren().add(0, null);
        this.getChildren().add(1, null);
    }
    
    public NodeBST getLeftChild(){
        if(this.getChildren().size() >= 1)
            return (NodeBST)this.getChildren().get(0);
        else
            return null;
    }

    public NodeBST getRightChild(){
        if(this.getChildren().size() >= 2)
            return (NodeBST)this.getChildren().get(1);
        else
            return null;
    }

    @Override
    public NodeBST getParent(){
        return (NodeBST)super.getParent();
    }

    public void setLeftChild(NodeBST node){
        this.getChildren().set(0, node);
    }
    public void setRightChild(NodeBST node){
        this.getChildren().set(1, node);
    }
    public void print(){
        System.out.println("valor: "+ this.getValue());
    }
}
