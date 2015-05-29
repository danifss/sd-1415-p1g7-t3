package Registry;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Interfaces.RegisterInterface;
import java.util.Scanner;

/**
 * This data type instantiates and registers a remote object that enables the registration of other
 * remote objects located in the same or other processing nodes in the local registry service.
 * Communication is based in Java RMI.
 */
public class ServerRegisterRemoteObject {

    /**
     * Main task.
     */
    public static void main(String[] args){
        /* get location of the registry service */
        Scanner in = new Scanner(System.in);
        String rmiRegHostName;
        int rmiRegPortNumb;

        System.out.println("\tConfiguration Server of problem 3: Aveiro Handicraft\n");
        
        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
        rmiRegHostName = in.nextLine();
        //System.out.print("Número do port de escuta do serviço de registo? ");
        rmiRegPortNumb = 22170; //in.nextInt();
        
        
        Configurations data;
        
        // Option to select values when the program starts.
        System.out.println("Use default values?(y/n) ");
        if(in.nextLine().equalsIgnoreCase("n")){
            // Logging file name
            System.out.println ("Name of logging file? ");
            String fName = in.nextLine();

            // nCustomers
            System.out.println("Number of Customers: ");
            int nCustomers = in.nextInt();

            // nCraftmans
            System.out.println("Number of Craftmans: ");
            int nCraftmans = in.nextInt();

            // Initial number of prime materials in the Factory
            System.out.println("Number of initial prime materials in Factory: ");
            int nPrimeMaterialsInFactory = in.nextInt();

            // Initial number of products in the Shop
            System.out.println("Number of initial products in Shop: ");
            int nInitialProductsInShop = in.nextInt();

            // Initial number of prime materials in the Storage
            System.out.println("Number of initial prime materials in Storage: ");
            int nInitialPrimeMaterialsInStorage = in.nextInt();
            
            // Prime materials needed per product
            System.out.println("Number of prime materials needed by product: ");
            int nPrimeMaterialsByProduct = in.nextInt();

            // Maximum number of products that the owner can carry
            System.out.println("Number of maximum products that the owner can carry: ");
            int nMaxProductsCollect = in.nextInt();

            // Minimum number of prime materials for restock
            System.out.println("Number of minimum prime materials for restock: ");
            int nMinPrimeMaterialsForRestock = in.nextInt();
            
            data = new Configurations(rmiRegHostName, rmiRegPortNumb, fName,nCraftmans, nCustomers, nPrimeMaterialsInFactory, nInitialProductsInShop, nInitialPrimeMaterialsInStorage, nPrimeMaterialsByProduct, nMinPrimeMaterialsForRestock, nMaxProductsCollect);
            
        } else {
            // default values
            data = new Configurations(rmiRegHostName, rmiRegPortNumb);
        }
        

        /* create and install the security manager */
        if (System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");

        /* instantiate a registration remote object and generate a stub for it */
        RegisterRemoteObject regEngine = new RegisterRemoteObject(rmiRegHostName, rmiRegPortNumb);
        RegisterInterface regEngineStub = null;
        int listeningPort = Configurations.REGISTERPORT;                   /* it should be set accordingly in each case */

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
    }
}
