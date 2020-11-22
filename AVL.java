/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;
import java.util.List;  
import java.util.stream.Collectors; 
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;

/**
 *
 * @author kaavk
 */
public class AVL extends Application {
  @Override
    public void start(Stage s) 
    {     
        AVLTree tree = new AVLTree();
        BorderPane bp = new BorderPane();
        AVLView view = new AVLView(tree); // Create a BTView
        bp.setCenter(view);
    
        s.setTitle("AVL Tree Visualization");
      
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.setSpacing(20);
        vbox.setStyle("-fx-background-color: #081856;");
        
        TextField value = new TextField("Type int");
        value.setMaxWidth(130);
        value.setStyle("-fx-text-fill: black");
        value.setFont(new Font("ARIAL", 14));
        value.setAlignment(Pos.CENTER);
        Button insert = new Button("Insert");
        insert.setPrefSize(100, 40);
        insert.setStyle("-fx-font-size:14");
        Button delete= new Button("Delete");
        delete.setPrefSize(100, 40);
        delete.setStyle("-fx-font-size:14");
        Button find= new Button("Find\n");
        find.setPrefSize(100, 40);
        find.setStyle("-fx-font-size:14");
        Label print = new Label("Print : \n");
        print.setStyle("-fx-font-weight: bold");
        print.setTextFill(Color.rgb(255,214,70));
        print.setFont(new Font("ARIAL",15));
        Button preorder= new Button("PreOrder");
        preorder.setPrefSize(100, 40);
        preorder.setStyle("-fx-font-size:14");
        Button inorder= new Button("InOrder");
        inorder.setPrefSize(100, 40);
        inorder.setStyle("-fx-font-size:14");
        Button postorder= new Button("PostOrder");
        postorder.setPrefSize(100, 40);
        postorder.setStyle("-fx-font-size:14");
        Label lb = new Label("HAPPY TRAVERSING!");
        lb.setStyle("-fx-font-weight: bold;");
        lb.setTextFill(Color.rgb(255,214,70));
        lb.setFont(new Font("ARIAL",15));
        
      
        vbox.getChildren().addAll(value, insert, delete, find, print, preorder, inorder, postorder,lb);
        vbox.setAlignment(Pos.CENTER);
		
        bp.setRight(vbox);
        bp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
	Alert a = new Alert(Alert.AlertType.INFORMATION); 
        Scene sc = new Scene(bp, 1000, 600); 
	// set the scene 
	s.setScene(sc); 
	s.show(); 
        //add delay
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
             Thread.currentThread().interrupt();
        }
        //set header text
        a.setHeaderText("!!* WELCOME *!!");
	// set content text 
	a.setContentText("This AVL Tree simulation is created by Kaavya Jain and Prakriti Agrawal for CSD203.\nIt can perform insertion, deletion, search and print the tree according to the chosen traversal."); 
	// show the dialog 
	a.show();
        insert.setOnAction((ActionEvent e) -> {
            int key = Integer.parseInt(value.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayAVLTree();
                view.setStatus(key + " is already in the tree");
                view.setHeight("Height: "+tree.height(tree.getRoot()));
                view.setVertices("No. of vertices: "+tree.countNodes());
                value.clear();
            } else {
                tree.insert(key); // Insert a new key
                view.displayAVLTree();
                view.setStatus(key + " is inserted in the tree");
                view.setHeight("Height: "+tree.height(tree.getRoot()));
                view.setVertices("No. of vertices: "+tree.countNodes());
                value.clear();
            }
        });

        delete.setOnAction(e -> {
            int key = Integer.parseInt(value.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayAVLTree();
                view.setStatus(key + " is not in the tree");
                view.setHeight("Height: "+tree.height(tree.getRoot()));
                view.setVertices("No. of vertices: "+tree.countNodes());
                value.clear();
            } else {
                tree.delete(key); // Delete a key
                view.displayAVLTree();
                view.setStatus(key + " is deleted from the tree");
                view.setHeight("Height: "+tree.height(tree.getRoot()));
                view.setVertices("No. of vertices: "+tree.countNodes());
                value.clear();
            }
        });
 
