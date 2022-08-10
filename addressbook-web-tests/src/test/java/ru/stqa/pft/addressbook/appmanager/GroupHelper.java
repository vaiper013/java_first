package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {


    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCacshe = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCacshe = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCacshe = null;
        returnToGroupPage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCacshe = null;

    public Groups all() {
        if (groupCacshe !=null) {
            return new Groups(groupCacshe);
        }
        groupCacshe = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")). getAttribute("value"));
            groupCacshe.add(new GroupData().withId (id).withName(name));
        }
        return new Groups(groupCacshe);
    }
}

//   public void delete(int index) {
//        selectGroup(index);
//        deleteSelectedGroups();
//        returnToGroupPage();
//    }

//public void selectGroup(int index) {
//        wd.findElements(By.name("selected[]")).get(index).click();
//    }

//    public List<GroupData> List() {
//        List<GroupData> groups = new ArrayList<GroupData>();
//        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
//        for (WebElement element : elements) {
//            String name = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")). getAttribute("value"));
//            groups.add(new GroupData().withId (id).withName(name));
//        }
//        return groups;
//    }