package CustomerSide;

import Interfaces.ConfigurationsInterface;
import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import Registry.Configurations;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class CustomerMain {
    public static void main(String[] args){
        /**
         * TODO: obter o objeto Repository e o objeto Shop por RMI usando o host e o port que vem por argumento.
         * Iniciar os customers.
         */
        
        /* get location of the generic registry service */
//        Scanner in = new Scanner(System.in);
        String rmiRegHostName = "localhost"; //Configurations.getRMIREGHOSTNAME();
        int rmiRegPortNumb = 22170; //Configurations.getRMIREGPORTNUMB();

//        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
//        rmiRegHostName = in.nextLine();
//        System.out.print("Número do port de escuta do serviço de registo? ");
//        rmiRegPortNumb = in.nextInt();
        
        
        /* look for the remote object by name in the remote host registry */
        String nameEntry;
        Registry registry = null;

        try{
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e){
            System.out.println("RMI registry creation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        
        // Get Configuration Object
        nameEntry = "Configuration";
        ConfigurationsInterface config = null;
        try{
            config = (ConfigurationsInterface) registry.lookup(nameEntry);
        } catch (RemoteException e){
            System.out.println("Configuration look up exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e){
            System.out.println("Configuration not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        // Get Repository object
        nameEntry = "Repository";
        RepositoryInterface repository = null;
        try{
            repository = (RepositoryInterface) registry.lookup(nameEntry);
        } catch (RemoteException e){
            System.out.println("Repository look up exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e){
            System.out.println("Repository not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        // Get Shop object
        nameEntry = "Shop";
        ShopInterface shop = null;
        try{
            shop = (ShopInterface) registry.lookup(nameEntry);
        } catch (RemoteException e){
            System.out.println("Shop look up exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e){
            System.out.println("Shop not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        
        int nCustomers = 0;
        try {
            nCustomers = config.getnCustomers();
        } catch(RemoteException e) {
            System.out.println("Configuration getnCustomers exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        Customer[] customer = new Customer[nCustomers];
        //Initialization of Craftmans
        for (int i = 0; i < nCustomers; i++)
            customer[i] = new Customer(i, repository, shop);
        
        // Starting Craftmans
        for (int i = 0; i < nCustomers; i++)
            customer[i].start();
    }
    
}
