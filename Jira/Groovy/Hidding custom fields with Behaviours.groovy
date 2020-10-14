/**
* Atlassian Community answer
* <a href="https://community.atlassian.com/t5/Jira-Software-questions/Behaviour-issue-Message-Custom-Field-not-hiding/qaq-p/1501067" target="_blank">Behaviour issue - Message Custom Field not hiding</a>
* @author Tamás Baglyas - https://github.com/tbaglyas
* @version 1.0
* @since 2020-10-14
*/
String partnerAgreementValue = getFieldById("customfield_13514")?.getValue()?.toString()?:null;

boolean singleIssue = (optionValue.equals("Yes")) ? false : true;
boolean message = (optionValue.equals("No")) ? false : true;

getFieldById("customfield_13515").setHidden(singleIssue);
getFieldById("customfield_13517").setHidden(message);