package ShopSide;

import Interfaces.ConfigurationsInterface;
import Interfaces.RegisterInterface;
import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class ServerShop {
    public static void main(String[] args){
        /* get location of the registry service */
//        Scanner in = new Scanner(System.in);
        String rmiRegHostName = "localhost"; //Configurations.getRMIREGHOSTNAME();
        int rmiRegPortNumb = 22170; //Configurations.getRMIREGPORTNUMB();

//        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
//        rmiRegHostName = in.nextLine();
//        System.out.print("Número do port de escuta do serviço de registo? ");
//        rmiRegPortNumb = in.nextInt();
        
        
        /* create and install the security manager */
        if (System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");
        
        
        /* look for the remote object by name in the remote host registry */
        Registry registry = null;

        try{
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e){
            System.out.println("RMI registry creation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("RMI registry was created!");
        
        
        // Get Configuration Object
        String nameEntry = "Configuration";
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
        
        
        /* instantiate a remote object that runs mobile code and generate a stub for it */
        int nInitialProductsInShop = 0;
        int nCustomer = 0;
        int totalProducts = 0;
        int listeningPort = 0;
        
        try{
            nInitialProductsInShop = config.getnInitialProductsInShop();
            nCustomer = config.getnCustomers();
            totalProducts = config.getTotalProducts();
            listeningPort = config.getSHOPPORT();
        } catch(RemoteException e) {
            System.out.println("Configurations shop getters exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        int nCraftmans = 0;
        try {
            nCraftmans = config.getnCraftmans();
        } catch(RemoteException e) {
            System.out.println("Configuration getnCraftmans exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println("Total products: "+totalProducts);
        Shop shop = new Shop(nInitialProductsInShop, nCustomer, totalProducts, repository, nCraftmans);
        ShopInterface shopStub = null;

        
        try{
            shopStub = (ShopInterface) UnicastRemoteObject.exportObject(shop, listeningPort);
        } catch (RemoteException e){
            System.out.println("Shop stub generation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was generated!");
        
        
        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        String nameEntryObject = "Shop";
        RegisterInterface reg = null;

        try{
            reg = (RegisterInterface) registry.lookup(nameEntryBase);
        } catch (RemoteException e){
            System.out.println("RegisterRemoteObject lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e){
            System.out.println("RegisterRemoteObject not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        try{
            reg.bind(nameEntryObject, shopStub);
        } catch (RemoteException e){
            System.out.println("Shop registration exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (AlreadyBoundException e){
            System.out.println("Shop already bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Shop object was registered!");
    }
}
