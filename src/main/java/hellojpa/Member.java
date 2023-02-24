package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;

    public Member(){

    }
    // JPA는 생성자가 기본적인 애가 필요하여 위에 만들어줌

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
//  generate : Command + N : 단축키
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
