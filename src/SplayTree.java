
public class SplayTree implements Tarea2Structure{
    NodeBST root;
    /**
     * Genera un arbol vacio
     */
    public SplayTree() {
        this.root = null;
    }

    @Override
    /**Inserta un valor en el arbol
     * @param value
     */
    public void insert(int value) {
        //se inserta como en un abb
        if (root == null) {
            root = new NodeBST(value);
            return;
        }

        NodeBST tempNode = root;
        while (tempNode != null) {
            //el valor ya se encuentra en el arbol
            if (value == tempNode.getValue()) {
                splay(tempNode);
                return;
            }
            //el valor que quiero insertar es menor que el que tengo en el nodo
            if (value < tempNode.getValue()) {
                //si el hijo izquierdo es null
                if (tempNode.getLeftChild() == null) {
                    NodeBST leftChild =
                            new NodeBST(value, tempNode);
                    tempNode.setLeftChild(leftChild);
                    splay(leftChild);
                    return;
                } else {
                    tempNode = tempNode.getLeftChild();
                }
            } else {
                //si el hijo derecho es null
                if (tempNode.getRightChild() == null) {
                    NodeBST rightChild =
                            new NodeBST(value, tempNode);
                    tempNode.setRightChild(rightChild);
                    splay(rightChild);
                    return;
                } else {
                    tempNode = tempNode.getRightChild();
                }
            }
        }
    }

    @Override
    /**
     * Borra el minimo del arbol
     */
    public void deleteMin(){
        NodeBST tempNode = root;

        if(tempNode == null)
            return;

        //avanzo hasta el hijo más a la izquierda
        while (tempNode != null) {
            if (tempNode.getLeftChild() == null) {
                break;
            }
            tempNode = tempNode.getLeftChild();
        }

        //si no tiene hijo derecho lo borro
        if(tempNode.getRightChild() == null){
            //si tiene padre
            if(tempNode.getParent() != null){
                tempNode.getParent().setLeftChild(null);
                tempNode = null;
            }
            else{
                //no tiene padre => es la raiz
                tempNode = null;
                root = null;
            }
        }
        else{
            if(tempNode.getParent() != null){
                tempNode.getParent().setLeftChild(tempNode.getRightChild());
                tempNode.getRightChild().setParent(tempNode.getParent());
            }
            else{
                //no tiene padre => es la raiz
                tempNode.getRightChild().setParent(null);
                root = tempNode.getRightChild();
            }
        }
    }

    /**Borra los nodos igual que en un arbol de busqueda binaria
     * @param value
     */
    public void delete(int value) {
        if (root == null) {
            return;
        }
        if (root.getValue() == value){
            root = null;
            return;
        }
        NodeBST tempNode = root;
        while (tempNode != null) {
            //es lo que quiero borrar
            if (value == tempNode.getValue()) {
                //si no tiene hijos, simplemente lo borro
                if (tempNode.getLeftChild() == null
                        && tempNode.getRightChild() == null) {
                    if(tempNode.getParent().getLeftChild() == tempNode)
                        tempNode.getParent().setLeftChild(null);
                    else
                        tempNode.getParent().setRightChild(null);
                    tempNode = null;
                    return;
                } else {
                    //si tiene 2 hijos
                    //NodeBST derecho = tempNode.getRightChild();
                    //NodeBST izquierdo = tempNode.getLeftChild();
                    if (tempNode.getLeftChild() != null
                            && tempNode.getRightChild() != null) {
                        changeWithRightMostNodeFromLeftChild(tempNode);
                    } //si tiene sólo un hijo, simplemente lo replazo por el hijo
                    else {
                        //es el hijo izquiedo
                        if (tempNode.getLeftChild() != null) {
                            tempNode.getLeftChild().setParent(tempNode.getParent());
                            //es el hijo izquierdo de su padre
                            if (tempNode.getParent().getLeftChild() == tempNode) {
                                tempNode.getParent().setLeftChild(tempNode.getLeftChild());
                            } else {
                                tempNode.getParent().setRightChild(tempNode.getLeftChild());
                            }
                            return;
                        } //es hijo derecho
                        else {
                            tempNode.getRightChild().setParent(tempNode.getParent());
                            //es el hijo izquierdo de su padre
                            if (tempNode.getParent().getLeftChild() == tempNode) {
                                tempNode.getParent().setLeftChild(tempNode.getRightChild());
                            } else {
                                tempNode.getParent().setRightChild(tempNode.getRightChild());
                            }
                        }
                    }
                }
            } else {
                if (value < tempNode.getValue()) {
                    //si el hijo izquierdo está vacio
                    if (tempNode.getLeftChild() == null) {
                        return;
                    }
                    tempNode = tempNode.getLeftChild();
                } else {
                    //si el hijo derecho está vacio
                    if (tempNode.getRightChild() == null) {
                        return;
                    }
                    tempNode = tempNode.getRightChild();
                }

            }
        }
    }

