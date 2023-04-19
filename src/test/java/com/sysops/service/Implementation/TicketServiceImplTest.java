package com.sysops.service.Implementation;

import com.sysops.dao.TicketDao;
import com.sysops.entity.Customer;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketDao ticketDao;
    @Mock
    private ExpertServiceImpl expertService;
    @Captor
    private ArgumentCaptor<Ticket> ticketCaptor;
    private TicketServiceImpl ticketServiceSpy;

    @BeforeEach
    void setUp() {
        ticketServiceSpy = Mockito.spy(new TicketServiceImpl(ticketDao, expertService));
    }


    @Test
    public void createTicketTest() {
        Ticket ticket = new Ticket(1, new Customer("123-456-7890", "email", false), "Description", "Seattle");
        Expert assignedExpert = mock(Expert.class);
        Ticket savedTicket = mock(Ticket.class);
        Ticket savedTicket2 = new Ticket(1, new Customer("123-456-7890", "email", false), "Description", "Seattle");
        Long ticketId = 1L;
        Long expertId = 2L;

        when(ticketDao.save(any(Ticket.class))).thenReturn(savedTicket);
        when(expertService.findMatchExpert(savedTicket)).thenReturn(Collections.singletonList(assignedExpert));
        when(expertService.getFirstAvailableExpert(anyList())).thenReturn(assignedExpert);
        when(assignedExpert.getExpertId()).thenReturn(expertId);
        when(savedTicket.getTicketId()).thenReturn(ticketId);
        doReturn(savedTicket).when(ticketServiceSpy).assignTicketToExpert(anyLong(), anyLong());

        Ticket result = ticketServiceSpy.createTicket(ticket);

        assertEquals(savedTicket, result);
        verify(ticketDao, times(1)).save(ticket);
        verify(expertService, times(1)).findMatchExpert(savedTicket);
        verify(expertService, times(1)).getFirstAvailableExpert(anyList());
        verify(assignedExpert, times(1)).getExpertId();
        verify(savedTicket, times(1)).getTicketId();
        verify(ticketServiceSpy, times(1)).assignTicketToExpert(anyLong(), anyLong());
    }


    @Test
    public void assignTicketToExpertTest() {
        Long expertId = 1L;
        Long ticketId = 1L;

        Expert expert = new Expert("123-456-7890", "Seattle");
        Ticket ticket = new Ticket(1, new Customer("111-111-1111", "email@example.com", false), "Description", "Seattle");

        when(ticketDao.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(expertService.getExpertById(expertId)).thenReturn(expert);
        when(ticketDao.save(ticket)).thenReturn(ticket);

        Ticket assignedTicket = ticketServiceSpy.assignTicketToExpert(expertId, ticketId);

        assertEquals(expert, assignedTicket.getExpert());
        assertTrue(expert.getTickets().contains(assignedTicket));
        verify(ticketDao, times(1)).findById(ticketId);
        verify(expertService, times(1)).getExpertById(expertId);
        verify(ticketDao, times(1)).save(ticket);
        verifyNoMoreInteractions(ticketDao, expertService);
    }

    @Test
    public void updateTicketStatusTest() {
        Long ticketId = 1L;
        Ticket.TicketStatus newStatus = Ticket.TicketStatus.RESOLVED;

        Ticket ticket = new Ticket(1, new Customer("111-111-1111", "email@example.com", false), "Description", "Seattle");
        ticket.setStatus(Ticket.TicketStatus.OPEN);

        when(ticketDao.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketDao.save(any(Ticket.class))).thenReturn(ticket);

        ticketServiceSpy.updateTicketStatus(ticketId, newStatus);

        verify(ticketDao, times(1)).findById(ticketId);
        verify(ticketDao, times(1)).save(ticketCaptor.capture());
        verifyNoMoreInteractions(ticketDao);

        Ticket updatedTicket = ticketCaptor.getValue();
        assertEquals(newStatus, updatedTicket.getStatus());
    }

}