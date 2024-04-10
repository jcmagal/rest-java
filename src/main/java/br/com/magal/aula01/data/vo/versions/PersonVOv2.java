package br.com.magal.aula01.data.vo.versions;



import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class PersonVOv2 implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String address;

    private Date birthday;
    private String gender;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public PersonVOv2() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVOv2 that)) return false;
        return getId().equals(that.getId()) && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getAddress().equals(that.getAddress()) && getBirthday().equals(that.getBirthday()) && getGender().equals(that.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getBirthday(), getGender());
    }
}
