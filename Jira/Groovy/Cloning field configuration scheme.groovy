/**
* Atlassian Community answer
* <a href="https://community.atlassian.com/t5/Jira-Service-Desk-questions/Cloning-field-configuration-scheme-and-field-configuration-using/qaq-p/1449149" target="_blank">Cloning field configuration scheme (and field configuration) using groovy</a>
* @author   Tamás Baglyas
* @version  1.0
* @since    2020-08-05
*/
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.issue.fields.config.manager.IssueTypeSchemeManager;
import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.fields.config.FieldConfigScheme.Builder;
import com.atlassian.jira.issue.fields.ConfigurableField;
import com.atlassian.jira.issue.context.JiraContextNode;
import com.atlassian.jira.issue.fields.config.FieldConfig;

ProjectManager projectManager = ComponentAccessor.getProjectManager();

//Create a project objects (Source & Target)
Project jiraProjectSourceObj = projectManager.getProjectObjByName("SD_TEMPLATE");
Project jiraProjectTargetObj = projectManager.getProjectObjByName("SD_TEST");

IssueTypeSchemeManager issueTypeSchemeManager = ComponentAccessor.getIssueTypeSchemeManager();

//Return a issue type scheme object
FieldConfigScheme targetFieldConfigScheme = issueTypeSchemeManager.getConfigScheme(jiraProjectTargetObj);

//Get issue types list
Collection<IssueType> issueTypes = issueTypeScheme.getIssueTypesForScheme(targetFieldConfigScheme);

//New FieldConfigScheme
FieldConfigScheme sourceFieldConfigScheme = issueTypeScheme.getConfigScheme(jiraProjectSourceObj)
FieldConfigScheme.Builder fieldConfigSchemeBuilder = new FieldConfigScheme.Builder(sourceFieldConfigScheme);
fieldConfigSchemeBuilder.setName("sd_name");
fieldConfigSchemeBuilder.setDescription("sd_desc");
FieldConfigScheme fieldConfigScheme = fieldConfigSchemeBuilder.toFieldConfigScheme();

//Get parameters
ConfigurableField field = fieldConfigScheme.getField();
List<JiraContextNode> contexts = fieldConfigScheme.getContexts();
FieldConfig fieldConfig = fieldConfigScheme.getOneAndOnlyConfig();

FieldConfigScheme newFieldConfigScheme = fieldConfigSchemeManager.createFieldConfigScheme(fieldConfigScheme, contexts, issueTypes, field);