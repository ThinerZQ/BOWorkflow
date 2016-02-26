package com.sysu.workflow.engine;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 业务对象实例树
 * Created by zhengshouzi on 2015/12/26.
 */
public class SCXMLInstanceTree {

    private TreeNode root=null;
    private String rootSessionId =null;
    private String rootSessionName=null;

    public SCXMLInstanceTree (String sessionId,String sessionName){
        this.rootSessionId = sessionId;
        this.rootSessionName = sessionName;
        root = new TreeNode(sessionId,sessionName,null,null);
    }

    /**
     * 插入节点,
     * @param insertLocation 插入的节点的sessionId
     * @param insertSessionId 待插入节点的sessionId
     */


    public void insert(String insertLocation,String insertSessionId,String insertSessionName){

        if (isContainNode(insertLocation)){
            TreeNode node = getNode(insertLocation);

            TreeNode newNode = new TreeNode(insertSessionId,insertSessionName,null,null);

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
            //set up parent node
            newNode.setParent(node);
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
     * 采用深度优先的方式遍历节点
     *
     * 得到以 tempRoot为根的所有 节点
     * @param tempRoot 当前节点
     * @return
     */
    public ArrayList<TreeNode> getAllTreeNode(TreeNode tempRoot){

        return  getAllTreeNodeByTargetName(tempRoot,null);

    }
    /**
     * 采用深度优先的方式遍历节点
     *
     * 得到以 tempRoot为根的所有 节点
     * @param tempRoot 当前节点
     * @return
     */
    public ArrayList<TreeNode> getAllTreeNodeByTargetName(TreeNode tempRoot,String targetName){

        ArrayList<TreeNode> treeNodeArrayList = new ArrayList<TreeNode>();

        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        nodeStack.push(tempRoot);
        TreeNode node = null;

        while (!nodeStack.empty()){
            node = nodeStack.pop();
            if (targetName!=null && targetName.equals(node.getSessionName())){
                treeNodeArrayList.add(node);
            }else if (targetName ==null){
                treeNodeArrayList.add(node);
            }
            //System.out.print(node.getSessionId());
            if (node.getRightBrother()!=null){
                nodeStack.push(node.getRightBrother());
            }
            if (node.getLeftChild()!=null){
                nodeStack.push(node.getLeftChild());
            }
        }
        //需要将tempRoot给移除了
        treeNodeArrayList.remove(tempRoot);

        return treeNodeArrayList;

    }

    public ArrayList<TreeNode> getAllAncestorTreeNodeByTargetName(TreeNode currentTreeNode, String targetName) {
        ArrayList<TreeNode> treeNodeArrayList = new ArrayList<TreeNode>();

        // TODO:
        //根据当前节点查找祖先

        return treeNodeArrayList;
    }

    public ArrayList<TreeNode> getAllAncestorTreeNode(TreeNode currentTreeNode) {



        return getAllAncestorTreeNodeByTargetName(currentTreeNode, null);
    }


    public ArrayList<TreeNode> getOffSpringTreeNodeByTargetName(TreeNode currentTreeNode, String targetName) {
        ArrayList<TreeNode> treeNodeArrayList = new ArrayList<TreeNode>();


        //根据当前节点查找祖先
        // TODO:
        return treeNodeArrayList;
    }

    public ArrayList<TreeNode> getOffspringTreeNode(TreeNode currentTreeNode) {
        return getOffSpringTreeNodeByTargetName(currentTreeNode,null);
    }

    public TreeNode getParentTreeNode(TreeNode currentTreeNode) {

        return currentTreeNode.getParent();
    }


    /**
     * 查找孩子节点
     * @param currentTreeNode
     * @param targetName
     * @return
     */

    public ArrayList<TreeNode> getChildTreeNodeByTargetName(TreeNode currentTreeNode, String targetName) {

        //根据当前节点查找孩子节点
        TreeNode tempNode = currentTreeNode;
        ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
        if (tempNode == null || tempNode.getLeftChild() == null){
            return nodeList;
        }
        tempNode = tempNode.getLeftChild();
        while (tempNode !=null){
            nodeList.add(tempNode);
            tempNode = tempNode.getRightBrother();
        }
        return nodeList;
    }
    public ArrayList<String> getChildTreeNodeSessionIdByTargetName(TreeNode currentTreeNode, String targetName) {

        //根据当前节点查找孩子节点
        ArrayList<String> childTreeNodeSessionId = new ArrayList<String>();
        ArrayList<TreeNode> childTreeNode = getChildTreeNodeByTargetName(currentTreeNode,targetName);
        for (TreeNode treeNode : childTreeNode){
            childTreeNodeSessionId.add(treeNode.getSessionId());
        }
        return childTreeNodeSessionId;
    }
    public ArrayList<TreeNode> getChildTreeNode(TreeNode currentTreeNode) {
        return getChildTreeNodeByTargetName(currentTreeNode, null);
    }
    public ArrayList<TreeNode> getChildTreeNode(String currentTreeNodeSessionId) {

        //根据当前节点的ID, 查找孩子节点
        TreeNode currentTreeNode = getNode(currentTreeNodeSessionId);

        return getChildTreeNodeByTargetName(currentTreeNode, null);
    }
    public ArrayList<String> getChildTreeNodeSessionId(String currentTreeNodeSessionId) {

        //根据当前节点的ID, 查找孩子节点
        TreeNode currentTreeNode = getNode(currentTreeNodeSessionId);

        return getChildTreeNodeSessionIdByTargetName(currentTreeNode, null);
    }

    /**
     * 表示树上节点的私有内部类
     */
    public class TreeNode{

        private String sessionId;
        private String sessionName;
        private TreeNode leftChild;
        private TreeNode rightBrother;
        // TODO: 添加上父亲的引用
        private TreeNode parent;


        public TreeNode(String sessionId) {
            this.sessionId = sessionId;
        }

        public TreeNode(String sessionId,String sessionName, TreeNode leftChild, TreeNode rightBrother) {
            this.sessionId = sessionId;
            this.sessionName = sessionName;
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

        public String getSessionName() {
            return sessionName;
        }

        public void setSessionName(String sessionName) {
            this.sessionName = sessionName;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }
    }

}

