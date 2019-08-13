package com.handshake.example.crud.controllers;

import com.handshake.example.crud.models.Alarm;
import com.handshake.example.crud.service.AlarmService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlarmController.class)
@AutoConfigureMockMvc
public class AlarmControllerTest {

    @MockBean
    AlarmController alarmController;

    @Mock
    private AlarmService alarmService;

    @Mock
    private Model model;

   private List<Alarm> expectedAlarms = new ArrayList<>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        alarmController.setAlarmService(alarmService);

        Alarm one = new Alarm(BigInteger.ONE, "first");
        Alarm two = new Alarm(BigInteger.valueOf(2L), "second");
        expectedAlarms.add(one);
        expectedAlarms.add(two);

        when(alarmController.getAllRecords(model)).thenReturn("test");
    }

    @Test
    public void testResponse() throws Exception {
        Assert.assertEquals("test", alarmController.getAllRecords(model));
    }
}