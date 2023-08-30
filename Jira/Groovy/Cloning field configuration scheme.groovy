/**
* Atlassian Community answer
* <a href="https://community.atlassian.com/t5/Jira-Service-Desk-questions/Cloning-field-configuration-scheme-and-field-configuration-using/qaq-p/1449149" target="_blank">Cloning field configuration scheme (and field configuration) using groovy</a>
* @author   Tamás Baglyas - https://github.com/tbaglyas
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
import com.atlassian.jira.issue.fields.config.manager.FieldConfigSchemeManager;

ProjectManager projectManager = ComponentAccessor.getProjectManager();

//Create Source and Target project objects
Project sourceProject = projectManager.getProjectObjByName("SD_TEMPLATE");
Project targetProject = projectManager.getProjectObjByName("SD_TEST");

//New issue types
IssueTypeSchemeManager issueTypeSchemeManager = ComponentAccessor.getIssueTypeSchemeManager();
FieldConfigScheme targetFieldConfigScheme = issueTypeSchemeManager.getConfigScheme(targetProject);
List<IssueType> issueTypes = (List<IssueType>)issueTypeSchemeManager.getIssueTypesForScheme(targetFieldConfigScheme);

//New FieldConfigScheme
FieldConfigScheme sourceFieldConfigScheme = issueTypeSchemeManager.getConfigScheme(sourceProject)
FieldConfigScheme.Builder fieldConfigSchemeBuilder = new FieldConfigScheme.Builder(sourceFieldConfigScheme);
fieldConfigSchemeBuilder.setName("sd_name");
fieldConfigSchemeBuilder.setDescription("sd_desc");
FieldConfigScheme newConfigScheme = fieldConfigSchemeBuilder.toFieldConfigScheme();

//Get parameters
ConfigurableField field = newConfigScheme.getField();
List<JiraContextNode> contexts = newConfigScheme.getContexts();

FieldConfigSchemeManager fieldConfigSchemeManager = ComponentAccessor.getFieldConfigSchemeManager();

FieldConfigScheme newFieldConfigScheme = fieldConfigSchemeManager.createFieldConfigScheme(newConfigScheme, contexts, issueTypes, field);