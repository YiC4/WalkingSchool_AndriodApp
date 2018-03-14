package ca.sfu.Navy.walkinggroup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {
    private Long id;
    private String groupDescription;
    private User leader;

    private List<User> memberUsers = new ArrayList<>();
    private List<Void> routeLatArray = new ArrayList<>();
    private List<Void> routeLngArray = new ArrayList<>();
    private String href;

    //getters
    public Long getId() {
        return id;
    }
    public String getGroupDescription() {
        return groupDescription;
    }
    public User getLeader() {
        return leader;
    }
    public List<User> getMemberUsers() {
        return memberUsers;
    }
    public List<Void> getRouteLatArray() {
        return routeLatArray;
    }
    public List<Void> getRouteLngArray() {
        return routeLngArray;
    }
    public String getHref() {
        return href;
    }


    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
    public void setLeader(User leader) {
        this.leader = leader;
    }
    public void setRouteLatArray(List<Void> routeLatArray) {
        this.routeLatArray = routeLatArray;
    }
    public void setRouteLngArray(List<Void> routeLngArray) {
        this.routeLngArray = routeLngArray;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public void setMemberUsers(List<User> memberUsers){
        this.memberUsers = memberUsers;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupDescription='" + groupDescription + '\'' +
                ", routeLatArray='" + routeLatArray + '\'' +
                ", routeLngArray='" + routeLngArray + '\'' +
                ", leader=" + leader +
                ", memberUsers=" + memberUsers +
                '}';
    }
}
