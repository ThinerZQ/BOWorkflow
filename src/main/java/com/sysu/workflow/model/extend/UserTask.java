package com.sysu.workflow.model.extend;

import com.sysu.workflow.*;
import com.sysu.workflow.entity.GroupEntity;
import com.sysu.workflow.entity.GroupWorkItemEntity;
import com.sysu.workflow.entity.UserEntity;
import com.sysu.workflow.entity.UserWorkItemEntity;
import com.sysu.workflow.model.Action;
import com.sysu.workflow.model.EnterableState;
import com.sysu.workflow.model.ModelException;
import com.sysu.workflow.service.indentityservice.IdentityService;
import com.sysu.workflow.service.taskservice.TaskDispatcher;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Date: 2015/12/11
 * Time: 13:17
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://blog.csdn.net/c601097836</a>
 * Email: 601097836@qq.com
 *
 */
public class UserTask extends Action {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String assignee;
    private String assigneeExpr;
    private String candidateUsers;
    private String candidateUsersExpr;
    private String candidateGroups;
    private String candidateGroupsExpr;
    private String dueDate;
    private String instances="1";
    private String instancesExpr;

    private Form form;


    public UserTask() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCandidateUsers() {
        return candidateUsers;
    }

    public void setCandidateUsers(String candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    public String getCandidateGroups() {
        return candidateGroups;
    }

    public void setCandidateGroups(String candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getInstances() {
        return instances;
    }

    public void setInstances(String instances) {
        this.instances = instances;
    }

    public String getAssigneeExpr() {
        return assigneeExpr;
    }

    public void setAssigneeExpr(String assigneeExpr) {
        this.assigneeExpr = assigneeExpr;
    }

    public String getCandidateUsersExpr() {
        return candidateUsersExpr;
    }

    public void setCandidateUsersExpr(String candidateUsersExpr) {
        this.candidateUsersExpr = candidateUsersExpr;
    }

    public String getCandidateGroupsExpr() {
        return candidateGroupsExpr;
    }

    public void setCandidateGroupsExpr(String candidateGroupsExpr) {
        this.candidateGroupsExpr = candidateGroupsExpr;
    }

    public String getInstancesExpr() {
        return instancesExpr;
    }

    public void setInstancesExpr(String instancesExpr) {
        this.instancesExpr = instancesExpr;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public void execute(ActionExecutionContext exctx) throws ModelException, SCXMLExpressionException {



        EnterableState parentState = getParentEnterableState();

        Context ctx = exctx.getContext(parentState);
        ctx.setLocal(getNamespacesKey(), getNamespaces());
        Evaluator eval = exctx.getEvaluator();

        //求出各个属性表达式的值
        String assigneeValue = getAssignee();
        if (assigneeValue==null && getAssigneeExpr()!=null){
          assigneeValue = (String)getTextContentIfNodeResult(eval.eval(ctx,getAssigneeExpr()));
            if (assigneeValue == null || assigneeValue.trim().length()==0
                    && exctx.getAppLog().isWarnEnabled()){
                exctx.getAppLog().warn("<userTask>: target expression \"" + assigneeExpr
                        + "\" evaluated to null or empty String");
            }
        }
        String candidateUsersValue = getCandidateUsers();
        if (candidateUsersValue==null && getCandidateUsersExpr()!=null){
            candidateUsersValue = (String)getTextContentIfNodeResult(eval.eval(ctx,getCandidateUsersExpr()));
            if (candidateUsersValue == null || candidateUsersValue.trim().length()==0
                    && exctx.getAppLog().isWarnEnabled()){
                exctx.getAppLog().warn("<userTask>: target expression \"" + candidateUsersExpr
                        + "\" evaluated to null or empty String");
            }
        }
        String candidateGroupValue = getCandidateGroups();
        if (candidateGroupValue==null && getCandidateGroupsExpr()!=null){
            candidateGroupValue = (String)getTextContentIfNodeResult(eval.eval(ctx,getCandidateGroupsExpr()));
            if (candidateGroupValue == null || candidateGroupValue.trim().length()==0
                    && exctx.getAppLog().isWarnEnabled()){
                exctx.getAppLog().warn("<userTask>: target expression \"" + candidateGroupsExpr
                        + "\" evaluated to null or empty String");
            }
        }
        String instancesValue = getCandidateGroups();
        if (instancesValue==null && getInstancesExpr()!=null){
            instancesValue = String.valueOf(getTextContentIfNodeResult(eval.eval(ctx,getInstancesExpr())));
            if (instancesValue == null || instancesValue.trim().length()==0
                    && exctx.getAppLog().isWarnEnabled()){
                exctx.getAppLog().warn("<userTask>: target expression \"" + instancesExpr
                        + "\" evaluated to null or empty String");
            }
        }

        IdentityService identityService = new IdentityService();


        //执行Form中的求值，
        getForm().execute(exctx);


        boolean flag = false;
        if (assigneeValue!=null){
            UserWorkItemEntity userWorkItemEntity = new UserWorkItemEntity();
            userWorkItemEntity.setItemName(getName())
                    .setItemCreateTime(new Date().toLocaleString())
                    .setItemDueTime(getDueDate())
                    .setItemProcessId((String)ctx.getSystemContext().get(SCXMLSystemContext.SESSIONID_KEY))
                    .setItemStateId(parentState.getId())
                    .setItemFormEntity(getForm().getFormEntity());
            //找到这个人
            UserEntity userEntity = identityService.createUserQuery().userRealName(assigneeValue).SingleResult();
            try {
                //id是保存到数据库的主键值
                long id = TaskDispatcher.newInstance().dispatchTask(userWorkItemEntity, userEntity);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (candidateUsersValue!=null){
            //分配到候选人
        }else if (candidateGroupValue!=null){
            //分配到组
            GroupWorkItemEntity groupWorkItemEntity = new GroupWorkItemEntity();
            groupWorkItemEntity.setItemName(getName())
                    .setItemCreateTime(new Date().toLocaleString())
                    .setItemDueTime(getDueDate())
                    .setItemProcessId((String)ctx.getSystemContext().get(SCXMLSystemContext.SESSIONID_KEY))
                    .setItemStateId(parentState.getId())
                    .setItemFormEntity(getForm().getFormEntity());
            //找到这个组
            GroupEntity groupEntity = identityService.createGroupQuery().groupName(candidateGroupValue).SingleResult();

            try {
                //id是保存到数据库的主键值
                long id = TaskDispatcher.newInstance().dispatchTask(groupWorkItemEntity, groupEntity);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
