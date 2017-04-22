package br.com.des.core.controller;

import br.com.des.core.Util;
import br.com.des.core.model.Client;
import br.com.des.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

/**
 * Created by diogobernard on 22/04/17.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/api/des/core")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "{version}/client/all",method = RequestMethod.GET)
    @ResponseBody
    public Object getClient() throws ParseException {
        List<Client> clients = clientService.getAllClient();
        return clients;
    }

    @RequestMapping(value = "{version}/client",method = RequestMethod.GET)
    @ResponseBody
    public Object getClient(@RequestParam(value = "login",required = true) String login) throws ParseException {
        Client client = clientService.getClientByLogin(login);
        return client;
    }

    @RequestMapping(value = "{version}/client",method = RequestMethod.POST)
    @ResponseBody
    public void createClient(@RequestParam(value = "login", required = true)String login,
                             @RequestParam(value = "name", required = true)String name,
                             @RequestParam(value = "age", required = true)Integer age,
                             @RequestParam(value = "birthday", required = true)String birthday) throws ParseException {


        Client client = new Client(login,name,age, Util.convert(birthday));
        clientService.saveClient(client);
    }

    @RequestMapping(value = "{version}/client",method = RequestMethod.DELETE)
    @ResponseBody
    public void removeClient(@RequestParam(value = "login",required = true) String login) throws ParseException {
        Client client = clientService.getClientByLogin(login);
        clientService.removeClient(client);
    }

    @RequestMapping(value = "{version}/client",method = RequestMethod.PUT)
    @ResponseBody
    public void updateClient(@RequestParam(value = "login", required = true)String login,
                               @RequestParam(value = "name", required = false)String name,
                               @RequestParam(value = "age", required = false)Integer age,
                               @RequestParam(value = "birthday", required = false)String birthday) throws ParseException {

        Client client = clientService.getClientByLogin(login);
        if(client == null){
            this.createClient(login,name,age,birthday);
        }else {
            Client newClient = new Client(login,name,age, Util.convert(birthday));
            clientService.updateClient(client,newClient);
        }
    }
}
