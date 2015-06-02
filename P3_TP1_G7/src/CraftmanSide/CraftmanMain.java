package CraftmanSide;

import Interfaces.ConfigurationsInterface;
import Interfaces.FactoryInterface;
import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class CraftmanMain {
    /**
    * Obter o objeto Repository, o objeto Shop e o objeto Factory por RMI usando o 
    * host e o port que vem por argumento.
    * 
    * Iniciar os craftmans.
    */
    public static void main(String[] args){
        
        /* get location of the generic registry service */
        String rmiRegHostName = "localhost"; //Configurations.getRMIREGHOSTNAME();
        int rmiRegPortNumb = 22170; //Configurations.getRMIREGPORTNUMB();

        
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
        
        // Get Factory object
        nameEntry = "Factory";
        FactoryInterface factory = null;
        try{
            factory = (FactoryInterface) registry.lookup(nameEntry);
        } catch (RemoteException e){
            System.out.println("Factory look up exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e){
            System.out.println("Factory not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        
        
        int nCraftmans = 0;
        try{
            nCraftmans = config.getnCraftmans();
        } catch(RemoteException e) {
            System.out.println("Configuration getnCraftmans exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        Craftman[] craftman = new Craftman[nCraftmans];
        //Initialization of Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i] = new Craftman(i, factory, shop, repository);
        
        // Starting Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i].start();
    }
    
}
