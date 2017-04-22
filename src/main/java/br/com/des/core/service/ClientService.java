package br.com.des.core.service;

import br.com.des.core.model.Client;
import br.com.des.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by diogobernard on 22/04/17.
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public void saveClient(Client client){
        repository.save(client);
    }

    public Client updateClient(Client oldClient,Client newClient){
        return repository.update(oldClient,newClient);
    }

    public Client getClientByLogin(String login) throws ParseException {
        return (Client) repository.findByLogin(login);
    }

    public List<Client> getAllClient() throws ParseException {
        return repository.findAll();
    }

    public void removeClient(Client client){
        repository.removeClient(client);
    }

}
