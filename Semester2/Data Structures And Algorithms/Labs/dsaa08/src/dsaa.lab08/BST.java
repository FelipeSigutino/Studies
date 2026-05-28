    package dsaa.lab08;

    import java.util.NoSuchElementException;

    public class BST<T extends Comparable<T>>{
        private class Node{
            T value;
            Node left,right,parent;
            int children = 0;
            public Node(T v) {
                value=v;
            }
            public Node(T value, Node left, Node right, Node parent) {
                super();
                this.value = value;
                this.left = left;
                this.right = right;
                this.parent = parent;
            }
        }
        private int size = 0;
        private Node root=null;

        public BST() {
        }

        public T getElement(T toFind) {

            Node current = root;

            if (current==null) return null;
            while (current!=null) {
                if (current.value.compareTo(toFind)==0) {
                    return current.value;
                }
                if (current.value.compareTo(toFind) > 0){
                    current = current.left;
                } else if (current.value.compareTo(toFind) < 0) {
                    current = current.right;
                }
            }
            return null;
        }

        public T successor(T elem) {
            if (elem==null || root ==null) return null;

            Node current = root;
            while (current!=null) {
                int cmp =current.value.compareTo(elem);

                if (cmp==0) {
                    break;
                }else if (cmp > 0) {
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }
            if (current==null) return null;

            if (current.right != null) {
                Node temp = current.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                return temp.value;
            }

            Node parent = current.parent;
            while (parent!=null && current == parent.right) {
                current = parent;
                parent = parent.parent;
            }
            if (parent == null) {
                return null;
            }


            return parent.value;
        }

        private void inOrder(Node node, StringBuilder sb) {
            if (node == null) {
                return;
            }

            inOrder(node.left,sb);

            if(sb.length()>0){
                sb.append(", ");
            }

            sb.append(node.value);

            inOrder(node.right,sb);
        }

        public String toStringInOrder() {
            StringBuilder sb=new StringBuilder();

            inOrder(root,sb);
            return sb.toString();
        }

        private void preOrder(Node node, StringBuilder sb) {
            if (node == null) {
                return;
            }
            sb.append(node.value).append(", ");

            preOrder(node.left,sb);
            preOrder(node.right,sb);
        }

        public String toStringPreOrder() {
            StringBuilder sb=new StringBuilder();

            preOrder(root,sb);

            if (sb.length()>=2){
                sb.setLength(sb.length()-2);
            }

            return sb.toString();
        }

        public void postOrder(Node node, StringBuilder sb) {
            if (node == null) return;

            postOrder(node.left,sb);
            postOrder(node.right,sb);

            sb.append(node.value).append(", ");
        }

        public String toStringPostOrder() {
            StringBuilder sb=new StringBuilder();

            postOrder(root,sb);

            if (sb.length()>=2) {
                sb.setLength(sb.length() - 2);
            }

            return sb.toString();
        }


        public boolean add(T elem) {
            if(root==null){
                root=new Node(elem);
                size++;
                return true;
            }

            Node current = root;
            Node parent = null;

            while(current!=null){
                parent = current;

                int cmp = elem.compareTo(current.value);

                if(cmp<0){
                    current=current.left;
                }else if(cmp>0){
                    current=current.right;
                } else{
                    return false;
                }
            }

            Node newNode = new Node(elem);
            newNode.parent = parent;

            if (elem.compareTo(parent.value)<0){
                parent.left=newNode;
            }else{
                parent.right=newNode;
            }

            size ++;
            return true;
        }

        public Node find(T elem) {
            if(root==null){
                return null;
            }

            Node current = root;

            while(current!=null){
                int cmp = elem.compareTo(current.value);

                if(cmp<0){
                    current=current.left;
                } else if(cmp>0){
                    current=current.right;
                } else {
                    return current;
                }
            }
            return null;
        }

        private Node minimum(Node node) {
            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        public T remove(T value) {
            if (root==null){
                return null;
            }

            Node current = find(value);

            if (current==null){
                return null;
            }

            T removedValue = current.value;

            if (current.right==null || current.left==null){
                Node child;

                if (current.left != null) {
                    child = current.left;
                } else{
                    child = current.right;
                }
                if (current.parent == null) {
                    root = child;

                    if (child != null) {
                        child.parent = null;
                    }
                } else if(current == current.parent.left) {

                    current.parent.left =child;

                    if (child != null) {
                        child.parent = current.parent;
                    }
                } else{
                    current.parent.right =child;

                    if (child != null) {
                        child.parent = current.parent;
                    }
                }

            }else {
                Node successor = minimum(current.right);

                current.value = successor.value;

                if(successor.parent.left == successor){

                    successor.parent.left = successor.right;

                    if (successor.right != null) {
                        successor.right.parent = successor.parent;
                    }
                } else{

                    successor.parent.right =successor.right;

                    if (successor.right != null) {
                        successor.right.parent = successor.parent;
                    }
                }
            }

            size--;
            return removedValue;    
        }

        public void clear() {
            root = null;
            size = 0;
        }

        public int size() {
            return size;
        }


        //Warunek?(jesliT):(jesliF) +
        private int traversal(Node node){
            if (node == null) return 0;

            return ((node.left != null && node.right != null)?1:0)+ traversal(node.left) + traversal(node.right);

        }


        public int twoChildren(){
            return traversal(root);
        }
    }
