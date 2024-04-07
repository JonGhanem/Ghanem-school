package com.ghanem.school.repository;

import com.ghanem.school.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {


    List<Contact> findByStatus(String status);
    //@Query("SELECT c FROM CONTACT c WHERE c.status = :status")
    Page<Contact> findByStatus(String status, Pageable pageable);
}
