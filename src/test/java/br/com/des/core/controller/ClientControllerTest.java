package br.com.des.core.controller;

import static org.junit.Assert.assertEquals;
import br.com.des.core.model.Client;
import br.com.des.core.repository.ClientRepository;
import br.com.des.core.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by diogobernard on 22/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class ClientControllerTest {

    @InjectMocks
    private ClientController controller;

    @Mock
    private ClientService clientService;

    private List<Client> listClient;

    @Before
    public void setUp() throws ParseException {
        listClient = new ArrayList<Client>();
        for(int i=0;i< 5;i++){
            Client c = new Client();
            c.setName("teste_"+i);
            c.setAge(i);
            c.setLogin("login_"+i);
            c.setBirthday(new Date());
            listClient.add(c);
        }
    }

    @Test
    public void testeGetClientAll() throws ParseException {
        Mockito.when(clientService.getAllClient()).thenReturn(listClient);
        List<Client> lista = (List<Client>) controller.getClient();
        assertEquals(5,lista.size());
    }



}
