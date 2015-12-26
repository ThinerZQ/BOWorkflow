package com.sysu.workflow.engine;

import java.util.Stack;

/**
 * 业务对象实例树
 * Created by zhengshouzi on 2015/12/26.
 */
public class SCXMLInstanceTree {

    public TreeNode root=null;
    public String rootSessionId =null;

    public SCXMLInstanceTree (String sessionId){
        this.rootSessionId = sessionId;
        root = new TreeNode(sessionId,null,null);
    }

    /**
     * 插入节点,
     * @param insertLocation 插入的节点的sessionId
     * @param insertSessionId 待插入节点的sessionId
     */


    public void insert(String insertLocation,String insertSessionId){

        if (isContainNode(insertLocation)){
            TreeNode node = getNode(insertLocation);

            TreeNode newNode = new TreeNode(insertSessionId,null,null);

            if (node.getLeftChild()==null){
                node.setLeftChild(newNode);
            }else{
                TreeNode brotherNode = node.getLeftChild().getRightBrother();
                TreeNode last = node.getLeftChild();
                while (brotherNode!=null){
                   last = brotherNode;
                    brotherNode = brotherNode.getRightBrother();
                }
                last.setRightBrother(newNode);
            }
        }
    }

    /**
     * 以root为根的树中是否包含sessionId
     * @param root
     * @param sessionId
     * @return
     */
    public boolean isContainNode(String root,String sessionId){
        return getNode(getRoot(),sessionId)==null?false:true;
    }

    /**
     * 判断树中是否包含sessionId
     * @param sessionId
     * @return
     */
    public boolean isContainNode(String sessionId){

        return getNode(sessionId)==null?false:true;
    }

    /**
     * 根据根节点和sessionid寻找节点在树中的位置
     * @param tempRoot
     * @param sessionId
     * @return 找到的节点，没有为null
     */
    public TreeNode getNode(TreeNode tempRoot,String sessionId){

        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        nodeStack.push(tempRoot);
        TreeNode node = null;
        while (!nodeStack.empty()){
            node = nodeStack.pop();
            //遍历根节点
            if (node.getSessionId() == sessionId){
                return node;
            }
            if (node.getRightBrother()!=null){
                nodeStack.push(node.getRightBrother());
            }
            if (node.getLeftChild()!=null){
                nodeStack.push(node.getLeftChild());
            }

        }
        return null;
    }

    /**
     * 深度优先遍历
     * @param tempRoot 遍历的树的根节点
     */
    public void depthFirstSearch(TreeNode tempRoot){

        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        nodeStack.push(tempRoot);
        TreeNode node = null;
        while (!nodeStack.empty()){
            node = nodeStack.pop();
            //遍历根节点
            System.out.print(node.getSessionId());
            if (node.getRightBrother()!=null){
                nodeStack.push(node.getRightBrother());
            }
            if (node.getLeftChild()!=null){
                nodeStack.push(node.getLeftChild());
            }
        }
    }

    public TreeNode getNode(String sessionId){

        return getNode(getRoot(),sessionId);
    }


    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public String getRootSessionId() {
        return rootSessionId;
    }

    public void setRootSessionId(String rootSessionId) {
        this.rootSessionId = rootSessionId;
    }

    /**
     * 表示树上节点的私有内部类
     */
    private class TreeNode{

        private String sessionId;
        private TreeNode leftChild;
        private TreeNode rightBrother;


        public TreeNode(String sessionId) {
            this.sessionId = sessionId;
        }

        public TreeNode(String sessionId, TreeNode leftChild, TreeNode rightBrother) {
            this.sessionId = sessionId;
            this.leftChild = leftChild;
            this.rightBrother = rightBrother;
        }

        public TreeNode() {
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightBrother() {
            return rightBrother;
        }

        public void setRightBrother(TreeNode rightBrother) {
            this.rightBrother = rightBrother;
        }
    }

}