    /**
     * Rotacion que se aplica cuando el padre del nodo es la raiz
     * @param node
     */
    private void zig(NodeBST node) {
        NodeBST parent = (NodeBST) node.getParent();
        //soy el hijo izquierdo
        if (parent.getLeftChild() == node) {
            //el que era mi hijo derecho, ahora es el hijo izquierdo de mi padre
            parent.setLeftChild(node.getRightChild());
            //el que era mi padre ahora es mi hijo derecho
            node.setRightChild(parent);

        } else {
            if (parent.getRightChild() == node) {
                //el que era mi hijo izquierdo, ahora es el hijo derecho de mi padre
                parent.setRightChild(node.getLeftChild());
                //el que era mi padre ahora es mi hijo izquierdo
                node.setLeftChild(parent);
            }
        }
        //mi padre ahora es null
        node.setParent(null);
        //ahora yo soy el padre de mi hijo
        parent.setParent(node);
    }

    /**
     * Rotacion que se realiza cuando el nodo y su padre son hijos izquierdos o
     * hijos derechos
     * @param node
     * @param greatGreatParent
     */
    private void zigZig(NodeBST node, NodeBST greatGreatParent) {
        NodeBST parent = (NodeBST) node.getParent();
        NodeBST greatParent = (NodeBST) parent.getParent();

        if (greatGreatParent.getLeftChild() == greatParent) {
            greatGreatParent.setLeftChild(node);
        } else {
            if (greatGreatParent.getRightChild() == greatParent) {
                greatGreatParent.setRightChild(node);
            }
        }

        //son hijos izquierdos
        if (greatParent.getLeftChild() == parent
                && parent.getLeftChild() == node) {
            parent.setLeftChild(node.getRightChild());
            greatParent.setLeftChild(parent.getRightChild());
            parent.setRightChild(greatParent);
            node.setRightChild(parent);
        } else {
            //son hijos derechos
            if (greatParent.getRightChild() == parent
                    && parent.getRightChild() == node) {
                parent.setRightChild(node.getLeftChild());
                greatParent.setRightChild(parent.getLeftChild());
                parent.setLeftChild(greatParent);
                node.setLeftChild(parent);
            }
        }

        node.setParent(greatGreatParent);
        parent.setParent(node);
        greatParent.setParent(parent);
    }

    /**
     * Rotacion que se realiza cuando el nodo y su padre no son hijos derechos o
     * izquierdos
     * @param node
     * @param greatGreatParent
     */
    private void zigZag(NodeBST node, NodeBST greatGreatParent) {
        NodeBST parent = (NodeBST) node.getParent();
        NodeBST greatParent = (NodeBST) parent.getParent();

        if (greatGreatParent.getLeftChild() == greatParent) {
            greatGreatParent.setLeftChild(node);
        } else {
            if (greatGreatParent.getRightChild() == greatParent) {
                greatGreatParent.setRightChild(node);
            }
        }

        if (greatParent.getLeftChild() == parent
                && parent.getRightChild() == node) {
            parent.setRightChild(node.getLeftChild());
            greatParent.setLeftChild(node.getRightChild());
            node.setRightChild(greatParent);
            node.setLeftChild(parent);
        } else {
            if (greatParent.getRightChild() == parent
                    && parent.getLeftChild() == node) {
                parent.setLeftChild(node.getRightChild());
                greatParent.setRightChild(node.getLeftChild());
                node.setLeftChild(greatParent);
                node.setRightChild(parent);
            }
        }

        node.setParent(greatGreatParent);
        parent.setParent(node);
        greatParent.setParent(node);
    }

    /**
     * Aplica splay al arbol
     * @param node
     */
    private void splay(NodeBST node) {
        while (node.getParent() != null) {
            NodeBST parent = (NodeBST) node.getParent();
            //el padre del nodo es la raiz
            if (parent == root) {
                zig(node);
            } else {
                NodeBST greatParent = (NodeBST) parent.getParent();
                NodeBST greatGreatParent;
                if (greatParent != null) {
                    greatGreatParent = (NodeBST) greatParent.getParent();
                } else {
                    greatGreatParent = null;
                }

                if ((greatParent.getLeftChild() == parent
                        && parent.getLeftChild() == node)
                        || (greatParent.getRightChild() == parent
                        && parent.getRightChild() == node)) {
                    zigZig(node, greatGreatParent);
                } else {
                    zigZag(node, greatGreatParent);
                }
            }
        }
        //llegué a la raiz y debo ser la raiz
        root = node;
    }

    /**
     * Cambia el nodo dado por el hijo de más a la derecha de su hijo izquierdo
     * @param node
     */
    private void changeWithRightMostNodeFromLeftChild(NodeBST node) {
        NodeBST tempNode = node.getLeftChild();
        //avanzo hasta el hijo más a la derecha de mi hijo izquierdo
        while (tempNode != null) {
            if (tempNode.getRightChild() == null) {
                break;
            }
            tempNode = tempNode.getRightChild();
        }

        //su hijo, es el hijo del que era su padre
        tempNode.getParent().setRightChild(tempNode.getLeftChild());
        if (tempNode.getLeftChild() != null) {
            tempNode.getLeftChild().setParent(tempNode.getParent());
        }

        tempNode.setParent(node.getParent());
        node.getParent().setLeftChild(tempNode);
    }

    public void print(NodeBST node) {
        if(node != null){
            node.print();
            if(node.getLeftChild() != null)
                print(node.getLeftChild());
            if(node.getRightChild() != null)
                print(node.getRightChild());
        }

    }

}
