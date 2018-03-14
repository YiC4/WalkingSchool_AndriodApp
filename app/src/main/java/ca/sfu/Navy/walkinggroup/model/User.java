package ca.sfu.Navy.walkinggroup.model;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class User {
    private Long id;
    private String name;
    private String email;
    private String password;

    private List<User> monitoredByUsers = new ArrayList<>();
    private List<User> monitorsUsers = new ArrayList<>();
    private List<Group> memberOfGroups = new ArrayList<>();
    private List<Group> leadsGroups = new ArrayList<>();

    private String href;


    //getters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public List<User> getMonitoredByUsers() {
        return monitoredByUsers;
    }
    public List<User> getMonitorsUsers() {
        return monitorsUsers;
    }
    public List<Group> getMemberOfGroups() {
        return memberOfGroups;
    }
    public String getHref() {
        return href;
    }
    public List<Group> getLeadsGroups() {
        return leadsGroups;
    }



    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMonitoredByUsers(List<User> monitoredByUsers) {
        this.monitoredByUsers = monitoredByUsers;
    }
    public void setMonitorsUsers(List<User> monitorsUsers) {
        this.monitorsUsers = monitorsUsers;
    }
    public void setMemberOfGroups(List<Group> memberOfGroups) {
        this.memberOfGroups = memberOfGroups;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public void setLeadsGroups(List<Group> leadsGroups) {
        this.leadsGroups = leadsGroups;
    }

    // List items modifiers
    public void addUsertoMonitor(User user){
        this.monitorsUsers.add(user);
    }
    public void removeUserfromMonitor(int index){
        this.monitorsUsers.remove(index);
    }




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", monitoredByUsers=" + monitoredByUsers +
                ", monitorsUsers=" + monitorsUsers +
                ", memberOfGroups=" + memberOfGroups +
                ", leadsGroups=" + leadsGroups +
                '}';
    }
}
