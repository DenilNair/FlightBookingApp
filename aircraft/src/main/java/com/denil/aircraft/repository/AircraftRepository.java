package com.denil.aircraft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircrafts, Integer>{
List<Aircrafts> findByCompanyName(String name);
List<Aircrafts> findById(int id);
void deleteById(int id);
List<Aircrafts> findByCompanyNameAndId(String name,int id);
}
