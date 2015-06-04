package Registry;

import Interfaces.ConfigurationsInterface;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Interfaces.RegisterInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

/**
 * This data type instantiates and registers a remote object that enables the registration of other
 * remote objects located in the same or other processing nodes in the local registry service.
 * Communication is based in Java RMI.
 */
public class ServerRegisterRemoteObject {

    /**
     * Main task.
     * @param args
     */
    public static void main(String[] args){
        /* get location of the registry service */
        Scanner in = new Scanner(System.in);
        String rmiRegHostName;
        int rmiRegPortNumb;

        System.out.println("\tConfiguration Server of problem 3: Aveiro Handicraft\n");
        
        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
        rmiRegHostName = in.nextLine();
        rmiRegPortNumb = 22170; //Integer.parseInt(args[1]); //in.nextInt();
        
        
        //Configurations.setRMIREGHOSTNAME(rmiRegHostName);
        //Configurations.setRMIREGPORTNUMB(rmiRegPortNumb);
        Configurations configs = new Configurations();
        
        String fName = "log.txt";
        int nCustomers = 3;
        int nCraftmans = 3;
        int nPrimeMaterialsInFactory = 10;
        int nInitialProductsInShop = 10;
        int nInitialPrimeMaterialsInStorage = 20;
        int nPrimeMaterialsByProduct = 1;
        int nMinPrimeMaterialsForRestock = 10;
        int nMaxProductsCollect = 5;
        
        // Option to select values when the program starts.
        System.out.print("Use default values?(y/n) ");
        if(in.nextLine().equalsIgnoreCase("n")){
            // Logging file name
            System.out.print ("Name of logging file? ");
            fName = in.nextLine();

            // nCustomers
            System.out.print("Number of Customers: ");
            nCustomers = in.nextInt();

            // nCraftmans
            System.out.print("Number of Craftmans: ");
            nCraftmans = in.nextInt();

            // Initial number of prime materials in the Factory
            System.out.print("Number of initial prime materials in Factory: ");
            nPrimeMaterialsInFactory = in.nextInt();

            // Initial number of products in the Shop
            System.out.print("Number of initial products in Shop: ");
            nInitialProductsInShop = in.nextInt();

            // Initial number of prime materials in the Storage
            System.out.print("Number of initial prime materials in Storage: ");
            nInitialPrimeMaterialsInStorage = in.nextInt();
            
            // Prime materials needed per product
            System.out.print("Number of prime materials needed by product: ");
            nPrimeMaterialsByProduct = in.nextInt();

            // Maximum number of products that the owner can carry
            System.out.print("Number of maximum products that the owner can carry: ");
            nMaxProductsCollect = in.nextInt();

            // Minimum number of prime materials for restock
            System.out.print("Number of minimum prime materials for restock: ");
            nMinPrimeMaterialsForRestock = in.nextInt();
            
            //Configurations.setConfigurations(rmiRegHostName, rmiRegPortNumb, fName,nCraftmans, nCustomers, nPrimeMaterialsInFactory, nInitialProductsInShop, nInitialPrimeMaterialsInStorage, nPrimeMaterialsByProduct, nMinPrimeMaterialsForRestock, nMaxProductsCollect);
            
        }
        
        configs.setfName(fName);
        configs.setnCustomers(nCustomers);
        configs.setnCraftmans(nCraftmans);
        configs.setnPrimeMaterialsInFactory(nPrimeMaterialsInFactory);
        configs.setnInitialProductsInShop(nInitialProductsInShop);
        configs.setnInitialPrimeMaterialsInStorage(nInitialPrimeMaterialsInStorage);
        configs.setnPrimeMaterialsByProduct(nPrimeMaterialsByProduct);
        configs.setnMaxProductsCollect(nMaxProductsCollect);
        configs.setnMinPrimeMaterialsForRestock(nMinPrimeMaterialsForRestock);
        configs.setTotalProducts((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage / nPrimeMaterialsByProduct) + nInitialProductsInShop);
        System.out.println("Total products: "+ configs.getTotalProducts());

        /* create and install the security manager */
        if (System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");

        /* instantiate a registration remote object and generate a stub for it */
        RegisterRemoteObject regEngine = new RegisterRemoteObject(rmiRegHostName, rmiRegPortNumb);
        RegisterInterface regEngineStub = null;
        int listeningPort = configs.getREGISTERPORT();

        try{
            regEngineStub = (RegisterInterface) UnicastRemoteObject.exportObject(regEngine, listeningPort);
        } catch (RemoteException e){
            System.out.println("RegisterRemoteObject stub generation exception: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Stub was generated!");

        /* register it with the local registry service */
        String nameEntry = "RegisterHandler";
        Registry registry = null;

        try{
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e){
            System.out.println("RMI registry creation exception: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("RMI registry was created!");

        try{
            registry.rebind(nameEntry, regEngineStub);
        } catch (RemoteException e){
            System.out.println("RegisterRemoteObject remote exception on registration: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("RegisterRemoteObject object was registered!");
        
        
       
        /* instantiate a remote object that runs mobile code and generate a stub for it */
        ConfigurationsInterface configStub = null;
        
        try{
            configStub = (ConfigurationsInterface) UnicastRemoteObject.exportObject(configs, listeningPort);
        } catch (RemoteException e){
            System.out.println("Configurations stub generation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was generated!");
        
        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        String nameEntryObject = "Configuration";
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
            reg.bind(nameEntryObject, configs);
        } catch (RemoteException e){
            System.out.println("Configurations registration exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (AlreadyBoundException e){
            System.out.println("Configurations already bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Configurations object was registered!");
    }
}
