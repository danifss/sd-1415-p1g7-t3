package OwnerSide;

import Interfaces.FactoryInterface;
import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
import Interfaces.StorageInterface;
import Registry.Configurations;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 3.0
 */
public class OwnerMain {
    public static void main(String[] args){
        /**
         * TODO: obter o objeto Repository, o objeto Shop, o objeto Factory e o objeto Storage 
         * por RMI usando o host e o port que vem por argumento.
         * 
         * Iniciar o owner.
         */
        
        /* get location of the generic registry service */
//        Scanner in = new Scanner(System.in);
        String rmiRegHostName = Configurations.RMIREGHOSTNAME;
        int rmiRegPortNumb = Configurations.RMIREGPORTNUMB;

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
        
        // Get Storage object
        nameEntry = "Storage";
        StorageInterface storage = null;
        try{
            storage = (StorageInterface) registry.lookup(nameEntry);
        } catch (RemoteException e){
            System.out.println("Factory look up exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e){
            System.out.println("Factory not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        //Initialization of Owner
        Owner owner = new Owner(repository, factory, shop, storage);
        
        // Starting Owner
        owner.start();
    }
}
