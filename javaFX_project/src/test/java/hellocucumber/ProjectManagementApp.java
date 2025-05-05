package hellocucumber;

import java.util.ArrayList;
import java.util.List;

public class ProjectManagementApp {

    private List<Project> projects = new ArrayList<>();

    public void createProject(String string){
        projects.add(new Project(string));
    }

}


public boolean containsProject(String projectName){
    return projects.stream().anyMatch(p->p.getName().equals(projectName));
}