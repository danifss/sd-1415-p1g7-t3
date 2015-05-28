package CraftmanSide;

import Interfaces.FactoryInterface;
import Interfaces.RepositoryInterface;
import Interfaces.ShopInterface;
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
public class CraftmanMain {
    public static void main(String[] args){
        /**
         * TODO: obter o objeto Repository, o objeto Shop e o objeto Factory por RMI usando o 
         * host e o port que vem por argumento.
         * 
         * Iniciar os craftmans.
         */
        
        /* get location of the generic registry service */
        Scanner in = new Scanner(System.in);
        String rmiRegHostName;
        int rmiRegPortNumb;

        System.out.print("Nome do nó de processamento onde está localizado o serviço de registo? ");
        rmiRegHostName = in.nextLine();
        System.out.print("Número do port de escuta do serviço de registo? ");
        rmiRegPortNumb = in.nextInt();
        
        System.out.print("Numero de Craftmans? ");
        int nCraftmans = in.nextInt();
        
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
        nameEntry = "RepositoryInterface";
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
        nameEntry = "ShopInterface";
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
        nameEntry = "FactoryInterface";
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
        
        
        Craftman[] craftman = new Craftman[nCraftmans];
        //Initialization of Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i] = new Craftman(i, factory, shop, repository);
        
        // Starting Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i].start();
    }
    
}
