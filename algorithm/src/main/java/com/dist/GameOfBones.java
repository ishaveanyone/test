package com.dist;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-10 14:13
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
class MiniMax {
    Tree tree;

    public void constructTree(int noOfBones) {
        tree = new Tree();
        Node root = new Node(noOfBones, true);
        tree.setRoot(root);
        constructTree(root);
    }

    private void constructTree(Node parentNode) {
        List<Integer> listofPossibleHeaps
                = GameOfBones.getPossibleStates(parentNode.getNoOfBones());
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
        listofPossibleHeaps.forEach(n -> {
            Node newNode = new Node(n, isChildMaxPlayer);
            parentNode.addChild(newNode);
            if (newNode.getNoOfBones() > 0) {
                constructTree(newNode);
            }
        });
    }
}
 class Node {
    int noOfBones;
    boolean isMaxPlayer;
    int score;
    List<Node> children;

     public int getNoOfBones() {
         return noOfBones;
     }

     public void setNoOfBones(int noOfBones) {
         this.noOfBones = noOfBones;
     }

     public boolean isMaxPlayer() {
         return isMaxPlayer;
     }

     public void setMaxPlayer(boolean maxPlayer) {
         isMaxPlayer = maxPlayer;
     }

     public int getScore() {
         return score;
     }

     public void setScore(int score) {
         this.score = score;
     }

     public List<Node> getChildren() {
         return children;
     }

     public void setChildren(List<Node> children) {
         this.children = children;
     }

     // setters and getters
}
 class Tree {
    Node root;

     public Node getRoot() {
         return root;
     }

     public void setRoot(Node root) {
         this.root = root;
     }

     // setters and getters
}
public class GameOfBones {

    static List<Integer> getPossibleStates(int noOfBonesInHeap) {
        return IntStream.rangeClosed(1, 3).boxed()
                .map(i -> noOfBonesInHeap - i)
                .filter(newHeapCount -> newHeapCount >= 0)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,2).boxed().forEach(System.out::println);
    }
}