        find.setOnAction((ActionEvent e) -> {
            vbox.getChildren().add(lb);
            int key = Integer.parseInt(value.getText());
            if(!tree.search(key)){
                view.displayAVLTree();
                view.setStatus(key+" is not found in tree.");
                view.setHeight("Height: "+tree.height(tree.getRoot()));
                view.setVertices("No. of vertices: "+tree.countNodes());
                value.clear();
            } else{
                
            }
        });
        
        inorder.setOnAction((ActionEvent e) ->{
        view.displayAVLTree();
        view.setStatus("Inorder Traversal printed.");
        view.setHeight("Height: "+tree.height(tree.getRoot()));
        view.setVertices("No. of vertices: "+tree.countNodes());
        Alert in = new Alert(Alert.AlertType.INFORMATION);
        in.setHeaderText("INORDER:");
        ArrayList list = new ArrayList();
        in.setContentText((tree.inorder(tree.getRoot(), list)).toString());
        in.show();
        });
        
        preorder.setOnAction((ActionEvent e) ->{
        view.displayAVLTree();
        view.setStatus("Preorder Traversal printed.");
        view.setHeight("Height: "+tree.height(tree.getRoot()));
        view.setVertices("No. of vertices: "+tree.countNodes());
        Alert pre = new Alert(Alert.AlertType.INFORMATION);
        pre.setHeaderText("PREORDER:");
        ArrayList list = new ArrayList();
        pre.setContentText((tree.preorder(tree.getRoot(), list)).toString());
        pre.show();
        });
                
        postorder.setOnAction((ActionEvent e) ->{
        view.displayAVLTree();
        view.setStatus("Postorder Traversal printed.");
        view.setHeight("Height: "+tree.height(tree.getRoot()));
        view.setVertices("No. of vertices: "+tree.countNodes());
        Alert post = new Alert(Alert.AlertType.INFORMATION);
        post.setHeaderText("POSTORDER:");
        ArrayList list = new ArrayList();
        post.setContentText((tree.postorder(tree.getRoot(), list)).toString());
        post.show();
        });
    }
        
	public static void main(String args[]) 
	{ 
		// launch the application 
		Application.launch(args); 
	} 
}

class AVLView extends Pane {
  private AVLTree tree = new AVLTree();
  public double radius = 25; // Tree node radius
  private final double vGap = 90; // Gap between two levels in a tree
  AVLView(AVLTree tree) {
  this.tree = tree;
  setStatus("Tree is empty");
  setHeight("Height: 0");
  setVertices("No. of vertices: 0");
  }
  public final void setStatus(String msg) {
      Text t = new Text(20, 20, msg);
      t.setFont(Font.font("ARIAL", FontWeight.BOLD,FontPosture.REGULAR, 15));
      t.setFill(Color.rgb(255,246,241));
      getChildren().add(t);
  }
  public final void setHeight(String h){
      Text t = new Text(370, 20, h);
      t.setFont(Font.font("ARIAL", FontWeight.BOLD,FontPosture.REGULAR, 15));
      t.setFill(Color.rgb(255,246,241));
      getChildren().add(t);
  }
   public final void setVertices(String v){
   Text t = new Text(680, 20, v);
   t.setFont(Font.font("ARIAL", FontWeight.BOLD,FontPosture.REGULAR, 15));
   t.setFill(Color.rgb(255,246,241));
   getChildren().add(t);
  }
  
public void displayAVLTree(){
    this.getChildren().clear();
    if(tree.getRoot() != null){
        displayAVLTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
    }
}
protected void displayAVLTree(AVLTree.Node root, double x, double y, double hGap){
        if(root.left != null){
            Line l = new Line(x - hGap, y + vGap, x, y);
            l.setStroke(Color.rgb(8,91,185));
            l.setStrokeWidth(5.0f);
            getChildren().add(l);
            displayAVLTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null){
            Line l = new Line(x + hGap, y + vGap, x, y);
            l.setStroke(Color.rgb(8,91,185));
            l.setStrokeWidth(5.0f);
            getChildren().add(l);
            displayAVLTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.rgb(255,214,70));
        circle.setStroke(Color.rgb(8,91,185));
        circle.setStrokeWidth(4.0f);
        Text node = new Text(x - 4, y + 4, root.key + "");
        node.setStyle("-fx-font-size: 15");
        getChildren().addAll(circle, node);
    }
 
 }
