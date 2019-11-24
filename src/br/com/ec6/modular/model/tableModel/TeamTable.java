package br.com.ec6.modular.model.tableModel;

import br.com.ec6.modular.model.Project;
import br.com.ec6.modular.model.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class TeamTable {
    private StringProperty Name;
    private ObjectProperty<User> Manager;
    private ObjectProperty<Project> project;

    public StringProperty getNameProperty() {
        return Name;
    }

    public void setNameProperty(StringProperty name) {
        Name = name;
    }

    public ObjectProperty<User> getManagerProperty() {
        return Manager;
    }

    public void setManagerProperty(ObjectProperty<User> manager) {
        Manager = manager;
    }

    public ObjectProperty<Project> getProjectProperty() {
        return project;
    }

    public void setProjectProperty(ObjectProperty<Project> project) {
        this.project = project;
    }
}
