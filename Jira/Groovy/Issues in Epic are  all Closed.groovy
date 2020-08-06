/**
* Atlassian Community answer
* <a href="https://community.atlassian.com/t5/Jira-questions/prevent-closing-an-Epic-until-all-linked-issues-are-closed/qaq-p/1450359" target="_blank">prevent closing an Epic until all linked issues are closed</a>
* @author   Tamás Baglyas - https://github.com/tbaglyas
* @version  1.0
* @since    2020-08-06
*/
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.link.IssueLinkManager;

//If you want to test in Script Console
//MutableIssue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey("STUDIO-42");

//Current issue is Epic or not
boolean allClosed = (!issue.getIssueTypeId().equals("6")) ? false : true; //Epic

if (allClosed) {
    //If you want to test in Script Console
    //IssueLinkManager issueLinkManager = ComponentAccessor.getIssueLinkManager();
    
    //Get all Epic Linked issues; outwards and inwards
    List<String> issueStatuses = issueLinkManager.getOutwardLinks(issue.getId()).findAll() {
        it.issueLinkType.getId() == 10200L //Epic Link
    }*.destinationObject*.getStatusId()?:[];
    issueStatuses.addAll(issueLinkManager.getInwardLinks(issue.getId()).findAll() {
        it.issueLinkType.getId() == 10200L //Epic Link
    }*.sourceObject*.getStatusId());

    //All Issues in Epic number equals Closed Issues in Epic
    allClosed = (issueStatuses.size() == issueStatuses.findAll() { it.equals("6") }.size()) ? true : false; //Closed
}

allClosed