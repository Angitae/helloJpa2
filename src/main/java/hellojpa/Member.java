package hellojpa;

import hellojpa.RoleType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Long getID() { return id;}

    public void setID(Long id) {this.id = id;}

    public String getUsername(){ return username;}

    public void setUsername(String username) {this.username = username;}

    public void setTeam(Team team){ this.team = team;}

    public Team getTeam(){ return team;
    }
}