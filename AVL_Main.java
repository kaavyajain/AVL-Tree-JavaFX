/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avlview;

// Java Program to create alert and set 
// different alert types and button type 
// and also set different content text 

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.control.Alert.AlertType; 
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;

public class AVL_Main extends Application { 
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
 
        find.setOnAction(e -> {
            int key = Integer.parseInt(value.getText());
            if(!tree.search(key)){
                view.setStatus(key+" is not found in tree.");
                view.setHeight("Height: "+tree.height(tree.getRoot()));
                view.setVertices("No. of vertices: "+tree.countNodes());
                value.clear();
            } else{
 
            }
 
        });
        
        inorder.setOnAction(e -> {
        //view.setStatus(tree.inorder(tree.getRoot())+"");
        view.setStatus("hi");
        });
 
      
        vbox.getChildren().addAll(value, insert, delete, find, print, preorder, inorder, postorder,lb);
        vbox.setAlignment(Pos.CENTER);
		
        bp.setRight(vbox);
        bp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
	Alert a = new Alert(AlertType.INFORMATION); 
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
        }
	public static void main(String args[]) 
	{ 
		// launch the application 
		Application.launch(args); 
	} 
 


class AVLView extends Pane {
  private AVLTree tree = new AVLTree();
  public double radius = 25; // Tree node radius
  private double vGap = 70; // Gap between two levels in a tree
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
      Text t = new Text(350, 20, h);
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
protected void displayAVLTree(AVLNode root, double x, double y, double hGap){
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
        circle.setStrokeWidth(5.0f);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }
 
 }
    
} 
    


