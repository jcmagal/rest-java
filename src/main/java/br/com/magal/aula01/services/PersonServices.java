package br.com.magal.aula01.services;

import br.com.magal.aula01.controllers.PersonController;
import br.com.magal.aula01.data.vo.versions.PersonVO;
import br.com.magal.aula01.data.vo.versions.PersonVOv2;
import br.com.magal.aula01.exceptions.RequiredObjectsIsNullException;
import br.com.magal.aula01.exceptions.ResourceNotFoundException;

import br.com.magal.aula01.mapper.DozerMapper;
import br.com.magal.aula01.mapper.custom.PersonMapper;
import br.com.magal.aula01.model.Person;
import br.com.magal.aula01.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }
    public PersonVO findById(Long id) throws Exception {
        logger.info("Find one person!");

        PersonVO person = new PersonVO();
        var entity = personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found for this id!"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person){
        if (person == null) throw new RequiredObjectsIsNullException();
        logger.info("Create one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOv2 createV2(PersonVOv2 person){
        logger.info("Create one person!");

        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo(personRepository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person) throws Exception {
        if (person == null) throw new RequiredObjectsIsNullException();
        logger.info("Update one person!");

        var entity = personRepository.findById(person.getKey())
                .orElseThrow(()->new ResourceNotFoundException("No records found for this id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id){
        logger.info("delete one person!");
        var entity = personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found for this id!"));
        personRepository.delete(entity);
    }

}
