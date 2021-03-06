package com.denil.customer.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
List<Ticket> findByPnr(String pnr);
List<Ticket> findByCustomerIdAndFlightNoAndBookingId(int custId,String flightNo,int bookingId);

List<Ticket> findByCustomerId(int id);

List<Ticket> findByBookingId(int id);
}
