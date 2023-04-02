package com.sysops.dao;

import com.sysops.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for managing Ticket entities.
 */
public interface TicketDao extends CrudRepository<Ticket, Long> {
}