class AVLTree {
    
    public Node root;
    public static class Node {
        public int key;
        public int balance;
        public int height;
        public Node left;
        public Node right;
        public Node parent;
 
        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }
 
    public boolean insert(int key) {
        if (root == null) {
            root = new Node(key, null);
            return true;
        }
 
        Node n = root;
        while (true) {
            if (n.key == key)
                return false;
 
            Node parent = n;
 
            boolean goLeft = n.key > key;
            n = goLeft ? n.left : n.right;
 
            if (n == null) {
                if (goLeft) {
                    parent.left = new Node(key, parent);
                } else {
                    parent.right = new Node(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }
 
    private void delete(Node node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }
 
        if (node.left != null) {
            Node child = node.left;
            while (child.right != null) child = child.right;
            node.key = child.key;
            delete(child);
        } else {
            Node child = node.right;
            while (child.left != null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }
 
    public void delete(int delKey) {
        if (root == null)
            return;
 
        Node child = root;
        while (child != null) {
            Node node = child;
            child = delKey >= node.key ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }
 
    private void rebalance(Node n) {
        setBalance(n);
 
        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
 
        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }
 
        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }
 
    private Node rotateLeft(Node a) {
 
        Node b = a.right;
        b.parent = a.parent;
 
        a.right = b.left;
 
        if (a.right != null)
            a.right.parent = a;
 
        b.left = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private Node rotateRight(Node a) {
 
        Node b = a.left;
        b.parent = a.parent;
 
        a.left = b.right;
 
        if (a.left != null)
            a.left.parent = a;
 
        b.right = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
 
    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
 
    public int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }
 
    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }
 
    public void printBalance() {
        printBalance(root);
    }
     public Node getRoot() {
      return root;
     }  
       public boolean search(int val)
     {
         return search(root, val);
     }
     private boolean search(Node r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.key;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(Node r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.left);
             l += countNodes(r.right);
             return l;
         }
     }
 
    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }
 
    private void reheight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
	void printPostorder(Node node) 
	{ 
		
               if (node == null) 
                return ;
               /* then print the data of node */
		System.out.print(node.key + " "); 
                // first recur on left subtree
                printPostorder(node.left);
		// then recur on right subtree 
		printPostorder(node.right); 
                
	} 

	/* Given a binary tree, print its nodes in inorder*/
	String printInorder(Node node) 
	{ 
		if (node == null) 
                return "Nothing in tree.";
		/* first recur on left child */
		printInorder(node.left); 

		/* then print the data of node */
		System.out.print(node.key + " "); 

		/* now recur on right child */
		printInorder(node.right);
                return "";
	} 
        public  ArrayList inorder(Node r,ArrayList l){ 
       if (r != null) {
       inorder(r.left,l);
       l.add(r.key);
       inorder(r.right,l);
       }
       return l;
 }
           public  ArrayList preorder(Node r,ArrayList l){ 
       if (r != null) {
           l.add(r.key);
       inorder(r.left,l);
       
       inorder(r.right,l);
       }
       return l;
 }
              public  ArrayList postorder(Node r,ArrayList l){ 
       if (r != null) {
       inorder(r.left,l);
       
       inorder(r.right,l);
       l.add(r.key);
       }
       return l;
 }

        


	/* Given a binary tree, print its nodes in preorder*/
	String printPreorder(Node node) 
	{ 
		if (node == null) 
			return "Nothing in tree."; 

		/* first print data of node */
		System.out.print(node.key + " "); 

		/* then recur on left sutree */
		printPreorder(node.left); 

		/* now recur on right subtree */
		printPreorder(node.right); 
                return"\n Tree printed.";
	}
}


