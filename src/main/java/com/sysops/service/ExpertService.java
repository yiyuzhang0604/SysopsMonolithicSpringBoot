package com.sysops.service;

import com.sysops.entity.Article;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;

import java.util.List;

/**
 * ExpertService responsible for managing expert-related operations.
 * It provides an interface to create, fetch, and update experts.
 */
public interface ExpertService {
    /**
     * Create a new expert with given information
     *
     * @param expert The expert object containing expert's information
     * @return The created expert
     */
    Expert register(Expert expert);

    /**
     * Get the phone number of the expert
     *
     * @param expertId ID of the expert
     * @return The phone number as a string
     */
    String getNumberByExpertId(Long expertId);

    /**
     * Get the expert object by id
     *
     * @param expertId ID of the expert
     * @return The expert object retrieved
     */
    Expert getExpertById(Long expertId);

    /**
     * Find the experts that meet the requirement of the ticket.
     *
     * @param ticket The ticket that need to find matching experts
     * @return A list of the experts that can be assigned to the ticket
     */
    List<Expert> findMatchExpert(Ticket ticket);

    /**
     * Retrieved the first expert from the list
     *
     * @param experts List of experts
     * @return the first expert from the list
     */
    Expert getFirstAvailableExpert(List<Expert> experts);

    /**
     * Get all the experts
     *
     * @return all the experts
     */
    List<Expert> getAllExperts();

    /**
     * Create a article for expert
     *
     * @param article  The article object containing article's information
     * @param expertId ID of the expert
     * @return The created article
     */
    Article createArticleForExpert(Article article, Long expertId);
}
