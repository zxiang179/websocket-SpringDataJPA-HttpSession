package org.sang.dao;

import org.sang.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Carl_Hugo on 2017/1/31.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByAddress(String name);

    Person findByNameAndAddress(String name, String address);

    @Query(value = "select p from Person p where p.name=:name")
    Person findByName(@Param("name") String name);

    @Query("select p from Person p where p.name=:name and p.address=:address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    Person withNameAndAddressNamedQuery(String name, String address);

}