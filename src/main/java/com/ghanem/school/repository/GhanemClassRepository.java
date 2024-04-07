package com.ghanem.school.repository;

import com.ghanem.school.model.GhanemClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GhanemClassRepository extends JpaRepository<GhanemClass, Integer> {

}
