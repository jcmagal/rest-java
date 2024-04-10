package br.com.magal.aula01.mapper.custom;

import br.com.magal.aula01.data.vo.versions.PersonVO;
import br.com.magal.aula01.data.vo.versions.PersonVOv2;
import br.com.magal.aula01.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOv2 convertEntityToVo(Person person){
        PersonVOv2 vo = new PersonVOv2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoToEntity(PersonVOv2 person){
        Person vo = new Person();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        //vo.setBirthday(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }


}
