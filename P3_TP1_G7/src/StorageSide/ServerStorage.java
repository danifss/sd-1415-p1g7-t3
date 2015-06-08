package StorageSide;

import Interfaces.ConfigurationsInterface;
import Interfaces.RegisterInterface;
import Interfaces.StorageInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class ServerStorage {
    public static void main(String[] args){
        /* get location of the registry service */
//        Scanner in = new Scanner(System.in);
        //String rmiRegHostName = "localhost"; //Configurations.getRMIREGHOSTNAME();
        String rmiRegHostName = "192.168.8.171";
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
        
        /* instantiate a remote object that runs mobile code and generate a stub for it */
        int nInitialPrimeMaterialsInStorage = 0;
        int nPrimeOwnerCarry = 0;
        int listeningPort = 0;
        
        try {
            nInitialPrimeMaterialsInStorage = config.getnInitialPrimeMaterialsInStorage();
            nPrimeOwnerCarry = config.getnMinPrimeMaterialsForRestock();
            listeningPort = config.getSTORAGEPORT();
        } catch(RemoteException e) {
            System.out.println("Configurations storage getters exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        Storage storage = new Storage(nInitialPrimeMaterialsInStorage, nPrimeOwnerCarry);
        StorageInterface storageStub = null;
        
        try{
            storageStub = (StorageInterface) UnicastRemoteObject.exportObject(storage, listeningPort);
        } catch (RemoteException e){
            System.out.println("Storage stub generation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was generated!");
        
        
        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        String nameEntryObject = "Storage";
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
            reg.bind(nameEntryObject, storageStub);
        } catch (RemoteException e){
            System.out.println("Storage registration exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (AlreadyBoundException e){
            System.out.println("Storage already bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Storage object was registered!");
    }
}
