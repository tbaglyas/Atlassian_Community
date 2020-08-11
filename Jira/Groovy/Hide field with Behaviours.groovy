/**
* Atlassian Community answer
* <a href="https://community.atlassian.com/t5/Jira-questions/Adaptavist-ScriptRunner-Behaviour-Hide-field-based-on-value-of/qaq-p/1453585" target="_blank">Adaptavist ScriptRunner Behaviour - Hide field based on value of other field</a>
* @author   Tamás Baglyas - https://github.com/tbaglyas
* @version  1.0
* @since    2020-08-11
*/
if (getActionName().equals("Transition name where you want to use this behaviours")) {
    boolean visible = getFieldById("customfield_10907")?.getValue()?.toString()?.equals("Training");
    getFieldById("customfield_28501").setHidden(visible);
}