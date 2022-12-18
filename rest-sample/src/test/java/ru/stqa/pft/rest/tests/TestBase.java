package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {
    protected static final ApplicationManager app
            = new ApplicationManager();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }


    public boolean isIssueOpen(int issueId) throws IOException {
        Set<Issue> Issues = getIssueById(issueId);
        Issue issue = Issues.iterator().next();
        if (!issue.getStateName().equals("Closed")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
    public Set<Issue> getIssueById(int issueId) throws IOException {
        String json = RestAssured.get((app.getProperty("rest.url")+ "issues/" + issueId + ".json")).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }
}

//public boolean isIssueOpen(int issueId) throws MalformedURLException, RemoteException {
//        String json = RestAssured.get((app.getProperty("rest.url")+ "issues/" + issueId + ".json")).asString();
//        JsonElement parsed = new JsonParser().parse(json);
//        JsonElement issues = parsed.getAsJsonObject().get("issues");
//        Set<Issue> currentIssues = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
//        for( Issue currentIssue:currentIssues){
//            if (currentIssue.getState_name().equals("Closed")){
//                return true;
//            }
//        }
//        return false;
//    }