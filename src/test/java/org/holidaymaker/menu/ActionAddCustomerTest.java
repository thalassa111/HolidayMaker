package org.holidaymaker.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionAddCustomerTest {

    ActionAddCustomer actionAddCustomer;

    @BeforeEach
    void setUp(){
        actionAddCustomer = new ActionAddCustomer();
    }

    @Test
    void executeAction() {
    }

    @Test
    void add() {
        int result = actionAddCustomer.add(1,1);
        assertEquals(2,result);
    }

    @Test
    void addName() {

    }
}