class AVLNode
 {    
     AVLNode left, right;
     int element;
     int height;
 
     /* Constructor */
     public AVLNode()
     {
         left = null;
         right = null;
         element = 0;
         height = 0;
     }
     /* Constructor */
     public AVLNode(int n)
     {
         left = null;
         right = null;
         element = n;
         height = 0;
     }     
 }
 
 /* Class AVLTree */
 class AVLTree
 {
     private AVLNode root;     
 
     /* Constructor */
     public AVLTree()
     {
         root = null;
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
     /* Make the tree logically empty */
     public void makeEmpty()
     {
         root = null;
     }
     /* Function to insert data */
     public void insert(int data)
     {
         root = insert(root, data);
     }
     public void delete(int data)
     {
      root = deleteNode(root, data);
     }
	// A utility function to get height of the tree 
    int height(AVLNode N) 
	{ 
		if (N == null) 
			return 0; 
		return N.height; 
	} 

	// A utility function to get maximum of two integers 
    int max(int a, int b) 
	{ 
		return (a > b) ? a : b; 
	} 

	// A utility function to right rotate subtree rooted with y 
	// See the diagram given above. 
	AVLNode rightRotate(AVLNode y) 
	{ 
		AVLNode x = y.left; 
		AVLNode T2 = x.right; 

		// Perform rotation 
		x.right = y; 
		y.left = T2; 

		// Update heights 
		y.height = max(height(y.left), height(y.right)) + 1; 
		x.height = max(height(x.left), height(x.right)) + 1; 

		// Return new root 
		return x; 
	} 

	// A utility function to left rotate subtree rooted with x 
	// See the diagram given above. 
	AVLNode leftRotate(AVLNode x) 
	{ 
		AVLNode y = x.right; 
		AVLNode T2 = y.left; 

		// Perform rotation 
		y.left = x; 
		x.right = T2; 

		// Update heights 
		x.height = max(height(x.left), height(x.right)) + 1; 
		y.height = max(height(y.left), height(y.right)) + 1; 

		// Return new root 
		return y; 
	} 

	// Get Balance factor of node N 
	int getBalance(AVLNode N) 
	{ 
		if (N == null) 
			return 0; 
		return height(N.left) - height(N.right); 
	} 

	AVLNode insert(AVLNode node, int key) 
	{ 
		/* 1. Perform the normal BST rotation */
		if (node == null) 
			return (new AVLNode(key)); 

		if (key < node.element) 
			node.left = insert(node.left, key); 
		else if (key > node.element) 
			node.right = insert(node.right, key); 
		else // Equal keys not allowed 
			return node; 

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left), 
							height(node.right)); 

		/* 3. Get the balance factor of this ancestor 
		node to check whether this node became 
		Wunbalanced */
		int balance = getBalance(node); 

		// If this node becomes unbalanced, then 
		// there are 4 cases Left Left Case 
		if (balance > 1 && key < node.left.element) 
			return rightRotate(node); 

		// Right Right Case 
		if (balance < -1 && key > node.right.element) 
			return leftRotate(node); 

		// Left Right Case 
		if (balance > 1 && key > node.left.element) 
		{ 
			node.left = leftRotate(node.left); 
			return rightRotate(node); 
		} 

		// Right Left Case 
		if (balance < -1 && key < node.right.element) 
		{ 
			node.right = rightRotate(node.right); 
			return leftRotate(node); 
		} 

		/* return the (unchanged) node pointer */
		return node; 
	} 

	/* Given a non-empty binary search tree, return the 
	node with minimum key value found in that tree. 
	Note that the entire tree does not need to be 
	searched. */
	AVLNode minValueNode(AVLNode node) 
	{ 
		AVLNode current = node; 

		/* loop down to find the leftmost leaf */
		while (current.left != null) 
		current = current.left; 

		return current; 
	} 

	AVLNode deleteNode(AVLNode root, int key) 
	{ 
		// STEP 1: PERFORM STANDARD BST DELETE 
		if (root == null) 
			return root; 

		// If the key to be deleted is smaller than 
		// the root's key, then it lies in left subtree 
		if (key < root.element) 
			root.left = deleteNode(root.left, key); 

		// If the key to be deleted is greater than the 
		// root's key, then it lies in right subtree 
		else if (key > root.element) 
			root.right = deleteNode(root.right, key); 

		// if key is same as root's key, then this is the node 
		// to be deleted 
		else
		{ 

			// node with only one child or no child 
			if ((root.left == null) || (root.right == null)) 
			{ 
				AVLNode temp = null; 
				if (temp == root.left) 
					temp = root.right; 
				else
					temp = root.left; 

				// No child case 
				if (temp == null) 
				{ 
					temp = root; 
					root = null; 
				} 
				else // One child case 
					root = temp; // Copy the contents of 
								// the non-empty child 
			} 
			else
			{ 

				// node with two children: Get the inorder 
				// successor (smallest in the right subtree) 
				AVLNode temp = minValueNode(root.right); 

				// Copy the inorder successor's data to this node 
				root.element = temp.element; 

				// Delete the inorder successor 
				root.right = deleteNode(root.right, temp.element); 
			} 
		} 

		// If the tree had only one node then return 
		if (root == null) 
			return root; 

		// STEP 2: UPDATE HEIGHT OF THE CURRENT NODE 
		root.height = max(height(root.left), height(root.right)) + 1; 

		// STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether 
		// this node became unbalanced) 
		int balance = getBalance(root); 

		// If this node becomes unbalanced, then there are 4 cases 
		// Left Left Case 
		if (balance > 1 && getBalance(root.left) >= 0) 
			return rightRotate(root); 

		// Left Right Case 
		if (balance > 1 && getBalance(root.left) < 0) 
		{ 
			root.left = leftRotate(root.left); 
			return rightRotate(root); 
		} 

		// Right Right Case 
		if (balance < -1 && getBalance(root.right) <= 0) 
			return leftRotate(root); 

		// Right Left Case 
		if (balance < -1 && getBalance(root.right) > 0) 
		{ 
			root.right = rightRotate(root.right); 
			return leftRotate(root); 
		} 

		return root; 
	} 

	
	  
     /* Functions to count number of nodes */
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(AVLNode r)
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
     /* Functions to search for an element */
     public boolean search(int val)
     {
         return search(root, val);
     }
     private boolean search(AVLNode r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.element;
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
     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
         
     }
     public void inorder(AVLNode r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.element +" ");
             inorder(r.right);
         }
         
     }
     /* Function for preorder traversal */
     public String preorder()
     {
         preorder(root);
         return "Tree: ";
     }
     public void preorder(AVLNode r)
     {
         if (r != null)
         {
             System.out.print(r.element +" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
         
     }
     public void postorder(AVLNode r)
     {
         if (r != null)
         {
             postorder(r.left);             
             postorder(r.right);
             System.out.print(r.element +" ");
         }
     }
     public AVLNode getRoot() {
      return root;
     }     
 }